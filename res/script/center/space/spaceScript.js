// 脚本id 唯一
function getScriptId() {
	return "spaceScript";   
}

// 脚本类型
function getInterfaceName() {
	return "com.chuangyou.xianni.space.script.ISpaceScript"; 
}

/////////////////////////////////////脚本逻辑开始
//////////////////////////////////////////////////////////////
/**
 * 随机点赞获得物品
 */
function getSpaceLikeGift(playerId,gift){

	var rand = Math.random() * 10000;

	if (rand < 5000) {
		return 0;
	}

	var awardArr = [
		6100001,
		6100002,
		6100003,
		6100004,
		6100005,
	];

	var itemId = awardArr[Math.floor(Math.random() * awardArr.length)];

	return itemId;

}