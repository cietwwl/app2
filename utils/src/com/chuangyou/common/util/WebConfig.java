package com.chuangyou.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

public class WebConfig {

	private static Properties properties = null;

	public static boolean initConfig(String path) {
		if (path == null || path.equals("")) {
			return false;
		}
		if (properties == null) {
			return loadProperties(path);
		}

		return true;
	}

	private static boolean loadProperties(String path) {
		try {
			properties = new Properties();
			InputStream inputStream = new BufferedInputStream(new FileInputStream(path));
			properties.load(inputStream);
			return true;
		} catch (FileNotFoundException e) {
			Log.error("加载配置文件异常, path : " + path, e);
			return false;
		} catch (IOException e) {
			Log.error("加载配置文件异常, path : " + path, e);
			return false;
		}
	}

	public static String initLoginKey(String site) {
		return getkey("LoginKey." + site, "注册登陆Key", null);
	}

	public static String initChargeKey(String site) {
		return getkey("ChargeKey." + site, "用户充值", null);
	}

	public static String initPrivateModulus(String site) {
		return getkey("PrivateModulus." + site, "私钥Modulus", null);
	}

	public static String initPrivateExponent(String site) {
		return getkey("PrivateExponent." + site, "私钥Exponent", null);
	}

	public static String initPublicModulus(String site) {
		return getkey("PublicModulus." + site, "公钥Modulus", null);
	}

	public static String initPublicExponent(String site) {
		return getkey("PublicExponent." + site, "公钥Exponent", null);
	}

	public static String initProxyKeyPairUrl(String site) {
		return getkey("ProxyKeyPairUrl." + site, "代理商更新Rsa公钥接口地址", null);
	}

	/**
	 * 是否开启第二种登陆方式
	 * 
	 * @return true:开启 false:不开启
	 */
	public static boolean initLogin2IsOpen() {
		String tmp = getkey("Login2IsOpen", "是否开启第二种登陆方式", "0");
		return "1".equals(tmp);
	}

	public static ArrayList<String> initSites() {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Set<Object> keySet = properties.keySet();
			for (Object item : keySet) {
				String temp = item.toString();
				if (temp != null && temp.length() > 0 && temp.contains("LoginKey.")) {
					int charIndex1 = temp.indexOf('.');
					int charIndex2 = temp.indexOf('=');
					if (charIndex2 > charIndex1) {
						list.add(temp.substring(charIndex1 + 1, charIndex2));
					} else {
						list.add(temp.substring(charIndex1 + 1));
					}
				}
			}
		} catch (Exception e) {
			Log.error("获取站点信息失败", e);
		}
		return list;
	}

	public static String getSite() {
		String key = "LoginKey.";
		String site = "";
		try {
			Set<Object> keySet = properties.keySet();
			for (Object item : keySet) {
				String temp = item.toString();
				if (temp != null && temp.length() > 0 && temp.contains(key)) {
					int charIndex1 = temp.indexOf('.');
					int charIndex2 = temp.indexOf('_');
					if (charIndex2 > charIndex1) {
						return temp.substring(charIndex1 + 1, charIndex2);
					} else {
						return temp.substring(charIndex1 + 1);
					}
				}
			}
		} catch (Exception e) {
			Log.error(e);
		}
		return site;
	}

	private static String getkey(String name, String desc, String defaultValue) {
		String property = properties.getProperty(name);
		if (property == null || property.trim().length() == 0) {
			Log.error("配置" + name + "不存在");
			if (defaultValue != null && defaultValue.trim().length() != 0) {
				return defaultValue;
			} else {
				return null;
			}
		} else {
			return property;
		}
	}

	/**
	 * 检验登陆IP
	 * 
	 * @return
	 */
	public static String getLoginIPKey() {
		return getkey("LoginIp", "登陆IP限制", null);
	}

	/**
	 * 检测充值IP
	 * 
	 * @return
	 */
	public static String getChargeIPKey() {
		return getkey("ChargeIp", "充值IP限制", null);
	}

	/**
	 * 检测GmIP
	 * 
	 * @return
	 */
	public static String getGmIPKey() {
		return getkey("GmIp", "GM接口调用IP限制", null);
	}

	public static String getProxyIpKey() {
		return getkey("ProxyIp", "代理商IP限制", null);
	}

	public static String getSmsSender() {
		return getkey("SmsSender", "短信发送接口", null);
	}
	
	public static String getSmsSender1() {
		return getkey("SmsSender1", "短信发送接口", null);
	}

	public static String getSmsContent() {
		return getkey("SmsContent", "短信内容", null);
	}

	public static String getPhoneRegular() {
		return getkey("PhoneRegular", "手机号码正则表达式", "^(13[0-9]|15[0-9]|18[0-9])[0-9]{8}$");
	}

	public static String getQuestionPath() {
		return getkey("QuestionPath", "客服问题提交接口", null);
	}

	public static String getAppraisalPath() {
		return getkey("AppraisalPath", "评价接口", null);
	}

	public static String getReplyPath() {
		return getkey("ReplyPath", "玩家回复接口", null);
	}

	public static String getCustomerKey() {
		return getkey("CustomerKey", "客服接口密钥", null);
	}
	public static String getCustomerSite(){
	    return getkey("CustomerSite", "客服站点", null);
	}
	
	/**
	 * 检验本地测试登陆IP
	 * 
	 * @return
	 */
	public static String getLocalTestLoginIpKey() {
		return getkey("LocalTestLoginIp", "测试登陆IP限制", null);
	}
}
