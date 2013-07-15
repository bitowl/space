package de.bitowl.space;

import com.badlogic.gdx.Game;

public class SpaceGame extends Game {
	@Override
	public void create() {		
		setScreen(new SplashScreen(this));
	}
}
