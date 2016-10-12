package com.chuangyou.xianni.soul.logic.calc;

import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.soul.CardComboConfig;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

/**
 * 等级对组合的附加影响
 * @author laofan
 *
 */
public class LvAddComboAttLogic extends GetComboAttLogic {

	public LvAddComboAttLogic(ICalcAttLogic logic) {
		super(logic);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doProcess(GamePlayer player, Map<Integer, SimpleProperty> map, SoulCardInfo soulCard, Map<Integer, SoulCardInfo> cards) {
		// TODO Auto-generated method stub
		logic.doProcess(player, map, soulCard, cards);
		List<Integer> combos = SoulTemplateMgr.getCardConfig(soulCard.getCardId()).getComboList();
		for (int combo : combos) {
			CardComboConfig config = SoulTemplateMgr.getCardComboMap().get(combo);
			if(!this.isValid(player, config))continue; 
			//todo添加等级对组合的属性加成
			int lv = 0;
			for(int cardId:config.getMateList()){
				SoulCardInfo card = player.getSoulInventory().getCards().get(cardId);
				if(lv == 0){
					lv = card.getLv();
				}else{
					lv = Math.min(lv, card.getLv());
				}
			}
			if(lv==0){
				Log.error("==========卡牌等级出错===========");
			}
			int temp = (int)((lv-1)/10);
			for(int att:config.getEffectList()){
				addAttValue(map,att,temp,config.getMateList().size());
			}		
			
//			CardComboConfig config = SoulTemplateMgr.getCardComboMap().get(combo);
//			if(!this.isValid(player,config))return;
//			//计算组合基本属性
//			List<Integer> list = config.getEffectList();
//			if(list==null)return;
//			for (int att : list) {
//				SoulCalcLogic.addAttValue(map, att);
//			}
		}
		
//		System.out.println("LvAddComboAttLogic:"+map);
		
	}
	
	
	/**
	 *  叠加属性接口
	 *  仙阶	组合内所有主魂每突破一个阶段（需同时满足），组合效果数值提升5%，						
	 * @param map
	 * @param att
	 * @param count:卡牌张数。每张卡都加
	 */
	private void addAttValue(Map<Integer, SimpleProperty> map,int att,int lv,int count){
		if (att > 0) {
			SimpleProperty pro = SkillUtil.readPro(att);
			int temp = (int) Math.floor(pro.getValue()*0.05*lv);
			pro.setValue(temp*count);
			if (map.containsKey(pro.getType())) {
				SimpleProperty t = map.get(pro.getType());
				t.setValue(t.getValue() +temp);
			}else{
				map.put(pro.getType(), pro);
			}
		}
	}
	
	

}
