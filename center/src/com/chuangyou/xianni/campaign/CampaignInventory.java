package com.chuangyou.xianni.campaign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.protobuf.pb.campaign.CampaignRecordInfoListMsgProto.CampaignRecordInfoListMsg;
import com.chuangyou.common.protobuf.pb.campaign.CampaignRecordInfoMsgProto.CampaignRecordInfoMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.campaign.CampaignRecordInfo;
import com.chuangyou.xianni.entity.campaign.CampaignTaskTemplateInfo;
import com.chuangyou.xianni.entity_id.EntityIdBuilder;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.sql.dao.CampaignRecordInfoDao;
import com.chuangyou.xianni.sql.dao.DBManager;

public class CampaignInventory implements IInventory {
	GamePlayer							player;

	/** 副本完成记录 */
	Map<Integer, CampaignRecordInfo>	records	= new ConcurrentHashMap<>();

	public CampaignInventory(GamePlayer player) {
		this.player = player;
	}

	public boolean isComplete(int campaignId) {
		return true;
	}

	@Override
	public boolean loadFromDataBase() {
		List<CampaignRecordInfo> allRecords = DBManager.getCampaignRecordInfoDao().getRecods(player.getPlayerId());

		if (allRecords != null && allRecords.size() > 0) {
			for (CampaignRecordInfo record : allRecords) {
				records.put(record.getCampaignId(), record);
			}
		}
		return true;
	}

	@Override
	public boolean unloadData() {
		records.clear();
		records = null;
		player = null;
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		List<CampaignRecordInfo> save = new ArrayList<>();
		save.addAll(records.values());
		CampaignRecordInfoDao dao = DBManager.getCampaignRecordInfoDao();
		for (CampaignRecordInfo info : save) {
			if (info.getOp() == Option.Insert || info.getOp() == Option.Update) {
				dao.saveOrUpdata(info);
			}
		}
		return true;
	}

	public void updataAll() {
		List<CampaignRecordInfo> save = new ArrayList<>();
		save.addAll(records.values());
		CampaignRecordInfoListMsg.Builder builder = CampaignRecordInfoListMsg.newBuilder();
		for (CampaignRecordInfo record : save) {
			CampaignRecordInfoMsg.Builder rcdBuilder = CampaignRecordInfoMsg.newBuilder();
			rcdBuilder.setCampaignId(record.getCampaignId());
			rcdBuilder.setPoint(record.getPoint());
			rcdBuilder.setStatus(record.getStatu());
			rcdBuilder.setUpdataTime(record.getUpdataTime());
			if (record.getAttrTaskIds() != null && record.getAttrTaskIds().size() > 0) {
				for (Integer id : record.getAttrTaskIds()) {
					rcdBuilder.addTaskIds(id);
				}
			}
			builder.addRecords(rcdBuilder);
		}
		PBMessage message = MessageUtil.buildMessage(Protocol.U_CAMPAIGN_RECORD, builder);
		player.sendPbMessage(message);
	}

	public void updataRecord(CampaignRecordInfo recordInfo) {
		CampaignRecordInfoListMsg.Builder builder = CampaignRecordInfoListMsg.newBuilder();
		CampaignRecordInfoMsg.Builder rcdBuilder = CampaignRecordInfoMsg.newBuilder();
		rcdBuilder.setCampaignId(recordInfo.getCampaignId());
		rcdBuilder.setPoint(recordInfo.getPoint());
		rcdBuilder.setStatus(recordInfo.getStatu());
		rcdBuilder.setUpdataTime(recordInfo.getUpdataTime());
		if (recordInfo.getAttrTaskIds() != null && recordInfo.getAttrTaskIds().size() > 0) {
			for (Integer id : recordInfo.getAttrTaskIds()) {
				rcdBuilder.addTaskIds(id);
			}
		}
		builder.addRecords(rcdBuilder);
		PBMessage message = MessageUtil.buildMessage(Protocol.U_CAMPAIGN_RECORD, builder);
		player.sendPbMessage(message);
	}

	/** 结算任务奖励 */
	public void billingTask(int campaignId, int taskId) {
		CampaignRecordInfo info = records.get(campaignId);
		if (info == null) {
			info = new CampaignRecordInfo(player.getPlayerId(), campaignId);
			info.setId(EntityIdBuilder.cmRecordIdBuilder());
			records.put(campaignId, info);
		}
		CampaignTaskTemplateInfo task = CampaignTaskTempMgr.get(taskId);
		if (task == null) {
			Log.error("billingTask but task is null,taskId :" + taskId);
			return;
		}
		// 修为奖励
		player.getBasePlayer().addRepair(task.getRepair());
		// 完成过挑战任务的，无奖励
		if (info.getAttrTaskIds() != null && info.getAttrTaskIds().contains(taskId)) {
			return;
		}
		int maxPoint = CampaignTempMgr.getMaxPoint(campaignId);
		int count = info.getPoint() + task.getPoint();
		info.setPoint(count >= maxPoint ? maxPoint : count);
		info.recordTask(taskId);
		updataRecord(info);
	}
}
