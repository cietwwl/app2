package com.chuangyou.xianni.activeSystem.cmd;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.chuangyou.common.protobuf.pb.activeSystem.ActiveRewardReqProto.ActiveRewardReqMsg;
import com.chuangyou.common.protobuf.pb.activeSystem.ActiveRewardRespProto.ActiveRewardRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.activeSystem.template.ActiveTemplateMgr;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.active.ActiveConfig;
import com.chuangyou.xianni.entity.active.ActiveInfo;
import com.chuangyou.xianni.entity.reward.RewardTemplate;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.retask.vo.ActiveTask;
import com.chuangyou.xianni.reward.RewardManager;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_ACTIVE_REWARD,desc="领奖")
public class ActiveRewardCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		ActiveRewardReqMsg req = ActiveRewardReqMsg.parseFrom(packet.getBytes());
		
		if(req.getType() == 1 || req.getType() == 2){
			reward(player,req.getType());
		}
	}
	
	private void reward(GamePlayer player,int type){
		List<RewardTemplate> list;
		ActiveConfig config = ActiveTemplateMgr.getActiveConfigs().get(type);
		ActiveTask task = player.getActiveInventory().getActiveTasks().get(type);
		if(config!=null && task!=null){
			list = RewardManager.getRewardTemps(config.getReward());
			if(list!=null && list.size()>0){
				//排序
				list.sort(new Comparator<RewardTemplate>() {
					@Override
					public int compare(RewardTemplate o1, RewardTemplate o2) {
						// TODO Auto-generated method stub
						return o1.getParam1() - o2.getParam1();
					}
				});
				for (RewardTemplate rewardTemplate : list) {
					if(rewardTemplate.getParam1()>task.getInfo().getStatus() && task.getInfo().getProcess()>=rewardTemplate.getParam1()){
						RewardManager.sendRewardCanInEmail(rewardTemplate, player);
						task.getInfo().setStatus(rewardTemplate.getParam1());
						task.getInfo().setRewardTime(new Date());
						task.getInfo().setOp(Option.Update);
						
						sendResult(player,type,task);
					}
				}
				
			}else{
				Log.error("奖励配置表为空：RewardTemplateID:"+config.getReward());
			}
		}else{
			Log.error("需要的数据1为空：config："+config+"task:"+task);
		}
	}
	
	
	/**
	 * 发送回复消息
	 * @param player
	 * @param type
	 */
	private void sendResult(GamePlayer player,int type,ActiveTask task){
		player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_NOTIFY_ACTIVE_PROCESS,((ActiveInfo)task.getTaskInfo()).getMsg()));
		ActiveRewardRespMsg.Builder resp = ActiveRewardRespMsg.newBuilder();
		resp.setType(type);
		player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_ACTIVE_REWARD,resp));
		
	}

}
