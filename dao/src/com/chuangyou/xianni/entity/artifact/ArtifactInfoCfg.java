package com.chuangyou.xianni.entity.artifact;

public class ArtifactInfoCfg {

	/** 神器ID */
	private int id;
	
	/** 神器名字 */
	private String name;
	
	/** 图标 */
	private int icon;
	
	/** 部位 */
	private int type;
	
	/** 宝石ID1 */
	private int jewel1;
	
	/** 宝石激活等级1 */
	private int level1;
	
	/** 宝石ID2 */
	private int jewel2;
	
	/** 宝石激活等级2 */
	private int level2;
	
	/** 宝石ID3 */
	private int jewel3;
	
	/** 宝石激活等级3 */
	private int level3;
	
	/** 宝石ID4 */
	private int jewel4;
	
	/** 宝石激活等级4 */
	private int level4;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getJewel1() {
		return jewel1;
	}

	public void setJewel1(int jewel1) {
		this.jewel1 = jewel1;
	}

	public int getLevel1() {
		return level1;
	}

	public void setLevel1(int level1) {
		this.level1 = level1;
	}

	public int getJewel2() {
		return jewel2;
	}

	public void setJewel2(int jewel2) {
		this.jewel2 = jewel2;
	}

	public int getLevel2() {
		return level2;
	}

	public void setLevel2(int level2) {
		this.level2 = level2;
	}

	public int getJewel3() {
		return jewel3;
	}

	public void setJewel3(int jewel3) {
		this.jewel3 = jewel3;
	}

	public int getLevel3() {
		return level3;
	}

	public void setLevel3(int level3) {
		this.level3 = level3;
	}

	public int getJewel4() {
		return jewel4;
	}

	public void setJewel4(int jewel4) {
		this.jewel4 = jewel4;
	}

	public int getLevel4() {
		return level4;
	}

	public void setLevel4(int level4) {
		this.level4 = level4;
	}
	
	public int getJewel(int pos){
		switch(pos){
			case 1:
				return this.getJewel1();
			case 2:
				return this.getJewel2();
			case 3:
				return this.getJewel3();
			case 4:
				return this.getJewel4();
			default:
				return 0;
		}
	}
	public int getLevel(int pos){
		switch(pos){
			case 1:
				return this.getLevel1();
			case 2:
				return this.getLevel2();
			case 3:
				return this.getLevel3();
			case 4:
				return this.getLevel4();
			default:
				return 0;
		}
	}
}
