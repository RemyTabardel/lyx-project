package com.rta.framework.scene;


public class FastUpdateLogic implements Runnable
{
	Game				game;
	Thread				updateThread	= null;
	volatile boolean	running			= false;

	public FastUpdateLogic(Game game)
	{
		this.game = game;
	}

	public void resume()
	{
		running = true;
		updateThread = new Thread(this);
		updateThread.start();

	}

	@Override
	public void run()
	{
		long startTime = System.currentTimeMillis();
		while (running)
		{
			float deltaTime = (System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();

			/*if (deltaTime > 3.15)
			{
				deltaTime = 3.15f;
			}*/
		
			game.update(deltaTime);
		}
	}

	public void pause()
	{
		running = false;
		while (true)
		{
			try
			{
				updateThread.join();
				break;
			}
			catch (InterruptedException e)
			{
				// retry
			}

		}
	}
}
