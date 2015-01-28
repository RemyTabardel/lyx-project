package com.rta.framework.input;

import java.util.List;

import com.rta.framework.input.Input.TouchEvent;
import com.rta.framework.math.Vector2;

public class Joystick
{
	Vector2	direction;
	int		posX, posY;
	int		radius;

	public Joystick(int posX, int posY, int radius)
	{
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
	}

	public void update(float deltaTime, List<TouchEvent> touchEvents)
	{
		int len = touchEvents.size();
		for (int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DRAGGED)
			{

				/*if (inBounds(event, 0, 500, 300, 300))
				{
					// game.setScreen(new GameScreen(game));
					joyx = (event.x-150);
					joyy = (event.y-150);
					
					vectx = (event.x-150)*0.02f;
					vecty = (event.y-650)*0.02f;

				}
				else
				{
					vectx = 0;
					vecty = 0;
					 joyx = 150;
					 joyy = 650;
				}*/

			}
		}
	}
}
