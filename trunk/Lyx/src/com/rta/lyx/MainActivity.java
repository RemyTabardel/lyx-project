package com.rta.lyx;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.Color;
import android.graphics.Paint;

import com.rta.framework.graphics.Animation;
import com.rta.framework.graphics.Graphics;
import com.rta.framework.graphics.SpriteSheet;
import com.rta.framework.input.Events;
import com.rta.framework.math.Vector2;
import com.rta.framework.scene.Game;

public class MainActivity extends Game
{
	// avec array copy pas besoin de synchronized
	CopyOnWriteArrayList<Vector2>	listPoints	= new CopyOnWriteArrayList<Vector2>();
	// List<Vector2> listPoints = Collections.synchronizedList(new ArrayList<Vector2>());
	int								x, y;
	Paint							paint		= new Paint();

	Animation						animation	= new Animation(16, 100, true);
	SpriteSheet						ss;

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
		if (paint == null)
		{
			paint.setTextSize(40);
			paint.setTextAlign(Paint.Align.CENTER);
			paint.setAntiAlias(true);
			paint.setColor(Color.WHITE);
		}

		Graphics graphics = getGraphics();
		int nbPoints = 0;
		// List<Vector2> list = Collections.synchronizedList(listPoints);

		nbPoints = listPoints.size();
		Iterator<Vector2> iterator = listPoints.iterator();
		while (iterator.hasNext())
		{
			Vector2 point = iterator.next();
			point.x += (x * 2);
			point.y += (y * 2);
			graphics.drawCircle((int) point.x, (int) point.y, 20, Color.RED);
		}

		graphics.drawString("Nb points : " + nbPoints, SCREEN_WIDTH - 270, 120, paint);
		if (ss == null)
		{
			ss = new SpriteSheet("", graphics);
		}

		// ss.paint(graphics, animation, x, y);
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
