package com.chuangyou.xianni.entity.magicwp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MagicwpLevelCfg {

	/** 前5位为法宝id，后3位为等级 */
	private int						id;
	/** 等级 */
	private int						lv;
	/** 每级对应的金币消耗 */
	private int						needGold;
	private int						att1;
	private int						att2;
	private int						att3;
	private int						att4;
	private int						att5;
	private int						att6;
	private int						att7;
	private int						att8;
	private int						att9;
	private int						att10;

	private Map<Integer, Integer>	attMap;

	/** 每级的冷却时间，单位秒 */
	private long					upLevCd;
	/** 消除冷却元宝 */
	private int						clearCdCash;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getNeedGold() {
		return needGold;
	}

	public void setNeedGold(int needGold) {
		this.needGold = needGold;
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

	public int getAtt9() {
		return att9;
	}

	public void setAtt9(int att9) {
		this.att9 = att9;
	}

	public int getAtt10() {
		return att10;
	}

	public void setAtt10(int att10) {
		this.att10 = att10;
	}

	public long getUpLevCd() {
		return upLevCd;
	}

	public void setUpLevCd(long upLevCd) {
		this.upLevCd = upLevCd;
	}

	public int getClearCdCash() {
		return clearCdCash;
	}

	public void setClearCdCash(int clearCdCash) {
		this.clearCdCash = clearCdCash;
	}

	public List<Integer> getAtts() {
		List<Integer> atts = new ArrayList<>();
		if (att1 > 0) {
			atts.add(att1);
		}
		if (att2 > 0) {
			atts.add(att2);
		}
		if (att3 > 0) {
			atts.add(att3);
		}
		if (att4 > 0) {
			atts.add(att4);
		}
		if (att5 > 0) {
			atts.add(att5);
		}
		if (att6 > 0) {
			atts.add(att6);
		}
		if (att7 > 0) {
			atts.add(att7);
		}
		if (att8 > 0) {
			atts.add(att8);
		}
		if (att9 > 0) {
			atts.add(att9);
		}
		if (att10 > 0) {
			atts.add(att10);
		}
		return atts;
	}

	public Map<Integer, Integer> getAttMap() {
		if (this.attMap == null) {
			this.attMap = new HashMap<Integer, Integer>();
			putAttNum(attMap, getAtt1());
			putAttNum(attMap, getAtt2());
			putAttNum(attMap, getAtt3());
			putAttNum(attMap, getAtt4());
			putAttNum(attMap, getAtt5());
			putAttNum(attMap, getAtt6());
			putAttNum(attMap, getAtt7());
			putAttNum(attMap, getAtt8());
		}
		return this.attMap;
	}

	private void putAttNum(Map<Integer, Integer> attMap, int attNum) {
		if (attNum > 0) {
			int attType = (int) (attNum / 1000000);
			int attValue = attNum % 1000000;
			attMap.put(attType, attValue);
		}
	}

	@Override
	public String toString() {
		return "MagicwpLevel [id=" + id + ", lv=" + lv + ", needGold=" + needGold + ", att1=" + att1 + ", att2=" + att2 + ", att3=" + att3 + ", att4=" + att4 + ", att5=" + att5 + ", att6=" + att6
				+ ", att7=" + att7 + ", att8=" + att8 + ", att9=" + att9 + ", att10=" + att10 + ", upLevCd=" + upLevCd + ", clearCdCash=" + clearCdCash + "]";
	}
}
