package com.chuangyou.xianni.http;

import com.chuangyou.common.util.JSONUtil;

/**
 * 
 * 管理后台操作时返回的结果
 *
 */
public class HttpResResult {
	int		status;
	Object	data;

	public static enum Code {
		FAIL(0), SUCCESS(1),;
		private int code;

		private Code(int code) {
			this.code = code;
		}
	}

	public static String getResult(Code statu, Object description) {
		return JSONUtil.getJSONString(new HttpResResult(statu.code, description));
	}

	private HttpResResult() {
	}

	private HttpResResult(int status, Object data) {
		this.status = status;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
