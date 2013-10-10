package de.bitowl.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractScreen implements Screen{
	
	SpaceCamera camera;
	SpriteBatch batch;
	
	public AbstractScreen(){
		camera=new SpaceCamera();
		camera.setToOrtho(false,800,480);
		batch=new SpriteBatch();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.activate(batch);
	}

	@Override
	public void resize(int width, int height) {

		camera.resize(width, height);
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
