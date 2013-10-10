package de.bitowl.space;

import com.badlogic.gdx.Game;

public class SpaceGame extends Game {
	static SpaceGame game;
	
	@Override
	public void create() {		
		game=this;
		setScreen(new SplashScreen());
	}
	
	public static void screen(AbstractScreen pScreen){
		game.setScreen(pScreen);
	}
	@Override
	public void dispose() {
		super.dispose();
		Resources.dispose();
	}
}
