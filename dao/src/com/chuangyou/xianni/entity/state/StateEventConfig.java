package com.chuangyou.xianni.entity.state;

public class StateEventConfig {
	

	/**
	 * 刷BUFF事件
	 */
	public static final int TYPE_BUFF = 1;
	
	/**
	 * 刷怪事件
	 */
	public static final int TYPE_MONSTER = 2;
	
	
	//----------------------------------------------------
	private int id;
	private String name;
	private int type;
	private int coolDown;
	private int comboChance;
	private int comboCd;
	private int comboEventID;
	private int process;
	private int qteId;
	private int qteLimitTime;
	private int qteChance;
	private int qteSucessEventID;
	private int param1;
	private int param2;
	private int param3;
	private int param4;
	private String paramStr;
	
	
	/**
	 * 将字符参数转整形数组
	 * @return
	 */
	public int[] getParamStrToInt(){
		String[] str = paramStr.split(",");
		int[] cons = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			String string = str[i];
			cons[i] = Integer.parseInt(string);
		}
		return cons;
	}
	
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

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCoolDown() {
		return coolDown;
	}
	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
	}
	public int getComboChance() {
		return comboChance;
	}
	public void setComboChance(int comboChance) {
		this.comboChance = comboChance;
	}
	public int getComboCd() {
		return comboCd;
	}
	public void setComboCd(int comboCd) {
		this.comboCd = comboCd;
	}
	public int getComboEventID() {
		return comboEventID;
	}
	public void setComboEventID(int comboEventID) {
		this.comboEventID = comboEventID;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}
	public int getQteId() {
		return qteId;
	}
	public void setQteId(int qteId) {
		this.qteId = qteId;
	}
	public int getQteLimitTime() {
		return qteLimitTime;
	}
	public void setQteLimitTime(int qteLimitTime) {
		this.qteLimitTime = qteLimitTime;
	}
	public int getQteChance() {
		return qteChance;
	}
	public void setQteChance(int qteChance) {
		this.qteChance = qteChance;
	}
	public int getQteSucessEventID() {
		return qteSucessEventID;
	}
	public void setQteSucessEventID(int qteSucessEventID) {
		this.qteSucessEventID = qteSucessEventID;
	}
	public int getParam1() {
		return param1;
	}
	public void setParam1(int param1) {
		this.param1 = param1;
	}
	public int getParam2() {
		return param2;
	}
	public void setParam2(int param2) {
		this.param2 = param2;
	}
	public int getParam3() {
		return param3;
	}
	public void setParam3(int param3) {
		this.param3 = param3;
	}
	public int getParam4() {
		return param4;
	}
	public void setParam4(int param4) {
		this.param4 = param4;
	}
	public String getParamStr() {
		return paramStr;
	}
	public void setParamStr(String paramStr) {
		this.paramStr = paramStr;
	}
	
	
	
}
