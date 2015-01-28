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
	float f = 0.0f;
	
	public MainScreen(Game game)
	{
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime)
	{
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN)
			{

				if (inBounds(event, 0, 0, 200, 200))
				{
					// game.setScreen(new GameScreen(game));
					f += 5;
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
		g.drawRect(0, 0, 240, 800, Color.RED);
		g.drawRect(1040, 0, 240, 800, Color.RED);
		g.drawImage(Assets.getImage("img1"), (int)f, 400);
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
