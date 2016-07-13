package com.chuangyou.xianni.warfield.helper;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Rect;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshSeeker;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshTriangle;

public class ParseMapDataHelper {

	/**
	 * 解析成Seeker
	 * @param data
	 * @return
	 */
	public static NavmeshSeeker parse2Seeker(byte[] data)
	{
		ByteBuf buf = Unpooled.buffer(data.length).order(ByteOrder.LITTLE_ENDIAN);
        buf.writeBytes(data);
        int step = buf.readInt();
        Rect rect = new Rect();
        rect.setxMax(buf.readFloat());
        rect.setyMax(buf.readFloat());
        rect.setxMin(buf.readFloat());
        rect.setyMin(buf.readFloat());
        //_mapBounds.put(configName, rect);
        List<NavmeshTriangle> triangles = new ArrayList<NavmeshTriangle>();
        int size = buf.readInt();
        for (int j = 0; j < size; j++)
        {
            int id = buf.readInt();
            Vector3 pos1 = new Vector3(buf.readFloat(), buf.readFloat(), buf.readFloat());
            Vector3 pos2 = new Vector3(buf.readFloat(), buf.readFloat(), buf.readFloat());
            Vector3 pos3 = new Vector3(buf.readFloat(), buf.readFloat(), buf.readFloat());
            NavmeshTriangle tri = new NavmeshTriangle(id, pos1, pos2, pos3);
            tri.setNeigbhor(0, buf.readInt());
            tri.setNeigbhor(1, buf.readInt());
            tri.setNeigbhor(2, buf.readInt());
            triangles.add(tri);
        }
        Map<Integer, List<Integer>> grids = new HashMap<Integer, List<Integer>>();
        int gridCount = buf.readInt();
        for(int g = 0; g<gridCount; g++)
        {
        	int index = buf.readInt();
        	int tirSize = buf.readInt();
        	List<Integer> triIds = new ArrayList<Integer>();
        	for(int t = 0; t<tirSize; t++)
        	{
        		triIds.add(buf.readInt());
        	}
        	grids.put(index, triIds);
        }
        NavmeshSeeker seeker = new NavmeshSeeker();
        seeker.SetData(triangles, grids, rect, step);
        return seeker;
	}
}
