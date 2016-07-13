package com.chuangyou.common.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

public class DirtyData {
	private static List<String> dirtyArry;
	private static String dirtyPath;
	private static final String proxyPrefix = "proxy_"; // 代理商脏字符的文件名前缀
	private static final String zipDirtyPath = "zip_test";// 压缩后的脏字符

	public static boolean initDirty(String path) {
		if ((path == null) || path.equals("")) {
			return false;
		}
		if (dirtyArry == null) {
			dirtyPath = path;
			return loadDirty(path);
		}
		return true;
	}

	private static boolean loadDirty(String path) {
		dirtyArry = new ArrayList<String>();
		boolean result = false;
		try {
			File a = new File(path);
			result = readDirtyTxt(path, "UTF-8", dirtyArry, false);
			result = readDirtyTxt(path.replace(a.getName(), proxyPrefix + a.getName()), "UTF-8", dirtyArry, true);
		} catch (IOException e) {
			Log.error("加载脏字符异常 , patch : " + path, e);
			return result;
		}
		return result;
	}

	/**
	 * 重新加载脏字符数据
	 * 
	 * @return 成功返回 true 失败返回false
	 */
	public static boolean reloadDirty() {
		if (dirtyPath != null) {
			return loadDirty(dirtyPath);
		}

		return false;
	}

	public static boolean checkStr(String strName) {
		if ((dirtyArry == null) || (strName == null))
			return true;
		if (strName.contains("$")) {
			return false;
		}
		for (int i = 0; i < dirtyArry.size(); i++) {
			if (strName.contains(dirtyArry.get(i)) && (!dirtyArry.get(i).equals(""))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 读取脏字符文本文件内容
	 * 
	 * @param filePathAndName 带有完整绝对路径的文件名
	 * @param encoding 文本文件打开的编码方式
	 * @param dirtyArry 装脏字符的集合
	 * @param isProxy true:为代理商的脏字符文件，false:为内部脏字符文件
	 * @throws IOException
	 */
	private static boolean readDirtyTxt(String filePathAndName, String encoding, List<String> dirtyArry, boolean isProxy) throws IOException {
		FileInputStream fs = null;
		BufferedReader br = null;
		try {
			encoding = encoding.trim();
			fs = new FileInputStream(filePathAndName);
			InputStreamReader isr;
			if (encoding.equals("")) {
				isr = new InputStreamReader(fs);
			} else {
				isr = new InputStreamReader(fs, encoding);
			}
			br = new BufferedReader(isr);
			String data = "";
			int i = 0;
			while ((data = br.readLine()) != null) {
				if (isProxy == false && i++ == 0) {
					dirtyArry.addAll(Arrays.asList(data.split("")));
					continue;
				}
				dirtyArry.addAll(Arrays.asList(data.split("\\|")));
			}
		} catch (Exception e) {
			Log.error("读取脏字符异常,filePath : " + filePathAndName + ",isProxy : " + isProxy, e);
			return false;
		} finally {
			if (br != null)
				br.close();
			if (fs != null)
				fs.close();
		}
		return true;
	}

	public static boolean createDirtyZip(String dirtyPath) {
		try {
			File fileIn = new File(dirtyPath);
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileIn)));

			String zipPath = dirtyPath.replace(fileIn.getName(), zipDirtyPath + fileIn.getName());
			BufferedOutputStream out = new BufferedOutputStream(new DeflaterOutputStream(new FileOutputStream(zipPath)));
			int c;
			while ((c = in.read()) != -1) {
				out.write(String.valueOf((char) c).getBytes("utf8"));
			}
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			Log.error("脏字符生成异常 ,", e);
		} catch (UnsupportedEncodingException e) {
			Log.error("脏字符生成异常,", e);
		} catch (IOException e) {
			Log.error("脏字符生成异常,", e);
		} catch (Exception e) {
			Log.error("脏字符生成异常,", e);
		}
		return true;
	}
}
