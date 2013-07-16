package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;

import de.bitowl.space.Resources;
import de.bitowl.space.Utils;

public abstract class Ship extends GameObject{
	public float angle;
	public float speed;
	
	/**
	 * acceleration to change the angle of the ship
	 */
	public float accAngle;
	/**
	 * acceleration to change the speed of the ship
	 */
	public float accSpeed;
	
	public float MAX_SPEED;
	public float ANGLE_ACC_SPEED;
	
	/**
	 * TODO replace with some acceleration stuff (MAX_ACC)
	 */
	public float SPEED_CONST=1.5f;
	
	
	/**
	 * where the ship is aiming at
	 */
	public GameObject aim;
	
	
	/**
	 * which team this ship is in
	 */
	public int team;
	
	
	
	
	
	public float life;
	public float strength;
	
	public Ship(AtlasRegion findRegion) {
		super(findRegion);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		if(aim!=null&&aim instanceof Ship){
			Resources.smallfont.draw(batch,team+"->"+((Ship)aim).team+" "+life,getX(),getY());
		}else{
			Resources.smallfont.draw(batch,""+team,getX(),getY());
		}
	}
	@Override
	public void act(Chunk pChunk,float delta) {
		super.act(delta);
		if(aim!=null){
			// the ship wants to fly there where it's aim is
			float desAngle=Utils.angle(getCenterX(), getCenterY(),aim.getCenterX(),aim.getCenterY());
					
			// turn around in the direction our aim is at
			float dif=desAngle-angle;
			if(Math.abs(Utils.differenceAngles(angle,desAngle))<ANGLE_ACC_SPEED*delta){
				angle=desAngle;
				accAngle=0;
				setRotation(MathUtils.radDeg*angle-90); 
			}else
			if((dif>0.05f&&dif<MathUtils.PI) || dif <-MathUtils.PI){
				accAngle=ANGLE_ACC_SPEED;
			}else if((dif<-0.05f&&dif>-MathUtils.PI) || dif>MathUtils.PI){
				accAngle=-ANGLE_ACC_SPEED;
			}else{
				accAngle=0;
			}
		}
		
		

		
		
		
		// accelerate the values
		speed+=accSpeed*delta;
		if(speed>MAX_SPEED){speed=MAX_SPEED;}if(speed<0){speed=0;}
		if(accAngle!=0){
			angle+=accAngle*delta;
			
			if(angle>MathUtils.PI){
				angle-=MathUtils.PI*2;
			}else if(angle<-MathUtils.PI){
				angle+=MathUtils.PI*2;
			}

			setRotation(MathUtils.radDeg*angle-90); // TODO in every frame? o.O
		}
		
		// move the player along its angle
		setX(getX()+speed*delta*MathUtils.cos(angle)*SPEED_CONST);
		setY(getY()+speed*delta*MathUtils.sin(angle)*SPEED_CONST);
	}

	public void checkColWithShot(Chunk pChunk,Shot shot) {
		if(toremove||shot.toremove){return;}
		
		if(shot.team==team){return;} // no friendly fire
		
		if(getRectangle().overlaps(shot.getRectangle())){
			shot.toremove=true;
			shot.hitSomething(pChunk);
			life-=shot.strength; // we are hit with the strenght of this sho

		}
	}
	public void checkColWithShip(Chunk pChunk,Ship ship) {
		if(toremove||ship.toremove  || ship.team==team){return;} // TODO do not hit friendly ships?
		
		if(getRectangle().overlaps(ship.getRectangle())){
			bounce();
			float tmp=ship.life;
			life-=ship.life;
			ship.bounce();
			ship.life-=tmp ;
		}
	}
	
	public void checkColWithPlayer() {
		if(toremove){return;}
		
		if(getRectangle().overlaps(GameObjects.player.getRectangle())){
			//bounce();
			GameObjects.player.bounce();
			life=0;
			GameObjects.player.life-=strength;
		}
	}
	public void bounce(){
		//Gdx.app.log("ship", "collides with player");
		// TODO add some bouncing :| (Never got any such code really good working. need to read more about that)
	//	setX(getX()-speed*0.05f*MathUtils.cos(angle)*SPEED_CONST);
		//setY(getY()-speed*0.05f*MathUtils.sin(angle)*SPEED_CONST);
		//speed-=-speed-GameObjects.player.speed;
		//speed=-speed-GameObjects.player.speed/5;
		//speed*=0.6;
		//speed+=GameObjects.player.speed;
		//angle+=MathUtils.PI;
	//	speed=0;
		
		speed*=0.4;
	}
	
	

	
}
