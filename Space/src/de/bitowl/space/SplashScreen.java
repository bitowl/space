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
	public SplashScreen() {
		
		// load preferences
		Preferences.init();
		
		
		// load texture for splash screen
		bitowl=new Texture("textures/bitowl.png");
		
		
		
		Res.load();
		
		// copy "space"-folder files to local space if necessary
		loadFileToLocal("space/weapons.json");
		loadFileToLocal("space/items.json");
		loadFileToLocal("space/enemies.json");
		
	}
	
	public void loadFileToLocal(String pFile){
		if(!Gdx.files.local(pFile).exists()){
			Gdx.files.internal(pFile).copyTo(Gdx.files.local(pFile));
		}
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin();
		batch.draw(bitowl,Gdx.graphics.getWidth()/2-64,Gdx.graphics.getHeight()/2-64,128,128);
		batch.end();
		
		//SpaceGame.screen(new IngameScreen());
		SpaceGame.screen(Res.shop);
	}
}
