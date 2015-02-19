package com.rta.lyx;

import android.graphics.Color;

import com.rta.framework.graphics.Graphics;
import com.rta.framework.math.Vector2;
import com.rta.framework.physics.BoundingAABB;
import com.rta.framework.scene.Game;

public class MainActivity extends Game
{
	@Override
	public void update(float deltaTime)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(float deltaTime)
	{
		BoundingAABB boundingAABB = new BoundingAABB(new Vector2(600, 400), 100, 50);
		Graphics g = this.getGraphics();
		g.clearScreen(Color.BLACK);
		// g.drawRect(0, 500, 300, 300, Color.RED);
		g.drawBoundingShape(boundingAABB, Color.GREEN);
		g.drawCircle(600, 400, 40, Color.RED);
		// g.drawRect((int)joyx+50, (int)joyy+50, 100, 100, Color.BLUE);
		// g.drawRect(1040, 0, 240, 800, Color.RED);
		// g.drawImage(Assets.getImage("img1"), (int)posx, (int)posy);
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
