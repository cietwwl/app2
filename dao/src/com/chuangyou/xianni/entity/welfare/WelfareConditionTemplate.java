package com.chuangyou.xianni.entity.welfare;
/*
 * Welcome to use the TableToBean Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 *  
 * Author:bianj
 * Email:edinsker@163.com
 * Version:3.0.0
 */

/**
 * (WELFARECONDITION)
 * 
 * @author bianj
 * @version 1.0.0 2016-10-26
 */
public class WelfareConditionTemplate implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 2274039600052881185L;
    
    /** 福利类型 */
    private Integer welfareType;
    
    /**  */
    private Integer resetType;
    
    /** 条件1 */
    private Integer conditionType1;
    
    /** 条件2 */
    private Integer conditionType2;
    
    /** 条件3 */
    private Integer conditionType3;
    
    /** 条件4 */
    private Integer conditionType4;
    
    /**
     * 获取福利类型
     * 
     * @return 福利类型
     */
    public Integer getWelfareType() {
        return this.welfareType;
    }
     
    /**
     * 设置福利类型
     * 
     * @param welfareType
     *          福利类型
     */
    public void setWelfareType(Integer welfareType) {
        this.welfareType = welfareType;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public Integer getResetType() {
        return this.resetType;
    }
     
    /**
     * 设置
     * 
     * @param resetType
     *          
     */
    public void setResetType(Integer resetType) {
        this.resetType = resetType;
    }
    
    /**
     * 获取条件1
     * 
     * @return 条件1
     */
    public Integer getConditionType1() {
        return this.conditionType1;
    }
     
    /**
     * 设置条件1
     * 
     * @param conditionType1
     *          条件1
     */
    public void setConditionType1(Integer conditionType1) {
        this.conditionType1 = conditionType1;
    }
    
    /**
     * 获取条件2
     * 
     * @return 条件2
     */
    public Integer getConditionType2() {
        return this.conditionType2;
    }
     
    /**
     * 设置条件2
     * 
     * @param conditionType2
     *          条件2
     */
    public void setConditionType2(Integer conditionType2) {
        this.conditionType2 = conditionType2;
    }
    
    /**
     * 获取条件3
     * 
     * @return 条件3
     */
    public Integer getConditionType3() {
        return this.conditionType3;
    }
     
    /**
     * 设置条件3
     * 
     * @param conditionType3
     *          条件3
     */
    public void setConditionType3(Integer conditionType3) {
        this.conditionType3 = conditionType3;
    }
    
    /**
     * 获取条件4
     * 
     * @return 条件4
     */
    public Integer getConditionType4() {
        return this.conditionType4;
    }
     
    /**
     * 设置条件4
     * 
     * @param conditionType4
     *          条件4
     */
    public void setConditionType4(Integer conditionType4) {
        this.conditionType4 = conditionType4;
    }
}