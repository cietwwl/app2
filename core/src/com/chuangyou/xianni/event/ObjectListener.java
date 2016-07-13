package com.chuangyou.xianni.event;

import java.util.EventListener;

public interface ObjectListener extends EventListener {
	
	public void onEvent(ObjectEvent event);

}
