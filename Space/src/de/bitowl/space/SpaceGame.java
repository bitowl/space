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
	
	public static void screen(Screen shopScreen){
		game.getScreen().dispose(); // TODO vllt. alle Screens laden und erst am ende disposen?
		game.setScreen(shopScreen);
	}
	@Override
	public void dispose() {
		super.dispose();
		Res.dispose();
	}
}
