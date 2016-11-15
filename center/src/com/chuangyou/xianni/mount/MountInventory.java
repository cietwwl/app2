package com.chuangyou.xianni.mount;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.army.PropertyListMsgProto.PropertyListMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg;
import com.chuangyou.common.protobuf.pb.player.OtherMountProto.OtherMountMsg;
import com.chuangyou.xianni.army.Hero;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.mount.MountEquipInfo;
import com.chuangyou.xianni.entity.mount.MountGradeCfg;
import com.chuangyou.xianni.entity.mount.MountInfo;
import com.chuangyou.xianni.entity.mount.MountSpecialGet;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.mount.manager.MountManager;
import com.chuangyou.xianni.mount.template.MountTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.sql.dao.DBManager;

public class MountInventory extends AbstractEvent implements IInventory {

	/**
	 * 玩家数据
	 */
	private GamePlayer						player;

	/**
	 * 玩家坐骑信息
	 */
	private MountInfo						mountInfo;

	/**
	 * 玩家坐骑装备信息
	 */
	private Map<Integer, MountEquipInfo>	mountEquipMap;

	/**
	 * 已获得的特殊坐骑
	 */
	private Map<Integer, MountSpecialGet>	mountSpecialMap;

	public MountInventory(GamePlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}

	/**
	 * 获取玩家坐骑信息
	 * 
	 * @return
	 */
	public MountInfo getMount() {
		if (this.mountInfo == null) {
			int initMountId = 0;
//			Map<Integer, MountGradeCfg> gradeCfgMap = MountTemplateMgr.getGradeTemps();
//			for (MountGradeCfg grade : gradeCfgMap.values()) {
//				if (grade.getGrade() == 1) {
//					initMountId = grade.getId();
//					break;
//				}
//			}
			mountInfo = new MountInfo(player.getPlayerId(), initMountId);
			mountInfo.setOp(Option.Insert);
		}
		return this.mountInfo;
	}

	/**
	 * 更新玩家坐骑信息
	 * 
	 * @param info
	 */
	public boolean updateMount(MountInfo info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		info.setOp(Option.Update);
		return true;
	}

	/**
	 * 获取玩家坐骑装备信息
	 * 
	 * @return
	 */
	public Map<Integer, MountEquipInfo> getMountEquip() {
		if (this.mountEquipMap == null)
			this.mountEquipMap = new HashMap<Integer, MountEquipInfo>();
		if (this.mountEquipMap.size() <= 0) {
			Set<Integer> idSet = MountTemplateMgr.getEquipTemps().keySet();
			for (int equipId : idSet) {
				MountEquipInfo info = new MountEquipInfo(player.getPlayerId(), equipId);
				info.setOp(Option.Insert);
				this.mountEquipMap.put(info.getEquipId(), info);
			}
		}
		return this.mountEquipMap;
	}

	/**
	 * 更新玩家装备信息
	 * 
	 * @param info
	 * @return
	 */
	public boolean updateMountEquip(MountEquipInfo info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		info.setOp(Option.Update);
		return true;
	}

	/**
	 * 获取玩家已经获得的特殊坐骑
	 * 
	 * @return
	 */
	public Map<Integer, MountSpecialGet> getMountSpecialMap() {
		if (this.mountSpecialMap == null)
			this.mountSpecialMap = new HashMap<Integer, MountSpecialGet>();
		return mountSpecialMap;
	}

	/**
	 * 玩家获得特殊坐骑
	 * 
	 * @param info
	 * @return
	 */
	public int addMountSpecial(MountSpecialGet info) {
		if (info.getPlayerId() != player.getPlayerId())
			return ErrorCode.UNKNOW_ERROR;

		MountGradeCfg mountCfg = MountTemplateMgr.getMountTemps().get(info.getMountId());
		int specialGrade = SystemConfigTemplateMgr.getIntValue("mount.grade.specialMount");
		if (mountCfg.getGrade() != specialGrade) {
			return MountSpecialGet.NOT_SPECIAL;
		}
		if (this.mountSpecialMap == null) {
			this.mountSpecialMap = new HashMap<Integer, MountSpecialGet>();
		}
		if (this.mountSpecialMap.get(info.getMountId()) != null) {
			return MountSpecialGet.ALREADY_ACTIVATED;
		}

		this.mountSpecialMap.put(info.getMountId(), info);
		info.setOp(Option.Insert);

		// 影响人物属性变更
		updataProperty();
		mountFight(info.getMountId(), false);

		return MountSpecialGet.ACTIVATE_SUCCESS;
	}
	
	/**
	 * 坐骑出战
	 * @param mountId
	 * @param sendErrorCode
	 */
	public void mountFight(int mountId, boolean sendErrorCode){
		MountInfo mount = getMount();
		if(mountId == mount.getMountId()){
			if(sendErrorCode == true){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.MOUNT_FIGHT_ALREADY, Protocol.C_MOUNT_FIGHT_CHOOSE);
			}
			return;
		}
		
		MountGradeCfg mountGrade = MountTemplateMgr.getMountTemps().get(mountId);
		
		int specialGrade = SystemConfigTemplateMgr.getIntValue("mount.grade.specialMount");
		
		if(specialGrade == mountGrade.getGrade()){
			MountSpecialGet mountGet = getMountSpecialMap().get(mountId);
			if(mountGet == null){
				if(sendErrorCode == true){
					ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Mount_Special_UnGet, Protocol.C_MOUNT_FIGHT_CHOOSE);
				}
				return;
			}
		}
		
		if(specialGrade != mountGrade.getGrade() && mountGrade.getGrade() > mount.getGrade()){
			if(sendErrorCode == true){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Mount_Grade_UnGet, Protocol.C_MOUNT_FIGHT_CHOOSE);
			}
			return;
		}
		
		mount.setMountId(mountId);
		updateMount(mount);
		
		MountFightChooseRespMsg.Builder msg = MountFightChooseRespMsg.newBuilder();
		msg.setMountId(mount.getMountId());
		
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MOUNT_FIGHT_CHOOSE, msg);
		player.sendPbMessage(p);
		
		player.getBasePlayer().updateMountId(mount.getMountId());
	}

	public boolean loadFromDataBase() {
		mountInfo = DBManager.getMountInfoDao().get(player.getPlayerId());
		mountEquipMap = DBManager.getMountEquipDao().getAll(player.getPlayerId());
		mountSpecialMap = DBManager.getMountSpecialDao().getAll(player.getPlayerId());
		return true;
	}

	public boolean unloadData() {
		player = null;

		mountInfo = null;
		if (mountEquipMap != null) {
			mountEquipMap.clear();
		}
		mountEquipMap = null;

		if (mountSpecialMap != null) {
			mountSpecialMap.clear();
		}
		mountSpecialMap = null;

		return true;
	}

	public boolean saveToDatabase() {
		boolean result = true;

		if (mountInfo != null) {
			short option = mountInfo.getOp();
			if (option == Option.Update) {
				result = DBManager.getMountInfoDao().update(mountInfo);
			} else if (option == Option.Insert) {
				result = DBManager.getMountInfoDao().add(mountInfo);
			}
		}

		if (mountEquipMap != null && mountEquipMap.size() > 0) {
			for (MountEquipInfo info : mountEquipMap.values()) {
				short option = info.getOp();
				if (option == Option.Update) {
					result = DBManager.getMountEquipDao().update(info);
				} else if (option == Option.Insert) {
					result = DBManager.getMountEquipDao().add(info);
				}
			}
		}

		if (mountSpecialMap != null && mountSpecialMap.size() > 0) {
			for (MountSpecialGet info : mountSpecialMap.values()) {
				short option = info.getOp();
				if (option == Option.Insert) {
					result = DBManager.getMountSpecialDao().add(info);
				} else if (option == Option.Delete) {
					result = DBManager.getMountSpecialDao().delete(info.getPlayerId(), info.getMountId());
				}
			}
		}

		return result;
	}

	/**
	 * 计算坐骑属性值
	 * 
	 * @param roleId
	 */
	public void computeMountAtt(BaseProperty mountData, BaseProperty mountPer) {
		Map<Integer, Integer> attMap = MountManager.computeMountAtt(player);
		for (int attType : attMap.keySet()) {
			SimpleProperty property = SkillUtil.readPro(attType, attMap.get(attType));
			if (property.isPre()) {
				SkillUtil.joinPro(mountPer, property.getType(), property.getValue());
			} else {
				SkillUtil.joinPro(mountData, property.getType(), property.getValue());
			}
		}
	}

	public void updataProperty() {
		if (player.getArmyInventory() != null) {
			BaseProperty skillData = new BaseProperty();
			BaseProperty skillPer = new BaseProperty();
			// 加入技能属性
			computeMountAtt(skillData, skillPer);
			player.getArmyInventory().getHero().addMount(skillData, skillPer);
			player.getArmyInventory().updateProperty();
		}
	}

	/** 写入其他用户查看信息 */
	public void writeInSimpOtherSnap(OtherMountMsg.Builder proto) {
		if (mountInfo == null) {
			return;
		}
		proto.setPlayerId(mountInfo.getPlayerId());
		proto.setLevel(mountInfo.getLevel());
		proto.setMountId(mountInfo.getMountId());
		proto.setLevel(mountInfo.getLevel());
		proto.setGrade(mountInfo.getGrade());
		BaseProperty skillData = new BaseProperty();
		BaseProperty skillPer = new BaseProperty();

		// 加入技能属性
		computeMountAtt(skillData, skillPer);
		Hero tempHero = new Hero(this.player);
		tempHero.addMount(skillData, skillPer);
		PropertyListMsg.Builder propertyMsgs = PropertyListMsg.newBuilder();
		tempHero.writeProto(propertyMsgs);

		PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
		proMsg.setType(EnumAttr.SPEED.getValue());
		proMsg.setTotalPoint(skillData.getSpeed());
		propertyMsgs.addPropertys(proMsg);

		proto.setPropertitys(propertyMsgs);
		tempHero = null;
	}
}
