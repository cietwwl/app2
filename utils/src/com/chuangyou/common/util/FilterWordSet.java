package com.chuangyou.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class FilterWordSet {
	private static final Set<String> filterWordCache = new HashSet<>();
	
	public FilterWordSet() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean loadFilterWord(String path){
		if(path == null || path.equals("")){
			return false;
		}
		
		try {
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(isr);
			String lineContent;
			int sum = 0;
			while ((lineContent = bufferedReader.readLine()) != null) {
				if(!lineContent.equals("")){
					filterWordCache.add(lineContent);
				}
			}
			System.out.println(sum);
			bufferedReader.close();
			isr.close();
			fis.close();
			return true;
		} catch (FileNotFoundException e) {
			Log.error("敏感字资源文件不存在", e);
			return false;
		} catch (IOException e) {
			Log.error("敏感字资源文件加载失败", e);
			return false;
		}
	}
	
	public static boolean reloadFilterWord(){
		filterWordCache.clear();
		boolean result = loadFilterWord(Config.getValue("filter_word"));
		return result;
	}

	public static Set<String> getFilterwordcache() {
		return filterWordCache;
	}
}
