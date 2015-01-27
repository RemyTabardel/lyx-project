package com.rta.lyx.framework.scene;

import com.rta.lyx.framework.audio.Audio;
import com.rta.lyx.framework.graphics.Graphics;
import com.rta.lyx.framework.input.Input;
import com.rta.lyx.framework.system.FileIO;

public interface Game
{

	public Audio getAudio();

	public Input getInput();

	public FileIO getFileIO();

	public Graphics getGraphics();

	public void setScreen(Screen screen);

	public Screen getCurrentScreen();

	public Screen getInitScreen();
}