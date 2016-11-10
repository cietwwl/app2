package com.chuangyou.xianni.entity.pet;

import java.util.HashSet;
import java.util.Set;

import com.chuangyou.common.util.StringUtils;

public class PetInfoCfg
{
	/** 道具激活 */
	public static final int ACTIVATE_BY_ITEM = 1;
	/** 境界激活 */
	public static final int ACTIVATE_BY_STATE = 2;
	
	/** 宠物id */
    private int id;
    /** 名字 */
    private String name;
    /** 图标 */
    private int icon;
    /** 宠物模型 */
    private String model;
    /** 特殊宠物 0普通 1特殊 */
    private byte isSpecial;
    /** 激活所需道具 */
    private int needItem;
    /** 宠物主动技能 */
    private String skills;
    /** 激活主角技能 */
    private int skillId;
    /** 提升资质所需道具1 */
    private int zizhiItem1;
    /** 提升资质所需道具2 */
    private int zizhiItem2;
    /** 提升宠物资质 */
    private int addZizhi;
    /** 获得途径描述 */
    private String des;
    /** 资质上限 */
    private int zizhiMax;
    
    /** 激活类型 1道具激活 2境界激活 */
    private int activateType;
    
    /** 境界等级 */
    private int jingjieLv;
    
    private Set<Integer> skillSet;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public byte getIsSpecial() {
		return isSpecial;
	}
	public void setIsSpecial(byte isSpecial) {
		this.isSpecial = isSpecial;
	}
	public int getNeedItem() {
		return needItem;
	}
	public void setNeedItem(int needItem) {
		this.needItem = needItem;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
		
		this.skillSet = new HashSet<>();
		if(this.skills == null){
			return;
		}
		String[] s = this.skills.split(",");
		
		for(String skillIdStr: s){
			if(StringUtils.isNullOrEmpty(skillIdStr)){
				continue;
			}
			if(!StringUtils.isNumber(skillIdStr)){
				continue;
			}
			this.skillSet.add(Integer.valueOf(skillIdStr));
		}
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public int getZizhiItem1() {
		return zizhiItem1;
	}
	public void setZizhiItem1(int zizhiItem1) {
		this.zizhiItem1 = zizhiItem1;
	}
	public int getZizhiItem2() {
		return zizhiItem2;
	}
	public void setZizhiItem2(int zizhiItem2) {
		this.zizhiItem2 = zizhiItem2;
	}
	public int getAddZizhi() {
		return addZizhi;
	}
	public void setAddZizhi(int addZizhi) {
		this.addZizhi = addZizhi;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getZizhiMax() {
		return zizhiMax;
	}
	public void setZizhiMax(int zizhiMax) {
		this.zizhiMax = zizhiMax;
	}
	public int getActivateType() {
		return activateType;
	}
	public void setActivateType(int activateType) {
		this.activateType = activateType;
	}
	public int getJingjieLv() {
		return jingjieLv;
	}
	public void setJingjieLv(int jingjieLv) {
		this.jingjieLv = jingjieLv;
	}
	public Set<Integer> getSkillSet() {
		return skillSet;
	}
	@Override
	public String toString() {
		return "PetInfoCfg [id=" + id + ", name=" + name + ", icon=" + icon + ", model=" + model + ", isSpecial="
				+ isSpecial + ", needItem=" + needItem + ", skills=" + skills + ", skillId=" + skillId + ", zizhiItem1="
				+ zizhiItem1 + ", zizhiItem2=" + zizhiItem2 + ", addZizhi=" + addZizhi + ", des=" + des + ", zizhiMax="
				+ zizhiMax + ", activateType=" + activateType + ", jingjieLv=" + jingjieLv + ", skillSet=" + skillSet
				+ "]";
	}
}
