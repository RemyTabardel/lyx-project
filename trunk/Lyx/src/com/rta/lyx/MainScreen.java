package com.rta.lyx;

import java.util.List;

import android.graphics.Color;

import com.rta.framework.content.Assets;
import com.rta.framework.graphics.Graphics;
import com.rta.framework.input.Input.TouchEvent;
import com.rta.framework.scene.Game;
import com.rta.framework.scene.Screen;

public class MainScreen extends Screen
{
	float		vectx		= 0;
	float		vecty		= 0;
	float	posx	= 0;
	float	posy	= 0;
	int joyx = 150;
	int joyy = 650;
	
	public MainScreen(Game game)
	{
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime)
	{
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		posx += vectx;
		posy += vecty;
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DRAGGED)
			{

				if (inBounds(event, 0, 500, 300, 300))
				{
					// game.setScreen(new GameScreen(game));
					joyx = (event.x-150);
					joyy = (event.y-150);
					
					vectx = (event.x-150)*0.04f;
					vecty = (event.y-650)*0.04f;

				}
				else
				{
					vectx = 0;
					vecty = 0;
					 joyx = 150;
					 joyy = 650;
				}

			}
			else if (event.type == TouchEvent.TOUCH_UP)
			{

				if (inBounds(event, 0, 500, 300, 300))
				{
					vectx = 0;
					vecty = 0;
					 joyx = 150;
					 joyy = 650;
				}

			}
		}

	}

	private boolean inBounds(TouchEvent event, int x, int y, int width, int height)
	{
		if (event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void paint(float deltaTime)
	{
		Graphics g = game.getGraphics();
		g.clearScreen(Color.BLACK);
		//g.drawRect(0, 500, 300, 300, Color.RED);
		g.drawCircle(150, 650, 100, Color.GREEN);
		//g.drawRect((int)joyx+50, (int)joyy+50, 100, 100, Color.BLUE);
		// g.drawRect(1040, 0, 240, 800, Color.RED);
		g.drawImage(Assets.getImage("img1"), (int)posx, (int)posy);
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton()
	{
		// TODO Auto-generated method stub

	}

}
