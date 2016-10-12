package com.chuangyou.xianni.truck.helper;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.MathUtils;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.role.objects.Material;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshSeeker;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshTriangle;

public class MaterialSpwanHelper {

	/**
	 * 最多个数
	 */
	private static final int MAX_PACKAGE_COUNT = 10;
	
	public static void main(String[] args)
	{
		MaterialSpwan(null, 100);
	}
	
	/**
	 * 物质生成
	 */
	public static void MaterialSpwan(Truck truck, int count)
	{
		List<Integer> counts = null;
		if(MAX_PACKAGE_COUNT < count)
		{
			counts = initCounts(MAX_PACKAGE_COUNT);
			int remain = count - MAX_PACKAGE_COUNT;
			int cursor = 0;
			while(remain > 0)
			{
				int cut = (int) Math.ceil(Math.random() * remain / (counts.size() - cursor));
				counts.set(cursor, counts.get(cursor) + cut);
				remain = remain - cut;
				cursor++;
				if(cursor >= counts.size())
					cursor = 0;
			}
		}
		else
		{
			counts = initCounts(count);
		}
		Vector3 vaildPoint = truck.getPostion();
		for(int i = 0; i<counts.size(); i++)
		{
			Material mat = new Material(truck.getId(), IDMakerHelper.nextID(), truck.getTrucktype());
			Vector3 point = MathUtils.GetRandomVector3ByCenter(truck.getPostion(), 3);
			NavmeshSeeker seeker = FieldMgr.getIns().GetSeekerTemp(
					truck.getField().getFieldInfo().getResName());
			NavmeshTriangle tri = seeker.getTriangle(vaildPoint);
			if(tri == null) 
				point = vaildPoint;
			mat.setPostion(point);
			mat.setProperty(EnumAttr.METAL, counts.get(i));	//添加物质
			if(mat.getTruckType() == Truck.TRUCK_P)
			{
				//个人运镖物资的itemtype
				mat.setProperty(EnumAttr.WOOD, SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual"));
			}
			else
			{
				//帮派运镖物资的itemtype
				mat.setProperty(EnumAttr.WOOD, SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction"));
			}
			truck.getField().enterField(mat);
		}
	}
	
	/**
	 * 初始化数列
	 */
	private static List<Integer> initCounts(int count)
	{
		List<Integer> lst = new ArrayList<Integer>();
		for(int i = 0; i<count; i++)
		{
			lst.add(1);
		}
		return lst;
	}
}
