package de.bitowl.space;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class SpaceGame extends Game {
	static SpaceGame game;
	
	@Override
	public void create() {		
		game=this;
		setScreen(new SplashScreen());
	}
	
	public static void screen(Screen pScreen){
		game.setScreen(pScreen);
	}
	@Override
	public void dispose() {
		super.dispose();
		Res.dispose();
	}
}
