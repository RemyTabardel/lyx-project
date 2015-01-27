package com.rta.framework.graphics;

import android.graphics.Bitmap;

import com.rta.framework.graphics.Graphics.ImageFormat;

public class Image
{
	Bitmap		bitmap;
	ImageFormat	format;

	public Image(Bitmap bitmap, ImageFormat format)
	{
		this.bitmap = bitmap;
		this.format = format;
	}

	public int getWidth()
	{
		return bitmap.getWidth();
	}

	public int getHeight()
	{
		return bitmap.getHeight();
	}

	public ImageFormat getFormat()
	{
		return format;
	}

	public void dispose()
	{
		bitmap.recycle();
	}
}