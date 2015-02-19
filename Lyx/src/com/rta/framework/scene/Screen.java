package com.rta.framework.scene;

interface Screen
{
	public abstract void update(Float deltaTime);

	public abstract void paint(Float deltaTime);

	public abstract void pause();

	public abstract void resume();

	public abstract void dispose();

	public abstract void backButton();
}
