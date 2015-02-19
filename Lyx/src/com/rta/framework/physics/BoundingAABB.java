package com.rta.framework.physics;

import com.rta.framework.math.Vector2;

public class BoundingAABB extends BoundingShape
{
	private int		width, height;
	private Vector2	pointTopLeft;	// point qui permet de calculer les collisions plus simplement

	public BoundingAABB(Vector2 position, int width, int height)
	{
		super(position);
		this.width = width;
		this.height = height;
		this.pointTopLeft = computePointTopLeft();
	}

	private Vector2 computePointTopLeft()
	{
		Vector2 point = new Vector2();
		point.setX(position.getX() - (width / 2));
		point.setY(position.getY() - (height / 2));

		return point;
	}

	@Override
	public void update(Vector2 position)
	{
		this.position = position;
		this.pointTopLeft = computePointTopLeft();
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public void setPointTopLeft(Vector2 pointTopLeft)
	{
		this.pointTopLeft = pointTopLeft;
	}

	public Vector2 getPointTopLeft()
	{
		return pointTopLeft;
	}
	
	

}
