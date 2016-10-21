package com.chuangyou.xianni.pvp_1v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import com.chuangyou.common.protobuf.pb.msg.AlertInfoMsgProto.AlertInfoMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerSimpleInfoMsgProto.PlayerSimpleInfoMsg;
import com.chuangyou.common.protobuf.pb.pvp1v1.PvP1v1BlueInfoProto.PvP1v1BlueInfoMsg;
import com.chuangyou.common.protobuf.pb.pvp1v1.PvP1v1RankInfoListProto.PvP1v1RankInfoListMsg;
import com.chuangyou.common.protobuf.pb.pvp1v1.PvP1v1RankinfoProto.PvP1v1RankinfoMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.constant.MsgCodeConstant;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.exec.AbstractActionQueue;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.pvp_1v1.action.MatchAction;
import com.chuangyou.xianni.pvp_1v1.action.RankAction;
import com.chuangyou.xianni.pvp_1v1.action.SwitchControlAction;
import com.chuangyou.xianni.pvp_1v1.action.WaitingFightAction;
import com.chuangyou.xianni.reward.RewardManager;
import com.chuangyou.xianni.word.WorldMgr;

public class PvP1v1Manager {

	// 参与者
	private static Map<Long, PvPRankInfo>		joiners;
	// 匹配队列
	private static Queue<PvPRankInfo>			queue;
	// 排行榜
	private static Map<Integer, PvPRankInfo>	rankInfos;
	private static ActionQueue					actionQueue	= new AbstractActionQueue(ThreadManager.actionExecutor);
	// 副本开闭状态
	public static boolean						STATUS		= false;
	// 玩家状态-正常
	public static final short					NORMAL		= 0;
	// 玩家状态-匹配
	public static final short					MATCHING	= 1;
	// 玩家状态-准备
	public static final short					PREPARED	= 2;

	// 执行排行榜时间
	public static long							rankTime	= 0l;
	// 是否存在延后排名队列
	public static volatile boolean				existsDelay	= false;

	public static ThreadSafeRandom				random		= new ThreadSafeRandom();

	public static void start() {
		STATUS = true;
		joiners = new HashMap<>();
		queue = new LinkedBlockingDeque<>();
		rankInfos = new HashMap<>();
		existsDelay = false;
		MatchAction action = new MatchAction(actionQueue, 5 * 1000);
		actionQueue.enDelayQueue(action);
	}

	public static void over() {
		STATUS = false;
		SwitchControlAction action = new SwitchControlAction(actionQueue, 60 * 1000);
		actionQueue.enDelayQueue(action);
	}

	public static void billingAndClear() {
		// 前5名发送排名奖励
		for (int i = 1; i <= 5; i++) {
			PvPRankInfo rinfo = rankInfos.get(i);
			if (rinfo == null) {
				continue;
			}
			GamePlayer player = WorldMgr.getPlayer(rinfo.getPlayerId());
			if (player != null) {
				RewardManager.sendPvP1v1RankReward(player, i);
			}
		}
		// 清理数据
		joiners.clear();
		queue.clear();
		rankInfos.clear();
	}

	// 当前状态 0 正常状态 1 开始匹配 2 匹配完毕 读条中
	/** 开始挑战 */
	public static boolean joinOrChallenge(GamePlayer player) {
		if (joiners == null || STATUS == false) {
			return false;
		}
		PvPRankInfo red = joiners.get(player.getPlayerId());
		if (red == null) {
			red = new PvPRankInfo(player.getPlayerId(), player.getBasePlayer().getPlayerInfo().getFight(), 0, player.getNickName());
			joiners.put(player.getPlayerId(), red);
		}
		if (red.getStatu() == PvPRankInfo.BEGIN) {
			Log.error("已经在匹配池，无需要再匹配,playerId :" + player.getPlayerId());
			return false;
		}
		queue.add(red);
		red.setStatu(PvPRankInfo.BEGIN);// 开始匹配
		sendRankInfo(player, red);
		return true;
	}

	public static void getPvPRankInfo(GamePlayer player) {
		if (joiners == null || STATUS == false) {
			return;
		}
		PvPRankInfo red = joiners.get(player.getPlayerId());
		if (red == null) {
			return;
		}
		sendRankInfo(player, red);
	}

	public static void matching() {
		if (queue.size() < 2) {
			return;
		}
		if (STATUS == false) {
			return;
		}
		ArrayList<PvPRankInfo> temp = new ArrayList<>();
		temp.addAll(queue);
		int matchSize = temp.size() / 2;
		for (int i = 0; i < matchSize; i++) {
			PvPRankInfo red = temp.get(random.next(temp.size()));
			temp.remove(red);
			PvPRankInfo blue = temp.get(random.next(temp.size()));
			temp.remove(blue);

			if (red != null && blue != null && blue.getPlayerId() != red.getPlayerId()) {
				queue.remove(blue);
				queue.remove(red);
				// 当被匹配的对手掉线，或者无数据时，清出匹配池，匹配下一个
				GamePlayer attacker = WorldMgr.getPlayer(red.getPlayerId());
				GamePlayer defencer = WorldMgr.getPlayer(blue.getPlayerId());
				if (attacker == null || attacker.getPlayerState() == PlayerState.OFFLINE || defencer == null || defencer.getPlayerState() == PlayerState.OFFLINE) {
					continue;
				}
				blue.setStatu(PvPRankInfo.READY);
				red.setStatu(PvPRankInfo.READY);
				WaitingFightAction wfAction = new WaitingFightAction(actionQueue, red.getPlayerId(), blue.getPlayerId(), 5000);
				ThreadManager.actionExecutor.enDelayQueue(wfAction);
				sendBlueInfoMsg(attacker, defencer);
				sendBlueInfoMsg(defencer, attacker);
				sendRankInfo(attacker, red);
				sendRankInfo(defencer, blue);
			}
		}
	}

	/** 取消挑战 */
	public static boolean cancel(GamePlayer player) {
		if (STATUS == false || joiners.get(player.getPlayerId()) == null) {
			return false;
		}
		PvPRankInfo joiner = joiners.get(player.getPlayerId());
		if (joiner == null) {
			return false;
		}
		joiner.setStatu(PvPRankInfo.NOMOR);
		queue.remove(joiner);
		sendRankInfo(player, joiner);
		return true;
	}

	private static void sendBlueInfoMsg(GamePlayer red, GamePlayer blue) {
		PvP1v1BlueInfoMsg.Builder builder = PvP1v1BlueInfoMsg.newBuilder();
		PlayerSimpleInfoMsg.Builder pinfomsg = PlayerSimpleInfoMsg.newBuilder();
		blue.getBasePlayer().getPlayerInfo().writeSimpInfo(pinfomsg);
		builder.setBlueInfo(pinfomsg);
		PBMessage message = MessageUtil.buildMessage(Protocol.U_OTHER_BATTER_IFNO, builder);
		red.sendPbMessage(message);
	}

	/** 1 胜 0 平 -1负 */
	public static void settlement(GamePlayer player, int result) {
		if (player == null) {
			return;
		}
		PvPRankInfo rankInfo = joiners.get(player.getPlayerId());
		if (rankInfo == null) {
			return;
		}
		rankInfo.setStatu(PvPRankInfo.NOMOR);
		if (result == 1) {
			rankInfo.addScore(3);
		}
		if (result == 0) {
			rankInfo.addScore(1);
		}
		if (result != -1) {
			if (System.currentTimeMillis() - rankTime > 5 * 1000) {
				rank();
			} else if (!existsDelay) {
				RankAction action = new RankAction(PvP1v1Manager.getActionQueue(), 10 * 1000);
				PvP1v1Manager.getActionQueue().enqueue(action);
			}

		}
		addWinningStreak(player, rankInfo, result);
		sendRankInfo(player, rankInfo, false);
	}

	/** 添加连胜场次 */
	public static void addWinningStreak(GamePlayer player, PvPRankInfo rankInfo, int result) {
		// 失败或者平局
		if (result == -1 || result == 0) {
			rankInfo.setWinningStreak(0);
		}
		// 成功
		if (result == 1) {
			int winCount = rankInfo.getWinningStreak() + 1;
			rankInfo.setWinningStreak(winCount);
			rankInfo.setWinCount(rankInfo.getWinCount() + 1);
			if (winCount > rankInfo.getMaxWinningStreak()) { // 是否更新最大连胜次数
				rankInfo.setMaxWinningStreak(winCount);
				// 发送连胜奖励（没有对应奖励则返回false）
				boolean get = RewardManager.sendPvP1v1WinReward(player, winCount);
				if (get && winCount > 1) {
					AlertInfoMsg.Builder alertMsg = AlertInfoMsg.newBuilder();
					alertMsg.setAlertCode(MsgCodeConstant.PVP_1v1_WINNING_STREAK_MSG);
					alertMsg.setStrParam1(player.getNickName());
					alertMsg.setLongParam1(player.getPlayerId());
					alertMsg.setIntParam1(winCount);
					WorldMgr.sendMsg2All(alertMsg);
				}
			}
		}

	}

	public static void sendRankInfo(GamePlayer player, PvPRankInfo rankInfo) {
		sendRankInfo(player, rankInfo, false);
	}

	/** 发送自己排名信息 */
	public static void sendRankInfo(GamePlayer player, PvPRankInfo rankInfo, boolean clientGet) {
		if (clientGet) {
			rankInfo = joiners.get(player.getPlayerId());
		}
		if (rankInfo == null) {
			return;
		}
		PvP1v1RankinfoMsg.Builder builder = PvP1v1RankinfoMsg.newBuilder();
		rankInfo.writeProto(builder);
		PBMessage message = MessageUtil.buildMessage(Protocol.U_ME_RANK_INFO, builder);
		player.sendPbMessage(message);
	}

	/** 发送前20名 */
	public static void sendRankInfo_20(GamePlayer player) {
		PvP1v1RankInfoListMsg.Builder toal = PvP1v1RankInfoListMsg.newBuilder();
		for (int i = 1; i <= 20; i++) {
			PvPRankInfo rankInfo = rankInfos.get(i);
			if (rankInfo != null) {
				PvP1v1RankinfoMsg.Builder infomsg = PvP1v1RankinfoMsg.newBuilder();
				rankInfo.writeProto(infomsg);
				toal.addRankLists(infomsg);
			}
		}
		PBMessage message = MessageUtil.buildMessage(Protocol.U_LEADER_BOARD, toal);
		player.sendPbMessage(message);
	}

	// 排名
	public static void rank() {
		List<PvPRankInfo> temp = new ArrayList<>();
		synchronized (joiners) {
			temp.addAll(joiners.values());
		}
		rankInfos.clear();
		java.util.Collections.sort(temp);
		for (int i = 1; i <= temp.size(); i++) {
			PvPRankInfo rinfo = temp.get(i - 1);
			if (rinfo != null) {
				rinfo.setRank(i);
			}
			rankInfos.put(rinfo.getRank(), rinfo);
		}
	}

	public static ActionQueue getActionQueue() {
		return actionQueue;
	}
}
