package com.chuangyou.xianni.entity.artifact;

import java.util.ArrayList;
import java.util.List;

public class ArtifactLevelupCfg {

	/** 神器ID*1000+等级 */
	private long id;
	
	/** 神器id */
	private int artifactId;
	
	/** 等级 */
	private int lv;
	
	/** 升级消耗道具 */
	private int item;
	
	/** 升级消耗道具数量 */
	private int count;
	
	/** 属性 */
	private int att1;
	
	/** 属性 */
	private int att2;
	
	/** 属性 */
	private int att3;
	
	/** 属性 */
	private int att4;
	
	/** 属性 */
	private int att5;
	
	/** 属性 */
	private int att6;
	
	/** 属性 */
	private int att7;
	
	/** 属性 */
	private int att8;
	
	private List<Integer> attList = null;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(int artifactId) {
		this.artifactId = artifactId;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getAtt1() {
		return att1;
	}

	public void setAtt1(int att1) {
		this.att1 = att1;
	}

	public int getAtt2() {
		return att2;
	}

	public void setAtt2(int att2) {
		this.att2 = att2;
	}

	public int getAtt3() {
		return att3;
	}

	public void setAtt3(int att3) {
		this.att3 = att3;
	}

	public int getAtt4() {
		return att4;
	}

	public void setAtt4(int att4) {
		this.att4 = att4;
	}

	public int getAtt5() {
		return att5;
	}

	public void setAtt5(int att5) {
		this.att5 = att5;
	}

	public int getAtt6() {
		return att6;
	}

	public void setAtt6(int att6) {
		this.att6 = att6;
	}

	public int getAtt7() {
		return att7;
	}

	public void setAtt7(int att7) {
		this.att7 = att7;
	}

	public int getAtt8() {
		return att8;
	}

	public void setAtt8(int att8) {
		this.att8 = att8;
	}
	
	public void setAttlist(){
		List<Integer> list = new ArrayList<Integer>();
		if (att1 > 0) {
			list.add(att1);
		}
		if (att2 > 0) {
			list.add(att2);
		}
		if (att3 > 0) {
			list.add(att3);
		}
		if (att4 > 0) {
			list.add(att4);
		}
		if (att5 > 0) {
			list.add(att5);
		}
		if (att6 > 0) {
			list.add(att6);
		}
		if (att7 > 0) {
			list.add(att7);
		}
		if (att8 > 0) {
			list.add(att8);
		}
		this.attList = list;
	}
	
	public List<Integer> getAttList() {
		return this.attList;
	}
}
