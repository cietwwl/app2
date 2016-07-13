package com.chuangyou.xianni.exec;

import java.util.Queue;
/**
 * <pre>
 * CmdTask命令处理队列
 * </pre>
 */
public interface CmdTaskQueue {
	
	public CmdTaskQueue getCmdTaskQueue();
	
	public void enqueue(CmdTask cmdTask);
	
	public void dequeue(CmdTask cmdTask);
	
	public Queue<CmdTask> getQueue();
	
	public void clear();
}
