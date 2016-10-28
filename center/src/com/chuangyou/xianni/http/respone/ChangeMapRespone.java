package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.ReqChangeMapMsgProto.ReqChangeMapMsg;
import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.exec.CmdTask;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.map.MapProxyManager;
import com.chuangyou.xianni.map.cmd.ChangeMapCmd;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "changeMap", desc = "切换地图")
public class ChangeMapRespone implements BaseRespone {
	@Override
	public String getResult(Map<String, String> params) throws Exception {
		long playerId = Long.valueOf(params.get("playerId"));
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		int mapId = Integer.valueOf(params.get("mapId"));
		int x = Integer.valueOf(params.get("x"));
		int y = Integer.valueOf(params.get("y"));
		int z = Integer.valueOf(params.get("z"));
		
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			FieldInfo info = MapProxyManager.getFieldTempInfo(mapId);
			if (info == null) {
				return HttpResult.getResult(Code.ERROR, "失败");
			}
			ReqChangeMapMsg.Builder req = ReqChangeMapMsg.newBuilder();
			PBVector3.Builder pos = PBVector3.newBuilder();
			pos.setX(x);
			pos.setY(y);
			pos.setZ(z);
			PostionMsg.Builder postion = PostionMsg.newBuilder();
			postion.setPostion(pos);
			PBMessage pkg;

			if (info.getType() == 1) { // 公共地图
				postion.setMapId(mapId);
				postion.setMapKey(mapId);
				req.setPostionMsg(postion.build());
				pkg = MessageUtil.buildMessage(Protocol.C_CHANGE_MAP, playerId, req);
				pkg.setBytes(pkg.getMessage().toByteArray());
				player.enqueue(new CmdTask(new ChangeMapCmd(), null, pkg, player.getCmdTaskQueue()));
			} else if (info.getType() == 2) { // 副本地图(单人。暂未处理多人副本情况)
				postion.setMapId(-1);
				postion.setMapKey(mapId);
				req.setPostionMsg(postion.build());
				pkg = MessageUtil.buildMessage(Protocol.C_CHANGE_MAP, playerId, req);
				pkg.setBytes(pkg.getMessage().toByteArray());
				player.enqueue(new CmdTask(new ChangeMapCmd(), null, pkg, player.getCmdTaskQueue()));
			}
			return HttpResult.getResult(Code.SUCCESS, "成功");
		}
		return HttpResult.getResult(Code.ERROR, "失败");
	}
}
