package com.chuangyou.xianni.guild.action;

import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.common.protobuf.pb.shop.BuyGoodsRespProto.BuyGoodsRespMsg;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.CommonType;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.ShopMsgHelper;
import com.chuangyou.xianni.shop.template.ShopTemplateMgr;

public class GuildShopBuyAction extends GuildIsGuildMemberAction {

	private int goodsId;
	private int num;
	
	public GuildShopBuyAction(GamePlayer player, int goodsId, int num) {
		super(player, null);
		// TODO Auto-generated constructor stub
		this.goodsId = goodsId;
		this.num = num;
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub

		if(this.num <= 0){
			return;
		}
		ShopCfg cfg = ShopTemplateMgr.getShopCfg(goodsId);
		if(cfg.getShopid() == ShopCfg.SHOP_GUILD_PLAYER ){
			if(guild.getGuildInfo().getShopLevel() < cfg.getLevel()){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_SHOP_LEVEL_LIMIT, Protocol.C_REQ_BUYGOODS, "帮派商店等级不足");
				return;
			}
		}
		if(cfg.getShopid() == ShopCfg.SHOP_GUILD_SYSTEM){
			if(player.getBasePlayer().getPlayerInfo().getStateLv() < cfg.getLevel()){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.PLAYER_STATELV_UNENOUGH, Protocol.C_REQ_BUYGOODS, "玩家境界不足");
				return;
			}
		}
		
		//判断消耗资源
		int discount = getDiscount(cfg);
		long totalPrice = (long)Math.ceil(cfg.getPrice() * num * discount / 100);
		if(!checkMoneyType(cfg.getMoneyType(), totalPrice, guild, player)){
			return;
		}
		long totalPrice1 = (long)Math.ceil(cfg.getPrice1() * num * discount / 100);
		if(!checkMoneyType(cfg.getMoneyType1(), totalPrice1, guild, player)){
			return;
		}
		
		//扣除消耗资源
		if(!pay(cfg.getMoneyType(), totalPrice, guild, player) || !pay(cfg.getMoneyType1(), totalPrice1, guild, player)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_BUYGOODS, "未知错误");
			return;
		}
		
		boolean isBind = false;
		if(cfg.getBind() == 1){
			isBind = true;
		}
		player.getBagInventory().addItemInBagOrEmail(cfg.getItemType(), num, ItemAddType.GUILD_SHOP_BUY, isBind);
		
		//帮贡更新
		GuildRespMsg.Builder contrMsg = GuildRespMsg.newBuilder();
		contrMsg.setAction(GuildOperateAction.CONTRIBUTION_UPDATE);
		contrMsg.setParam1(memberInfo.getContributionTotal());
		contrMsg.setParam2(memberInfo.getContribution());
		contrMsg.setParam3(memberInfo.getConsumeSupply());
		PBMessage contrPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, contrMsg);
		player.sendPbMessage(contrPkg);
		
		//帮派物资更新
		GuildRespMsg.Builder supplyMsg = GuildRespMsg.newBuilder();
		supplyMsg.setAction(GuildOperateAction.GUILD_SUPPLY_UPDATE);
		supplyMsg.setParam1(guild.getSupply());
//		BroadcastUtil.sendBroadcastPacket(guild.getMemberIds(), Protocol.U_GUILD_ACTION_RESP, supplyMsg.build());
		PBMessage supplyPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, supplyMsg);
		player.sendPbMessage(supplyPkg);
		
		//回复购买结果
		BuyGoodsRespMsg.Builder msg = ShopMsgHelper.getBuyResult(player, cfg, true);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_BUYGOODS, msg);
		player.sendPbMessage(pkg);
	}
	
	/**
	 * 判断消耗是否足够
	 * @param moneyType
	 * @param totalPrice
	 * @param guild
	 * @param player
	 * @return
	 */
	private boolean checkMoneyType(int moneyType, long totalPrice, Guild guild, GamePlayer player){
		if(moneyType == CommonType.CurrencyItemType.GUILD_SUPPLY){
			if(guild.getSupply() < totalPrice){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_SUPPLY_UNENOUGH, Protocol.C_REQ_BUYGOODS, "数据错误--帮派物资不足");
				return false;
			}
		}else if(moneyType == CommonType.CurrencyItemType.GUILD_CONTRIBUTION){
			GuildMemberInfo member = guild.getMember(player.getPlayerId());
			if(member.getContribution() < totalPrice){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_CONTRIBUTION_UNENOUGH, Protocol.C_REQ_BUYGOODS, "数据错误--帮派贡献不足");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 支付
	 * @param moneyType
	 * @param totalPrice
	 * @param guild
	 * @param player
	 * @return
	 */
	private boolean pay(int moneyType, long totalPrice, Guild guild, GamePlayer player){
		if(moneyType == CommonType.CurrencyItemType.GUILD_SUPPLY){
			if(!guild.consumeSupply(totalPrice, player.getPlayerId()))
				return false;
		}else if(moneyType == CommonType.CurrencyItemType.GUILD_CONTRIBUTION){
			GuildMemberInfo member = guild.getMember(player.getPlayerId());
			if(!member.consumeContribution(totalPrice))
				return false;
		}
		return true;
	}
	
	/**
	 * 获取商品折扣
	 * @param cfg
	 * @return
	 */
	private int getDiscount(ShopCfg cfg) {
		if (cfg.getDiscountStart().equals("0") || cfg.getDiscountEnd().equals("0"))
			return cfg.getDiscount();
		if (TimeUtil.isInDate(System.currentTimeMillis(), TimeUtil.getDateByString(cfg.getDiscountStart(), 3), TimeUtil.getDateByString(cfg.getDiscountEnd(), 3))) {
			return cfg.getDiscount();
		} else {
			return 100;
		}
	}

}
