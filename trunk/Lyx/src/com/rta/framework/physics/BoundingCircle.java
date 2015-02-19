package com.rta.framework.physics;

import com.rta.framework.math.Vector2;

public class BoundingCircle extends BoundingShape
{
	private int	radius;

	public BoundingCircle(Vector2 position, int radius)
	{
		super(position);
		this.radius = radius;
	}

	@Override
	public void update(Vector2 position)
	{
		// TODO Auto-generated method stub
	}

	public int getRadius()
	{
		return radius;
	}

	public void setRadius(int radius)
	{
		this.radius = radius;
	}

	@Override
	public boolean isCollide(Vector2 point)
	{
		int d2 = (int) ((point.x - this.position.x) * (point.x - this.position.x) + (point.y - this.position.y) * (point.y - this.position.y));

		if (d2 > (this.radius * this.radius))
		{
			return false;
		}

		return true;
	}
}
