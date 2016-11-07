// 脚本id 唯一
function getScriptId() {
	return "npc_10010093";   //对应跟NPC配置表配置的脚本ID
}

// 脚本类型
function getInterfaceName() {
	return "com.chuangyou.xianni.npcDialog.script.INpcDialogTrigger";   //NPC对话脚本类型
}

/////////////////////////////////////脚本逻辑开始
//////////////////////////////////////////////////////////////

/**
 * 显示NPC对话（点击NPC首先调用这个接口）
 * @param roleId
 */
function showDialog(roleId,npcId,npcEntryId){
	 //=====================>固定格式，创建NPC对话选项数组<=========================
	 var listClass = Java.type('java.util.ArrayList'); 
	 var commandStructClass = Java.type('com.chuangyou.xianni.npcDialog.NpcCommand'); 
	 var list = new listClass();
	 //=====================>NED固定格式，创建NPC对话选项数组<=========================
	 
	 //添加选项  new commandStructClass(commandId,param,"des")
	 list.add(new commandStructClass(100,969691,"前往追击"));	 
	 //发送给客户端sendToClient(roleId,list,"显示NPC对话内容");
	
	 
	 sendToClient(roleId,list,"这里是编号:"+npcId+"的智能NPC在为您服务");
}

/**
 * 处理NPC对话选项
 * @param commandParam：选项携带的参数ID
 */
function processWithCommandParam(roleId,commandParam,npcId,npcEntryId){
	 //=====================>固定格式，创建NPC对话选项数组<=========================
	 var listClass = Java.type('java.util.ArrayList'); 
	 var commandStructClass =Java.type('com.chuangyou.xianni.npcDialog.NpcCommand'); 
	 var list = new listClass();
	 var content = "";  // 对话内容
	 //=====================>NED固定格式，创建NPC对话选项数组<=========================
	 
	if(commandParam==969691){

		//changeMap(roleId,2002,684,84,-814);

		triggerTransferNpc(roleId,npcEntryId);
		
		sendHintToClient(roleId,"进入了活动副本");

	}
	
	sendToClient(roleId,list,content);
}






