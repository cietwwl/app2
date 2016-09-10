package com.chuangyou.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 网关配置
 */
public class NetConfigSet {

	public static int							gatewayCount	= 0;
	public static int							crossCount		= 0;

	private static Map<Integer, NetConfigXml>	gatewayMap		= new HashMap<Integer, NetConfigXml>();
	private static Map<Integer, NetConfigXml>	centerMap		= new HashMap<Integer, NetConfigXml>();
	private static Map<Integer, NetConfigXml>	sceneMap		= new HashMap<Integer, NetConfigXml>();
	private static Map<Integer, NetConfigXml>	crossMap		= new HashMap<Integer, NetConfigXml>();
	private static Map<Integer, NetConfigXml>	mainDBMap		= new HashMap<Integer, NetConfigXml>();
	private static Map<Integer, NetConfigXml>	logDBMap		= new HashMap<Integer, NetConfigXml>();
	private static Map<Integer, NetConfigXml>	adminMap		= new HashMap<Integer, NetConfigXml>();

	// 服务器ID
	public static int							server_id		= 9999;
	// 默认值
	public static String						server_name		= "DEFAULT";

	public static boolean init() {
		if (!initGlobal()) {
			return false;
		}
		gatewayCount = gatewayMap.size();
		crossCount = crossMap.size();
		return true;
	}

	private static boolean initGlobal() {
		try {
			String globalPath = Config.getValue("path");

			SAXReader reader = new SAXReader();
			Document doc = reader.read(globalPath);
			Element root = doc.getRootElement();

			Element center_server = root.element("center_server");
			init(center_server, "center", centerMap);

			Element scene_server = root.element("scene_server");
			init(scene_server, "scene", sceneMap);

			Element gateway_server = root.element("gateway_server");
			init(gateway_server, "gateway", gatewayMap);

			Element cross_server = root.element("cross_server");
			init(cross_server, "cross", crossMap);

			Element mainDb = root.element("maindb_server");
			init(mainDb, "db", mainDBMap);

			Element logDb = root.element("logdb_server");
			init(logDb, "db_log", logDBMap);

			Element admin_server = root.element("admin_server");
			init(admin_server, "admin", adminMap);

			Element server_info = root.element("server_info");
			if (server_info != null) {
				return initServerInfo(server_info);
			} else {
				Log.error("-------net_config.xml--文件--没有配置服务器ID或服务器名-----------");
				return false;
			}
		} catch (Exception e) {
			Log.error("init global config file fialed., server start failed.", e);
			return false;
		}
	}

	private static boolean initServerInfo(Element server_info) {
		if (server_info != null) {
			Iterator<?> itr = server_info.elementIterator("info");
			while (itr.hasNext()) {
				Element element = (Element) itr.next();
				String id = element.attributeValue("id");
				if (id != null && !id.equals("")) {
					server_id = Integer.valueOf(id);
				} else {
					Log.error("-------net_config.xml--文件--没有配置服务器ID或服务器名-----------");
					return false;
				}
				String name = element.attributeValue("name");
				if (name != null && !name.equals("")) {
					server_name = name;
				} else {
					Log.error("-------net_config.xml--文件--没有配置服务器ID或服务器名-----------");
					return false;
				}

			}
		}
		return true;
	}

	private static void init(Element root, String nodeName, Map<Integer, NetConfigXml> map) {
		if (root == null) {
			return;
		}
		Iterator<?> itr = root.elementIterator(nodeName);
		while (itr.hasNext()) {
			Element element = (Element) itr.next();
			int id = Integer.valueOf(element.attributeValue("id"));
			String name = element.attributeValue("name");
			int port = Integer.valueOf(element.attributeValue("port"));
			int adminPort = 0;
			if (element.attributeValue("adminPort") != null) {
				adminPort = Integer.valueOf(element.attributeValue("adminPort"));
			}
			String address = element.attributeValue("address");
			String user = element.attributeValue("user");
			String password = element.attributeValue("password");
			NetConfigXml configXml = new NetConfigXml(id, name, address, port, adminPort, user, password);
			map.put(configXml.getId(), configXml);
			Log.info("parse server config succ," + configXml);
		}
	}

	/**
	 * 服务器配置
	 * 
	 * @param serverType
	 * @param serverId
	 * @return
	 */
	public static NetConfigXml getNetConfigXml(int serverType, int serverId) {
		NetConfigXml xml = null;
		switch (serverType) {
			case ServerType.GATEWAY:
				xml = gatewayMap.get(serverId);
				break;
			case ServerType.CENTER:
				xml = centerMap.get(serverId);
				break;
			case ServerType.SCENE:
				xml = sceneMap.get(serverId);
				break;
			case ServerType.CROSS:
				xml = crossMap.get(serverId);
				break;
			case ServerType.MAINDB:
				xml = mainDBMap.get(serverId);
				break;
			case ServerType.LOGDB:
				xml = logDBMap.get(serverId);
				break;
			case ServerType.ADMIN_SERVER:
				xml = adminMap.get(serverId);
		}
		return xml;
	}

	/**
	 * 服务器配置
	 * 
	 * @param serverType
	 * @param serverId
	 * @return
	 */
	public static Collection<NetConfigXml> getNetConfigXml(int serverType) {
		Collection<NetConfigXml> xml = null;
		switch (serverType) {
			case ServerType.GATEWAY:
				xml = gatewayMap.values();
				break;
			case ServerType.CENTER:
				xml = centerMap.values();
				break;
			case ServerType.SCENE:
				xml = sceneMap.values();
				break;
			case ServerType.CROSS:
				xml = crossMap.values();
				break;
		}
		return xml;
	}

	/**
	 * 网关连接数量
	 * 
	 * @return
	 */
	public static int gatewayCount() {
		return gatewayCount;
	}

	public static int getCrossCount() {
		return crossCount;
	}
}
