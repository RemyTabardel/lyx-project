package com.rta.framework.input;

import java.util.List;

import android.graphics.Color;

import com.rta.framework.graphics.Graphics;
import com.rta.framework.gui.Button;
import com.rta.framework.input.Input.TouchEvent;
import com.rta.framework.math.Vector2;
import com.rta.framework.physics.BoundingCircle;
import com.rta.framework.scene.Game;

public class Controller
{
	private Dpad	dpad;
	private Button	buttonA, buttonB;
	Events			events	= new Events();

	public Controller()
	{
		dpad = new Dpad(90, 90);

		int radiusButton = 60;
		buttonA = new Button(new Vector2(Game.SCREEN_WIDTH - (int) (radiusButton * 3.5), Game.SCREEN_HEIGHT - radiusButton), radiusButton);
		buttonB = new Button(new Vector2(Game.SCREEN_WIDTH - radiusButton, Game.SCREEN_HEIGHT - (int) (radiusButton * 2.5)), radiusButton);

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
				if (dpad.boundingAABB.isCollide(point) == true)
				{
					events.up = dpad.isPressUp(point);
					events.down = dpad.isPressDown(point);
					events.left = dpad.isPressLeft(point);
					events.right = dpad.isPressRight(point);
				}
			}
			else if (touchEvent.type == TouchEvent.TOUCH_DOWN)
			{
				if (dpad.boundingAABB.isCollide(point) == true)
				{
					if (dpad.isPressUp(point))
					{
						events.up = true;
					}
					else if (dpad.isPressDown(point))
					{
						events.down = true;
					}
					else if (dpad.isPressLeft(point))
					{
						events.left = true;
					}
					else if (dpad.isPressRight(point))
					{
						events.right = true;
					}
				}
				else
				{

					if (buttonA.isClicked(point))
					{
						events.a = true;
					}
					else if (buttonB.isClicked(point))
					{
						events.b = true;
					}
				}
			}
			else if (touchEvent.type == TouchEvent.TOUCH_UP)
			{
				if (dpad.boundingAABB.isCollide(point) == true)
				{
					if (dpad.isPressUp(point))
					{
						events.up = false;
					}
					else if (dpad.isPressDown(point))
					{
						events.down = false;
					}
					else if (dpad.isPressLeft(point))
					{
						events.left = false;
					}
					else if (dpad.isPressRight(point))
					{
						events.right = false;
					}
				}
			}
		}

		return events;
	}

	public void paint(Graphics g)// D
	{
		// g.drawBoundingShape(dpad.boundingAABB, Color.BLUE);
		g.drawBoundingShape(dpad.arBoundingAABB[0], Color.GREEN);
		g.drawBoundingShape(dpad.arBoundingAABB[1], Color.GREEN);
		g.drawBoundingShape(dpad.arBoundingAABB[2], Color.GREEN);
		g.drawBoundingShape(dpad.arBoundingAABB[3], Color.GREEN);

		g.drawBoundingShape((BoundingCircle) buttonA.getBoundingShape(), Color.RED);
		g.drawBoundingShape((BoundingCircle) buttonB.getBoundingShape(), Color.RED);
	}
}
