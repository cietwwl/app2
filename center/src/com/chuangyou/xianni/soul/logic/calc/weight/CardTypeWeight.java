package com.chuangyou.xianni.soul.logic.calc.weight;

import com.chuangyou.common.util.random.IWeight;

public class CardTypeWeight implements IWeight {

	private int cardId;
	private int weight;
	private int num;
	
	
	public CardTypeWeight(int cardId, int weight) {
		super();
		this.cardId = cardId;
		this.weight = weight;
	}


	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}


	public int getCardId() {
		return cardId;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}

}
