package com.chuangyou.xianni.exec;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.socket.Command;

import io.netty.channel.Channel;

/**
 * <pre>
 * 执行cmd
 * </pre>
 */
public class CmdTask implements Runnable {

	private CmdTaskQueue	queue;
	private Channel			channel;
	private PBMessage		packet;
	private Command			cmd;
	protected Long			createTime;

	public CmdTask(Command cmd, Channel channel, PBMessage packet, CmdTaskQueue queue) {
		this.channel = channel;
		this.packet = packet;
		this.cmd = cmd;
		this.queue = queue;
		createTime = System.currentTimeMillis();
	}

	@Override
	public void run() {
		if (queue != null) {
			long start = System.currentTimeMillis();
			try {
				cmd.execute(channel, packet);
				long end = System.currentTimeMillis();
				long interval = end - start;
				long leftTime = end - createTime;
				if (interval >= 100) {
					Log.warn("execute cmder : " + this.toString() + ", interval : " + interval + ", leftTime : " + leftTime + ", size : " + queue.getQueue().size());
				}
			} catch (Exception e) {
				Log.error("执行 command 异常, command : " + cmd.toString() + ", packet : " + packet.toString(), e);
			} finally {
				queue.dequeue(this);
			}
		}
	}

	@Override
	public String toString() {
		return cmd.toString() + ", packet : " + packet.toString();
	}
}
