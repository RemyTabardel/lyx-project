package com.rta.framework.graphics;

import com.rta.framework.scene.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FastRenderView extends SurfaceView implements Runnable
{
	Game				game;
	Bitmap				framebuffer;
	Thread				renderThread	= null;
	SurfaceHolder		holder;
	volatile boolean	running			= false;

	public FastRenderView(Game game, Bitmap framebuffer)
	{
		super(game);
		this.game = game;
		this.framebuffer = framebuffer;
		this.holder = getHolder();

	}

	public void resume()
	{
		running = true;
		renderThread = new Thread(this);
		renderThread.start();

	}

	@Override
	public void run()
	{
		Rect dstRect = new Rect();
		long startTime = System.currentTimeMillis();
		while (running)
		{
			if (!holder.getSurface().isValid())
				continue;

			float deltaTime = (System.currentTimeMillis() - startTime);
			startTime = System.currentTimeMillis();

			/*if (deltaTime > 3.15)
			{
				deltaTime = 3.15f;
			}*/
		
			//game.update(deltaTime);
			game.paint(deltaTime);

			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(dstRect);
			canvas.drawBitmap(framebuffer, null, dstRect, null);
			holder.unlockCanvasAndPost(canvas);

		}
	}

	public void pause()
	{
		running = false;
		while (true)
		{
			try
			{
				renderThread.join();
				break;
			}
			catch (InterruptedException e)
			{
				// retry
			}

		}
	}

}
