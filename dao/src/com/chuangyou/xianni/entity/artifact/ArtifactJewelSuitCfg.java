package com.chuangyou.xianni.entity.artifact;

import java.util.ArrayList;
import java.util.List;

public class ArtifactJewelSuitCfg {

	/** id */
	private int id;
	
	/** 宝石总等级 */
	private int level;
	
	/** 属性 */
	private int attr1;
	
	/** 属性 */
	private int attr2;
	
	/** 属性 */
	private int attr3;
	
	/** 属性 */
	private int attr4;
	
	/** 属性 */
	private int attr5;
	
	/** 属性 */
	private int attr6;
	
	/** 属性 */
	private int attr7;
	
	/** 属性 */
	private int attr8;
	
	private List<Integer> attList = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAttr1() {
		return attr1;
	}

	public void setAttr1(int attr1) {
		this.attr1 = attr1;
	}

	public int getAttr2() {
		return attr2;
	}

	public void setAttr2(int attr2) {
		this.attr2 = attr2;
	}

	public int getAttr3() {
		return attr3;
	}

	public void setAttr3(int attr3) {
		this.attr3 = attr3;
	}

	public int getAttr4() {
		return attr4;
	}

	public void setAttr4(int attr4) {
		this.attr4 = attr4;
	}

	public int getAttr5() {
		return attr5;
	}

	public void setAttr5(int attr5) {
		this.attr5 = attr5;
	}

	public int getAttr6() {
		return attr6;
	}

	public void setAttr6(int attr6) {
		this.attr6 = attr6;
	}

	public int getAttr7() {
		return attr7;
	}

	public void setAttr7(int attr7) {
		this.attr7 = attr7;
	}

	public int getAttr8() {
		return attr8;
	}

	public void setAttr8(int attr8) {
		this.attr8 = attr8;
	}
	
	public void setAttList(){
		List<Integer> list = new ArrayList<Integer>();
		if (attr1 > 0) {
			list.add(attr1);
		}
		if (attr2 > 0) {
			list.add(attr2);
		}
		if (attr3 > 0) {
			list.add(attr3);
		}
		if (attr4 > 0) {
			list.add(attr4);
		}
		if (attr5 > 0) {
			list.add(attr5);
		}
		if (attr6 > 0) {
			list.add(attr6);
		}
		if (attr7 > 0) {
			list.add(attr7);
		}
		if (attr8 > 0) {
			list.add(attr8);
		}
		this.attList = list;
	}
	
	public List<Integer> getAttList() {
		return this.attList;
	}
}
