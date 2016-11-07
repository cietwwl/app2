package com.chuangyou.xianni.exchange.cmd;

import com.chuangyou.common.protobuf.pb.exchange.ExchangeReqProto.ExchangeReqMsg;
import com.chuangyou.common.protobuf.pb.exchange.ExchangeRespProto.ExchangeRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_EXCHANGE,desc="兑换")
public class ExchangeCmd extends AbstractCommand {

	/**计算说明	
	 * 兑换1次消耗仙玉 = max（1，向下取整（（N+1）/2）*2）*A,其中 N 指当日已兑换次数，A=20，可配置							
	       兑换10次消耗仙玉 = max（51，（40+（N+1）*10））*A,其中 N 指当日已兑换次数，A=20，可配置							
		兑换1次获得的灵石 = 境界等级 * A + 玩家等级 * B + 额外赠送，A=5000,B=200，可配置							
		额外赠送 = M * C ，其中 M 是指玩家总累计兑换的次数；C = 50，可配置 							
		兑换10次获得的灵石 = （境界等级 * A + 玩家等级 * B） * 10 + 额外赠送	
		额外赠送 = （45 + 10 * M） * C					
	*/
	
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		ExchangeReqMsg msg = ExchangeReqMsg.parseFrom(packet.getBytes());
		int comsume = 0;
		int n = player.getBasePlayer().getPlayerTimeInfo().getPersonalExchangeTime();
		final int A = 20;
		final int A1 = 5000;
		final int B = 200;
		final int C = 50;
		int time = 1;
		int m =  player.getBasePlayer().getPlayerTimeInfo().getPersonalExchangeTotalTime();
		long award = 0;
		long awardAdd = 0;
		if(msg.getExchangeType() == 1){
			comsume = (int) (Math.max(1, Math.floor((n+1)/2))*A);
			award = player.getBasePlayer().getPlayerInfo().getStateLv()*A1 + player.getBasePlayer().getPlayerInfo().getLevel()*B;
			awardAdd = m*C;
			time = 1;
		}else if(msg.getExchangeType() == 2){
			comsume = (int) (Math.max(51, 40+(n+1)*10)*A);
			award = (player.getBasePlayer().getPlayerInfo().getStateLv()*A1 + player.getBasePlayer().getPlayerInfo().getLevel()*B)*10;
			awardAdd = (45 + 10 * m) * C;
			time = 10;
		}
		
		if(!player.getBasePlayer().consumeCommonCash(comsume, ItemRemoveType.EXCHANGE)){
			Log.error("兑换金钱不够：playerId:"+player.getPlayerId()+"===>comsume"+comsume);
			return;
		}
		if(!player.getBasePlayer().addMoney(award+awardAdd, ItemAddType.EXCHANGE)){
			Log.error("金钱添加失败：playerId:"+player.getPlayerId()+"===>award+awardAdd:"+(award+awardAdd));
			return;
		}
		player.getBasePlayer().getPlayerTimeInfo().setPersonalExchangeTime(n+time);
		player.getBasePlayer().getPlayerTimeInfo().setPersonalExchangeTotalTime(m+time);
		
		ExchangeRespMsg.Builder resp = ExchangeRespMsg.newBuilder();
		resp.setExchangeType(msg.getExchangeType());
		resp.setCurDayTime(player.getBasePlayer().getPlayerTimeInfo().getPersonalExchangeTime());
		resp.setTotalTime(player.getBasePlayer().getPlayerTimeInfo().getPersonalExchangeTotalTime());
		resp.setAwardMoney(award);
		resp.setAwardMoneyAdd(awardAdd);
		player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_EXCHANGE,resp));
		
	}

}
