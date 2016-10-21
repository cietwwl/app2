package com.chuangyou.xianni.fieldBoss.action;

import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.spawn.EliteBossSpawnNode;
import com.chuangyou.xianni.warfield.spawn.NodeState;
import com.chuangyou.xianni.warfield.spawn.WorkingState;

public class CreateEliteBossAction extends DelayAction {

	private EliteBossSpawnNode node;
	
	public CreateEliteBossAction(Field field, int delay, EliteBossSpawnNode node) {
		// TODO Auto-generated constructor stub
		super(field, delay);
		this.node = node;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(node.getState().getCode() != NodeState.WORK){
			node.stateTransition(new WorkingState(node));
		}
	}
}
