package com.chuangyou.xianni.state.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.chuangyou.xianni.entity.state.StateEventConfig;
import com.chuangyou.xianni.state.template.StateTemplateMgr;

public class RandomLogic {
	
	/**
	 * 随机QTE
	 * @param value
	 * @return
	 */
	public static boolean randomStateQte(int value){
		Random r = new Random();
		int a = r.nextInt(100);
		return a<value?true:false;
	}
	
	
	/**
	 * 随机combo事件
	 * @param value
	 * @return
	 */
	public static boolean randomStateCombo(int value){
		Random r = new Random();
		int a = r.nextInt(100);
		return a<value?true:false;
	}
	
	/**
	 * 获取随机事件
	 * @param events
	 * @return
	 */
	public static StateEventConfig getRandomEvent(int[] events){
		List<StateEventConfig> configs = new ArrayList<>();
		for (int id : events) {
			StateEventConfig config = StateTemplateMgr.getEvents().get(id);
			if(config!=null){
				configs.add(config);
			}
		}
		if(configs.size()==0)return null;
		int len = configs.size();
		int r = new Random().nextInt(len);
		return configs.get(r);
	}
	
	
}
