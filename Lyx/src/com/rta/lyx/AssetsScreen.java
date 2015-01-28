package com.rta.lyx;

import com.rta.framework.content.LoadingScreen;
import com.rta.framework.scene.Game;
import com.rta.framework.scene.Screen;

public class AssetsScreen extends LoadingScreen
{

	public AssetsScreen(Game game)
	{
		super(game);
	}

	@Override
	public void load()
	{
		loadImage("img1", "kn_img_toast_ok.png");
	}

	@Override
	public Screen getInitScreen()
	{
		return new MainScreen(game);
	}
}
