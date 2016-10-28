package com.chuangyou.xianni.entity.welfare;

/**
 * 配置表数据
 * @author wyf
 */
public class WelfareTemplate implements java.io.Serializable {
	/** 版本号 */
	private static final long	serialVersionUID	= -4756376025073223545L;

	/**  */
	private Integer				id;

	/** 福利类型 */
	private Integer				type;

	/** 奖励名称 */
	private String				awardName;

	/** 领取奖励的最低等级 */
	private int				lev;

	/** 重置类型（1：不会重置，2：按照天来重置，3：按照月来重置） */
	private Integer				resetType;

	/** 开始时间（某些有时间限制的福利会用到） */
	private Long				timeBegin;

	/** 结束时间（与开始时间一起使用） */
	private Long				timeEnd;

	/**  */
	private Integer				item1;

	/**  */
	private Integer				bind1;

	/**  */
	private Integer				num1;

	/**  */
	private Integer				item2;

	/**  */
	private Integer				bind2;

	/**  */
	private Integer				num2;

	/**  */
	private Integer				item3;

	/**  */
	private Integer				bind3;

	/**  */
	private Integer				num3;

	/**  */
	private Integer				item4;

	/**  */
	private Integer				bind4;

	/**  */
	private Integer				num4;

	/**  */
	private Integer				item5;

	/**  */
	private Integer				bind5;

	/**  */
	private Integer				num5;

	/**  */
	private Integer				item6;

	/**  */
	private Integer				bind6;

	/**  */
	private Integer				num6;

	/**  */
	private Integer				item7;

	/**  */
	private Integer				bind7;

	/**  */
	private Integer				num7;

	/**  */
	private Integer				item8;

	/**  */
	private Integer				bind8;

	/**  */
	private Integer				num8;

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设置
	 * 
	 * @param id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取福利类型
	 * 
	 * @return 福利类型
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 设置福利类型
	 * 
	 * @param type
	 *            福利类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取奖励名称
	 * 
	 * @return 奖励名称
	 */
	public String getAwardName() {
		return this.awardName;
	}

	/**
	 * 设置奖励名称
	 * 
	 * @param awardName
	 *            奖励名称
	 */
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	/**
	 * 获取领取奖励的最低等级
	 * 
	 * @return 领取奖励的最低等级
	 */
	public int getLev() {
		return this.lev;
	}

	/**
	 * 设置领取奖励的最低等级
	 * 
	 * @param lev
	 *            领取奖励的最低等级
	 */
	public void setLev(int lev) {
		this.lev = lev;
	}

	/**
	 * 获取重置类型（1：不会重置，2：按照天来重置，3：按照月来重置）
	 * 
	 * @return 重置类型（1：不会重置
	 */
	public Integer getResetType() {
		return this.resetType;
	}

	/**
	 * 设置重置类型（1：不会重置，2：按照天来重置，3：按照月来重置）
	 * 
	 * @param resetType
	 *            重置类型（1：不会重置，2：按照天来重置，3：按照月来重置）
	 */
	public void setResetType(Integer resetType) {
		this.resetType = resetType;
	}

	/**
	 * 获取开始时间（某些有时间限制的福利会用到）
	 * 
	 * @return 开始时间（某些有时间限制的福利会用到）
	 */
	public Long getTimeBegin() {
		return this.timeBegin;
	}

	/**
	 * 设置开始时间（某些有时间限制的福利会用到）
	 * 
	 * @param timeBegin
	 *            开始时间（某些有时间限制的福利会用到）
	 */
	public void setTimeBegin(Long timeBegin) {
		this.timeBegin = timeBegin;
	}

	/**
	 * 获取结束时间（与开始时间一起使用）
	 * 
	 * @return 结束时间（与开始时间一起使用）
	 */
	public Long getTimeEnd() {
		return this.timeEnd;
	}

	/**
	 * 设置结束时间（与开始时间一起使用）
	 * 
	 * @param timeEnd
	 *            结束时间（与开始时间一起使用）
	 */
	public void setTimeEnd(Long timeEnd) {
		this.timeEnd = timeEnd;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getItem1() {
		return this.item1;
	}

	/**
	 * 设置
	 * 
	 * @param item1
	 * 
	 */
	public void setItem1(Integer item1) {
		this.item1 = item1;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getBind1() {
		return this.bind1;
	}

	/**
	 * 设置
	 * 
	 * @param bind1
	 * 
	 */
	public void setBind1(Integer bind1) {
		this.bind1 = bind1;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getNum1() {
		return this.num1;
	}

	/**
	 * 设置
	 * 
	 * @param num1
	 * 
	 */
	public void setNum1(Integer num1) {
		this.num1 = num1;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getItem2() {
		return this.item2;
	}

	/**
	 * 设置
	 * 
	 * @param item2
	 * 
	 */
	public void setItem2(Integer item2) {
		this.item2 = item2;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getBind2() {
		return this.bind2;
	}

	/**
	 * 设置
	 * 
	 * @param bind2
	 * 
	 */
	public void setBind2(Integer bind2) {
		this.bind2 = bind2;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getNum2() {
		return this.num2;
	}

	/**
	 * 设置
	 * 
	 * @param num2
	 * 
	 */
	public void setNum2(Integer num2) {
		this.num2 = num2;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getItem3() {
		return this.item3;
	}

	/**
	 * 设置
	 * 
	 * @param item3
	 * 
	 */
	public void setItem3(Integer item3) {
		this.item3 = item3;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getBind3() {
		return this.bind3;
	}

	/**
	 * 设置
	 * 
	 * @param bind3
	 * 
	 */
	public void setBind3(Integer bind3) {
		this.bind3 = bind3;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getNum3() {
		return this.num3;
	}

	/**
	 * 设置
	 * 
	 * @param num3
	 * 
	 */
	public void setNum3(Integer num3) {
		this.num3 = num3;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getItem4() {
		return this.item4;
	}

	/**
	 * 设置
	 * 
	 * @param item4
	 * 
	 */
	public void setItem4(Integer item4) {
		this.item4 = item4;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getBind4() {
		return this.bind4;
	}

	/**
	 * 设置
	 * 
	 * @param bind4
	 * 
	 */
	public void setBind4(Integer bind4) {
		this.bind4 = bind4;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getNum4() {
		return this.num4;
	}

	/**
	 * 设置
	 * 
	 * @param num4
	 * 
	 */
	public void setNum4(Integer num4) {
		this.num4 = num4;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getItem5() {
		return this.item5;
	}

	/**
	 * 设置
	 * 
	 * @param item5
	 * 
	 */
	public void setItem5(Integer item5) {
		this.item5 = item5;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getBind5() {
		return this.bind5;
	}

	/**
	 * 设置
	 * 
	 * @param bind5
	 * 
	 */
	public void setBind5(Integer bind5) {
		this.bind5 = bind5;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getNum5() {
		return this.num5;
	}

	/**
	 * 设置
	 * 
	 * @param num5
	 * 
	 */
	public void setNum5(Integer num5) {
		this.num5 = num5;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getItem6() {
		return this.item6;
	}

	/**
	 * 设置
	 * 
	 * @param item6
	 * 
	 */
	public void setItem6(Integer item6) {
		this.item6 = item6;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getBind6() {
		return this.bind6;
	}

	/**
	 * 设置
	 * 
	 * @param bind6
	 * 
	 */
	public void setBind6(Integer bind6) {
		this.bind6 = bind6;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getNum6() {
		return this.num6;
	}

	/**
	 * 设置
	 * 
	 * @param num6
	 * 
	 */
	public void setNum6(Integer num6) {
		this.num6 = num6;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getItem7() {
		return this.item7;
	}

	/**
	 * 设置
	 * 
	 * @param item7
	 * 
	 */
	public void setItem7(Integer item7) {
		this.item7 = item7;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getBind7() {
		return this.bind7;
	}

	/**
	 * 设置
	 * 
	 * @param bind7
	 * 
	 */
	public void setBind7(Integer bind7) {
		this.bind7 = bind7;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getNum7() {
		return this.num7;
	}

	/**
	 * 设置
	 * 
	 * @param num7
	 * 
	 */
	public void setNum7(Integer num7) {
		this.num7 = num7;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getItem8() {
		return this.item8;
	}

	/**
	 * 设置
	 * 
	 * @param item8
	 * 
	 */
	public void setItem8(Integer item8) {
		this.item8 = item8;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getBind8() {
		return this.bind8;
	}

	/**
	 * 设置
	 * 
	 * @param bind8
	 * 
	 */
	public void setBind8(Integer bind8) {
		this.bind8 = bind8;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Integer getNum8() {
		return this.num8;
	}

	/**
	 * 设置
	 * 
	 * @param num8
	 * 
	 */
	public void setNum8(Integer num8) {
		this.num8 = num8;
	}
}