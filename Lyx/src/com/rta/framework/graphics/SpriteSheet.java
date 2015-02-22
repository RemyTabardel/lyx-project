package com.rta.framework.graphics;

import java.util.ArrayList;
import java.util.List;

public class SpriteSheet
{
	private String name;
	private List<Sprite> listSprites = new ArrayList<Sprite>();
	
	public SpriteSheet(String name, Graphics graphics)
	{
		super();
		this.name = name;
		
		registerSprites(graphics);		
	}
	
	private void registerSprites(Graphics graphics)
	{
		int currentSprite = 0;
		boolean next = true;
		
		while(next)
		{
			String spriteName = currentSprite+".png";
			
			boolean exist = graphics.checkAsset(spriteName);
			
			if(exist == true)
			{
				listSprites.add(new Sprite(spriteName));
				currentSprite++;
			}
			else
			{
				next = false;
			}
		}
	}
	
	public void paint(Graphics graphics, Animation animation, int x, int y)
	{
		listSprites.get(animation.getCurrentFrame()).paint(graphics, x, y);
	}
	
}
