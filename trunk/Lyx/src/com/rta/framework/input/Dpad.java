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
	public BoundingAABB[]		arBoundingAABB;
	public BoundingAABB		boundingAABB;

	public Dpad(int buttonWidth, int buttonHeight)
	{
		this.position = new Vector2(0 + buttonWidth + (buttonHeight / 2), Game.SCREEN_HEIGHT - buttonWidth - (buttonHeight / 2));
		
		boundingAABB = new BoundingAABB(position, buttonWidth*4, buttonHeight*4);
		
		arBoundingAABB = new BoundingAABB[4];
		arBoundingAABB[UP] = new BoundingAABB(new Vector2(position.x, position.y - (buttonWidth / 2) - (buttonHeight / 2)), buttonHeight, buttonWidth);
		arBoundingAABB[DOWN] = new BoundingAABB(new Vector2(position.x, position.y + (buttonWidth / 2) + (buttonHeight / 2)), buttonHeight, buttonWidth);
		arBoundingAABB[LEFT] = new BoundingAABB(new Vector2(position.x - (buttonWidth / 2) - (buttonHeight / 2), position.y), buttonWidth, buttonHeight);
		arBoundingAABB[RIGHT] = new BoundingAABB(new Vector2(position.x + (buttonWidth / 2) + (buttonHeight / 2), position.y), buttonWidth, buttonHeight);
	}

	public boolean isPressDown(Vector2 point)
	{
		return arBoundingAABB[DOWN].isCollide(point);
	}

	public boolean isPressUp(Vector2 point)
	{
		return arBoundingAABB[UP].isCollide(point);
	}

	public boolean isPressLeft(Vector2 point)
	{
		return arBoundingAABB[LEFT].isCollide(point);
	}

	public boolean isPressRight(Vector2 point)
	{
		return arBoundingAABB[RIGHT].isCollide(point);
	}
}
