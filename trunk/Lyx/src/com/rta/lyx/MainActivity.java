package com.rta.lyx;

import com.rta.lyx.framework.scene.AndroidGame;
import com.rta.lyx.framework.scene.Screen;

public class MainActivity extends AndroidGame
{

	@Override
	public Screen getInitScreen()
	{
		// TODO Auto-generated method stub
		return new MainScreen(this);
	}

	/*@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}*/
}
