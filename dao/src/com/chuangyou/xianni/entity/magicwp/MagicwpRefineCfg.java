package com.chuangyou.xianni.entity.magicwp;

import java.util.HashMap;
import java.util.Map;

public class MagicwpRefineCfg {

	/** 法宝id */
	private int id;
	/** 增加属性几率万分比，减少属性几率=1-增加属性几率 */
	private int pro;
	
	/** 洗练属性1 */
	private int att1;
	/** 洗练值1区间最小值 */
	private int randomMin1;
	/** 洗练值1区间最大值 */
	private int randomMax1;
	
	private int att2;
	private int randomMin2;
	private int randomMax2;
	
	private int att3;
	private int randomMin3;
	private int randomMax3;
	
	private int att4;
	private int randomMin4;
	private int randomMax4;
	
	private int att5;
	private int randomMin5;
	private int randomMax5;
	
	private int att6;
	private int randomMin6;
	private int randomMax6;
	
	private int att7;
	private int randomMin7;
	private int randomMax7;
	
	private int att8;
	private int randomMin8;
	private int randomMax8;
	
	private Map<Integer, Integer> minAttMap;
	private Map<Integer, Integer> maxAttMap;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPro() {
		return pro;
	}
	public void setPro(int pro) {
		this.pro = pro;
	}
	public int getAtt1() {
		return att1;
	}
	public void setAtt1(int att1) {
		this.att1 = att1;
	}
	public int getRandomMin1() {
		return randomMin1;
	}
	public void setRandomMin1(int randomMin1) {
		this.randomMin1 = randomMin1;
	}
	public int getRandomMax1() {
		return randomMax1;
	}
	public void setRandomMax1(int randomMax1) {
		this.randomMax1 = randomMax1;
	}
	public int getAtt2() {
		return att2;
	}
	public void setAtt2(int att2) {
		this.att2 = att2;
	}
	public int getRandomMin2() {
		return randomMin2;
	}
	public void setRandomMin2(int randomMin2) {
		this.randomMin2 = randomMin2;
	}
	public int getRandomMax2() {
		return randomMax2;
	}
	public void setRandomMax2(int randomMax2) {
		this.randomMax2 = randomMax2;
	}
	public int getAtt3() {
		return att3;
	}
	public void setAtt3(int att3) {
		this.att3 = att3;
	}
	public int getRandomMin3() {
		return randomMin3;
	}
	public void setRandomMin3(int randomMin3) {
		this.randomMin3 = randomMin3;
	}
	public int getRandomMax3() {
		return randomMax3;
	}
	public void setRandomMax3(int randomMax3) {
		this.randomMax3 = randomMax3;
	}
	public int getAtt4() {
		return att4;
	}
	public void setAtt4(int att4) {
		this.att4 = att4;
	}
	public int getRandomMin4() {
		return randomMin4;
	}
	public void setRandomMin4(int randomMin4) {
		this.randomMin4 = randomMin4;
	}
	public int getRandomMax4() {
		return randomMax4;
	}
	public void setRandomMax4(int randomMax4) {
		this.randomMax4 = randomMax4;
	}
	public int getAtt5() {
		return att5;
	}
	public void setAtt5(int att5) {
		this.att5 = att5;
	}
	public int getRandomMin5() {
		return randomMin5;
	}
	public void setRandomMin5(int randomMin5) {
		this.randomMin5 = randomMin5;
	}
	public int getRandomMax5() {
		return randomMax5;
	}
	public void setRandomMax5(int randomMax5) {
		this.randomMax5 = randomMax5;
	}
	public int getAtt6() {
		return att6;
	}
	public void setAtt6(int att6) {
		this.att6 = att6;
	}
	public int getRandomMin6() {
		return randomMin6;
	}
	public void setRandomMin6(int randomMin6) {
		this.randomMin6 = randomMin6;
	}
	public int getRandomMax6() {
		return randomMax6;
	}
	public void setRandomMax6(int randomMax6) {
		this.randomMax6 = randomMax6;
	}
	public int getAtt7() {
		return att7;
	}
	public void setAtt7(int att7) {
		this.att7 = att7;
	}
	public int getRandomMin7() {
		return randomMin7;
	}
	public void setRandomMin7(int randomMin7) {
		this.randomMin7 = randomMin7;
	}
	public int getRandomMax7() {
		return randomMax7;
	}
	public void setRandomMax7(int randomMax7) {
		this.randomMax7 = randomMax7;
	}
	public int getAtt8() {
		return att8;
	}
	public void setAtt8(int att8) {
		this.att8 = att8;
	}
	public int getRandomMin8() {
		return randomMin8;
	}
	public void setRandomMin8(int randomMin8) {
		this.randomMin8 = randomMin8;
	}
	public int getRandomMax8() {
		return randomMax8;
	}
	public void setRandomMax8(int randomMax8) {
		this.randomMax8 = randomMax8;
	}
	
	public void setAttMap(){
		this.minAttMap = new HashMap<Integer, Integer>();
		this.minAttMap.put((int)(randomMin1/1000000), randomMin1%1000000);
		this.minAttMap.put((int)(randomMin2/1000000), randomMin2%1000000);
		this.minAttMap.put((int)(randomMin3/1000000), randomMin3%1000000);
		this.minAttMap.put((int)(randomMin4/1000000), randomMin4%1000000);
		this.minAttMap.put((int)(randomMin5/1000000), randomMin5%1000000);
		this.minAttMap.put((int)(randomMin6/1000000), randomMin6%1000000);
		this.minAttMap.put((int)(randomMin7/1000000), randomMin7%1000000);
		this.minAttMap.put((int)(randomMin8/1000000), randomMin8%1000000);
		
		this.maxAttMap = new HashMap<>();
		this.maxAttMap.put((int)(randomMax1/1000000), randomMax1%1000000);
		this.maxAttMap.put((int)(randomMax2/1000000), randomMax2%1000000);
		this.maxAttMap.put((int)(randomMax3/1000000), randomMax3%1000000);
		this.maxAttMap.put((int)(randomMax4/1000000), randomMax4%1000000);
		this.maxAttMap.put((int)(randomMax5/1000000), randomMax5%1000000);
		this.maxAttMap.put((int)(randomMax6/1000000), randomMax6%1000000);
		this.maxAttMap.put((int)(randomMax7/1000000), randomMax7%1000000);
		this.maxAttMap.put((int)(randomMax8/1000000), randomMax8%1000000);
	}
	
	public Map<Integer, Integer> getMinAttMap(){
		return this.minAttMap;
	}
	public Map<Integer, Integer> getMaxAttMap(){
		return this.maxAttMap;
	}
	
	@Override
	public String toString() {
		return "MagicwpRefine [id=" + id + ", pro=" + pro + ", att1=" + att1 + ", randomMin1=" + randomMin1
				+ ", randomMax1=" + randomMax1 + ", att2=" + att2 + ", randomMin2=" + randomMin2 + ", randomMax2="
				+ randomMax2 + ", att3=" + att3 + ", randomMin3=" + randomMin3 + ", randomMax3=" + randomMax3
				+ ", att4=" + att4 + ", randomMin4=" + randomMin4 + ", randomMax4=" + randomMax4 + ", att5=" + att5
				+ ", randomMin5=" + randomMin5 + ", randomMax5=" + randomMax5 + ", att6=" + att6 + ", randomMin6="
				+ randomMin6 + ", randomMax6=" + randomMax6 + ", att7=" + att7 + ", randomMin7=" + randomMin7
				+ ", randomMax7=" + randomMax7 + ", att8=" + att8 + ", randomMin8=" + randomMin8 + ", randomMax8="
				+ randomMax8 + "]";
	}
}
