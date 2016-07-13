package com.chuangyou.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccessTextFile {
	final static String		path	= "F:/abc.txt";
	final static boolean	test	= false;

	public static void saveRecord(String record) {
		if (test == false) {
			return;
		}
		OutputStream out = null;
		PrintWriter write = null;
		try {
			File file = new File(path);
			if (!file.exists() || file.isDirectory()) {
				file.createNewFile();
			}
			out = new FileOutputStream(file, true);
			write = new PrintWriter(out);
			String content = TimeUtil.getDateFormat(new Date()) + "__" + record;
			write.println(content);
		} catch (Exception e) {
			Log.error("客户端记录日志异常");
		} finally {
			try {
				write.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void readToBuffer(StringBuffer buffer, InputStream is) throws IOException {
		String line; // 用来保存每行读取的内容
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		line = reader.readLine(); // 读取第一行
		List<String> results = new ArrayList<String>();
		List<String> temps = new ArrayList<String>();
		while (line != null) { // 如果 line 为空说明读完了
			line = reader.readLine(); // 读取第一行
			if (line != null && line.contains("id:		-1")) {
				String templteId = reader.readLine(); // 读取下一行
				templteId = templteId.replace("\t\t", "");
				String[] tempIds = templteId.split("\\:");
				String tempId = tempIds[1];

				String userId = reader.readLine(); // 读取下一行
				userId = userId.replace("\t\t", "");
				String[] userIds = userId.split("\\:");
				String id = userIds[1];

				String result = "templateId:" + tempId + ",UserId:" + id;
				if (!results.contains(result)) {
					results.add(result);
					System.err.println(result);
				}
				temps.add(result);
			}
		}
		System.err.println("总记录：" + temps.size());
		System.err.println("过滤后：" + results.size());
	}

	/**
	 * 2. 演示将 StringBuffer 中的内容读出到流中
	 */
	public void writeFromBuffer(StringBuffer buffer, OutputStream os) {
		// 用 PrintStream 可以方便的把内容输出到输出流中
		// 其对象的用法和 System.out 一样
		// （System.out 本身就是 PrintStream 对象）
		PrintStream ps = new PrintStream(os);
		ps.print(buffer.toString());
	}

	/**
	 * 3*. 从输入流中拷贝内容到输入流中
	 * 
	 * @throws IOException
	 */
	public void copyStream(InputStream is, OutputStream os) throws IOException {
		// 这个读过过程可以参阅 readToBuffer 中的注释
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(os));
		line = reader.readLine();
		while (line != null) {
			writer.println(line);
			line = reader.readLine();
		}
		writer.flush(); // 最后确定要把输出流中的东西都写出去了
		// 这里不关闭 writer 是因为 os 是从外面传进来的
		// 既然不是从这里打开的，也就不从这里关闭
		// 如果关闭的 writer，封装在里面的 os 也就被关了
	}

	/**
	 * 3. 调用 copyStream(InputStream, OutputStream) 方法拷贝文本文件
	 */
	public void copyTextFile(String inFilename, String outFilename) throws IOException {
		// 先根据输入/输出文件生成相应的输入/输出流
		InputStream is = new FileInputStream(inFilename);
		OutputStream os = new FileOutputStream(outFilename);
		copyStream(is, os); // 用 copyStream 拷贝内容
		is.close(); // is 是在这里打开的，所以需要关闭
		os.close(); // os 是在这里打开的，所以需要关闭
	}

	/**
	 * <pre>
	 * 将字符写入文本文件尾部
	 * </pre>
	 */
	public static void writeToStern(String filePath, String content) {
		FileWriter out = null;
		BufferedWriter writer = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			out = new FileWriter(file, true);
			writer = new BufferedWriter(out);
			writer.write(content);
			writer.newLine();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				out.close();
			} catch (IOException e) {
				Log.error("", e);
			}

		}

	}

	// public static void main(String[] args) throws IOException {
	// int sw = 1; // 三种测试的选择开关
	// AccessTextFile test = new AccessTextFile();
	//
	// switch (sw) {
	// case 1: // 测试读
	// {
	// InputStream is = new FileInputStream("D:\\error.log");
	// StringBuffer buffer = new StringBuffer();
	// test.readToBuffer(buffer, is);
	// System.out.println(buffer); // 将读到 buffer 中的内容写出来
	// is.close();
	// break;
	// }
	// case 2: // 测试写
	// {
	// StringBuffer buffer = new StringBuffer("Only a test\n");
	// test.writeFromBuffer(buffer, System.out);
	// break;
	// }
	// case 3: // 测试拷贝
	// {
	// test.copyTextFile("E:\\test.txt", "E:\\r.txt");
	// }
	// break;
	// }
	// }

	public static void main(String[] args) {
		// File file = new
		// File("C:\\Users\\alvin\\Desktop\\压力测试工具\\stress\\lib");
		// File[] listfile = file.listFiles();
		// StringBuilder sb = new StringBuilder();
		// for (File file2 : listfile) {
		// sb.append("../lib/" + file2.getName()+";");
		// }
		// System.err.println(sb.toString());
	}
}
