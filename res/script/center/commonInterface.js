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
function changeMap(roleId,mapId,x,y,z,angle){
	com.chuangyou.xianni.map.MapProxyManager.changeMap(roleId,mapId,x,y,z,angle);
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
function addItemFromGather(playerId,templateId,count,isBind){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.addItemFromGather(playerId,templateId,count,isBind);
}

/**
 * 开箱子获得物品
 * @param playerId
 * @param templateId
 * @param count
 * @param isBind
 * @returns
 */
function addItemFromOpenItem(playerId,templateId,count,isBind){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.addItemFromOpenItem(playerId,templateId,count,isBind);
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
/**
 * 点击NPC进入某个副本
 * @param playerId
 * @param camaignId  副本ID
 * @param npcIndexId npc唯一的ID
 */
function createCampaingByOnceNpc(playerId,camaignId,npcIndexId){
	com.chuangyou.xianni.script.manager.ScriptInterfaceManager.createCampaingByOnceNpc(playerId,camaignId,npcIndexId);
}

/**
 * 触发传送门
 * @param playerId
 * @param npcEntityId
 */
function triggerTransferNpc(playerId,npcEntityId){
	com.chuangyou.xianni.script.manager.ScriptInterfaceManager.triggerTransferNpc(playerId, npcEntityId);
}

/**
 * 获取玩家等级
 * @param playerId
 */
function getPlayerLevel(playerId){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.getPlayerLevel(playerId);
}

/**
 * 获取玩家名字
 * @param playerId
 * @return
 */
function getPlayerName(playerId){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.getPlayerName(playerId);
}

/**
 * 获取玩家职业
 * @param playerId
 * @return
 */
function getPlayerJob(playerId){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.getPlayerJob(playerId);
}

/**
 * 获取玩家境界等级
 * @param playerId
 * @return
 */
function getPlayerState(playerId){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.getPlayerState(playerId);
}

/**
 * 获取玩家背包剩余空间
 * @param playerId
 * @return
 */
function getPlayerBagSpace(playerId){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.getPlayerBagSpace(playerId);
}

/**
 * 只加气血
 */
function addCurBlood(playerId, addNum){
	com.chuangyou.xianni.script.manager.ScriptInterfaceManager.addCurBlood(playerId, addNum);
}
/**
 * 只加魂血
 * @param playerId
 * @param addNum
 */
function addCurSoul(playerId, addNum){
	com.chuangyou.xianni.script.manager.ScriptInterfaceManager.addCurSoul(playerId, addNum);
}

/**
 * 加血，先提升元魂，再提升气血
 * @param playerId
 * @param addNum
 */
function addCurSoulOrBlood(playerId, addNum){
	com.chuangyou.xianni.script.manager.ScriptInterfaceManager.addCurSoulOrBlood(playerId, addNum);
}

/**
 * 给玩家加buff
 * @param playerId
 * @param buffTempId
 */
function addBuff(playerId, buffTempId){
	com.chuangyou.xianni.script.manager.ScriptInterfaceManager.addBuff(playerId, buffTempId);
}

/**
 * 发送公告
 * @param channel 频道号 1系统消息  9滚屏公告 10可被顶的滚屏公告
 * @param content 公告内容
 */
function sendNotice(channel, content){
	com.chuangyou.xianni.script.manager.ScriptInterfaceManager.sendNotice(channel, content);
}

/**
 * 发送场景内公告
 * @param channel
 * @param content
 */
function sendSceneNotice(playerId, channel, content){
	com.chuangyou.xianni.script.manager.ScriptInterfaceManager.sendSceneNotice(playerId, channel, content);
}

/**
 * 获取物品颜色
 * @param itemTempId
 */
function getItemColor(itemTempId){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.getItemColor(itemTempId);
}

/**
 * 获取物品名字
 * @param itemTempId
 */
function getItemName(itemTempId){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.getItemName(itemTempId);
}



/**
 * 激活指定分身
 * @param playerId
 * @param tempId
 * @return
 */
function activeAvatar(playerId,tempId){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.activeAvatar(playerId,tempId);
}

/**
 * 激活指定宠物
 * @param playerId
 * @param tempId
 * @return
 */
function activePet(playerId,tempId){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.activePet(playerId,tempId);
}

/**
 * 激活指定法宝
 * @param playerId
 * @param tempId
 * @return
 */
function activeMagicwp(playerId,tempId){
	return com.chuangyou.xianni.script.manager.ScriptInterfaceManager.activeMagicwp(playerId,tempId);
	
}
