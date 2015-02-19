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
}
