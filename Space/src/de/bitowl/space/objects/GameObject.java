package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.bitowl.space.Chunk;

/**
 * any object that can move on the screen
 * so that we can organize them in one ArrayList
 * 
 * @author bitowl
 *
 */
public class GameObject extends Image{
	public GameObject(AtlasRegion findRegion) {
		super(findRegion);
	}

	/**
	 * this object should be removed in the next frame
	 */
	public boolean toremove;

	public float getCenterX(){
		return getX()+getOriginX();
	}
	public float getCenterY(){
		return getY()+getOriginY();
	}
	public void act(Chunk pChunk,float delta) {
		super.act(delta);
	}

	public Rectangle getRectangle(){
		// TODO make one rectangle and update it
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
}
