package com.chuangyou.xianni.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.xianni.player.GamePlayer;

public class MapProxy {
	private int						mapId;		// 地图唯一ID
	private int						mapTempId;	// 地图唯一ID
	private String					mapName;	// 地图名称
	private Map<Long, GamePlayer>	playersMap;	// 玩家集

	public MapProxy(int mapId, int mapTempId) {
		playersMap = new ConcurrentHashMap<Long, GamePlayer>();
	}

	/** 进入地图 */
	public void enterMap(GamePlayer player) {
		playersMap.put(player.getPlayerId(), player);
	}

	/** 退出地图 */
	public void outMap(GamePlayer player) {
		playersMap.remove(player.getPlayerId());
	}

	/** 副本结束 */
	public void over() {

	}
	
	/**副本是否打开状态*/
	public boolean isOpen() {
		return true;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public int getMapTempId() {
		return mapTempId;
	}

	public void setMapTempId(int mapTempId) {
		this.mapTempId = mapTempId;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

}
