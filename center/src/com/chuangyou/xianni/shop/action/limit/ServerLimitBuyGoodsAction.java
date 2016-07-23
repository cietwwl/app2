package com.chuangyou.xianni.shop.action.limit;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.entity.shop.ShopServerCache;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.ShopServerManager;
import com.chuangyou.xianni.shop.action.exec.ExecAction;

/**
 * 全服限购商品购买
 * @author laofan
 *
 */
public class ServerLimitBuyGoodsAction extends BaseLimitBuyAction {

	/**  全服购买将执行放入具体的执行器里（排队执行。解决同步问题） */
	protected ExecAction exec;
	
	protected int num;
	protected long totalPrice;
	
	public ServerLimitBuyGoodsAction(GamePlayer player, ShopCfg cfg) {
		super(player, cfg);
		// TODO Auto-generated constructor stub
		exec = new ExecAction(ThreadManager.actionExecutor.getDefaultQueue(), this);		
	}

	@Override
	public void limitBuy(int num, long totalPrice) {
		
		this.num = num;
		this.totalPrice = totalPrice;
		exec.getActionQueue().enqueue(exec);
	}
	
	/**
	 * 排队开始执行
	 */
	public void doLimitBuy(){
		if(limitCheck(num, totalPrice)){
			super.limitBuy(num, totalPrice);
		}else{
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.NPC_SHOP_NOT_ENGOUCH, Protocol.C_REQ_BUYGOODS,"商品数量不足");
			sendResult(false);
		}
	}

	/**
	 * 检测购买数量的合法性
	 */
	@Override
	public boolean limitCheck(int num, long totalPrice) {
		// TODO Auto-generated method stub
		ShopServerCache info = ShopServerManager.get(cfg.getId());
		if(info.getBuyNum()+num>cfg.getServerLimitNum()){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean updateCache(int num, long totalPrice) {
		// TODO Auto-generated method stub
		ShopServerCache info = ShopServerManager.get(cfg.getId());
		info.setBuyNum(info.getBuyNum()+num);
		info.setOp(Option.Update);
		return true;
		
	}
	
	

}
