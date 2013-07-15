package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.Animation;

import de.bitowl.space.AnimAction;
import de.bitowl.space.Chunk;
import de.bitowl.space.Resources;

/**
 * one of these awesome explosions
 * 
 * soon to be animated :)
 * 
 * @author bitowl
 *
 */
public class Explosion extends GameObject{
	float lifetime;
	float max_lifetime;
	
	public Explosion(String pName,float pFrameTime, float pLifeTime){
		super(Resources.atlas.findRegion(pName));
		addAction(new AnimAction(new Animation(pFrameTime,Resources.atlas.findRegions(pName)), false));
		setOrigin(getWidth()/2,getHeight()/2);
		max_lifetime=pLifeTime;
	}
	@Override
	public void act(Chunk pChunk,float delta) {
		super.act(pChunk,delta);
		lifetime+=delta;
		if(lifetime>max_lifetime){
			toremove=true;
		}
	}
}
