package com.chuangyou.xianni.http;

import com.chuangyou.common.util.JSONUtil;

/**
 * 
 * 管理后台操作时返回的结果
 *
 */
public class HttpResult {

	int		code;
	Object	content;

	public static enum Code {
		SUCCESS(1001), ERROR(404),;
		private int num;

		private Code(int num) {
			this.num = num;
		}
	}

	public static String getResult(Code statu, Object description) {
		int code = statu.num;
		return JSONUtil.getJSONString(new HttpResult(code, description));
	}

	private HttpResult() {

	}

	private HttpResult(int code, Object content) {
		this.code = code;
		this.content = content;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
