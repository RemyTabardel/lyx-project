package com.rta.lyx.framework;

import com.rta.lyx.framework.Graphics.ImageFormat;

public interface Image
{
	public int getWidth();

	public int getHeight();

	public ImageFormat getFormat();

	public void dispose();
}
