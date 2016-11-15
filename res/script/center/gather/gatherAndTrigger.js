// 脚本id 唯一
function getScriptId() {
	return "10101";   //对应跟NPC配置表配置的脚本ID
}

// 脚本类型
function getInterfaceName() {
	return "com.chuangyou.xianni.gather.script.IGatherScript";   //NPC对话脚本类型
}

/////////////////////////////////////脚本逻辑开始
//////////////////////////////////////////////////////////////

/**
 * 执行脚本
 * @param playerId :角色ID
 * @param id ：采集物或触发点模板ID
 */
function doScript(playerId,id){
	
	switch (id) {
	case 1009403:
		addItemFromGather(playerId,8100004,1,1)
		break;
	case 1009404:
		addItemFromGather(playerId,8100005,2,1)
		break;
	case 1009405:
		addItemFromGather(playerId,8100006,2,1)
		break;
	case 1014402:
		addItemFromGather(playerId,8100007,1,1)
		break;	
	}
	
	//位面副本配置 ‘触发器id’ ： {taskId：对应任务id,campingId ：进入副本id}
	var taskCampaingSet = {
		'1009511' : {taskId : 10024,campingId : 1011009},
		'1009512' : {taskId : 10045,campingId : 1021009},
		'1009513' : {taskId : 10052,campingId : 1031009},
		'1009514' : {taskId : 10054,campingId : 1041009},
		'1014502' : {taskId : 10063,campingId : 1011014},
		'1014503' : {taskId : 10067,campingId : 1021014},
	} 
	//踩到某个触发器时如果有该触发器对应的任务则进入指定的位面副本

	if (taskCampaingSet[id] == undefined || taskCampaingSet[id] == null) {
		return;
	}
	if (isHasTask(playerId,taskCampaingSet[id].taskId)){
		createCampaingByOnceNpc(playerId,taskCampaingSet[id].campingId,-1);
	}
}


/////////////////////////////////////脚本逻辑结束
//////////////////////////////////////////////////////////////
