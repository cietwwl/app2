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
}


/////////////////////////////////////脚本逻辑结束
//////////////////////////////////////////////////////////////
