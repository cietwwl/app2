package com.chuangyou.xianni.conn;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.netty.LinkedClient;

/**
 * <pre>
 * 服务器端连接断开,重连
 * </pre>
 */
public class ClientTryer {
	private static final ClientTryer	inst	= new ClientTryer();
	private ScheduledThreadPoolExecutor	executor;

	private ClientTryer() {
		executor = new ScheduledThreadPoolExecutor(1);// 初始化1个线程
	}

	public static ClientTryer getInstance() {
		return inst;
	}

	/**
	 * 尝试连接
	 * 
	 * @param client
	 *            尝试连接的服务器
	 * @param period
	 *            周期(秒)
	 * @param tryTimes
	 *            尝试次数
	 */
	public void ctry(LinkedClient client, int period, int tryTimes) {
		TryRunner tryRunner = new TryRunner(client, tryTimes);
		ScheduledFuture<?> future = executor.scheduleAtFixedRate(tryRunner, period, period, TimeUnit.SECONDS);
		tryRunner.setFuture(future);
	}
}

/**
 * 连接,直到连接成功或者到达尝试次数为止
 */
class TryRunner implements Runnable {
	ScheduledFuture<?>	future		= null;
	int					tryTimes	= -1;
	LinkedClient		client		= null;

	public TryRunner(LinkedClient client, int tryTimes) {
		this.client = client;
		this.tryTimes = tryTimes;
	}

	public void setFuture(ScheduledFuture<?> future) {
		this.future = future;
	}

	public void run() {
		try {
			if (tryTimes > 0) {
				tryTimes--;
			}
			// if (!client.isTry()) {
			// future.cancel(true);
			// Log.info("Try connect to " + client.getAddress() + ":" +
			// client.getPort()
			// + " cancel, tryRunner will exit.");
			// return;
			// }

			boolean result = client.connect();
			Log.info("try connect to " + client.getName() + " server, address is " + client.getAddress() + ":"
					+ client.getPort() + (result ? " succeed" : " failed") + ".");
			if (result) {
				future.cancel(true);
				client.setTry(true);
				Log.info("Try connect to " + client.getAddress() + ":" + client.getPort() + " succeed,tryRunner exit.");
				ClientSet.sendRegisterMsg(client);
			} else if (tryTimes == 0) {
				future.cancel(true);
				ClientSet.removeServerClient(client);
				Log.info("Try connect to " + client.getAddress() + " fail, tryRunner will exit.");
			}
		} catch (Exception e) {
			Log.error("TryRunner has exception:", e);
		}
	}
}
