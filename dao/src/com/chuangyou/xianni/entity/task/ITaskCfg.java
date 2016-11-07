package com.chuangyou.xianni.entity.task;

/**
 * 任务配置表接口
 * @author laofan
 *
 */
public interface ITaskCfg {
	
	/**
	 * 目标类型
	 * @return
	 */
	public byte getTaskTarget();
	
	/**
	 *  目标参数
	 * @return
	 */
	public int getTargetId();
	
	/**
	 * 目标参数1
	 * @return
	 */
	public int getTargetId1();
	
	/**
	 * 目标完成数量
	 * @return
	 */
	public int getTargetNum();
	
	/**
	 * 任务的触发类型
	 * @return
	 */
	public int getTargetTrigger();

}
