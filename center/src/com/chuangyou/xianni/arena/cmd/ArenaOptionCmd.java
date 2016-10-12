package com.chuangyou.xianni.arena.cmd;

import com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg;
import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.xianni.arena.ArenaInventory;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.robot.RobotManager;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code = Protocol.C_DEKARON_OPTION, desc = "请求创建副本/前往组队目标")
public class ArenaOptionCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ArenaOptionMsg opMsg = ArenaOptionMsg.parseFrom(packet.getBytes());
		// 获取信息
		if (opMsg.getOptionType() == 0) {
			sendArenaInfo(player);
		}
		// 挑战
		if (opMsg.getOptionType() == 1) {
			challenge(player, opMsg.getParam1());
		}
		// 领奖
		if (opMsg.getOptionType() == 2) {
			getReward(player, (int) opMsg.getParam1());
		}
		// 重置数据
		if (opMsg.getOptionType() == 3) {
			reset(player);
		}

	}

	private void challenge(GamePlayer player, long targetId) {
		if (player.getArenaInventory() != null && player.getArenaInventory().challenge()) {
			RobotInfoMsg.Builder builder = RobotInfoMsg.newBuilder();
			if (RobotManager.isRobot(targetId)) {
				RobotManager.writeRobotData(player, targetId, builder);
			} else {
				GamePlayer target = WorldMgr.getPlayer(targetId);
				if (target != null && target.getArmyInventory() != null) {
					target.getArmyInventory().getArmy().getHero().writeRobotInfo(target, builder);
				}
			}
			PBMessage message = MessageUtil.buildMessage(Protocol.S_CREATE_ARENA_BATTLE, builder);
			player.sendPbMessage(message);
		}
	}

	private void getReward(GamePlayer player, int param) {
		if (player.getArenaInventory() != null) {
			player.getArenaInventory().getReward(param);
		}
	}

	private void reset(GamePlayer player) {
		if (player.getArenaInventory() != null) {
			if (player.getBasePlayer().consumeCommonCash(ArenaInventory.RESET_COST, ItemRemoveType.ARNE_RESET_COST)) {
				player.getArenaInventory().reset(true);
			}
		}
	}

	private void sendArenaInfo(GamePlayer player) {
		if (player.getArenaInventory() != null) {
			player.getArenaInventory().sendArenaInfo();
		}
	}
}
