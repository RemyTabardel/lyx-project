package com.rta.framework.input;

import java.util.List;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

public class Input
{
	TouchHandler	touchHandler;

	public static class TouchEvent
	{
		public static final int	TOUCH_DOWN		= 0;
		public static final int	TOUCH_UP		= 1;
		public static final int	TOUCH_DRAGGED	= 2;
		public static final int	TOUCH_HOLD		= 3;

		public int				type;
		public int				x, y;
		public int				pointer;

	}
	
	public Input(Context context, View view, float scaleX, float scaleY)
	{
		if (VERSION.SDK_INT < 5)
			touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
		else
			touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
	}

	public boolean isTouchDown(int pointer)
	{
		return touchHandler.isTouchDown(pointer);
	}

	public int getTouchX(int pointer)
	{
		return touchHandler.getTouchX(pointer);
	}

	public int getTouchY(int pointer)
	{
		return touchHandler.getTouchY(pointer);
	}

	public List<TouchEvent> getTouchEvents()
	{
		return touchHandler.getTouchEvents();
	}

}
