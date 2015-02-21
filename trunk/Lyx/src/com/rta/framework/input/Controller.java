package com.rta.framework.input;

import java.util.List;

import android.graphics.Color;

import com.rta.framework.graphics.Graphics;
import com.rta.framework.input.Input.TouchEvent;
import com.rta.framework.math.Vector2;
import com.rta.framework.physics.BoundingAABB;
import com.rta.framework.physics.BoundingCircle;
import com.rta.framework.physics.BoundingShape;
import com.rta.framework.scene.Game;

public class Controller
{
	private static final int	BUTTON_UP		= 0;
	private static final int	BUTTON_DOWN		= 1;
	private static final int	BUTTON_LEFT		= 2;
	private static final int	BUTTON_RIGHT	= 3;
	private static final int	BUTTON_A		= 4;
	private static final int	BUTTON_B		= 5;

	private BoundingShape[]		bButtons		= new BoundingShape[6];
	private BoundingAABB		bDPad;

	private Events				events			= new Events();

	public Controller()
	{
		buildDPad(90, 90);
		buildActions(60);
	}

	private void buildActions(int radius)
	{
		bButtons[BUTTON_A] = new BoundingCircle(new Vector2(Game.SCREEN_WIDTH - (int) (radius * 3.5), Game.SCREEN_HEIGHT - radius), radius);
		bButtons[BUTTON_B] = new BoundingCircle(new Vector2(Game.SCREEN_WIDTH - radius, Game.SCREEN_HEIGHT - (int) (radius * 2.5)), radius);
	}

	private void buildDPad(int buttonWidth, int buttonHeight)
	{
		// position calculée suivant la hauteur et largeur
		Vector2 position = new Vector2(0 + buttonWidth + (buttonHeight / 2), Game.SCREEN_HEIGHT - buttonWidth - (buttonHeight / 2));

		// Limite du DPad pour les touches dragged
		bDPad = new BoundingAABB(position, buttonWidth * 4, buttonHeight * 4);

		bButtons[BUTTON_UP] = new BoundingAABB(new Vector2(position.x, position.y - (buttonWidth / 2) - (buttonHeight / 2)), buttonHeight, buttonWidth);
		bButtons[BUTTON_DOWN] = new BoundingAABB(new Vector2(position.x, position.y + (buttonWidth / 2) + (buttonHeight / 2)), buttonHeight, buttonWidth);
		bButtons[BUTTON_LEFT] = new BoundingAABB(new Vector2(position.x - (buttonWidth / 2) - (buttonHeight / 2), position.y), buttonWidth, buttonHeight);
		bButtons[BUTTON_RIGHT] = new BoundingAABB(new Vector2(position.x + (buttonWidth / 2) + (buttonHeight / 2), position.y), buttonWidth, buttonHeight);
	}

	public void paint(Graphics g)
	{
		// g.drawBoundingShape(dpad.boundingAABB, Color.BLUE);
		g.drawBoundingShape((BoundingAABB) bButtons[BUTTON_UP], Color.GREEN);
		g.drawBoundingShape((BoundingAABB) bButtons[BUTTON_DOWN], Color.GREEN);
		g.drawBoundingShape((BoundingAABB) bButtons[BUTTON_LEFT], Color.GREEN);
		g.drawBoundingShape((BoundingAABB) bButtons[BUTTON_RIGHT], Color.GREEN);
		g.drawBoundingShape((BoundingCircle) bButtons[BUTTON_A], Color.RED);
		g.drawBoundingShape((BoundingCircle) bButtons[BUTTON_B], Color.RED);
	}

	public Events getEvents(List<TouchEvent> touchEvents)
	{
		int len = touchEvents.size();
		events.a = false;
		events.b = false;

		for (int i = 0; i < len; i++)
		{
			TouchEvent touchEvent = touchEvents.get(i);
			Vector2 point = new Vector2(touchEvent.x, touchEvent.y);

			if (touchEvent.type == TouchEvent.TOUCH_DRAGGED)
			{
				if (bDPad.isCollide(point) == true)
				{
					events.up = bButtons[BUTTON_UP].isCollide(point);
					events.down = bButtons[BUTTON_DOWN].isCollide(point);
					events.left = bButtons[BUTTON_LEFT].isCollide(point);
					events.right = bButtons[BUTTON_RIGHT].isCollide(point);
				}
			}
			else if (touchEvent.type == TouchEvent.TOUCH_DOWN)
			{
				if (bDPad.isCollide(point) == true)
				{
					if (bButtons[BUTTON_UP].isCollide(point))
					{
						events.up = true;
					}
					else if (bButtons[BUTTON_DOWN].isCollide(point))
					{
						events.down = true;
					}
					else if (bButtons[BUTTON_LEFT].isCollide(point))
					{
						events.left = true;
					}
					else if (bButtons[BUTTON_RIGHT].isCollide(point))
					{
						events.right = true;
					}
				}
				else
				{
					if (bButtons[BUTTON_A].isCollide(point))
					{
						events.a = true;
					}
					else if (bButtons[BUTTON_B].isCollide(point))
					{
						events.b = true;
					}
				}
			}
			else if (touchEvent.type == TouchEvent.TOUCH_UP)
			{
				if (bDPad.isCollide(point) == true)
				{
					if (bButtons[BUTTON_UP].isCollide(point))
					{
						events.up = false;
					}
					else if (bButtons[BUTTON_DOWN].isCollide(point))
					{
						events.down = false;
					}
					else if (bButtons[BUTTON_LEFT].isCollide(point))
					{
						events.left = false;
					}
					else if (bButtons[BUTTON_RIGHT].isCollide(point))
					{
						events.right = false;
					}
				}
			}
		}

		return events;
	}
}
