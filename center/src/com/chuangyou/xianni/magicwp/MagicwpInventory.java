package com.chuangyou.xianni.magicwp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.army.PropertyListMsgProto.PropertyListMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanGetInfoRespProto.MagicwpBanGetInfoRespMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanInfoBeanProto.MagicwpBanInfoBeanMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpOpenRespProto.MagicwpOpenRespMsg;
import com.chuangyou.common.protobuf.pb.player.OtherMagicwpMsgProto.OtherMagicwpMsg;
import com.chuangyou.xianni.army.Hero;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpAtt;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanLevelCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpLevelCfg;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.magicwp.manager.MagicwpRefineManager;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.sql.dao.DBManager;

public class MagicwpInventory extends AbstractEvent implements IInventory {

	/**
	 * 玩家数据
	 */
	private GamePlayer						player;

	/**
	 * 法宝总属性
	 */
	private MagicwpAtt						magicwpAtt;

	/**
	 * 法宝信息列表
	 */
	private Map<Integer, MagicwpInfo>		magicwpInfoMap;

	/**
	 * 法宝禁制信息列表
	 */
	private Map<Integer, MagicwpBanInfo>	banInfoMap;

	private List<Integer>					autoUpBanList	= null;

	public MagicwpInventory(GamePlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}

	/**
	 * 获取玩家法宝总属性
	 * 
	 * @return
	 */
	public MagicwpAtt getMagicwpAtt() {
		if (magicwpAtt == null) {
			magicwpAtt = new MagicwpAtt(player.getPlayerId());
			magicwpAtt.setOp(Option.Insert);
		}
		return magicwpAtt;
	}

	/**
	 * 更新法宝总属性，玩家有多个法宝，总属性只有一个
	 * 
	 * @param info
	 * @return
	 */
	public boolean updateMagicwpAtt(MagicwpAtt info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		info.setOp(Option.Update);
		return true;
	}

	/**
	 * 获取法宝信息
	 * 
	 * @param magicwpId
	 * @return
	 */
	public MagicwpInfo getMagicwpInfo(int magicwpId) {
		return this.magicwpInfoMap.get(magicwpId);
	}

	/**
	 * 激活/添加法宝
	 * 
	 * @param info
	 * @return
	 */
	public boolean addMagicwpInfo(MagicwpInfo info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		if (this.magicwpInfoMap.get(info.getMagicwpId()) != null) {
			info.setOp(Option.Update);
		} else {
			info.setOp(Option.Insert);
		}
		magicwpInfoMap.put(info.getMagicwpId(), info);
		return true;
	}

	/**
	 * 更新法宝信息
	 * 
	 * @param info
	 * @return
	 */
	public boolean updateMagicwpInfo(MagicwpInfo info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		info.setOp(Option.Update);
		return true;
	}

	/**
	 * 获取禁制信息
	 * 
	 * @param banId
	 * @return
	 */
	public MagicwpBanInfo getBan(int banId) {
		MagicwpBanInfo ban = banInfoMap.get(banId);
		if (ban == null) {
			MagicwpBanCfg banCfg = MagicwpTemplateMgr.getBanTemps().get(banId);
			if (banCfg == null)
				return null;
			ban = new MagicwpBanInfo(player.getPlayerId(), banId);
			ban.setOp(Option.Insert);
			banInfoMap.put(banId, ban);
		}
		return ban;
	}

	/**
	 * 更新禁制信息
	 * 
	 * @param info
	 * @return
	 */
	public boolean updateBan(MagicwpBanInfo info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		info.setOp(Option.Update);
		return true;
	}

	/**
	 * 获取已设置自动升级的禁制ID列表
	 * 
	 * @return
	 */
	public List<Integer> getAutoUpBanList() {
		if (autoUpBanList == null) {
			this.refreshAutoUpBanList();
		}
		return autoUpBanList;
	}

	/**
	 * 刷新已设置自动升级的禁制ID列表
	 */
	public void refreshAutoUpBanList() {
		List<Integer> list = new ArrayList<>();
		for (MagicwpBanInfo ban : banInfoMap.values()) {
			if (ban.getAutoUpLevel() == 1) {
				list.add(ban.getBanId());
			}
		}
		this.autoUpBanList = list;
	}

	/**
	 * 获取玩家已有法宝信息列表
	 * 
	 * @return
	 */
	public Map<Integer, MagicwpInfo> getMagicwpInfoMap() {
		return magicwpInfoMap;
	}

	/**
	 * 获取玩家禁制信息列表
	 * 
	 * @return
	 */
	public Map<Integer, MagicwpBanInfo> getBanInfoMap() {
		return banInfoMap;
	}

	public boolean loadFromDataBase() {
		magicwpAtt = DBManager.getMagicwpAttDao().get(player.getPlayerId());
		magicwpInfoMap = DBManager.getMagicwpInfoDao().getAll(player.getPlayerId());
		banInfoMap = DBManager.getMagicwpBanInfoDao().getAll(player.getPlayerId());
		return true;
	}

	public boolean unloadData() {
		player = null;
		magicwpAtt = null;

		if (magicwpInfoMap != null) {
			magicwpInfoMap.clear();
		}
		magicwpInfoMap = null;

		if (banInfoMap != null) {
			banInfoMap.clear();
		}
		banInfoMap = null;

		return true;
	}

	public boolean saveToDatabase() {

		boolean result = false;

		if (magicwpAtt != null) {
			short option = magicwpAtt.getOp();
			if (option == Option.Update) {
				result = DBManager.getMagicwpAttDao().update(magicwpAtt);
			} else if (option == Option.Insert) {
				result = DBManager.getMagicwpAttDao().add(magicwpAtt);
			}
		}

		if (magicwpInfoMap != null && magicwpInfoMap.size() > 0) {
			for (MagicwpInfo info : magicwpInfoMap.values()) {
				short option = info.getOp();
				if (option == Option.Update) {
					result = DBManager.getMagicwpInfoDao().update(info);
				} else if (option == Option.Insert) {
					result = DBManager.getMagicwpInfoDao().add(info);
				}
			}
		}

		if (banInfoMap != null && banInfoMap.size() > 0) {
			for (MagicwpBanInfo info : banInfoMap.values()) {
				short option = info.getOp();
				if (option == Option.Update) {
					result = DBManager.getMagicwpBanInfoDao().update(info);
				} else if (option == Option.Insert) {
					result = DBManager.getMagicwpBanInfoDao().add(info);
				}
			}
		}

		return true;
	}

	public void computeMagicwpAtt(BaseProperty bagData, BaseProperty bagPer) {
		MagicwpAtt magicwpAtt = getMagicwpAtt();
		List<Integer> toalPro = new ArrayList<>();
		// 属性丹加成
		// 属性丹加成
		int danId = SystemConfigTemplateMgr.getIntValue("magicwp.dan.prop.id");

		ItemTemplateInfo itemTemplate = ItemManager.findItemTempInfo(danId);
		List<Integer> attList = new ArrayList<Integer>();
		attList.add(itemTemplate.getStatistics1());
		attList.add(itemTemplate.getStatistics2());
		attList.add(itemTemplate.getStatistics3());
		attList.add(itemTemplate.getStatistics4());
		for (Integer att : attList) {
			SimpleProperty property = SkillUtil.readPro(att);
			if (property.isPre()) {
				SkillUtil.joinPro(bagPer, property.getType(), property.getValue() * magicwpAtt.getUseDanNum());
			} else {
				SkillUtil.joinPro(bagData, property.getType(), property.getValue() * magicwpAtt.getUseDanNum());
			}
		}

		// 法宝属性加成
		Map<Integer, MagicwpInfo> roleMagicwpMap = player.getMagicwpInventory().getMagicwpInfoMap();
		for (MagicwpInfo magicwp : roleMagicwpMap.values()) {
			// 等级加成
			MagicwpLevelCfg magicwpLevelCfg = MagicwpTemplateMgr.getLevelTemps().get(magicwp.getMagicwpId() * 1000 + magicwp.getLevel());
			if(magicwpLevelCfg == null){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, (short)0, "配置错误：" + (magicwp.getMagicwpId() * 1000 + magicwp.getLevel()));
				return;
			}
			toalPro.addAll(magicwpLevelCfg.getAtts());
			// 洗炼加成
			toalPro.addAll(MagicwpRefineManager.getRefineAtts(magicwp.getRefineAtts()));
		}

		// 禁制属性加成
		Map<Integer, MagicwpBanInfo> roleBanMap = player.getMagicwpInventory().getBanInfoMap();
		for (MagicwpBanInfo ban : roleBanMap.values()) {
			MagicwpBanLevelCfg banLevCfg = null;
			if (ban.getLevel() > 0) {
				banLevCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + ban.getLevel());
				toalPro.add(banLevCfg.getAtt1());
				toalPro.add(banLevCfg.getAtt2());
				toalPro.add(banLevCfg.getAtt3());
				toalPro.add(banLevCfg.getAtt4());
			} else {
				banLevCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + 1);

				String[] idxArr = ban.getFragmentStr().split("_");
				for (String indexStr : idxArr) {
					if (indexStr.equals(""))
						continue;
					int index = Integer.parseInt(indexStr);
					switch (index) {
						case 1:
							toalPro.add(banLevCfg.getAtt1());
							break;
						case 2:
							toalPro.add(banLevCfg.getAtt2());
							break;
						case 3:
							toalPro.add(banLevCfg.getAtt3());
							break;
						case 4:
							toalPro.add(banLevCfg.getAtt4());
							break;
					}
				}
			}
		}

		for (Integer pro : toalPro) {
			SimpleProperty property = SkillUtil.readPro(pro);
			if (property.isPre()) {
				SkillUtil.joinPro(bagPer, property.getType(), property.getValue());
			} else {
				SkillUtil.joinPro(bagData, property.getType(), property.getValue());
			}

		}
	}

	public void updataProperty() {
		if (player.getArmyInventory() != null) {
			BaseProperty skillData = new BaseProperty();
			BaseProperty skillPer = new BaseProperty();
			// 加入技能属性
			computeMagicwpAtt(skillData, skillPer);
			player.getArmyInventory().getHero().addMagicwp(skillData, skillPer);
			player.getArmyInventory().updateProperty();
		}
	}
	
	/**
	 * 通知scene服已装备禁制更新
	 */
	public void writeMagicwpMsg2Scene(){
		MagicwpBanGetInfoRespMsg.Builder msg = MagicwpBanGetInfoRespMsg.newBuilder();
		
		for(MagicwpBanInfo ban:getBanInfoMap().values()){
			if(ban.getPosition() > 0){
				MagicwpBanInfoBeanMsg.Builder bean = MagicwpBanInfoBeanMsg.newBuilder();
				bean.setBanId(ban.getBanId());
				bean.setPosition(ban.getPosition());
				bean.setFragmentStr(ban.getFragmentStr());
				bean.setLevel(ban.getLevel());
				bean.setExp(ban.getExp());
				bean.setAutoUpLev(ban.getAutoUpLevel());
				msg.addBans(bean);
			}
		}
		PBMessage p = MessageUtil.buildMessage(Protocol.S_MAGICWP_DATA_UPDATE, msg);
		player.sendPbMessage(p);
	}

	/** 写入其他用户查看信息 */
	public void writeInSimpOtherSnap(OtherMagicwpMsg.Builder proto) {
		if (magicwpAtt == null || magicwpInfoMap == null) {
			return;
		}
		proto.setPlayerId(magicwpAtt.getPlayerId());
		MagicwpInfo magicwpInfo = getMagicwpInfo(magicwpAtt.getCurMagicwpId());
		if (magicwpInfo == null) {
			return;
		}
		proto.setLevel(magicwpInfo.getLevel());
		proto.setMagicwpId(magicwpInfo.getMagicwpId());

		BaseProperty skillData = new BaseProperty();
		BaseProperty skillPer = new BaseProperty();
		// 加入技能属性
		computeMagicwpAtt(skillData, skillPer);
		Hero tempHero = new Hero(this.player);
		tempHero.addMagicwp(skillData, skillPer);
		PropertyListMsg.Builder propertyMsgs = PropertyListMsg.newBuilder();
		tempHero.writeProto(propertyMsgs);
		proto.setPropertitys(propertyMsgs);
		tempHero = null;
	}
	
	/**
	 * 激活法宝
	 * @param tempId
	 */
	public void activeMagicwp(int tempId){
	
		MagicwpCfg magicwpCfg = MagicwpTemplateMgr.getMagicwpTemps().get(tempId);
		
		if(magicwpCfg == null){
			return;
		}
		
		MagicwpInfo magicwp = player.getMagicwpInventory().getMagicwpInfo(tempId);
		
		if(magicwp != null){
			return;
		}
		
		magicwp = new MagicwpInfo(player.getPlayerId(),tempId);
		magicwp.setLevel(1);
		addMagicwpInfo(magicwp);
		
		MagicwpOpenRespMsg.Builder msg = MagicwpOpenRespMsg.newBuilder();
		msg.setMagicwpId(magicwp.getMagicwpId());
		msg.setLevel(magicwp.getLevel());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_OPEN, msg);
		player.sendPbMessage(p);
		
		//法宝总属性改变
//		MagicwpManager.changeMagicwpAtt(roleId);
		//影响人物属性变更
		updataProperty();
		
		player.notifyListeners(new ObjectEvent(this, magicwp.getMagicwpId(), EventNameType.MAGICWP_ACTIVE));
	}
	
}
