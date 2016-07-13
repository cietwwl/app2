package com.chuangyou.xianni.warfield.spawn;

public class DeleteState extends NodeState {

	public DeleteState(SpwanNode spawnNode) {
		super(spawnNode);
		this.code = DELETE;
	}

	@Override
	public void work() {
		spawnNode.delete();
	}

}
