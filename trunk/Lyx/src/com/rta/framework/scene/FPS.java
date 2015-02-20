package com.rta.framework.scene;

import com.rta.framework.graphics.Graphics;

import android.graphics.Color;
import android.graphics.Paint;

public class FPS
{
	private long currentTime = 0;
	private long lastTime = 0;
	private int fps = 0;
	private int n = 0;
	private Paint paint = new Paint();

	
	public FPS(Graphics graphics)
	{
		paint.setTextSize(40);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
	}
	
	public void update()
	{
		n++;
		currentTime = System.currentTimeMillis();
		
		if((currentTime - lastTime) >= 1000)
		{
			fps = n;
			n = 0;
			lastTime = currentTime;
		}
	}
	
	public void paint(Graphics graphics)
	{
		graphics.drawString("FPS : " + fps, Game.SCREEN_WIDTH - 100, 40, paint);
	}
	
	public int get()
	{
		return fps;
	}
}
