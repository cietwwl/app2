package com.chuangyou.xianni.campaign;

public interface ICampaignStateWork {
	void prepare();

	void start();

	void success();

	void fail();

	void stop();
}
