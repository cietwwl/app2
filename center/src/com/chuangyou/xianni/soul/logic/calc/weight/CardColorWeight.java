package com.chuangyou.xianni.soul.logic.calc.weight;

import com.chuangyou.common.util.random.IWeight;

public class CardColorWeight implements IWeight {

	private int color;
	private int startNum;
	private int endNum;
	private int weight;
	
	
	public CardColorWeight(int color, int startNum, int endNum, int weight) {
		super();
		this.color = color;
		this.startNum = startNum;
		this.endNum = endNum;
		this.weight = weight;
	}








	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}








	public int getColor() {
		return color;
	}








	public int getStartNum() {
		return startNum;
	}








	public int getEndNum() {
		return endNum;
	}

}
