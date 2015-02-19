package com.rta.framework.gui;

import com.rta.framework.math.Vector2;
import com.rta.framework.physics.BoundingAABB;
import com.rta.framework.physics.BoundingCircle;
import com.rta.framework.physics.BoundingShape;

public class Button
{
	private Vector2			position;
	private BoundingShape	boundingShape;

	private Button(Vector2 position)
	{
		this.position = position;
	}

	public Button(Vector2 position, int radius)
	{
		this(position);
		this.boundingShape = new BoundingCircle(position, radius);
	}

	public Button(Vector2 position, int width, int height)
	{
		this(position);
		this.boundingShape = new BoundingAABB(position, width, height);
	}

	public BoundingShape getBoundingShape()
	{
		return boundingShape;
	}
	
	public boolean isClicked(Vector2 point)
	{
		return this.boundingShape.isCollide(point);
	}

}
