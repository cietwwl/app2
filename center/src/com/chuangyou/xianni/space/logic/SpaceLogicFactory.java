package com.chuangyou.xianni.space.logic;

import com.chuangyou.xianni.space.logic.collection.AddCollectionLogic;
import com.chuangyou.xianni.space.logic.collection.DelAllCollectionLogic;
import com.chuangyou.xianni.space.logic.collection.DelCollectionLogic;
import com.chuangyou.xianni.space.logic.collection.ISpaceCollectionLogic;
import com.chuangyou.xianni.space.logic.collection.SetCollectionNumLogic;
import com.chuangyou.xianni.space.logic.edit.EditBirthdayLogic;
import com.chuangyou.xianni.space.logic.edit.EditCityLogic;
import com.chuangyou.xianni.space.logic.edit.EditFaceLogic;
import com.chuangyou.xianni.space.logic.edit.EditNameLogic;
import com.chuangyou.xianni.space.logic.edit.EditNoMsgLogic;
import com.chuangyou.xianni.space.logic.edit.EditSignatureLogic;
import com.chuangyou.xianni.space.logic.edit.GetBirthdayItemLogic;
import com.chuangyou.xianni.space.logic.edit.ISpaceLogic;
import com.chuangyou.xianni.space.logic.op.EggsOpLogic;
import com.chuangyou.xianni.space.logic.op.FlowerOpLogic;
import com.chuangyou.xianni.space.logic.op.ISpaceOpLogic;
import com.chuangyou.xianni.space.logic.op.LikeOpLogic;

public class SpaceLogicFactory {
	
	/**
	 * //操作：1：头像  2：名字 3：签名  4：城市  5：生日   6:禁止留言  7：取消禁止留言
	 * @param op
	 * @return
	 */
	public static ISpaceLogic getSpaceLogic(int op){
		ISpaceLogic logic = null;
		switch(op){
		case 1:
			logic = new EditFaceLogic();
			break;
		case 2:
			logic = new EditNameLogic();
			break;
		case 3:
			logic = new EditSignatureLogic();
			break;
		case 4:
			logic = new EditCityLogic();
			break;
		case 5:
			logic = new EditBirthdayLogic();
			break;
		case 6:
			logic = new EditNoMsgLogic();
			break;
		case 7:
			logic = new GetBirthdayItemLogic();
			break;
		}
		return logic;
	}
	
	/**
	 * 获取空间操作逻辑
	 * @param op  //操作：1：点赞  2：送花 3：鸡蛋
	 * @return
	 */
	public static ISpaceOpLogic getOpLogic(int op){
		ISpaceOpLogic logic = null;
		switch(op){
		case 1:
			logic = new LikeOpLogic();
			break;
		case 2:
			logic = new FlowerOpLogic();
			break;
		case 3:
			logic = new EggsOpLogic();
			break;
		}
		return logic;
	}
	
	/**
	 *  获取收藏相关的逻辑
	 * @param op 1：收藏指定留言  2：取消指定留言的收藏 3：新增留言收藏数量  4:取消全部收藏
	 * @return
	 */
	public static ISpaceCollectionLogic getCollectionLogic(int op){
		ISpaceCollectionLogic logic = null;
		switch(op){
		case 1:
			logic = new AddCollectionLogic();
			break;
		case 2:
			logic = new DelCollectionLogic();
			break;
		case 3:
			logic = new SetCollectionNumLogic();
			break;
		case 4:
			logic = new DelAllCollectionLogic();
			break;
		}
		return logic;
	}
	
}
