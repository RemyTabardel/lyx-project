package com.rta.lyx;

import android.graphics.Color;

import com.rta.framework.graphics.Graphics;
import com.rta.framework.input.Dpad;
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
		int x = 600;
		int y = 300;
		
		Dpad dpad = new Dpad(80, 70);
		
		Graphics g = this.getGraphics();
		g.clearScreen(Color.BLACK);
		// g.drawRect(0, 500, 300, 300, Color.RED);
		g.drawBoundingShape(dpad.boundingAABB[0], Color.GREEN);
		g.drawBoundingShape(dpad.boundingAABB[1], Color.GREEN);
		g.drawBoundingShape(dpad.boundingAABB[2], Color.GREEN);
		g.drawBoundingShape(dpad.boundingAABB[3], Color.GREEN);
		
		//g.drawCircle(x, y, 10, Color.RED);
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
