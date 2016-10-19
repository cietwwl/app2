package com.chuangyou.xianni.warfield.spawn;

public class WorkingState extends NodeState {

	public WorkingState(SpwanNode spawnNode) {
		super(spawnNode);
		this.code = WORK;
	}

	@Override
	public void work() {
		spawnNode.start();
	}

}
