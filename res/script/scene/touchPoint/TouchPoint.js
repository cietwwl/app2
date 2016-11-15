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
			createCampaign(playerId,1);
			break;
		//月星->赵国传送点
		case 1005301:
			if (getPlayerLevel(playerId) >=1){
				changeMap(playerId,1009,3717,264,5809,0);
			}
			break;
		//赵国->月星传送点
		case 1009301:
		    if (getPlayerLevel(playerId) >=400){
				changeMap(playerId,1005,669,679,-974,0);
			}
			break;
		//藤家城->赵国野外
		case 1008302:
			if (getPlayerLevel(playerId) >=1){
				changeMap(playerId,1009,5111,60,6081,0);
			}
			
			break;
		//赵国野外->藤家城
		case 1009307:
			if (getPlayerLevel(playerId) >=15){
				changeMap(playerId,1008,5800,-5,5099,0);
			}
			break;	
		//域外战场->决明谷
		case 1014301:
			if (getPlayerLevel(playerId) >=15){
				changeMap(playerId,1009,5753,-13,7476,0);
			}
			break;		
		//决明谷->域外战场
		case 1009303:
			if (getPlayerLevel(playerId) >=25){
				changeMap(playerId,1014,134,236,1003,0);
			}
			break;	
		//域外战场->修魔海	
		case 1014302:
			if (getPlayerLevel(playerId) >=35){
				changeMap(playerId,1010,2808,158,2447,0);
			}
			break;
		//修魔海->域外战场	
		case 1010001:
			if (getPlayerLevel(playerId) >=25){
				changeMap(playerId,1010,2808,158,2447,0);
			}
			break;	
		//修魔海->古神之心	
		case 1010002:
			if (getPlayerLevel(playerId) >=45){
				changeMap(playerId,1010,2808,158,2447,0);
			}		
			break;	
		//修魔海->洞府	
		case 1010003:
			if (getPlayerLevel(playerId) >=35){
				changeMap(playerId,1015,-700,86,-564,0);
			}		
			break;	
		//古神之心->修魔海
		case 1011001:
			if (getPlayerLevel(playerId) >=35){
				changeMap(playerId,1010,2225,131,2389,0);
			}		
			break;	
		//古神之心上->古神之心中
		case 1011002:
			changeMap(playerId,1011,-118,791,624,0);
			break;	
		//古神之心中->古神之心上
		case 1011003:
			changeMap(playerId,1011,411,764,833,0);
			break;	
		//古神之心中->古神之心下
		case 1011004:
			changeMap(playerId,1011,117,428,-1120,0);
			break;	
		//古神之心下->古神之心中
		case 1011005:
			changeMap(playerId,1011,-441,527,-763,0);
			break;	
		//古神之心下->古神之心底
		case 1011006:
			changeMap(playerId,1011,687,116,-157,0);
			break;			
		//古神之心底->古神之心下
		case 1011007:
			changeMap(playerId,1011,1192,340,-882,0);
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