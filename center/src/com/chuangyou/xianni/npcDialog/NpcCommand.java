package com.chuangyou.xianni.npcDialog;

public class NpcCommand {
	
	public NpcCommand(short commandId, int commandParam, String commandDes) {
		super();
		this.commandId = commandId;
		this.commandParam = commandParam;
		this.commandDes = commandDes;
	}

	/**
	 * 命令ID(具体含义查看)
	 */
	private short commandId;

	public short getCommandId() {
		return commandId;
	}

	public void setCommandId(short commandId) {
		this.commandId = commandId;
	}

	/**
	 * 命令参数
	 */
	private int commandParam;

	public int getCommandParam() {
		return commandParam;
	}

	public void setCommandParam(int commandParam) {
		this.commandParam = commandParam;
	}

	/**
	 * 命令描述
	 */
	private String commandDes;

	public String getCommandDes() {
		return commandDes;
	}

	public void setCommandDes(String commandDes) {
		this.commandDes = commandDes;
	}

	
	@Override
	public String toString() {
		return "NpcCommand [commandId=" + commandId + ", commandParam=" + commandParam + ", commandDes=" + commandDes
				+ "]";
	}

	

}