// 脚本id 唯一
function getScriptId() {
	return "item";   //对应跟NPC配置表配置的脚本ID
}

// 脚本类型
function getInterfaceName() {
	return "com.chuangyou.xianni.bag.script.IItemScript";   //使用物品脚本类型
}

/**
 * 使用物品,程序调用的方法
 * @param playerId
 * @param itemTempId
 * @param count
 */
function useItem(playerId, itemTempId, count){

	var blankNum 		= getPlayerBagSpace(playerId);
	var playerName 		= getPlayerName(playerId);
	var playerLev 		= getPlayerLevel(playerId);
	var playerJob		= getPlayerJob(playerId);
	var itemTempName 	= getItemName(itemTempId);

	//如果道具使用失败，一定要返回fasle


	//元婴橙色武器箱子
	if (itemTempId == 5110405){
		
		if (blankNum < 1) {
			sendHintToClient(playerId,"背包剩余空间不足");
			return false;
		}

		var awardItem;

		if (playerJob == 1){
			awardItem = 1111551;
		}else if (playerJob == 2){
			awardItem = 1111552;
		}else if (playerJob == 3){
			awardItem = 1111553;
		}

		addItemFromOpenItem(playerId,awardItem,1,1);
		
		//使用成功返回true,服务器会删除物品
		return true;
	}
	
	if (itemTempId >= 5110405 && itemTempId <= 5111008){
		var	itemSet = {
			'5111000' : {count:10},	//器化灵石·凡
			'5111001' : {count:20},	//器化灵石·良
			'5111002' : {count:50},	//器化灵石·中
			'5111003' : {count:100},	//器化灵石·上
			'5111004' : {count:200},	//器化灵石·极
			'5111005' : {count:500},	//器化灵石·王
			'5111006' : {count:1000},	//器化灵石·仙
			'5111007' : {count:2000},	//器化灵石·尊
			'5111008' : {count:10000},	//器化灵石·无
		}
		var templateId = 7140000	//装备经验
		var count = itemSet[itemTempId].count
		var isBind = 1	//绑定状态
		addItemFromOpenItem(playerId,templateId,count,isBind)
		//使用成功返回true,服务器会删除物品
	}
	
}

//常用方法

//获取物品颜色
function getColorVal(itemId) {
	var colorSet = ['#FFFFFF','#00FF7F','#00BFFF','#FF00FF','#FF4500','#FF0000'];
	var itemColor = getItemColor(itemId);
	return colorSet[itemColor-1];
}

/*生成min至max-1之间的随机数
 *js数组下标从0开始，生成减1的随机数方便使用
 *只传入1个参数时，生成0至参数-1之间的随机数
 */
function getRandNum(min,max) {
	if (max == undefined){
		max = min;
		min = 0;
	}	
	return Math.floor(Math.random()*(max-min)+min);
}
