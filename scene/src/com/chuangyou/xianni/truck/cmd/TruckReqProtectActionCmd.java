package com.chuangyou.xianni.truck.cmd;

import java.util.Set;

import com.chuangyou.common.protobuf.pb.truck.ReqPlayerProtectActionProto.ReqPlayerProtectAction;
import com.chuangyou.common.protobuf.pb.truck.RespTruckProtectProto.RespTruckProtect;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.TruckMgr;
import com.chuangyou.xianni.truck.TruckRelationshipMgr;
import com.chuangyou.xianni.truck.helper.FunctionHelper;
import com.chuangyou.xianni.truck.helper.RobAndProtTruckHelper;
import com.chuangyou.xianni.truck.helper.TruckActionRespHelper;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.TransportHelper;
import com.chuangyou.xianni.warfield.helper.selectors.ExcludePetSelector;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

@Cmd(code = Protocol.S_REQ_TRUCK_PROTECTACTION, desc = "请求护镖保护")
public class TruckReqProtectActionCmd extends AbstractCommand {

	/** 同意 */
	public static final int AGREE = 1;
	/** 拒绝 */
	public static final int REJECT = 2;

	/** 回应同意 */
	public static final int RESP_AGREE = 1;
	/** 回应拒绝 */
	public static final int RESP_REJECT = 2;
	/** 回应镖车不存在 */
	public static final int RESP_NOTFIND = 3;

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		ReqPlayerProtectAction action = ReqPlayerProtectAction.parseFrom(packet
				.getBytes());
		RespTruckProtect.Builder resp = RespTruckProtect.newBuilder();
		Truck truck = TruckMgr.getTruck(action.getTruckId());
		// 消耗次数
		if(truck.getTruckSkillCount().get(FunctionHelper.FUN_ZHAOMU) <= 0) return;
		ArmyProxy beProtector = WorldMgr.getArmy(action.getBeProtectorId());
		if (beProtector == null)
			return;
		if (!TruckMgr.hasTruck(action.getTruckId())) {
			resp.setTruckID(action.getTruckId());
			resp.setProtectorId(action.getProtectorId());
			resp.setTrucktype(0);
			resp.setAction(RESP_NOTFIND);
			PBMessage pkg = MessageUtil.buildMessage(
					Protocol.U_RESP_TRUCK_PROTECT_ACTION, resp);
			army.sendPbMessage(pkg);
			return;
		}
		if (action.getAction() == AGREE) // 同意护镖
		{
			
			// 向保护者发送可以开始护镖
			RobAndProtTruckHelper.startProt(army, truck);
			// 转场
			TransportHelper.transport(army, truck.getPostion(),
					truck.getField().id);
			FunctionHelper.cutFuncCount(truck, FunctionHelper.FUN_ZHAOMU);
			FunctionHelper.funcUpdate(beProtector, FunctionHelper.STATE_UPDATE,
					truck.getTruckSkillCount());
		} else if (action.getAction() == REJECT) // 拒绝护镖
		{
			resp.setTruckID(action.getTruckId());
			resp.setProtectorId(action.getProtectorId());
			resp.setTrucktype(truck.getTrucktype());
			resp.setAction(RESP_REJECT);
			PBMessage pkg = MessageUtil.buildMessage(
					Protocol.U_RESP_TRUCK_PROTECT_ACTION, resp);
			beProtector.sendPbMessage(pkg);
		}
	}

}
