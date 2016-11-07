package com.chuangyou.xianni.soul.logic.calc;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.xianni.entity.soul.CardRateConfig;
import com.chuangyou.xianni.soul.logic.calc.weight.CardColorWeight;

public class DrawPieceLogic {
	
	
	public int getRandomPiece(CardRateConfig config){
		
		List<CardColorWeight> list = new ArrayList<>();
		list.add(new CardColorWeight(1, config.getClipChanceWhiteStartNum(), config.getClipChanceWhiteEndNum(), config.getClipChanceWhite()));
		list.add(new CardColorWeight(2, config.getClipChanceGreenStartNum(), config.getClipChanceGreenEndNum(), config.getClipChanceGreen()));
		list.add(new CardColorWeight(3, config.getClipChanceBuleStartNum(), config.getClipChanceBuleEndNum(), config.getClipChanceBule()));
		list.add(new CardColorWeight(4, config.getClipChancePurpleStartNum(), config.getClipChancePurpleEndNum(), config.getClipChancePurple()));
		list.add(new CardColorWeight(5, config.getClipChanceOrangeStartNum(), config.getClipChanceOrangeEndNum(), config.getClipChanceOrange()));
		list.add(new CardColorWeight(6, config.getClipChanceRedStartNum(), config.getClipChanceRedEndNum(), config.getClipChanceRed()));
		
		
		
		return 0;
	}
	
}
