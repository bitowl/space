package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;

import de.bitowl.space.AnimAction;
import de.bitowl.space.Res;

/**
 * configuration for the shots
 * 
 * @author bitowl
 *
 */
public class ConfiguredShot{
	public enum Type{NORMAL,STEERED,EXPLOSIVE,SINUS};
	public Type type;
	
	public float angle; // change in angle
	
	public float speed;
	public float strength;
	
	public float posAngle; // where does the shot start relative to the ship
	
	public String image;
	public float animTime;
	
	public float maxAccAngle;		// only STEERED
	public float explosionRadius;	// only EXPLOSIVE
	public float bulletSpeed;		// only SINUS
	public float bulletAmplitude;	// only SINUS
	
	public Shot create(float pX,float pY,float pAngle){
		Shot shot;
		switch(type){
			case STEERED:
				shot=new SteeredShot(Res.atlas.findRegion(image));
				((SteeredShot)shot).ANGLE_ACC_SPEED=maxAccAngle;
				break;
			case EXPLOSIVE:
				shot=new ExplosiveShot(Res.atlas.findRegion(image));
				((ExplosiveShot)shot).radius=explosionRadius;
				break;
			case SINUS:
				shot=new SinusShot(Res.atlas.findRegion(image));
				((SinusShot)shot).bulletSpeed	  = bulletSpeed;
				((SinusShot)shot).bulletAmplitude = bulletAmplitude;
				break;
			default:
				shot=new Shot(Res.atlas.findRegion(image));
			break;
		}
		shot.speed=speed;
		shot.strength=strength;
		shot.setX(pX-shot.getOriginX()+MathUtils.cos(MathUtils.degRad*pAngle+posAngle)*48); // TODO radius customizable?
		shot.setY(pY-shot.getOriginY()+MathUtils.sin(MathUtils.degRad*pAngle+posAngle)*48);
		shot.setAngle(pAngle+MathUtils.degRad*angle);
		if(animTime!=-1){
			shot.addAction(new AnimAction(new Animation(animTime,Res.atlas.findRegions(image)),true));
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
			case 3:
				return Type.SINUS;
		}
		return Type.NORMAL;
	}
}
