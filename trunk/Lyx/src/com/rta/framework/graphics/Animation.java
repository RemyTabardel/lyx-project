package com.rta.framework.graphics;

public class Animation
{
	private int		currentFrame	= 0;
	private int		maxFrames;
	private float	currentTime		= 0.0f;
	private float	maxTime;
	private boolean	loop;
	private boolean	isFinished		= false;

	public Animation(int maxFrames, float maxTime, boolean loop)
	{
		super();
		this.maxFrames = maxFrames;
		this.maxTime = maxTime;
		this.loop = loop;
	}

	public void update(float deltaTime)
	{
		if (isFinished == false)
		{
			currentTime += deltaTime;

			if (currentTime >= maxTime)
			{
				currentTime = currentTime - maxTime;
				currentFrame++;

				if (currentFrame >= maxFrames)
				{
					if (loop == true)
					{
						currentFrame = 0;
					}
					else
					{
						currentFrame--;
						isFinished = true;
					}
				}
			}
		}
	}

	public int getCurrentFrame()
	{
		return currentFrame;
	}

	public boolean isFinished()
	{
		return isFinished;
	}

}
