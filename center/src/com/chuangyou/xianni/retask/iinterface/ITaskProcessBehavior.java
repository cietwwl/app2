package com.chuangyou.xianni.retask.iinterface;

/**
 *  任务进度更新形为处理器
 *  @author laofan
 *
 */
public interface ITaskProcessBehavior extends IGetTask{

	/**
	 * 任务进度处理
	 * @param num
	 */
	public void process(int num);
}
