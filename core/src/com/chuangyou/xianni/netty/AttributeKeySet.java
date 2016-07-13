package com.chuangyou.xianni.netty;

import io.netty.util.AttributeKey;

/**
 * 
 */
public interface AttributeKeySet {
	static final AttributeKey<Long>		USER_ID			= AttributeKey.newInstance("user_id");
	static final AttributeKey<String>	LOGIN_KEY		= AttributeKey.newInstance("login_key");
	static final AttributeKey<Long>	TEMP_USER_ID	= AttributeKey.newInstance("temp_user_id");
	static final AttributeKey<Integer>	SERVER_ID		= AttributeKey.newInstance("server_id");
	static final AttributeKey<Long>		PLAYER_ID		= AttributeKey.newInstance("player_id");
	static final AttributeKey<Boolean>	LOGIN_STATU		= AttributeKey.newInstance("login_statu");
	static final AttributeKey<Integer>	CLIENT_TYPE		= AttributeKey.newInstance("linkedClient_type");

}
