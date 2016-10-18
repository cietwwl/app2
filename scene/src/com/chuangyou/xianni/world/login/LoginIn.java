package com.chuangyou.xianni.world.login;

import com.chuangyou.common.protobuf.pb.LoginResultMsgProto.LoginResultMsg;
import com.chuangyou.common.protobuf.pb.army.ArmyInfoMsgProto.ArmyInfoMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.LoginResult;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.role.objects.Pet;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.team.Team;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.SimplePlayerInfo;
import com.chuangyou.xianni.world.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.S_LOGIN_IN, desc = "登入场景服")
public class LoginIn implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		ArmyInfoMsg msg = ArmyInfoMsg.parseFrom(packet.getBytes());
		long playerId = msg.getHeorAppearance().getPlayerId();
		SimplePlayerInfo simPlayer = new SimplePlayerInfo();
		simPlayer.readProto(msg.getHeorAppearance());
		// 清理数据
		ArmyProxy existed = WorldMgr.getArmy(playerId);
		if (existed != null) {
			Log.error("------------------调试为啥有部队没有清理的问题,看到输出日志请告知-----------------" + playerId);
			WorldMgr.unLine(playerId);
		}

		ArmyProxy army = new ArmyProxy(playerId, "center", channel, simPlayer);
		WorldMgr.addOnline(army);

		// 初始化英雄数据
		Player player = new Player(playerId);
		Team t = TeamMgr.getTeam(playerId);
		if (t == null) {
			player.setTeamId(0);
		} else {
			player.setTeamId(t.getTeamid());
		}
		player.setSimpleInfo(simPlayer);
		// 初始化觉醒技能buff
		player.updateWeaponBuff();
		player.readHeroInfo(msg.getHeoBattleInfo());
		army.setPlayer(player);

		// 设置宠物
		Pet pet = null;
		if (msg.getPetBattleInfo().getPetTempId() > 0) {
			// 初始化宠物数据
			pet = new Pet(playerId, IDMakerHelper.nextID());
			pet.readPetInfo(msg.getPetBattleInfo());
		}
		army.setPet(pet);

		PBMessage message = MessageUtil.buildMessage(Protocol.U_ARMY_HERO_INFO, msg.getHeoBattleInfo());
		army.sendPbMessage(message);

		// 通知客户端，已经登录成功
		LoginResultMsg.Builder lr = LoginResultMsg.newBuilder();
		lr.setResult(LoginResult.SUCCESS);
		lr.setTime(System.currentTimeMillis());
		PBMessage loginResult = MessageUtil.buildMessage(Protocol.U_G_LOGIN_RESULT, playerId, lr);
		army.sendPbMessage(loginResult);

		// 通知center服务,部队创建成功
		PBMessage armyResult = MessageUtil.buildMessage(Protocol.C_SCENE_LOGIN_SUCCESS, playerId);
		army.sendPbMessage(armyResult);

	}

}
