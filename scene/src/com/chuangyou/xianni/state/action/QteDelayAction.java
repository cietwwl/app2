package com.chuangyou.xianni.state.action;

import com.chuangyou.xianni.campaign.StateCampaign;
import com.chuangyou.xianni.entity.state.StateEventConfig;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.state.QteTemp;
import com.chuangyou.xianni.state.template.StateTemplateMgr;

/**
 * QTE定时执行清除
 * 执行下一个事件
 * @author laofan
 *
 */
public class QteDelayAction extends DelayAction {

	private int qteId;
	private StateCampaign campaign;
	
	public QteDelayAction(ActionQueue queue, int delay, int qteId,StateCampaign campaign) {
		super(queue, delay);
		this.qteId = qteId;
		this.campaign = campaign;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		synchronized (campaign.getQteTempMap()) {			
			QteTemp qte = campaign.getQteTempMap().get(this.qteId);
			if(qte!=null){
				qte.getCampaign().getQteTempMap().remove(qte.getQteId());
				StateEventConfig event = StateTemplateMgr.getEvents().get(qte.getEventId());
				if(event!=null){
					qte.getCampaign().execOneEvent(event);
				}		
			}
		}
	}

}
