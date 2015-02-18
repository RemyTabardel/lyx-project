package com.rta.lyx;

import android.graphics.Color;

import com.rta.framework.graphics.Graphics;
import com.rta.framework.scene.Game;

public class MainActivity extends Game
{
	float posX = 0;
	
	@Override
	public void update(float deltaTime)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(float deltaTime)
	{
		posX += 0.1f;
		Graphics g = this.getGraphics();
		g.clearScreen(Color.BLACK);
		//g.drawRect(0, 500, 300, 300, Color.RED);
		g.drawCircle((int)posX, 650, 100, Color.GREEN);
		//g.drawRect((int)joyx+50, (int)joyy+50, 100, 100, Color.BLUE);
		// g.drawRect(1040, 0, 240, 800, Color.RED);
		//g.drawImage(Assets.getImage("img1"), (int)posx, (int)posy);
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
