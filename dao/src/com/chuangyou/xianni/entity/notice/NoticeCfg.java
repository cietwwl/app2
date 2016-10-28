package com.chuangyou.xianni.entity.notice;

public class NoticeCfg {

	/** 公告模板ID */
	private int noticeId;
	
	/** 频道ID(1系统频道不滚屏，9滚屏公告频道) */
	private int channel;
	
	/** 通知范围(默认0全服 1当前地图) */
	private int notifyRange;
	
	/** 公告内容 */
	private String content;
	
	/** 说明 */
	private String desc;
	
	/** 是否关闭 */
	private byte noticeClose;

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getNotifyRange() {
		return notifyRange;
	}

	public void setNotifyRange(int notifyRange) {
		this.notifyRange = notifyRange;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public byte getNoticeClose() {
		return noticeClose;
	}

	public void setNoticeClose(byte noticeClose) {
		this.noticeClose = noticeClose;
	}
}
