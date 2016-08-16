package com.chuangyou.xianni.soul.logic;

import com.chuangyou.xianni.soul.logic.card.CardComboLogic;
import com.chuangyou.xianni.soul.logic.card.CardDownLogic;
import com.chuangyou.xianni.soul.logic.card.CardLvLogic;
import com.chuangyou.xianni.soul.logic.card.CardStarLogic;
import com.chuangyou.xianni.soul.logic.card.CardUpLogic;
import com.chuangyou.xianni.soul.logic.card.ICardLogic;

public class SoulCardFactory {

	/**
	 * 操作 1：合成  2：穿上  3：脱下  4:升星  5:升级  6:洗技能
	 * @param op
	 * @return
	 */
	public static ICardLogic getCardLogic(int op,int skillIndex) {
		ICardLogic logic = null;
		switch (op) {
		case 1:
			logic = new CardComboLogic(skillIndex);
			break;
		case 2:
			logic = new CardUpLogic(skillIndex);
			break;
		case 3:
			logic = new CardDownLogic(skillIndex);
			break;
		case 4:
			logic = new CardStarLogic(skillIndex);
			break;
		case 5:
			logic = new CardLvLogic(skillIndex);
			break;
		case 6:
			logic = new CardStarLogic(skillIndex);
			break;
		}
		return logic;
	}
}
