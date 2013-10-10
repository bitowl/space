package de.bitowl.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;

/**
 * really simple game over screen
 * 
 * @author bitowl
 *
 */
public class GameOverScreen extends AbstractScreen {

	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin();
		Res.font.drawWrapped(batch, "GAME OVER",0,Gdx.graphics.getHeight()/2+32,Gdx.graphics.getWidth(),HAlignment.CENTER);
		batch.end();
		if(Gdx.input.justTouched()){
			SpaceGame.screen(new IngameScreen());
		}
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
	}

}
