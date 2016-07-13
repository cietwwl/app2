package com.chuangyou.common.util;

public class FormatUtil {

	private static final String usedata = "<a t='1' id='%s' name='%s' consortiaId='%s' serverName='%s' grades='%s' />";
	private static final String stardata = "<a t='3' id='%s' name='%s' grades='%s'/>";
	private static final String itemdata = "<a t='2' id='%s' name='%s' bind='%s' join='%s,%s,%s,%s' skill='%s,%s,%s,%s,%s' tempid='%s' grade='%s' mouldGrade='%s'/>";
	private static final String roomData = "<a t='6' id='%s' name='%s' campaignId='%s' islock='%s' position='%s' roomType='%s' />";
	private static final String vipData = "<a t='7' name='%s' />";
	private static final String imageData = "<a t='8' name='%s' sontype='%s'/>";
	private static final String appellData = "<a t='10' name='%s' appellId='%s' />";
	private static final String roseData = "<a t='11' />";
	private static final String treasureMapData = "<a t='12' userId='%s' mapId='%s' posX='%s' posY='%s' />";
	private static final String marryData = "<a t='13' userIdA='%s' userIdB='%s' />";
	private static final String fashionApp = "<a t='14' tempid='%s' name='%s'/>";
	private static final String reinforeData = "<a t='15' userId='%s' mapId='%s' posX='%s' posY='%s' />";
	private static final String newyearcardData = "<a t='16' senderId='%s' receiverId='%s' senderName='%s' receiverName='%s' count='%s' itemId='%s' sgp='%s' rgp='%s'/>";

	public static String getUserFormat() {
		return usedata;
	}

	public static String getRoseXml() {
		return String.format(roseData);
	}

	public static String getUserXML(int userid, String name, int consortiaId, int grade) {
		return String.format(usedata, userid, name, consortiaId, "", grade);
	}

	public static String getUserXML(int userid, String name, int consortiaId, String serverName, int grade) {
		return String.format(usedata, userid, name, consortiaId, serverName, grade);
	}

	public static String getFirstPlayerXML(int userid, String name, int consortiaId, int sonType, int grade) {
		return String.format(imageData, "", sonType) + String.format(usedata, userid, name, consortiaId, "", grade);
	}

	public static String getAppellXML(String appellName, int id) {
		return String.format(appellData, appellName, id);
	}

	public static String getStarFormat() {
		return stardata;
	}

	public static String getStarXML(int id, String name, int grades) {
		return String.format(stardata, id, name, grades);
	}

	public static String getItemFormat() {
		return itemdata;
	}

	public static String getRoomFormat(int roomNo, String campaignName, int campaignId, int isLock, int position) {
		return String.format(roomData, roomNo, campaignName, campaignId, isLock, position, 0);
	}

	public static String getMarRoomFormat(String userKey, String campaignName, int campaignId, int isLock, String password, int roomType) {
		return String.format(roomData, userKey, campaignName, campaignId, isLock, password, roomType);
	}

	public static String getOpenVipTipsFormat(String vipTips) {
		return String.format(vipData, vipTips);
	}

	public static String getOpenVipTipsFormat() {
		String tips = LanguageSet.getResource("CastleServer.PlayerVip.OpenLink");
		return String.format(vipData, tips);
	}

	public static String getTreasureMapFormat(int userId, int mapId, int posX, int posY) {
		return String.format(treasureMapData, userId, mapId, posX, posY);
	}

	public static String getMarryFormat(int userIdA, int userIdB) {
		return String.format(marryData, userIdA, userIdB);
	}

	public static String getFashionAppFormat(int tempId, String name) {
		return String.format(fashionApp, tempId, name);
	}

	public static String getReinforeFormat(int userId, int mapId, int posX, int posY) {
		return String.format(reinforeData, userId, mapId, posX, posY);
	}

	public static String newYearCardFormat(int senderId, int receiverId, String senderName, String receiverName, int count, int itemId, int sgp, int rgp) {
		return String.format(newyearcardData, senderId, receiverId, senderName, receiverName, count, itemId, sgp, rgp);
	}
}
