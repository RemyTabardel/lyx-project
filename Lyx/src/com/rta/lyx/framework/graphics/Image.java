package com.rta.lyx.framework.graphics;

import com.rta.lyx.framework.graphics.Graphics.ImageFormat;

public interface Image
{
	public int getWidth();

	public int getHeight();

	public ImageFormat getFormat();

	public void dispose();
}
