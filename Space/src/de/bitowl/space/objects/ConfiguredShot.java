package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;

import de.bitowl.space.AnimAction;
import de.bitowl.space.Resources;

/**
 * configuration for the shots
 * 
 * @author bitowl
 *
 */
public class ConfiguredShot{
	public enum Type{NORMAL,STEERED,EXPLOSIVE};
	public Type type;
	
	public float angle;
	public float speed;
	public float strength;
	
	public String image;
	public float animTime;
	
	public float maxAccAngle;// only STEERED
	public float explosionRadius;// only EXPLOSIVE
	
	public Shot create(float pX,float pY,float pAngle){
		Shot shot;
		switch(type){
			case STEERED:
				shot=new SteeredShot(Resources.atlas.findRegion(image));
				((SteeredShot)shot).ANGLE_ACC_SPEED=maxAccAngle;
				break;
			case EXPLOSIVE:
				shot=new ExplosiveShot(Resources.atlas.findRegion(image));
				((ExplosiveShot)shot).radius=explosionRadius;
				break;
			default:
				shot=new Shot(Resources.atlas.findRegion(image));
			break;
		}
		shot.speed=speed;
		shot.strength=strength;
		shot.setX(pX-shot.getOriginX()+MathUtils.cos(pAngle+angle)*48); // TODO radius customizable?
		shot.setY(pY-shot.getOriginY()+MathUtils.sin(pAngle+angle)*48);
		shot.setAngle(pAngle);
		if(animTime!=-1){
			shot.addAction(new AnimAction(new Animation(animTime,Resources.atlas.findRegions(image)),true));
		}
		
		return shot;
	}

	public static Type getType(int i) {
		switch(i){
			case 0:
				return Type.NORMAL;
			case 1:
				return Type.STEERED;
			case 2:
				return Type.EXPLOSIVE;
		}
		return Type.NORMAL;
	}
}
