package com.rta.framework.physics;

import com.rta.framework.math.Vector2;

public abstract class BoundingShape
{
	protected Vector2	position;	// position au centre de la forme

	public BoundingShape(Vector2 position)
	{
		this.position = position;
	}

	public abstract void update(Vector2 position);

	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	public abstract boolean isCollide(Vector2 point);
}
