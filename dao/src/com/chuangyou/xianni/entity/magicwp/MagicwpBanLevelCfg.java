package com.chuangyou.xianni.entity.magicwp;

public class MagicwpBanLevelCfg {

	/** 前5位为禁制id，后3位为等级 */
	private int id;
	
	private int att1;
	private int att2;
	private int att3;
	private int att4;
	
	/** 升级所需经验 */
	private int exp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	@Override
	public String toString() {
		return "MagicwpBanLevel [id=" + id + ", att1=" + att1 + ", att2=" + att2 + ", att3=" + att3 + ", att4=" + att4
				+ ", exp=" + exp + "]";
	}
}
