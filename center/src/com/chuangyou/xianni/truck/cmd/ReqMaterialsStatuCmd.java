package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.InnerReqChangeMatProto.InnerReqChangeMat;
import com.chuangyou.common.protobuf.pb.truck.InnerRespChangeResultProto.InnerRespChangeResult;
import com.chuangyou.common.protobuf.pb.truck.RespTruckActionProto.RespTruckAction;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

/**
 * 查询物质状态
 * @author wkghost
 *
 */
@Cmd(code = Protocol.C_TRUCK_CHANGEMAT, desc = "查询物质状态")
public class ReqMaterialsStatuCmd extends AbstractCommand {
	/** 灵石兑换 */
	private static final int CHANGE_MONEY = 1;
	/** 物品兑换 */
	private static final int CHANGE_ITEM = 2;
	
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		InnerReqChangeMat mat = InnerReqChangeMat.parseFrom(packet.getBytes());
		boolean result = false;
		if(mat.getAddtype() == CHANGE_MONEY)	//灵石兑换
		{
			result = player.getBasePlayer().consumeMoney(mat.getCount() * mat.getPrice(), ItemRemoveType.TRUCK_MAT);
			if(!result)
			{
				Log.error("运镖物质添加，扣灵石失败");
				return;
			}
		}
		else if(mat.getAddtype() == CHANGE_ITEM)	//物品兑换
		{
			int itemCount = player.getBagInventory().getItemCount(mat.getItemTypeId());
			if(itemCount < mat.getCount())
			{
				//物品数量不够
				Log.error("物品 ：" + mat.getItemTypeId() + ", 数量不够");
				return;
			}
			//itemCount = itemCount - mat.getCount();
			result = player.getBagInventory().removeItem(mat.getItemTypeId(), mat.getCount(), ItemRemoveType.TRUCK_MAT);
			if(!result)
			{
				Log.error("运镖物质添加，扣物品失败");
				return;
			}
		}
		InnerRespChangeResult.Builder builder = InnerRespChangeResult.newBuilder();
		builder.setTruckId(mat.getTruckId());
		builder.setMapId(mat.getMapId());
		builder.setCount(mat.getCount());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_RESP_TRUCK_MATCHANGED, builder);
		player.sendPbMessage(pkg);
	}

}
