package com.chuangyou.xianni.shop.action.exec;

import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.shop.action.limit.ServerLimitBuyGoodsAction;

/**
 * 全服购买排队执行器
 * @author laofan
 *
 */
public class ExecAction extends Action {

	protected ServerLimitBuyGoodsAction action;
	
	public ExecAction(ActionQueue queue,ServerLimitBuyGoodsAction action) {
		super(queue);
		this.action = action;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		action.doLimitBuy();
	}

}
