package com.rta.framework.input;

import com.rta.framework.math.Vector2;
import com.rta.framework.physics.BoundingAABB;
import com.rta.framework.scene.Game;

public class Dpad
{
	private static final int	UP		= 0;
	private static final int	DOWN	= 1;
	private static final int	LEFT	= 2;
	private static final int	RIGHT	= 3;

	private Vector2				position;
	public BoundingAABB[]		boundingAABB;

	public Dpad(int width, int height)
	{
		this.position = new Vector2(0 + width + (height / 2), Game.SCREEN_HEIGHT - width - (height / 2));
		boundingAABB = new BoundingAABB[4];

		boundingAABB[UP] = new BoundingAABB(new Vector2(position.x, position.y - (width / 2) - (height / 2)), height, width);
		boundingAABB[DOWN] = new BoundingAABB(new Vector2(position.x, position.y + (width / 2) + (height / 2)), height, width);
		boundingAABB[LEFT] = new BoundingAABB(new Vector2(position.x - (width / 2) - (height / 2), position.y), width, height);
		boundingAABB[RIGHT] = new BoundingAABB(new Vector2(position.x + (width / 2) + (height / 2), position.y), width, height);
	}

	public boolean isPressDown(Vector2 point)
	{
		return boundingAABB[DOWN].isCollide(point);
	}

	public boolean isPressUp(Vector2 point)
	{
		return boundingAABB[UP].isCollide(point);
	}

	public boolean isPressLeft(Vector2 point)
	{
		return boundingAABB[LEFT].isCollide(point);
	}

	public boolean isPressRight(Vector2 point)
	{
		return boundingAABB[RIGHT].isCollide(point);
	}
}
