package com.rta.lyx;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.rta.framework.graphics.Animation;
import com.rta.framework.graphics.Graphics;
import com.rta.framework.graphics.SpriteSheet;
import com.rta.framework.input.Events;
import com.rta.framework.math.Vector2;
import com.rta.framework.scene.Game;

public class MainActivity extends Game
{
	List<Vector2>	listPoints	= new ArrayList<Vector2>();
	int				x, y;
	
	Animation animation = new Animation(16, 100, true);
	SpriteSheet ss;
	
	@Override
	public void update(float deltaTime, Events events)
	{
		animation.update(deltaTime);
		
		x = 0;
		y = 0;
		
		if (events.a == true)
		{
			listPoints.add(new Vector2(Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2));
		}
		if (events.b == true)
		{
			if (listPoints.size() > 0)
			{
				listPoints.remove(0);
			}
		}
		if (events.up)
		{
			y = -1;
		}
		if (events.down)
		{
			y = 1;
		}
		if (events.right)
		{
			x = 1;
		}
		if (events.left)
		{
			x = -1;
		}

	}

	@Override
	public void paint()
	{
		Graphics graphics = getGraphics();

		for (Vector2 point : listPoints)
		{
			point.x += (x * 2);
			point.y += (y * 2);
			graphics.drawCircle((int) point.x, (int) point.y, 20, Color.RED);
		
		}
		
		if(ss == null)
		{
			ss = new SpriteSheet("", graphics);
		}
		
		ss.paint(graphics, animation, x, y);
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
