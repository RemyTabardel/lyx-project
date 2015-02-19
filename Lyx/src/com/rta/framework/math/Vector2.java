package com.rta.framework.math;

public class Vector2
{
	private float	x, y;

	public Vector2()
	{
		this(0.0f, 0.0f);
	}

	public Vector2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}
	
	
}
