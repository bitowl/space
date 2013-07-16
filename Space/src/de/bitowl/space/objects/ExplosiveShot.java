package de.bitowl.space.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * a shot that explodes if it hits anything
 * 
 * @author bitowl
 *
 */
public class ExplosiveShot extends Shot{
	float radius;
	public ExplosiveShot(AtlasRegion pRegion) {
		super(pRegion);
	}
	
	@Override
	public void hitSomething(Chunk pChunk) {
		Explosion expl=new Explosion("plasmaexpl",0.04f,0.5f);
		expl.setX(getCenterX()-expl.getOriginX());
		expl.setY(getCenterY()-expl.getOriginY());
		pChunk.objects.add(expl);
		
		// hit all the ships in a certain radius
		ArrayList<Ship>tohit=pChunk.getShipsInRadius(getCenterX(),getCenterY(),radius);
		for(int i=0;i<tohit.size();i++){
			tohit.get(i).life-=strength;
		}
	}
}
