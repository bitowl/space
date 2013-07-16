package de.bitowl.space;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * a simple OrthographicCamera for this SpaceGame
 * just resizes to the screen size
 * 
 * @author bitowl
 *
 */
public class SpaceCamera extends OrthographicCamera {
	
	/**
	 * resizes the viewport of this camera
	 * @param width
	 * @param height
	 */
	public void resize(int width,int height){
		setToOrtho(false,width,height);
	}
	
	/**
	 * activates this Camera on a Batch
	 * @param batch
	 */
	public void activate(SpriteBatch batch){
		batch.setProjectionMatrix(combined);
	}
	
	public float getWidth(){
		return viewportWidth*zoom;
	}
	public float getHeight(){
		return viewportHeight*zoom;
	}
}
