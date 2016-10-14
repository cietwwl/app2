package com.chuangyou.xianni.avatar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.chuangyou.common.protobuf.pb.PlayerInfoMsgProto.PlayerInfoMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyListMsgProto.PropertyListMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.army.RobotInfoListProto.RobotInfoListMsg;
import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.common.protobuf.pb.avatar.AvatarBeanListMsgProto.AvatarBeanListMsg;
import com.chuangyou.common.protobuf.pb.avatar.AvatarBeanProto.AvatarBeanMsg;
import com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg;
import com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg;
import com.chuangyou.common.protobuf.pb.avatar.SingleReardInfoMsgProto.SingleReardInfoMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerSomeThingUpdateProto.PlayerSomeThingUpdateMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.avatar.temlate.AvatarTempManager;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.constant.CampaignConstant;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.avatar.AvatarCorrespondTemplateInfo;
import com.chuangyou.xianni.entity.avatar.AvatarInfo;
import com.chuangyou.xianni.entity.avatar.AvatarStarTemplate;
import com.chuangyou.xianni.entity.avatar.AvatarTemplateInfo;
import com.chuangyou.xianni.entity.avatar.AvatarUpGradeTemplate;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.entity_id.EntityIdBuilder;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.skill.template.SkillTempMgr;
import com.chuangyou.xianni.sql.dao.AvatarInfoDao;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

public class AvatarInventory implements IInventory {
	private GamePlayer					player;
	// 所有被激活的分身
	private Map<Integer, AvatarInfo>	avatarInfos					= new HashMap<>();
	// 出战的分身
	private Map<Integer, AvatarInfo>	finghtingInfos				= new HashMap<>();

	public static final int				FIGHTING					= 1;
	// 初始仙力上限为1000
	public static final int				INIT_AVATAR_ENERGY			= 1000;

	// 失败
	static final int					REWARD_NOTHING				= 0;
	// 等级提升
	static final int					REWARD_GREAD_UP				= 1;
	// 技能
	static final int					REWARD_SKILL_UP				= 2;
	// 秘籍提升
	static final int					REWARD_SKILLSTRENTHEN_UP	= 3;
	// 物品奖励
	static final int					REWARD_ITEM					= 4;

	public AvatarInventory(GamePlayer player) {
		this.player = player;
	}

	@Override
	public boolean loadFromDataBase() {
		List<AvatarInfo> myAvatarInfos = DBManager.getAvatarInfoDao().get(player.getPlayerId());
		if (myAvatarInfos == null) {
			return true;
		}
		for (AvatarInfo ainfo : myAvatarInfos) {
			avatarInfos.put(ainfo.getTempId(), ainfo);
			// 出战
			if (ainfo.getIndex() > 0) {
				if (finghtingInfos.get(ainfo.getIndex()) != null) {
					Log.error("-----------玩家分身出战顺序出现问题------------------playerId :" + player.getPlayerId());
					// 当分身出战顺序出现问题，则分身取消出战
					ainfo.setIndex(0);
					ainfo.setStatu(0);
				} else {
					finghtingInfos.put(ainfo.getIndex(), ainfo);
				}
			}
		}
		return true;
	}

	// 添加仙力
	public int addAvatarEnergy(int addCount, int addType) {
		int oldCount = player.getBasePlayer().getPlayerInfo().getAvatarEnergy();
		int max = getMaxAvatarEnery();
		if (oldCount >= max) {
			return 0;
		}
		int newCount = oldCount + addCount;
		player.getBasePlayer().addAvatarEnergy(newCount > max ? max : newCount, addType);

		// 复写仙力值到scene服
		PlayerSomeThingUpdateMsg.Builder msg = PlayerSomeThingUpdateMsg.newBuilder();
		msg.setCount(player.getBasePlayer().getPlayerInfo().getAvatarEnergy());
		PBMessage message = MessageUtil.buildMessage(Protocol.S_RE_WRITE_AVATAR_ENERGY, msg);
		player.sendPbMessage(message);
		return newCount - oldCount;
	}

	// 获取灵气最大值
	public int getMaxAvatarEnery() {
		int max = INIT_AVATAR_ENERGY;
		for (AvatarInfo avatar : avatarInfos.values()) {
			AvatarCorrespondTemplateInfo correspondTemp = AvatarTempManager.getAvatarCorrespondTemplateInfo(avatar.getTempId(), avatar.getCorrespond());
			if (correspondTemp != null) {
				max += correspondTemp.getEnergyLimit();
			}
		}
		return max;
	}

	@Override
	public boolean unloadData() {
		avatarInfos.clear();
		finghtingInfos.clear();
		player = null;
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		if (avatarInfos == null || avatarInfos.size() == 0) {
			return true;
		}
		AvatarInfoDao dao = DBManager.getAvatarInfoDao();
		for (AvatarInfo ainfo : avatarInfos.values()) {
			if (ainfo.getOp() == Option.Insert || ainfo.getOp() == Option.Update) {
				dao.saveOrUpdata(ainfo);
			}
			if (ainfo.getOp() == Option.Delete) {
				Log.error(ainfo.getId(), new Exception());
			}
		}
		return true;
	}

	// 0升级技能， 1-5升级神通
	public boolean upgradeSkill(int tempId, int index) {
		AvatarInfo info = avatarInfos.get(tempId);
		if (info == null) {
			return false;
		}
		int oldSkillId = 0;
		switch (index) {
			case 0:
				oldSkillId = info.getSkillId();
				break;
			case 1:
				oldSkillId = info.getSkillStrengthen1();
				break;
			case 2:
				oldSkillId = info.getSkillStrengthen2();
				break;
			case 3:
				oldSkillId = info.getSkillStrengthen3();
				break;
			case 4:
				oldSkillId = info.getSkillStrengthen4();
				break;
			case 5:
				oldSkillId = info.getSkillStrengthen5();
				break;
			default:
				return false;
		}
		SkillTempateInfo oldSkillTemp = SkillTempMgr.getSkillTemp(oldSkillId);
		// 技能模板不存在
		if (oldSkillTemp == null) {
			return false;
		}
		SkillTempateInfo newSkillTemp = SkillTempMgr.getSkillTemp(oldSkillTemp.getNextTempId());
		// 没有可升级基恩给你
		if (newSkillTemp == null) {
			return false;
		}
		info.addskill(newSkillTemp.getTemplateId(), index);
		sendSingleAvatarInfo(info);
		updataProperty();
		return true;
	}

	// 分身升星
	public void upStar(int tempId) {
		AvatarInfo info = avatarInfos.get(tempId);
		// 玩家是否拥有此分身
		if (info == null) {
			return;
		}
		// 获取升星模板
		AvatarStarTemplate starTemp = AvatarTempManager.getAvatarStarTemplate(info.getTempId(), info.getStar());
		// 升星模板是否存在
		if (starTemp == null) {
			return;
		}
		// 是否已经到了顶级
		AvatarStarTemplate nextLevel = AvatarTempManager.getAvatarStarTemplate(info.getTempId(), info.getStar() + 1);
		if (nextLevel == null) {
			return;
		}
		// 消耗物品
		if (!player.getBagInventory().removeItem(starTemp.getNeedItem(), starTemp.getNeedNum(), ItemRemoveType.AVATAR_UP_STAR_COST)) {
			return;
		}
		// 升级
		info.setStar(info.getStar() + 1);
		info.addskill(nextLevel.getSkillId(), info.getStar());
		sendSingleAvatarInfo(info);
		updataProperty();
	}

	// 出战
	public void fight(int tempId, int size) {
		if (inAvatarCampaign()) {
			return;
		}
		AvatarInfo info = avatarInfos.get(tempId);
		// 玩家是否拥有此分身
		if (info == null) {
			return;
		}
		if (size < 1 || size > 3) {
			return;
		}
		info.setIndex(size);
		finghtingInfos.put(size, info);
		sendSingleAvatarInfo(info);
	}

	// 休息
	public void sleep(int tempId) {
		if (inAvatarCampaign()) {
			return;
		}
		AvatarInfo info = avatarInfos.get(tempId);
		// 玩家是否拥有此分身
		if (info == null) {
			return;
		}
		info.setIndex(0);
		finghtingInfos.remove(info);
		sendSingleAvatarInfo(info);
	}

	// 分身默契提升
	public void upCorrespond(int tempId) {
		AvatarInfo info = avatarInfos.get(tempId);
		// 玩家是否拥有此分身
		if (info == null) {
			return;
		}
		// 获取升星模板
		AvatarCorrespondTemplateInfo correspondTemp = AvatarTempManager.getAvatarCorrespondTemplateInfo(info.getTempId(), info.getCorrespond());
		// 升星模板是否存在
		if (correspondTemp == null) {
			return;
		}
		// 是否已经到了顶级
		AvatarCorrespondTemplateInfo nextLevel = AvatarTempManager.getAvatarCorrespondTemplateInfo(info.getTempId(), info.getCorrespond() + 1);
		if (nextLevel == null) {
			return;
		}
		// 消耗物品
		if (!player.getBagInventory().removeItem(correspondTemp.getNeedItem(), correspondTemp.getNeedNum(), ItemRemoveType.AVATAR_UP_STAR_COST)) {
			return;
		}
		// 是否直接升级
		Random random = new Random();
		if (random.nextInt(10000) < correspondTemp.getRate()) {
			info.setSchedule(info.getSchedule() + 1);
		} else {
			// 进度减1
			int newSchedule = info.getSchedule() - 1;
			info.setSchedule(newSchedule < 0 ? 0 : newSchedule);
		}
		// 进度满格则升级
		if (info.getSchedule() > correspondTemp.getMaxPro()) {
			info.setCorrespond(info.getCorrespond() + 1);
			info.setSchedule(0);
			updataProperty();
		}
		sendSingleAvatarInfo(info);
	}

	// 激活分身
	public void activeAvatar(int avatarTempId) {
		AvatarTemplateInfo temp = AvatarTempManager.getAvatarTemplateInfo(avatarTempId);
		// 模板不存在
		if (temp == null) {
			return;
		}
		// 是否已经激活
		if (avatarInfos.containsKey(avatarTempId)) {
			return;
		}
		// 扣除消耗物品
		if (!player.getBagInventory().removeItem(temp.getNeedItem(), 1, ItemRemoveType.ACTIVE_AVATAR_COST)) {
			return;
		}
		// 激活分身
		AvatarInfo ainfo = new AvatarInfo(EntityIdBuilder.avatarIdBuilder(), player.getPlayerId(), avatarTempId, temp.getSkillId());
		avatarInfos.put(ainfo.getTempId(), ainfo);

		// 当有未出战位置时,默认选取一个
		for (int i = 1; i <= 3; i++) {
			if (finghtingInfos.get(i) == null) {
				ainfo.setIndex(i);
				finghtingInfos.put(i, ainfo);
				break;
			}
		}
		sendSingleAvatarInfo(ainfo);
	}

	// 发送所有分身信息
	public void sendAllAvatarInfos() {
		AvatarBeanListMsg.Builder builder = AvatarBeanListMsg.newBuilder();
		for (AvatarInfo info : avatarInfos.values()) {
			AvatarBeanMsg.Builder msg = AvatarBeanMsg.newBuilder();
			info.writeProto(msg);
			builder.addAvatarListInfos(msg);
		}
		PBMessage message = MessageUtil.buildMessage(Protocol.U_TOAL_AVARTAR_INFOS, builder);
		player.sendPbMessage(message);
		writeAvatarMsg2Scene();
	}

	// 发送某个分身的信息
	public void sendSingleAvatarInfo(AvatarInfo ainfo) {
		AvatarBeanMsg.Builder msg = AvatarBeanMsg.newBuilder();
		ainfo.writeProto(msg);
		PBMessage message = MessageUtil.buildMessage(Protocol.U_SINGLE_AVARTAR_INFO, msg);
		player.sendPbMessage(message);
		writeAvatarMsg2Scene();
	}

	// 分身系统添加属性
	public void computeProperty(BaseProperty avatarData, BaseProperty avatarPer) {
		for (AvatarInfo ainfo : avatarInfos.values()) {
			writeProperty(ainfo, avatarData);
		}
	}

	private void writeProperty(AvatarInfo ainfo, BaseProperty avatarData) {
		// 升级模板加成
		AvatarUpGradeTemplate upgTemp = AvatarTempManager.getAvatarUpGradeTemplate(ainfo.getTempId(), ainfo.getGrade());
		if (upgTemp != null) {
			avatarData.addBlood(upgTemp.getBlood());
			avatarData.addSoul(upgTemp.getSoul());
			avatarData.addAttack(upgTemp.getAttack());
			avatarData.addDefence(upgTemp.getDefence());
			avatarData.addAccurate(upgTemp.getAccurate());
			avatarData.addDodge(upgTemp.getDodge());
			avatarData.addCrit(upgTemp.getCrit());
			avatarData.addCritDefence(upgTemp.getCritDefence());
		}
		// 升星属性加成
		AvatarStarTemplate starTemp = AvatarTempManager.getAvatarStarTemplate(ainfo.getTempId(), ainfo.getStar());
		if (starTemp != null) {
			avatarData.addBlood(starTemp.getBlood());
			avatarData.addSoul(starTemp.getSoul());
			avatarData.addAttack(starTemp.getAttack());
			avatarData.addDefence(starTemp.getDefence());
			avatarData.addAccurate(starTemp.getAccurate());
			avatarData.addDodge(starTemp.getDodge());
			avatarData.addCrit(starTemp.getCrit());
			avatarData.addCritDefence(starTemp.getCritDefence());
		}
		// 默契加成
		AvatarCorrespondTemplateInfo actemp = AvatarTempManager.getAvatarCorrespondTemplateInfo(ainfo.getTempId(), ainfo.getCorrespond());
		if (actemp != null) {
			avatarData.addBlood(actemp.getBlood());
			avatarData.addSoul(actemp.getSoul());
			avatarData.addAttack(actemp.getAttack());
			avatarData.addDefence(actemp.getDefence());
			avatarData.addAccurate(actemp.getAccurate());
			avatarData.addDodge(actemp.getDodge());
			avatarData.addCrit(actemp.getCrit());
			avatarData.addCritDefence(actemp.getCritDefence());
		}
	}

	// 更新属性
	public void updataProperty() {
		if (player.getAvatarInventory() != null) {
			BaseProperty avatarData = new BaseProperty();
			BaseProperty avatarPer = new BaseProperty();

			computeProperty(avatarData, avatarPer);
			player.getArmyInventory().getHero().addSoul(avatarData, avatarPer);
			player.getArmyInventory().updateProperty();
		}
	}

	// 获取几个分身信息
	public void writeAvatarMsg2Scene() {
		if (finghtingInfos == null || finghtingInfos.size() == 0) {
			return;
		}
		RobotInfoListMsg.Builder builder = RobotInfoListMsg.newBuilder();
		for (AvatarInfo ainfo : finghtingInfos.values()) {
			builder.addRobotDatas(writeProto(ainfo));
		}
		PBMessage message = MessageUtil.buildMessage(Protocol.S_SYNC_AVATAR_DATA, builder);
		player.sendPbMessage(message);
	}

	// 写入PB
	private RobotInfoMsg writeProto(AvatarInfo ainfo) {
		// 机器人信息
		RobotInfoMsg.Builder builder = RobotInfoMsg.newBuilder();

		BaseProperty avatarData = new BaseProperty();
		writeProperty(ainfo, avatarData);

		PlayerInfoMsg.Builder simpleInfo = PlayerInfoMsg.newBuilder();
		simpleInfo.setPlayerId(ainfo.getPlayerId());
		simpleInfo.setJob(ainfo.getTempId());
		simpleInfo.setLevel(ainfo.getGrade());
		simpleInfo.setSkinId(ainfo.getTempId());
		simpleInfo.setMagicWeaponId(ainfo.getCorrespond());
		simpleInfo.setFashionId(ainfo.getStar());

		builder.setSimpInfo(simpleInfo);

		PropertyListMsg.Builder propertyMsgs = PropertyListMsg.newBuilder();
		avatarData.writeProto(propertyMsgs);

		// 注入分身默契等级
		PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
		proMsg.setType(EnumAttr.AVTAR_CORRESPOND.getValue());
		proMsg.setTotalPoint(ainfo.getCorrespond());
		propertyMsgs.addPropertys(proMsg);

		builder.setPropertis(propertyMsgs);

		if (ainfo.getSkillId() != 0) {
			builder.addBattleSkills(ainfo.getSkillId());
		}
		if (ainfo.getSkillStrengthen1() != 0) {
			builder.addBattleSkills(ainfo.getSkillStrengthen1());
		}
		if (ainfo.getSkillStrengthen2() != 0) {
			builder.addBattleSkills(ainfo.getSkillStrengthen2());
		}
		if (ainfo.getSkillStrengthen3() != 0) {
			builder.addBattleSkills(ainfo.getSkillStrengthen3());
		}
		if (ainfo.getSkillStrengthen4() != 0) {
			builder.addBattleSkills(ainfo.getSkillStrengthen4());
		}
		if (ainfo.getSkillStrengthen5() != 0) {
			builder.addBattleSkills(ainfo.getSkillStrengthen5());
		}

		return builder.build();
	}

	/** 是否处于分身副本 */
	public boolean inAvatarCampaign() {
		int campaignId = player.getCurCampaign();
		if (campaignId == 0) {
			return false;
		}
		CampaignTemplateInfo temp = CampaignTempMgr.getTempInfo(campaignId);
		if (temp == null || temp.getType() != CampaignConstant.CampaignType.AVATAR) {
			return false;
		}
		return true;
	}

	// 分身副本挑战奖励
	public boolean challageReward(int campaignId, int rewardCount) {
		AvatarCampaignRewardListMsg.Builder listBuilder = AvatarCampaignRewardListMsg.newBuilder();
		listBuilder.setCampaignId(campaignId);
		for (AvatarInfo avatar : finghtingInfos.values()) {
			AvatarCampaignRewardMsg.Builder rewards = AvatarCampaignRewardMsg.newBuilder();
			rewards.setAvatarId(avatar.getTempId());
			for (int i = 1; i <= rewardCount; i++) {
				SingleReardInfoMsg.Builder reward = SingleReardInfoMsg.newBuilder();
				reward.setIndex(i);
				// 1 随机是否能获得奖励
				if (!canGet(campaignId, avatar)) {
					reward.setType(REWARD_NOTHING);
					rewards.addRewards(reward);
					continue;
				}
				// 2随机获奖类型
				int type = getRewardType(campaignId, avatar);
				// 3奖励生效
				if (type == REWARD_GREAD_UP) {
					int newGreade = avatar.getGrade() + 1;
					if (AvatarTempManager.getAvatarUpGradeTemplate(avatar.getTempId(), newGreade) != null) {
						avatar.setGrade(newGreade);
					}
					type = REWARD_ITEM;
				}
				if (type == REWARD_SKILL_UP) {
					
				}
				if (type == REWARD_SKILLSTRENTHEN_UP) {

				}
				if (type == REWARD_ITEM) {

				}

			}
		}

		return false;
	}

	private boolean canGet(int campaignId, AvatarInfo avatar) {
		return true;
	}

	private int getRewardType(int campaignId, AvatarInfo avatar) {
		return ThreadSafeRandom.instance.next(4) + 1;
	}

	private int findNextGreadSkill(int skillIds) {
		return 0;
	}
}
