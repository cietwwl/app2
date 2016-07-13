package com.chuangyou.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间辅助类
 * 
 * @author Administrator
 */
public class DateTimeUtil {

	private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	/**
	 * 把日期类型转换为字节数组
	 * 
	 * @param date
	 * @return
	 */
	public static byte[] dateToBytes(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		byte[] byteArray = new byte[7];
		short year = (short) calendar.get(Calendar.YEAR);
		byteArray[0] = (byte) ((year >>> 8) & 0xFF);
		byteArray[1] = (byte) (year & 0xFF);
		byteArray[2] = (byte) (calendar.get(Calendar.MONTH) + 1);
		byteArray[3] = (byte) calendar.get(Calendar.DATE);
		byteArray[4] = (byte) calendar.get(Calendar.HOUR_OF_DAY);
		byteArray[5] = (byte) calendar.get(Calendar.MINUTE);
		byteArray[6] = (byte) calendar.get(Calendar.SECOND);
		return byteArray;
	}
	
	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		return FORMAT.format(date);
	}
}
