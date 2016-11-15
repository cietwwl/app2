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
 * 将主角传送到指定地图的指定位置 
 * @param mapId
 * @param x
 * @param y
 * @param z
 */
function changeMap(playerId,mapId,x,y,z,angle){
	com.chuangyou.xianni.script.manager.ScriptInterfaceScenceManager.changeMap(playerId,mapId,x,y,z,angle);
}
/**
 *创建一个副本并传送进去
 */
function createCampaign(playerId,campaignId){
	com.chuangyou.xianni.script.manager.ScriptInterfaceScenceManager.createCampaign(playerId,campaignId);
}

/**
 * 怪我死亡
 */
function onDie(playerId,monsterId){
	com.chuangyou.xianni.script.manager.ScriptInterfaceScenceManager.onDie(playerId,monsterId);
}


/**
 * 获取玩家等级
 * @param playerId
 */
function getPlayerLevel(playerId){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceScenceManager.getPlayerLevel(playerId);
}
/**
 * 给玩家发提示
 * @param playerId
 */
function sendHintToClient(playerId){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceScenceManager.sendHintToClient(playerId);
}