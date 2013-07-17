package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;

import de.bitowl.space.Resources;
import de.bitowl.space.Utils;

/**
 * a shot that is steering towards an aim
 * 
 * @author bitowl
 *
 */
public class SteeredShot extends Shot{
	float accAngle;
	public GameObject aim;
	float ANGLE_ACC_SPEED=3;
	
	public SteeredShot(AtlasRegion pRegion) {
		super(pRegion);
	
	}
	@Override
	public void act(Chunk pChunk,float delta) {
		super.act(pChunk,delta);
		
		// the enemy follows the player
		if(aim!=null&&!aim.toremove){
			float desAngle=MathUtils.atan2(
					(aim.getCenterY()-(getY()+getOriginY())),
					(aim.getCenterX()-(getX()+getOriginX()))
				);
			
			// turn around in the direction our aim is at
			float dif=desAngle-angle;
			if(Math.abs(Utils.differenceAngles(angle,desAngle))<ANGLE_ACC_SPEED*delta){
				angle=desAngle;
				accAngle=0;
				setRotation(MathUtils.radDeg*angle-90); 
			}else if((dif>0.05f&&dif<MathUtils.PI) || dif <-MathUtils.PI){
				accAngle=ANGLE_ACC_SPEED;
			}else if((dif<-0.05f&&dif>-MathUtils.PI) || dif>MathUtils.PI){ 
				accAngle=-ANGLE_ACC_SPEED;
			}else{
				accAngle=0;
			}
		}else{
			
			aim=pChunk.getNearestEnemy(team,getCenterX(),getCenterY()); // search a new aim from another team
			accAngle=0;
		}
		
		if(accAngle!=0){
			angle+=accAngle*delta;
			
			if(angle>MathUtils.PI){
				angle-=MathUtils.PI*2;
			}else if(angle<-MathUtils.PI){
				angle+=MathUtils.PI*2;
			}

			setRotation(MathUtils.radDeg*angle-90); // TODO in every frame? o.O
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		if(aim!=null){
			
			Resources.smallfont.draw(batch,""+team+"|"+((Ship)aim).toremove+accAngle,getX(),getY());
			if(aim.toremove){aim=null;System.err.println("YYY? :(");}
		}
	}
}
