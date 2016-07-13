package com.chuangyou.xianni.socket;

import com.chuangyou.xianni.proto.PBMessage;
import io.netty.channel.Channel;

public interface Command {

	public abstract void execute(Channel channel, PBMessage packet) throws Exception;

}
