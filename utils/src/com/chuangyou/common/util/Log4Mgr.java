package com.chuangyou.common.util;
//package com.road.yishi.util;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.io.Writer;
//
//import org.apache.log4j.Logger;
//
//public class Log4Mgr {
//
//	private static boolean isDebug=true;
//	private static final Logger log = Logger.getLogger(Log4Mgr.class.getName());
//	private static Writer w = new StringWriter();
//
//	/**
//	 * 有log日志出错
//	 * 
//	 * @param fromLog 来源日志
//	 * @param userMsg
//	 * @param e
//	 */
//	public static void error(Logger fromLog, String userMsg, Exception e) {
//		//e.printStackTrace(new PrintWriter(w));
//		fromLog.error(userMsg,e);
//		//fromLog.error(String.format("出错信息：%s，\n程序提示：%s，\nTrace：%s\n", e.getMessage(), userMsg, w.toString()));
//
//	}
//
//	public static void error(String userMsg, Exception e) {		
////		e.printStackTrace(new PrintWriter(w));
////		log.error(String.format("出错信息：%s，\n程序提示：%s，\nTrace：%s\n", e.getMessage(), userMsg, w.toString()));
//		log.error(userMsg, e);
//	}
//	
//	public static void error(String userMsg)
//	{
//		log.error(userMsg);
//	}
//	
//	public static void info(String userMsg)
//	{
//		log.info(userMsg);
//	}
//
//	/**
//	 * 有log日志提示
//	 * 
//	 * @param fromLog
//	 * @param userMsg
//	 */
//	public static void getInfoMsg(Logger fromLog, String userMsg) {
//		if (isDebug) {
//			System.out.print(userMsg);
//		}
//		fromLog.error(userMsg);
//	}
//
//}
