package com.chuangyou.xianni.soul.logic.calc;

import java.util.List;

import com.chuangyou.xianni.entity.soul.CardComboConfig;
import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 上阵才加成属性的组合类型
 * 
 * @author laofan
 *
 */
public class UpComboAttLogic extends BaseComboAttLogic {

	public UpComboAttLogic(ICalcAttLogic logic) {
		super(logic);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 1：组合齐全。
	 * 2：组合中所有卡都上阵
	 */
	@Override
	protected boolean isValid(GamePlayer player, CardComboConfig config) {
		// TODO Auto-generated method stub
		SoulInfo info = player.getSoulInventory().getSoulInfo();
		List<Integer> list = info.getCardsList();
		boolean i = list.containsAll(config.getMateList());
		return i;
	}

	

	

}
