package com.chuangyou.xianni.role.action;

import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Material;

public class MaterialAutoDestroy extends DelayAction {

	Material mat;
	
	public MaterialAutoDestroy(Material queue, int delay) {
		super(queue, delay);
		mat = queue;
	}

	@Override
	public void execute() {
		if(mat.getLivingState() == Living.DISTORY) 
		{
			return;
		}
		if(mat.getField() != null)
			mat.getField().leaveField(mat, true);
		mat.destory();
	}

}
