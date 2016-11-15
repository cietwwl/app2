// 脚本id 唯一
function getScriptId() {
	return "campaign"; //副本使用的脚本ID
}

// 脚本类型
function getInterfaceName() {
	return "com.chuangyou.xianni.campaign.script.ICampaignScript"; // 副本脚本类型
}

/**
 * 队友已经进入副本，但是玩家不在副本创建的地图中
 * @param playerId
 * @param campaignId
 */
function notInCampaignCreateField(playerId, campaignId){
	if (campaignId == 80001){
		var content = "不在副本进入地图，进入副本失败";
		sendHintToClient(playerId,content);
	}
}