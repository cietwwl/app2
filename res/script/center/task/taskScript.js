// 脚本id 唯一
function getScriptId() {
	return "10005";   //对应跟NPC配置表配置的脚本ID
}

// 脚本类型
function getInterfaceName() {
	return "com.chuangyou.xianni.task.script.ITaskScript";   //NPC对话脚本类型
}


/////////////////////////////////////脚本逻辑开始
//////////////////////////////////////////////////////////////

/** *
 * 接任务执行脚本
 * @param playerId
 * @param taskId
 */
function acceptTask(playerId,taskId){
	sendHintToClient(playerId,"接受：任务："+taskId);
	switch (taskId) {
	case 10014:
	    createPrivateMonster(playerId,24001,900000);
		break;
	}
}

/**
 * 完成任务执行脚本
 * @param playerId
 * @param taskId
 */
function finishTask(playerId,taskId){

	switch (taskId) {
	case 10002:
		changeMap(playerId,1007,2724,721,4059);
		break;
	case 10005:
		changeMap(playerId,1009,4054,247,5936);
		break;	
	case 10026:
		changeMap(playerId,1008,5811,-4,4873);
		break;
	case 10034:
		changeMap(playerId,1009,4735,18,6978);
		break;
	case 10050:
		changeMap(playerId,1009,5603,14,7282);
		break;	
	case 10057:
		changeMap(playerId,1014,134,236,1003);
		break;
	}
}

/**
 * 提交任务执行脚本
 * @param playerId
 * @param taskId
 */
function commitTask(playerId,taskId){

	switch (taskId) {
	case 10005:
		changeMap(playerId,1009,-7612,172,5265);
		break;
	case 10031:
		changeMap(playerId,1009,-5211,8.9,5351);
		break;
	case 10036:
		changeMap(playerId,1009,-6928,-55.7,7296);
		break;
	case 10040:
		changeMap(playerId,1009,-7913,-34,7672);
		break;
	case 10080:
		changeMap(playerId,1009,-5591,-30,7259);
		break;	
	}
}


/////////////////////////////////////脚本逻辑结束
//////////////////////////////////////////////////////////////
