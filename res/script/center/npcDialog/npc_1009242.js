// 脚本id 唯一
function getScriptId() {
	return "npc_1009242";   //对应跟NPC配置表配置的脚本ID
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

function showDialog(roleId,npcId){
	 //=====================>固定格式，创建NPC对话选项数组<=========================
	 var listClass = Java.type('java.util.ArrayList'); 
	 var commandStructClass = Java.type('com.chuangyou.xianni.npcDialog.NpcCommand'); 
	 var list = new listClass();

	 var dialogText='天资虽然重要，但毅力也是关键，修仙本是逆天之事，若无大毅力，就算有再好的天资，也是无用。'; 

	 sendToClient(roleId,list,dialogText);
	 
}

/**
 * 处理NPC对话选项
 * @param commandParam：选项携带的参数ID
 */
function processWithCommandParam(roleId,commandParam,npcId){
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






