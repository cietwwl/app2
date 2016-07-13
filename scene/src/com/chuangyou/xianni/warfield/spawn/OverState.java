package com.chuangyou.xianni.warfield.spawn;

public class OverState extends NodeState {

	public OverState(SpwanNode spawnNode) {
		super(spawnNode);
		this.code = OVER;
	}

	@Override
	public void work() {
		spawnNode.over();
	}

}
