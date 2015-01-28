package com.rta.framework.content;

import java.util.HashMap;
import java.util.Map;

import com.rta.framework.audio.Music;
import com.rta.framework.audio.Sound;
import com.rta.framework.graphics.Image;

public class Assets
{
	private static Map<String, Image>	mapImage	= new HashMap<String, Image>();
	private static Map<String, Sound>	mapSound	= new HashMap<String, Sound>();
	private static Map<String, Music>	mapMusic	= new HashMap<String, Music>();

	// Méthodes get
	public static Image getImage(String key)
	{
		Image image = get(mapImage, key);
		return  mapImage.get(key);
	}

	public static Sound getSound(String key)
	{
		return get(mapSound, key);
	}

	public static Music getMusic(String key)
	{
		return get(mapMusic, key);
	}

	private static <T> T get(Map<String, T> map, String key)
	{
		if (map.containsKey(key) == true)
		{
			map.get(key);
		}

		return null;
	}

	// Méthodes add
	public static boolean addImage(String key, Image image)
	{
		return add(mapImage, key, image);
	}

	public static boolean addSound(String key, Sound sound)
	{
		return add(mapSound, key, sound);
	}

	public static boolean addMusic(String key, Music music)
	{
		return add(mapMusic, key, music);
	}

	private static <T> boolean add(Map<String, T> map, String key, T value)
	{
		if (map.containsKey(key) == false)
		{
			map.put(key, value);

			return true;
		}

		return false;
	}
}
