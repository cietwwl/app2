package com.chuangyou.xianni.npcDialog.script;


import com.chuangyou.xianni.script.IScript;

/**
 * NPC对话
 * @author laofan
 *
 */
public interface INpcDialogTrigger extends IScript{

	
	/**
	 * 处理NPC对话的选项
	 * @param roleId
	 * @param commandParam
	 * @param npcId
	 */
	public void processWithCommandParam(long roleId,int commandParam,int npcId,long npcEntryId);
	

	/**
	 * 显示NPC对话
	 * @param roleId
	 * @param npcId
	 */
	public void showDialog(long roleId,int npcId,long npcEntryId);
}
