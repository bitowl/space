package de.bitowl.space;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Resources, that are used throughout the game
 * 
 * @author bitowl
 *
 */
public class Resources {
	public static TextureAtlas atlas;
	public static IngameScreen ingame;
	public static BitmapFont font;
	public static BitmapFont smallfont;
	
	public static Preferences preferences; // TODO build own preferences system based on JSON and saved in the application directory instead of the home folder
}
