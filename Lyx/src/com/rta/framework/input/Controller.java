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

	public Controller()
	{
		dpad = new Dpad(90, 90);

		int radiusButton = 60;
		buttonA = new Button(new Vector2(Game.SCREEN_WIDTH - (int) (radiusButton * 3.5), Game.SCREEN_HEIGHT - radiusButton), radiusButton);
		buttonB = new Button(new Vector2(Game.SCREEN_WIDTH - radiusButton, Game.SCREEN_HEIGHT - (int) (radiusButton * 2.5)), radiusButton);

	}

	public Events getEvents(List<TouchEvent> touchEvents)
	{
		Events events = new Events();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++)
		{
			TouchEvent touchEvent = touchEvents.get(i);
			Vector2 point = new Vector2(touchEvent.x, touchEvent.y);

			if (dpad.isTouchUp(point))
			{
				events.up = true;
			}
			else if (dpad.isTouchDown(point))
			{
				events.down = true;
			}
			else if (dpad.isTouchLeft(point))
			{
				events.left = true;
			}
			else if (dpad.isTouchRight(point))
			{
				events.right = true;
			}
			else
			{
				if (touchEvent.type == TouchEvent.TOUCH_DOWN)
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
		}

		return events;
	}

	public void paintdDebug(Graphics g)// D
	{
		g.drawBoundingShape(dpad.boundingAABB[0], Color.GREEN);
		g.drawBoundingShape(dpad.boundingAABB[1], Color.GREEN);
		g.drawBoundingShape(dpad.boundingAABB[2], Color.GREEN);
		g.drawBoundingShape(dpad.boundingAABB[3], Color.GREEN);

		g.drawBoundingShape((BoundingCircle) buttonA.getBoundingShape(), Color.RED);
		g.drawBoundingShape((BoundingCircle) buttonB.getBoundingShape(), Color.RED);
	}
}
