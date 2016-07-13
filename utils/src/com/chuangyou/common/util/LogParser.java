package com.chuangyou.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志解析器
 * 
 * @author Administrator
 */
public class LogParser {

	private static List<String[]> bucket = new ArrayList<String[]>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File logFile = new File("e:\\fatal.log.20110902");
			if (!logFile.exists()) {
				System.err.println("Can not found this log file, parset will exit.");
				return;
			}

			// 读取并解析文件
			BufferedReader reader = new BufferedReader(new FileReader(logFile));
			String line = reader.readLine();
			while (line != null) {
				String nextParse = parseNext(line, reader);
				if (nextParse != null) {
					line = nextParse;
				} else {
					line = reader.readLine();
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * 向下解析
	 * 
	 * @param line
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	private static String parseNext(String line, BufferedReader reader) throws IOException {
		String[] lineArray = line.split("[|]");
		String nextParseLine = null;
		bucket.clear();
		if (lineArray[0].equals("R")) {// 解析下一天
			String nextLine = null;
			while ((nextLine = reader.readLine()) != null) {
				String[] nextLineArray = nextLine.split("[|]");
				if (nextLineArray[0].equals("S") && lineArray[1].equals(nextLineArray[1])) {// 相同的玩家的数据
					bucket.add(nextLineArray);
				} else if (nextLineArray[0].equals("R")) {
					nextParseLine = nextLine;
					break;
				}
			}

			// 统计相同
			count(bucket);			
		}
		return nextParseLine;
	}

	/**
	 * 统计数量
	 * @param bucket
	 * @return
	 */
	private static void count(List<String[]> bucket){
		Object[] bucketArray = bucket.toArray();
		int count = 0;
		for (int i = 0; i < bucketArray.length; i++) {
			String[] firstArray = (String[]) bucketArray[i];
			count = 1;
			for (int j = i+1; j < bucketArray.length; j++) {
				String[] nextArray = (String[]) bucketArray[j];
				if(firstArray[2].equals(nextArray[2])){
					count ++;
				}
			}
			
			if(count > 1){
				System.err.println("玩家:"+firstArray[1]+"协议编号:"+firstArray[2]+",重复次数:"+count);
			}
			count = 0;
		}
	}
}
