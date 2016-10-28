// 脚本id 唯一
function getScriptId() {
	return "npc_1008234";   //对应跟NPC配置表配置的脚本ID
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

	 var dialogText='朱雀国，用了近十万年的时间，以突飞猛进的攀升速度从一级达到了六级，成为耗时最少的六级修真国之一。'; 

	 sendToClient(roleId,list,dialogText);
	 
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
	 
	if(commandParam==969696){

		/*sendHintToClient(roleId,"我一秒钟几十万上下会和你闲聊？！");
		return;*/
		
		content = "我一秒钟几十万上下会和你闲聊？！";

	}
	
	sendToClient(roleId,list,content);
}





