package com.rta.lyx;

import com.rta.framework.scene.Game;
import com.rta.framework.scene.Screen;

public class MainActivity extends Game
{

	@Override
	public Screen getInitScreen()
	{
		// TODO Auto-generated method stub
		return new AssetsScreen(this);
	}

	/*@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}*/
}
