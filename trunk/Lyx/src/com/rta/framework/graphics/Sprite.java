package com.rta.framework.graphics;

import com.rta.framework.graphics.Graphics.ImageFormat;

public class Sprite
{
	private String	name;
	private boolean	isLoaded	= false;
	private Image	image		= null;

	public Sprite(String name)
	{
		this.name = name;
	}

	public void paint(Graphics graphics, int x, int y)
	{
		if(isLoaded == false)
		{
			image = graphics.newImage(name, ImageFormat.RGB565);
		}
		
		graphics.drawImage(image, x, y);
	}
}
