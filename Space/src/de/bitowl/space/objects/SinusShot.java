package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;

import de.bitowl.space.Res;
import de.bitowl.space.Utils;

/**
 * a shot that is movin on a sinus road
 * 
 * @author bitowl
 *
 */
public class SinusShot extends Shot{
	
	float bulletSpeed;
	float bulletAmplitude;
	
	float time;
	
	/**
	 * where this shot would be if he was normal
	 */
	float rlX,rlY;
	
	public SinusShot(AtlasRegion pRegion) {
		super(pRegion);
		//time=MathUtils.PI/4;
	
	}
	@Override
	public void setAngle(float pAngle) {
		super.setAngle(pAngle);
		// get the position
		rlX=getX();
		rlY=getY();
	}
	@Override
	public void act(Chunk pChunk,float delta) {
		time+=delta*bulletSpeed;
		//super.act(pChunk,delta);

		// move shot along its angle
		rlX+=speed*delta*MathUtils.cos(angle);
		rlY+=speed*delta*MathUtils.sin(angle);
		
		setX(rlX- MathUtils.sin(angle)*bulletAmplitude*MathUtils.sin(time) );
		setY(rlY+ MathUtils.cos(angle)*bulletAmplitude*MathUtils.sin(time));
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	
	}
}
