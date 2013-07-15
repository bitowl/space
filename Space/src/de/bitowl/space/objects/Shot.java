package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import de.bitowl.space.Chunk;

public class Shot extends GameObject{
	float angle;
	float speed;
	float strength;
	/**
	 * which team fired this shot? no friendly fire
	 */
	public int team;
	
	public Shot(AtlasRegion findRegion) {
		super(findRegion);

		setOrigin(getWidth()/2,getHeight()/2);
	}

	public void setAngle(float pAngle){
		angle=pAngle;
		setRotation(MathUtils.radDeg*angle-90);
	}
	
	@Override
	public void act(Chunk pChunk,float delta) {
		super.act(pChunk,delta);
		
		// move the shot along its angle
		setX(getX()+speed*delta*MathUtils.cos(angle));
		setY(getY()+speed*delta*MathUtils.sin(angle));
	}
	
	public void hitSomething(Chunk pChunk){
		pChunk.addExplosion(new Explosion("shotexpl",0.1f,0.4f), getCenterX(),getCenterY());
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public boolean outsideWorld(int x, int y, int worldWidth, int worldHeight) {
		if(getX()<x||getY()<y||getX()>x+worldWidth||getY()>y+worldHeight){
			return true;
		}
		return false;
	}
	

}
