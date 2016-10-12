package com.chuangyou.xianni.artifact.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.artifact.ArtifactDataProto.ArtifactDataMsg;
import com.chuangyou.common.protobuf.pb.artifact.ArtifactReqProto.ArtifactReqMsg;
import com.chuangyou.common.protobuf.pb.artifact.ArtifactRespProto.ArtifactRespMsg;
import com.chuangyou.common.util.AttributeUtil;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.artifact.ArtifactInventory;
import com.chuangyou.xianni.artifact.ArtifactOperateAction;
import com.chuangyou.xianni.artifact.template.ArtifactTemplateMgr;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.artifact.ArtifactGradeupCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactInfo;
import com.chuangyou.xianni.entity.artifact.ArtifactInfoCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactJewelLevelCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactJewelSuitCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactLevelupCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactSuitCfg;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.state.event.ArtifactStateEvent;

public class ArtifactManager {

	/**
	 * 请求所有神器数据
	 * @param player
	 * @param req
	 * @param protocol
	 */
	public static void requestAllArtifact(GamePlayer player, ArtifactReqMsg req, short protocol){
		ArtifactInventory artifactInventory = player.getArtifactInventory();
		if(artifactInventory == null) return;
		Map<Integer, ArtifactInfo> artifactMap = artifactInventory.getArtifactMap();
		
		ArtifactDataMsg.Builder dataMsg = ArtifactDataMsg.newBuilder();
		for(ArtifactInfo info: artifactMap.values()){
			ArtifactInfoCfg cfg = ArtifactTemplateMgr.getArtifactCfg(info.getArtifactId());
			dataMsg.addArtifactInfo(info.writeProto(cfg));
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_ARTIFACT_DATA, dataMsg);
		player.sendPbMessage(pkg);
		
		ArtifactRespMsg.Builder respMsg = ArtifactRespMsg.newBuilder();
		respMsg.setAction(ArtifactOperateAction.REQUEST_ARTIFACTLIST);
		respMsg.setResult(0);
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_ARTIFACT_RESP, respMsg);
		player.sendPbMessage(respPkg);
	}
	
	/**
	 * 神器升级
	 * @param player
	 * @param req
	 * @param protocol
	 */
	public static void artifactLevelup(GamePlayer player, ArtifactReqMsg req, short protocol){
		ArtifactInventory artifactInventory = player.getArtifactInventory();
		if(artifactInventory == null) return;
		ArtifactInfo info = artifactInventory.getArtifactMap().get(req.getArtifactId());
		
		if(info == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ARTIFACT_NOT_EXIST, protocol, "神器不存在");
			return;
		}
		
		ArtifactLevelupCfg nextLevelCfg = ArtifactTemplateMgr.getLevelUpCfg(req.getArtifactId(), info.getLevel() + 1);
		ArtifactLevelupCfg levelCfg = ArtifactTemplateMgr.getLevelUpCfg(req.getArtifactId(), info.getLevel());
		if(levelCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "神器升级配置错误");
			return;
		}
		if(nextLevelCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ARTIFACT_MAX_LEVEL, protocol, "神器已达到最高等级");
			return;
		}
		
		int hasItemNum = player.getBagInventory().getItemCount(levelCfg.getItem());
		if(hasItemNum < levelCfg.getCount()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Item_UnEnough, protocol, "神器升级材料不足");
			return;
		}
		
		if(!player.getBagInventory().removeItem(levelCfg.getItem(), levelCfg.getCount(), ItemRemoveType.ARTIFACT_LEVELUP)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "消耗材料失败");
			return;
		}
		
		info.setLevel(info.getLevel() + 1);
		
		player.notifyListeners(new ArtifactStateEvent(ArtifactManager.class, 1, info.getArtifactId(), info.getLevel(),EventNameType.ARTIFACT));
		
		ArtifactDataMsg.Builder dataMsg = ArtifactDataMsg.newBuilder();
		ArtifactInfoCfg cfg = ArtifactTemplateMgr.getArtifactCfg(info.getArtifactId());
		dataMsg.addArtifactInfo(info.writeProto(cfg));
		PBMessage dataPkg = MessageUtil.buildMessage(Protocol.U_ARTIFACT_DATA, dataMsg);
		player.sendPbMessage(dataPkg);
		
		ArtifactRespMsg.Builder respMsg = ArtifactRespMsg.newBuilder();
		respMsg.setAction(ArtifactOperateAction.ARTIFACT_LEVELUP);
		respMsg.setResult(0);
		respMsg.setArtifactId(info.getArtifactId());
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_ARTIFACT_RESP, respMsg);
		player.sendPbMessage(respPkg);
		
		//更新人物属性
		player.getArtifactInventory().updateProperty();
	}
	
	/**
	 * 神器升星
	 * @param player
	 * @param req
	 * @param protocol
	 */
	public static void artifactGradeup(GamePlayer player, ArtifactReqMsg req, short protocol){
		ArtifactInventory artifactInventory = player.getArtifactInventory();
		if(artifactInventory == null) return;
		ArtifactInfo info = artifactInventory.getArtifactMap().get(req.getArtifactId());
		
		if(info == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ARTIFACT_NOT_EXIST, protocol, "神器不存在");
			return;
		}
		if(info.getLevel() <= 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ARTIFACT_NOT_ACTIVATE, protocol, "神器未激活");
			return;
		}
		
		ArtifactGradeupCfg gradeCfg = ArtifactTemplateMgr.getGradeUpCfg(info.getArtifactId(), info.getStarLevel(), info.getStar());
		if(gradeCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "神器升星配置错误");
			return;
		}
		
		ArtifactGradeupCfg nextStarCfg = ArtifactTemplateMgr.getGradeUpCfg(info.getArtifactId(), info.getStarLevel(), info.getStar() + 1);
		ArtifactGradeupCfg nextGradeCfg = ArtifactTemplateMgr.getGradeUpCfg(info.getArtifactId(), info.getStarLevel() + 1, 0);
		if(nextStarCfg == null && nextGradeCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ARTIFACT_MAX_LEVEL, protocol, "神器已达到最高星级");
			return;
		}
		
		int hasItemNum = player.getBagInventory().getItemCount(gradeCfg.getCostItem());
		if(hasItemNum < gradeCfg.getCostNum()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Item_UnEnough, protocol, "神器升级材料不足");
			return;
		}
		
		if(!player.getBagInventory().removeItem(gradeCfg.getCostItem(), gradeCfg.getCostNum(), ItemRemoveType.ARTIFACT_GRADEUP)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "消耗材料失败");
			return;
		}
		
		boolean isSuccess = false;
		ThreadSafeRandom random = new ThreadSafeRandom();
		//使用升星符必定成功
		if(req.getParam1() == 1){
			isSuccess = true;
			
			//扣除升星符
			int hasAmuletNum = player.getBagInventory().getItemCount(gradeCfg.getStarAmuletId());
			if(hasAmuletNum < gradeCfg.getStarAmuletNum()){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ARTIFACT_GRADEUP_STARAMULET, protocol, "神器升星符不足");
				return;
			}
			if(!player.getBagInventory().removeItem(gradeCfg.getStarAmuletId(), gradeCfg.getStarAmuletNum(), ItemRemoveType.ARTIFACT_GRADEUP)){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "扣除升星符失败");
				return;
			}
		}else{
			//幸运值满，必定成功
			if(info.getStarBless() >= gradeCfg.getBlessMax()){
				isSuccess = true;
			}else{
				isSuccess = random.isSuccessful(gradeCfg.getRate(), 10000);
			}
		}
		
		int blessChange = 0;
		if(isSuccess == true){
			//升星成功时判断如果是满星，则升到下一星级
			if(nextStarCfg == null){
				info.setStarLevel(info.getStarLevel() + 1);
				info.setStar(0);
				info.setStarBless(0);
			}else{
				info.setStar(info.getStar() + 1);
				info.setStarBless(0);
			}
		}else{
			boolean isAddBless = random.isSuccessful(gradeCfg.getBlessrate(), 10000);
			if(isAddBless == true){
				info.setStarBless(info.getStarBless() + 1);
				blessChange = 1;
			}else{
				if(info.getStarBless() > 0){
					info.setStarBless(info.getStarBless() - 1);
					blessChange = -1;
				}
			}
		}
		
		ArtifactDataMsg.Builder dataMsg = ArtifactDataMsg.newBuilder();
		ArtifactInfoCfg cfg = ArtifactTemplateMgr.getArtifactCfg(info.getArtifactId());
		dataMsg.addArtifactInfo(info.writeProto(cfg));
		PBMessage dataPkg = MessageUtil.buildMessage(Protocol.U_ARTIFACT_DATA, dataMsg);
		player.sendPbMessage(dataPkg);
		
		ArtifactRespMsg.Builder respMsg = ArtifactRespMsg.newBuilder();
		respMsg.setAction(ArtifactOperateAction.ARTIFACT_GRADEUP);
		if(isSuccess == true){
			respMsg.setResult(0);
		}else{
			respMsg.setResult(1);
			respMsg.setParam1(blessChange);
		}
		respMsg.setArtifactId(info.getArtifactId());
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_ARTIFACT_RESP, respMsg);
		player.sendPbMessage(respPkg);
		
		//如果升星成功，更新人物属性
		if(isSuccess == true){
			player.getArtifactInventory().updateProperty();
			player.notifyListeners(new ArtifactStateEvent(ArtifactManager.class,3, info.getArtifactId(), info.getStar(),EventNameType.ARTIFACT));
		}
	}
	
	/**
	 * 神器宝石激活
	 * @param player
	 * @param req
	 * @param protocol
	 */
	public static void artifactStoneActivate(GamePlayer player, ArtifactReqMsg req, short protocol){
		ArtifactInventory artifactInventory = player.getArtifactInventory();
		if(artifactInventory == null) return;
		ArtifactInfo info = artifactInventory.getArtifactMap().get(req.getArtifactId());
		
		if(info == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ARTIFACT_NOT_EXIST, protocol, "神器不存在");
			return;
		}
		if(info.getLevel() <= 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ARTIFACT_NOT_ACTIVATE, protocol, "神器未激活");
			return;
		}
		
		if(info.getStoneLevel(req.getStonePos()) < 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "宝石索引错误");
			return;
		}
		
		if(info.getStoneLevel(req.getStonePos()) > 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ARTIFACT_STONE_ALREADY_ACTIVATE, protocol, "神器宝石已激活");
			return;
		}
		
		ArtifactInfoCfg cfg = ArtifactTemplateMgr.getArtifactCfg(info.getArtifactId());
		if(cfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "神器配置错误");
			return;
		}
		if(player.getLevel() < cfg.getLevel(req.getStonePos())){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.RoleLevel_UnEnough, protocol, "人物等级不足");
			return;
		}
		
		ArtifactJewelLevelCfg stoneLevelCfg = ArtifactTemplateMgr.getJewelLevelUpCfg(cfg.getJewel(req.getStonePos()), info.getStoneLevel(req.getStonePos()));
		if(stoneLevelCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "神器等级配置错误");
			return;
		}
		int hasItemNum = player.getBagInventory().getItemCount(stoneLevelCfg.getItem());
		if(hasItemNum < stoneLevelCfg.getCount()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Item_UnEnough, protocol, "材料不足");
			return;
		}
		
		if(!player.getBagInventory().removeItem(stoneLevelCfg.getItem(), stoneLevelCfg.getCount(), ItemRemoveType.ARTIFACT_STONE_ACTIVATE)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "扣除神器宝石激活材料失败");
			return;
		}
		
		info.setStoneLevel(req.getStonePos(), 1);
		
		ArtifactDataMsg.Builder dataMsg = ArtifactDataMsg.newBuilder();
		dataMsg.addArtifactInfo(info.writeProto(cfg));
		PBMessage dataPkg = MessageUtil.buildMessage(Protocol.U_ARTIFACT_DATA, dataMsg);
		player.sendPbMessage(dataPkg);
		
		ArtifactRespMsg.Builder respMsg = ArtifactRespMsg.newBuilder();
		respMsg.setAction(ArtifactOperateAction.STONE_ACTIVATE);
		respMsg.setResult(0);
		respMsg.setArtifactId(info.getArtifactId());
		respMsg.setStonePos(req.getStonePos());
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_ARTIFACT_RESP, respMsg);
		player.sendPbMessage(respPkg);
		
		player.getArtifactInventory().updateProperty();
	}
	
	/**
	 * 神器宝石升级
	 * @param player
	 * @param req
	 * @param protocol
	 */
	public static void artifactStoneLevelup(GamePlayer player, ArtifactReqMsg req, short protocol){
		ArtifactInventory artifactInventory = player.getArtifactInventory();
		if(artifactInventory == null) return;
		ArtifactInfo info = artifactInventory.getArtifactMap().get(req.getArtifactId());
		
		if(info == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ARTIFACT_NOT_EXIST, protocol, "神器不存在");
			return;
		}
		if(info.getLevel() <= 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ARTIFACT_NOT_ACTIVATE, protocol, "神器未激活");
			return;
		}
		
		if(info.getStoneLevel(req.getStonePos()) < 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "宝石索引错误");
			return;
		}
		
		if(info.getStoneLevel(req.getStonePos()) <= 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ARTIFACT_STONE_NOT_ACTIVATE, protocol, "神器宝石未激活");
			return;
		}
		ArtifactInfoCfg cfg = ArtifactTemplateMgr.getArtifactCfg(info.getArtifactId());
		if(cfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "神器配置错误");
			return;
		}
		ArtifactJewelLevelCfg stoneLevelCfg = ArtifactTemplateMgr.getJewelLevelUpCfg(cfg.getJewel(req.getStonePos()), info.getStoneLevel(req.getStonePos()));
		if(stoneLevelCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "神器等级配置错误");
			return;
		}
		ArtifactJewelLevelCfg nextLevelCfg = ArtifactTemplateMgr.getJewelLevelUpCfg(cfg.getJewel(req.getStonePos()), info.getStoneLevel(req.getStonePos()) + 1);
		if(stoneLevelCfg != null && nextLevelCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ARTIFACT_STONE_LEVEL_MAX, protocol, "神器宝石已达到最高等级");
			return;
		}
		
		
		int needItemId = 0;
		int needItemNum = 0;
		int addExp = 0;
		//普通升级
		if(req.getParam1() == 0){
			needItemId = stoneLevelCfg.getItem();
			needItemNum = stoneLevelCfg.getCount();
			addExp = stoneLevelCfg.getExp();
		}else if(req.getParam1() == 1){//使用直升
			needItemId = stoneLevelCfg.getLevelItem();
			needItemNum = stoneLevelCfg.getLevelCount();
			addExp = stoneLevelCfg.getMaxExp();
		}else if(req.getParam1() == 2){//普通物品升级一键升级
			int stoneLevel = info.getStoneLevel(req.getStonePos());
			long needExp = ArtifactTemplateMgr.getJewelLevelNeedExp(cfg.getJewel(req.getStonePos()), stoneLevel + 1) - info.getStoneTotalExp(req.getStonePos());
			
			//升级需要的物品组数(这里将宝石升级表内的材料视为一组，因为个数可配)
			int needItemSet = (int)Math.ceil(needExp/stoneLevelCfg.getExp());
			
			needItemId = stoneLevelCfg.getItem();
			
			//算出需要物品的数量(组数 * 每组配置的数量)
			needItemNum = needItemSet * stoneLevelCfg.getCount();
			
			//如果背包里的材料数量少于升级所需，则算出背包里有多少组材料，并消耗掉
			int hasNeedItemCount = player.getBagInventory().getItemCount(needItemId);
			if(needItemNum > hasNeedItemCount){
				needItemSet = hasNeedItemCount/stoneLevelCfg.getCount();
				needItemNum = needItemSet * stoneLevelCfg.getCount();
			}
			
			addExp = needItemSet * stoneLevelCfg.getExp();
		}
		//判断并扣除材料
		int hasItemNum = player.getBagInventory().getItemCount(needItemId);
		if(hasItemNum < needItemNum){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Item_UnEnough, protocol, "材料不足");
			return;
		}
		if(!player.getBagInventory().removeItem(needItemId, needItemNum, ItemRemoveType.ARTIFACT_STONE_LEVELUP)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, protocol, "扣除神器宝石升级材料失败");
			return;
		}
		
		//先增加经验，然后判断是否升级了
		info.setStoneTotalExp(req.getStonePos(), info.getStoneTotalExp(req.getStonePos()) + addExp);
		info.setStoneExp(req.getStonePos(), info.getStoneExp(req.getStonePos()) + addExp);
		
		boolean hasLevelUp = false;
		if(info.getStoneExp(req.getStonePos()) >= stoneLevelCfg.getMaxExp()){
			int level = ArtifactTemplateMgr.getJewelLevel(cfg.getJewel(req.getStonePos()), info.getStoneTotalExp(req.getStonePos()));
			info.setStoneLevel(req.getStonePos(), level);
			
			long needTotalExp = ArtifactTemplateMgr.getJewelLevelNeedExp(cfg.getJewel(req.getStonePos()), info.getStoneLevel(req.getStonePos()));
			info.setStoneExp(req.getStonePos(), info.getStoneTotalExp(req.getStonePos()) - needTotalExp);
			
			hasLevelUp = true;
		}
		
		//更新数据
		ArtifactDataMsg.Builder dataMsg = ArtifactDataMsg.newBuilder();
		dataMsg.addArtifactInfo(info.writeProto(cfg));
		PBMessage dataPkg = MessageUtil.buildMessage(Protocol.U_ARTIFACT_DATA, dataMsg);
		player.sendPbMessage(dataPkg);
		//请求反馈
		ArtifactRespMsg.Builder respMsg = ArtifactRespMsg.newBuilder();
		respMsg.setAction(ArtifactOperateAction.STONE_LEVELUP);
		respMsg.setResult(0);
		respMsg.setArtifactId(info.getArtifactId());
		respMsg.setStonePos(req.getStonePos());
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_ARTIFACT_RESP, respMsg);
		player.sendPbMessage(respPkg);
		
		//升级后更新人物属性
		if(hasLevelUp){
			player.getArtifactInventory().updateProperty();
			player.notifyListeners(new ArtifactStateEvent(ArtifactManager.class, 5, info.getArtifactId(), info.getStoneMaxLevel(),EventNameType.ARTIFACT));
		}
	}
	
	public static Map<Integer, Integer> computeArtifactAtt(GamePlayer player){
		Map<Integer, Integer> attMap = new HashMap<>();
		
		Map<Integer, ArtifactInfo> artifactInfoMap = player.getArtifactInventory().getArtifactMap();
		
		int activateNum = 0;
		int stoneTotalLevel = 0;
		//神器总属性
		Map<Integer, Integer> artifactTotalAttMap = new HashMap<>();
		//宝石总属性
		Map<Integer, Integer> stoneTotalAttMap = new HashMap<>();
		for(ArtifactInfo info:artifactInfoMap.values()){
			Map<Integer, Integer> artifactAtts = new HashMap<>();
			
			//神器等级属性
			if(info.getLevel() > 0){
				ArtifactLevelupCfg levelCfg = ArtifactTemplateMgr.getLevelUpCfg(info.getArtifactId(), info.getLevel());
				AttributeUtil.putAttListToMap(artifactAtts, levelCfg.getAttList());
				
				activateNum += 1;
			}
			
			//神器星级加成
			if(info.getStarLevel() > 0 || info.getStar() > 0){
				ArtifactGradeupCfg gradeCfg = ArtifactTemplateMgr.getGradeUpCfg(info.getArtifactId(), info.getStarLevel(), info.getStar());
				
				for(int attType: artifactAtts.keySet()){
					int attValue = artifactAtts.get(attType) * (1 + gradeCfg.getAttr()/10000);
					artifactAtts.put(attType, attValue);
				}
			}
			//单件神器属性放入神器总属性中
			AttributeUtil.putAttToMap(artifactTotalAttMap, artifactAtts);
			
			//单件神器中宝石属性统计
			Map<Integer, Integer> stoneAtts = new HashMap<>();
			ArtifactInfoCfg cfg = ArtifactTemplateMgr.getArtifactCfg(info.getArtifactId());
			for(int i = 1; i <= 4; i++){
				int stoneId = cfg.getJewel(i);
				int stoneLevel = info.getStoneLevel(i);
				ArtifactJewelLevelCfg stoneLevelCfg = ArtifactTemplateMgr.getJewelLevelUpCfg(stoneId, stoneLevel);
				AttributeUtil.putAttNumToMap(stoneAtts, stoneLevelCfg.getAttr());
				
				stoneTotalLevel += stoneLevel;
			}
			//单件神器中的宝石属性放入宝石总属性
			AttributeUtil.putAttToMap(stoneTotalAttMap, stoneAtts);
		}
		
		//计算神器套装加成
		List<ArtifactSuitCfg> suitList = ArtifactTemplateMgr.getSuitList();
		int addPer = 0;
		for(ArtifactSuitCfg suitCfg: suitList){
			if(activateNum >= suitCfg.getSuitid()){
				addPer += suitCfg.getAtt();
			}
		}
		for(int attType:artifactTotalAttMap.keySet()){
			int attValue = artifactTotalAttMap.get(attType) * (1 + addPer/10000);
			artifactTotalAttMap.put(attType, attValue);
		}
		
		//计算宝石套装加成
		List<ArtifactJewelSuitCfg> stoneSuitList = ArtifactTemplateMgr.getJewelSuitList();
		for(ArtifactJewelSuitCfg stoneSuitCfg:stoneSuitList){
			if(stoneTotalLevel >= stoneSuitCfg.getLevel()){
				AttributeUtil.putAttListToMap(stoneTotalAttMap, stoneSuitCfg.getAttList());
			}
		}
		
		//神器总属性和宝石总属性都放入属性最终属性列表
		AttributeUtil.putAttToMap(attMap, artifactTotalAttMap);
		AttributeUtil.putAttToMap(attMap, stoneTotalAttMap);
		
		return attMap;
	}
}
