package com.chuangyou.xianni.soul.logic.calc;

import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.soul.CardComboConfig;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

/**
 * 星级给卡牌组合附加的属性加成计算
 * @author laofan
 *
 */
public class StarAddComboAttLogic extends GetComboAttLogic {

	public StarAddComboAttLogic(ICalcAttLogic logic) {
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
			//todo 计算星级对组合的加成
			int star = 0;
			for(int cardId:config.getMateList()){
				SoulCardInfo card = player.getSoulInventory().getCards().get(cardId);
				star+=card.getStar();
			}
			for(int att:config.getEffectList()){
				addAttValue(map,att,star,config.getMateList().size());
			}
		}
		
//		System.out.println("StarAddComboAttLogic:"+map);
	}

	
	/**
	 *  激活条件为获得激活的两条组合均可通过以下途径进行提升							
		星级	组合内所有主魂的星级总和每提升1星，组合效果数值提升1%						
	 * @param map
	 * @param att
	 * @param count:每张卡卡牌都加。
	 */
	private void addAttValue(Map<Integer, SimpleProperty> map,int att,int star,int count){
		if (att > 0) {
			SimpleProperty pro = SkillUtil.readPro(att);
			int temp = (int) Math.floor(pro.getValue()*0.01*star);
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
