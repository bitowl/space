package de.bitowl.space;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * a first, simple splash screen
 * 
 * @author bitowl
 *
 */
public class SplashScreen extends AbstractScreen {	
	Texture bitowl;
	public SplashScreen(SpaceGame pGame) {
		super(pGame);
		// load texture for splash screen
		bitowl=new Texture("textures/bitowl.png");
		
		// load all textures
		Resources.atlas=new TextureAtlas(Gdx.files.internal("textures/textures.atlas"));
		// make the textures smooth
		Iterator<Texture> iterator=Resources.atlas.getTextures().iterator();
		while(iterator.hasNext()){
			iterator.next().setFilter(TextureFilter.Linear,TextureFilter.Linear);
		}
		
		// load font
		Resources.font=new BitmapFont(Gdx.files.internal("fonts/karmatic_arcade.fnt"), false);
		Resources.smallfont=new BitmapFont();
		
}
	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin();
		batch.draw(bitowl,Gdx.graphics.getWidth()/2-64,Gdx.graphics.getHeight()/2-64,128,128);
		batch.end();
		game.setScreen(new IngameScreen(game));
	}
}
