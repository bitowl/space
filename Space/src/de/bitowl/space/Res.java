package de.bitowl.space;


import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Resources, that are used throughout the game
 * 
 * @author bitowl
 *
 */
public class Res {
	public static TextureAtlas atlas;
	public static IngameScreen ingame;
	public static BitmapFont font;
	public static BitmapFont smallfont;
	public static Skin skin;
	
	public static Preferences preferences; // TODO build own preferences system based on JSON and saved in the application directory instead of the home folder

	/**
	 * loads all the cool stuff
	 */
	public static void load() {
		// load all textures
		atlas=new TextureAtlas(Gdx.files.internal("textures/textures.atlas"));
		// make the textures smooth
		Iterator<Texture> iterator=atlas.getTextures().iterator();
		while(iterator.hasNext()){
			iterator.next().setFilter(TextureFilter.Linear,TextureFilter.Linear);
		}
		
		// load font
		font=new BitmapFont(Gdx.files.internal("fonts/karmatic_arcade.fnt"), false);
		smallfont=new BitmapFont();
		
		skin=new Skin(Gdx.files.internal("ui/defaultskin.json"));
		
		
	}
	
	/**
	 * throws all the cool stuff away
	 */
	public static void dispose() {
		atlas.dispose();
		font.dispose();
		smallfont.dispose();
	}

	
}
