package com.chuangyou.common.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class SqlCreateTool {
	public static void main(String[] args) {
		String userIds = getSql("Mount.txt");
		String itemIds = getSql("Item.txt");
		String sqlText = "UPDATE t_u_mountavatar a JOIN t_u_userinfo b ON a.UserId=b.UserId SET a.ExpairDate='2012-8-10 00:00:01',a.IsExist=0 WHERE a.UserId=%s AND a.MountTempId=1007 AND (b.Site='%s' ";
		String sqlBox = "UPDATE t_u_iteminfo a JOIN t_u_userinfo b ON a.UserId=b.UserId SET a.`Count`= CASE WHEN a.`Count`-%s<0 THEN 0 ELSE a.`Count`-%s END WHERE a.UserId=%s AND a.TemplateId=2060042 AND (b.Site='%s' ";
		if (userIds.trim().length() == 0) {
			return;
		}
		String[] ids = userIds.split("\\|");
		if (ids.length == 0) {
			return;
		}
		int i = 0;
		System.err.println("==============================删除羊驼SQL================================");
		for (String userIdStr : ids) {
			String[] idSet = userIdStr.split(",");
			String result = "";
			if (idSet.length >= 2) {
				result = String.format(sqlText, idSet[0], idSet[1]);
				i++;
			}
			if (idSet.length >= 3) {
				result += " OR b.Site='" + idSet[2] + "' ";
			} 
			if (idSet.length >= 4) {
				result += " OR b.Site='" + idSet[3] + "' ";
			} 
			if (idSet.length >= 5) {
				result += " OR b.Site='" + idSet[4] + "' ";
			}
			if (idSet.length >= 6) {
				result += " OR b.Site='" + idSet[5] + "' ";
			}
			if (idSet.length >= 7) {
				result += " OR b.Site='" + idSet[6] + "' ";
			}
			if (idSet.length >= 8) {
				result += " OR b.Site='" + idSet[7] + "' ";
			}
			if (idSet.length >= 9) {
				result += " OR b.Site='" + idSet[8] + "' ";
			}
			result += ");";
			System.err.println(result);
		}
		System.err.println("==============================删除羊驼总影响行数" + i + "行================================");
		System.err.println("==============================删除失落的宝箱SQL================================");
		i = 0;
		String[] items = itemIds.split("\\|");
		for (String temps : items) {
			String[] tempSet = temps.split(",");
			String msg = "";
			if (tempSet.length >= 3) {
				msg = String.format(sqlBox, tempSet[0], tempSet[0], tempSet[1], tempSet[2]);
				i++;
			}
			if (tempSet.length >= 4) {
				msg += " OR b.Site='" + tempSet[3] + "' ";
			} 
			if (tempSet.length >= 5) {
				msg += " OR b.Site='" + tempSet[4] + "' ";
			}
			if (tempSet.length >= 6) {
				msg += " OR b.Site='" + tempSet[5] + "' ";
			}
			if (tempSet.length >= 7) {
				msg += " OR b.Site='" + tempSet[6] + "' ";
			}
			if (tempSet.length >= 8) {
				msg += " OR b.Site='" + tempSet[7] + "' ";
			}
			if (tempSet.length >= 9) {
				msg += " OR b.Site='" + tempSet[8] + "' ";
			}
			msg += ");";
			System.err.println(msg);
		}
		System.err.println("==============================删除失落的宝箱总共影响" + i + "行================================");
	}

	private static String getSql(String fileName) {
		try {
			String s = null;
			StringBuilder sb = new StringBuilder();
			FileReader inone = new FileReader("D:\\" + fileName);
			BufferedReader intwo = new BufferedReader(inone);
			while ((s = intwo.readLine()) != null) {
				sb.append(s);
			}
			return sb.toString();
		} catch (Exception e) {
			Log.error("读取被禁文件失败", e);
		}
		return "";
	}
}
