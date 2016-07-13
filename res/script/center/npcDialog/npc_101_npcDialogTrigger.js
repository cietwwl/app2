// 脚本id 唯一
function getScriptId() {
	return "10100";   //对应跟NPC配置表配置的脚本ID
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
	 //=====================>NED固定格式，创建NPC对话选项数组<=========================
	 
	 //添加选项  new commandStructClass(commandId,param,"des")
	 list.add(new commandStructClass(100,969691,"杂货店"));
	 list.add(new commandStructClass(100,969692,"兑换商店"));
	 list.add(new commandStructClass(100,969693,"切换地图"));
	 list.add(new commandStructClass(100,969694,"进入副本"));
	 list.add(new commandStructClass(100,969695,"了解行情"));
	 list.add(new commandStructClass(100,969696,"闲聊"));
	 //发送给客户端sendToClient(roleId,list,"显示NPC对话内容");
	
	 
	 sendToClient(roleId,list,"这里是编号:"+npcId+"的智能NPC在为您服务");
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
	 
	if(commandParam==969691)
	{
		
		list.add(new commandStructClass(101,10101,"NPC商店"));

	}
	else if(commandParam==969692){

		list.add(new commandStructClass(101,10102,"NPC商店"));

	}
	else if(commandParam==969693){

		changeMap(roleId,1009,-7612,172,5265);
		sendHintToClient(roleId,"进入了赵国");

	}
	else if(commandParam==969694){

		changeMap(roleId,2002,684,84,-814);
		sendHintToClient(roleId,"进入了副本");

	}
	else if(commandParam==969695){

		var random = Math.random() * 10000
		if (random > 5000){
			content = "据可靠消息，大蒜将会暴涨，有想法的可以囤货了";
		}
		else{
			content = "钢铁行业不景气，炼钢厂纷纷倒闭";
		}

	}
	else if(commandParam==969696){

		/*sendHintToClient(roleId,"我一秒钟几十万上下会和你闲聊？！");
		return;*/
		
		content = "我一秒钟几十万上下会和你闲聊？！";

	}
	
	sendToClient(roleId,list,content);
}






