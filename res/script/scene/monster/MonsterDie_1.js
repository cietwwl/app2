// 脚本id 唯一
function getScriptId() {
	return "MonsterDie_1"; // 对应跟NPC配置表配置的脚本ID
}

// 脚本类型
function getInterfaceName() {
	return "com.chuangyou.xianni.role.script.IMonsterDie"; // NPC对话脚本类型
}

function action(playerId, monsterId) {
	return onDie(playerId, monsterId);
}