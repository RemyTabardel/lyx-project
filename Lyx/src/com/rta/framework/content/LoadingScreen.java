package com.rta.framework.content;

import com.rta.framework.audio.Music;
import com.rta.framework.audio.Sound;
import com.rta.framework.graphics.Graphics.ImageFormat;
import com.rta.framework.graphics.Graphics;
import com.rta.framework.graphics.Image;
import com.rta.framework.scene.Game;
import com.rta.framework.scene.Screen;

public abstract class LoadingScreen extends Screen
{

	public LoadingScreen(Game game)
	{
		super(game);
	}

	public void loadMusic(String key, String filename)
	{
		Assets.addMusic(key, game.getAudio().createMusic(filename));
	}

	public void loadSound(String key, String filename)
	{
		Assets.addSound(key, game.getAudio().createSound(filename));
	}

	public void loadImage(String key, String filename)
	{
		Assets.addImage(key, game.getGraphics().newImage(filename, ImageFormat.ARGB8888));
	}

	public abstract void load();

	public abstract Screen getInitScreen();

	@Override
	public void update(float deltaTime)
	{
		load();

		game.setScreen(getInitScreen());
	}

	@Override
	public void paint(float deltaTime)
	{

	}

	@Override
	public void pause()
	{

	}

	@Override
	public void resume()
	{

	}

	@Override
	public void dispose()
	{

	}

	@Override
	public void backButton()
	{

	}

}
