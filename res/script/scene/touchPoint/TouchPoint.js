// 脚本id 唯一
function getScriptId() {
	return "touchPoint"; // 对应跟NPC配置表配置的脚本ID
}

// 脚本类型
function getInterfaceName() {
	return "com.chuangyou.xianni.touchPoint.script.ITouchPointTrigger"; // NPC对话脚本类型
}

function action(playerId, npcType) {

		switch (npcType) {

		case 241:
		createCampaign(playerId, 1);

		//月星->赵国传送点
		case 1005301:
			changeMap(playerId,1009,2928,353,5039,1);
			break;
		//赵国->月星传送点
		case 1009301:
			changeMap(playerId,1005,669,679,-974,1);
			break;
		//藤家城->赵国野外
		case 1008302:
			changeMap(playerId,1009,3379,346,5286,1);
			break;
		//恒岳派后山->山间泉眼
		case 1009305:
			changeMap(playerId,1009,3122,189,5681,1);
			break;
		//山间泉眼->恒岳派后山
		case 1009306:
			changeMap(playerId,1009,3382,345,5293,1);
			break;	
		//赵国野外->藤家城
		case 1009307:
			changeMap(playerId,1008,5800,-4.8,5099);
			break;	
		/*case 91001:
			//createCampaign(playerId, 1);
			changeMap(playerId, 4001, 1670, 1, -1570);
			break;
		case 91002:
			changeMap(playerId, 4002, 100, 1, -1814);
			break;
		case 91003:
			changeMap(playerId, 4003, 370, 0, -1160);
			break;
		case 91004:
			changeMap(playerId, 4004, 1490, 140, -820);
			break;
		case 91005:
			changeMap(playerId, 4005, 540, 68, -980);
			break;
		case 91006:
			changeMap(playerId, 5001, 1360, 85, -1032);
			break;
		case 91007:
			changeMap(playerId, 1005, 377, -7, -1520);
			break;*/
	}
}