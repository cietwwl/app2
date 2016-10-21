package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.shop.BuyGoodsReqProto.BuyGoodsReqMsg;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.exec.CmdTask;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.cmd.ShopBuyGoodCmd;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command="getMallInfo",desc="测试获取商店数据")
public class TestGetMallInfo implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		String pId = params.get("playerId");
		GamePlayer player = WorldMgr.getPlayerFromCache(Long.parseLong(pId));
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
//			GetMallInfoReqMsg.Builder req = GetMallInfoReqMsg.newBuilder();
//			req.setRequestType(1);
//			PBMessage pkg = MessageUtil.buildMessage(Protocol.C_REQ_MALL_INFO, player.getPlayerId(), req);
//			pkg.setBytes(pkg.getMessage().toByteArray());
//			player.enqueue(new CmdTask(new GetMallInfoReqCmd(), null, pkg, player.getCmdTaskQueue()));
			
			String privateId = params.get("id");
			BuyGoodsReqMsg.Builder req = BuyGoodsReqMsg.newBuilder();
			req.setCount(1);
			req.setPrivateId(Integer.parseInt(privateId));
			req.setTotalMoney(100);
			PBMessage pkg = MessageUtil.buildMessage(Protocol.C_REQ_BUYGOODS,player.getPlayerId(),req);
			pkg.setBytes(pkg.getMessage().toByteArray());
			player.enqueue(new CmdTask(new ShopBuyGoodCmd(), null, pkg, player.getCmdTaskQueue()));
			
		}
		
		return HttpResult.getResult(Code.SUCCESS, "*_*get mall Info success*_*");
	}

}
