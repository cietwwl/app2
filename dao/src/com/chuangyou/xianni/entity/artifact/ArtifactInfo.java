package com.chuangyou.xianni.entity.artifact;

import com.chuangyou.common.protobuf.pb.artifact.ArtifactInfoProto.ArtifactInfoMsg;
import com.chuangyou.common.protobuf.pb.artifact.ArtifactStoneInfoProto.ArtifactStoneInfoMsg;
import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class ArtifactInfo extends DataObject {

	private long playerId;
	private int artifactId;
	private int level;
	private int starLevel;
	private int star;
	private int starBless;
	
	private int stoneLevel1;
	private long stoneExp1;
	private long stoneTotalExp1;
	
	private int stoneLevel2;
	private long stoneExp2;
	private long stoneTotalExp2;
	
	private int stoneLevel3;
	private long stoneExp3;
	private long stoneTotalExp3;
	
	private int stoneLevel4;
	private long stoneExp4;
	private long stoneTotalExp4;
	
	public ArtifactInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public ArtifactInfo(long playerId, int artifactId){
		this.playerId = playerId;
		this.artifactId = artifactId;
		
		this.level = 0;
		this.starLevel = 0;
		this.star = 0;
		this.starBless = 0;
		
		this.stoneLevel1 = 0;
		this.stoneExp1 = 0;
		this.stoneTotalExp1 = 0;
		
		this.stoneLevel2 = 0;
		this.stoneExp2 = 0;
		this.stoneTotalExp2 = 0;
		
		this.stoneLevel3 = 0;
		this.stoneExp3 = 0;
		this.stoneTotalExp3 = 0;
		
		this.stoneLevel4 = 0;
		this.stoneExp4 = 0;
		this.stoneTotalExp4 = 0;
	}
	
	public int getStoneMaxLevel(){
		int tempLevel = 0;
		tempLevel = Math.max(stoneLevel1,tempLevel);
		tempLevel = Math.max(stoneLevel2,tempLevel);
		tempLevel = Math.max(stoneLevel3,tempLevel);
		tempLevel = Math.max(stoneLevel4,tempLevel);
		return tempLevel;
	}
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public int getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(int artifactId) {
		this.artifactId = artifactId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		setOp(Option.Update);
		this.level = level;
	}
	public int getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(int starLevel) {
		setOp(Option.Update);
		this.starLevel = starLevel;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		setOp(Option.Update);
		this.star = star;
	}
	public int getStarBless() {
		return starBless;
	}
	public void setStarBless(int starBless) {
		setOp(Option.Update);
		this.starBless = starBless;
	}
	public int getStoneLevel1() {
		return stoneLevel1;
	}
	public void setStoneLevel1(int stoneLevel1) {
		setOp(Option.Update);
		this.stoneLevel1 = stoneLevel1;
	}
	public long getStoneExp1() {
		return stoneExp1;
	}
	public void setStoneExp1(long stoneExp1) {
		setOp(Option.Update);
		this.stoneExp1 = stoneExp1;
	}
	public long getStoneTotalExp1() {
		return stoneTotalExp1;
	}
	public void setStoneTotalExp1(long stoneTotalExp1) {
		setOp(Option.Update);
		this.stoneTotalExp1 = stoneTotalExp1;
	}
	public int getStoneLevel2() {
		return stoneLevel2;
	}
	public void setStoneLevel2(int stoneLevel2) {
		setOp(Option.Update);
		this.stoneLevel2 = stoneLevel2;
	}
	public long getStoneExp2() {
		return stoneExp2;
	}
	public void setStoneExp2(long stoneExp2) {
		setOp(Option.Update);
		this.stoneExp2 = stoneExp2;
	}
	public long getStoneTotalExp2() {
		return stoneTotalExp2;
	}
	public void setStoneTotalExp2(long stoneTotalExp2) {
		setOp(Option.Update);
		this.stoneTotalExp2 = stoneTotalExp2;
	}
	public int getStoneLevel3() {
		return stoneLevel3;
	}
	public void setStoneLevel3(int stoneLevel3) {
		setOp(Option.Update);
		this.stoneLevel3 = stoneLevel3;
	}
	public long getStoneExp3() {
		return stoneExp3;
	}
	public void setStoneExp3(long stoneExp3) {
		setOp(Option.Update);
		this.stoneExp3 = stoneExp3;
	}
	public long getStoneTotalExp3() {
		return stoneTotalExp3;
	}
	public void setStoneTotalExp3(long stoneTotalExp3) {
		setOp(Option.Update);
		this.stoneTotalExp3 = stoneTotalExp3;
	}
	public int getStoneLevel4() {
		return stoneLevel4;
	}
	public void setStoneLevel4(int stoneLevel4) {
		setOp(Option.Update);
		this.stoneLevel4 = stoneLevel4;
	}
	public long getStoneExp4() {
		return stoneExp4;
	}
	public void setStoneExp4(long stoneExp4) {
		setOp(Option.Update);
		this.stoneExp4 = stoneExp4;
	}
	public long getStoneTotalExp4() {
		return stoneTotalExp4;
	}
	public void setStoneTotalExp4(long stoneTotalExp4) {
		setOp(Option.Update);
		this.stoneTotalExp4 = stoneTotalExp4;
	}
	
	public int getStoneLevel(int pos){
		switch(pos){
			case 1:
				return this.getStoneLevel1();
			case 2:
				return this.getStoneLevel2();
			case 3:
				return this.getStoneLevel3();
			case 4:
				return this.getStoneLevel4();
			default:
				return -1;
		}
	}
	public void setStoneLevel(int pos, int value){
		switch(pos){
			case 1:
				this.setStoneLevel1(value);
				break;
			case 2:
				this.setStoneLevel2(value);
				break;
			case 3:
				this.setStoneLevel3(value);
				break;
			case 4:
				this.setStoneLevel4(value);
				break;
		}
	}
	
	public long getStoneExp(int pos){
		switch(pos){
			case 1:
				return this.getStoneExp1();
			case 2:
				return this.getStoneExp2();
			case 3:
				return this.getStoneExp3();
			case 4:
				return this.getStoneExp4();
			default:
				return -1;
		}
	}
	public void setStoneExp(int pos, long value){
		switch(pos){
			case 1:
				this.setStoneExp1(value);
				break;
			case 2:
				this.setStoneExp2(value);
				break;
			case 3:
				this.setStoneExp3(value);
				break;
			case 4:
				this.setStoneExp4(value);
				break;
		}
	}
	
	public long getStoneTotalExp(int pos){
		switch(pos){
			case 1:
				return this.getStoneTotalExp1();
			case 2:
				return this.getStoneTotalExp2();
			case 3:
				return this.getStoneTotalExp3();
			case 4:
				return this.getStoneTotalExp4();
			default:
				return -1;
		}
	}
	public void setStoneTotalExp(int pos, long value){
		switch(pos){
			case 1:
				this.setStoneTotalExp1(value);
				break;
			case 2:
				this.setStoneTotalExp2(value);
				break;
			case 3:
				this.setStoneTotalExp3(value);
				break;
			case 4:
				this.setStoneTotalExp4(value);
				break;
		}
	}
	
	public ArtifactInfoMsg writeProto(ArtifactInfoCfg cfg){
		ArtifactInfoMsg.Builder msg = ArtifactInfoMsg.newBuilder();
		msg.setArtifactId(this.getArtifactId());
		msg.setLevel(this.getLevel());
		msg.setStarLevel(this.getStarLevel());
		msg.setStar(this.getStar());
		msg.setStarBless(this.getStarBless());
		
		ArtifactStoneInfoMsg.Builder stoneMsg1 = ArtifactStoneInfoMsg.newBuilder();
		stoneMsg1.setArtifactId(this.getArtifactId());
		stoneMsg1.setPos(1);
		stoneMsg1.setStoneId(cfg.getJewel1());
		stoneMsg1.setLevel(this.getStoneLevel1());
		stoneMsg1.setExp(this.getStoneExp1());
		msg.addStoneInfo(stoneMsg1.build());
		
		ArtifactStoneInfoMsg.Builder stoneMsg2 = ArtifactStoneInfoMsg.newBuilder();
		stoneMsg2.setArtifactId(this.getArtifactId());
		stoneMsg2.setPos(2);
		stoneMsg2.setStoneId(cfg.getJewel2());
		stoneMsg2.setLevel(this.getStoneLevel2());
		stoneMsg2.setExp(this.getStoneExp2());
		msg.addStoneInfo(stoneMsg2.build());
		
		ArtifactStoneInfoMsg.Builder stoneMsg3 = ArtifactStoneInfoMsg.newBuilder();
		stoneMsg3.setArtifactId(this.getArtifactId());
		stoneMsg3.setPos(3);
		stoneMsg3.setStoneId(cfg.getJewel3());
		stoneMsg3.setLevel(this.getStoneLevel3());
		stoneMsg3.setExp(this.getStoneExp3());
		msg.addStoneInfo(stoneMsg3);
		
		ArtifactStoneInfoMsg.Builder stoneMsg4 = ArtifactStoneInfoMsg.newBuilder();
		stoneMsg4.setArtifactId(this.getArtifactId());
		stoneMsg4.setPos(4);
		stoneMsg4.setStoneId(cfg.getJewel4());
		stoneMsg4.setLevel(this.getStoneLevel4());
		stoneMsg4.setExp(this.getStoneExp4());
		msg.addStoneInfo(stoneMsg4);
		
		return msg.build();
	}
	
}
