// 脚本id 唯一
function getScriptId() {
	return "commonInterface";   //脚本公共接口脚本
}

// 脚本类型
function getInterfaceName() {
	return "com.chuangyou.xianni.script.IScript";   //NPC对话脚本类型
}



/////////////////////////////////////脚本逻 辑 结 束
//////////////////////////////////////////////////////////////
// 下面为服务端提供的固定接口，只管调用
/**
 * 发送NPC对话内容给客户端
 */
function sendToClient(roleId,list,content){
	com.chuangyou.xianni.npcDialog.manager.NpcDialogManager.sendResultToClient(roleId,list,content);
}

/**
 * 发送提示信息给客户端
 * @param roleId ：发给客户端的角色ID
 * @param content：提示内容
 */
function sendHintToClient(roleId,content){
	com.chuangyou.xianni.script.manager.ScriptInterfaceManager.sendHintToClient(roleId,content);
}

/**
 * 将主角传送到指定地图的指定位置 
 * @param mapId
 * @param x
 * @param y
 * @param z
 */
function changeMap(roleId,mapId,x,y,z){
	com.chuangyou.xianni.map.MapProxyManager.changeMap(roleId,mapId,x,y,z);
}


/**
 * 查询人物身上是否有某个任务
 * @param playerId
 * @param taskId
 * @return true:有
 */
function isHasTask(playerId,taskId){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.isHasTask(playerId,taskId)
}


/**
 * 添加物品接口
 * @param playerId
 * @param templateId
 * @param num
 * @param isBind
 * @return true:成功
 */
function addItem(playerId,templateId,count,isBind){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.addItem(playerId,templateId,count,isBind);
}


/**
 * 刷私有怪物
 * @param playerId
 * @param monsterId
 * @param leaveTime:存活时间（单位毫秒）
 * @param mapId 地图ID
 */
function createPrivateMonster(playerId,monsterId,leaveTime,mapId){
	com.chuangyou.xianni.script.manager.ScriptInterfaceManager.createPrivateMonster(playerId,monsterId,leaveTime,mapId);
}


/**
 * 刷私有怪物
 * @param playerId
 * @param monsterId
 * @param x
 * @param y
 * @param z
 * @param leaveTime:存活时间（单位毫秒）
 */
function createPrivateMonster(playerId,monsterId,x,y,z,leaveTime,mapId){
	com.chuangyou.xianni.script.manager.ScriptInterfaceManager.createPrivateMonster(playerId,monsterId,x,y,z,leaveTime,mapId);
}

