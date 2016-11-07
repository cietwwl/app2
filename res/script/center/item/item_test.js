// 脚本id 唯一
function getScriptId() {
	return "item_test";   //对应跟NPC配置表配置的脚本ID
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
	var itemTempName 	= getItemName(itemTempId);

	//如果道具使用失败，一定要返回fasle



	if (itemTempId >= 5190101 && itemTempId <= 5199902){
		
		if (blankNum < 1) {
			sendHintToClient(playerId,"背包剩余空间不足");
			return false;
		}

		var awardSet 		= [4100601,4100602,4100603,4100604,4100605,4100606,4100607,4100608,4100609,4100610];
		var awardItem 		= awardSet[getRandNum(awardSet.length)];
		var awardItemName 	= getItemName(awardItem);
		var awardItemColor 	= getItemColor(awardItem);
		
		var chanel 	= awardItemColor >= 4 ? 9:10;
		var content = '<color=#00BFFF>'+playerName+'</color>从<color='+getColorVal(itemTempId)+'>【'+itemTempName+'】</color>获得了<color='+getColorVal(awardItem)+'>【'+awardItemName+'】</color>';
		sendNotice(chanel,content);
		addItemFromOpenBox(playerId,awardItem,1,1);
		
	}

	

	//使用成功返回true,服务器会删除物品
	return true;
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
