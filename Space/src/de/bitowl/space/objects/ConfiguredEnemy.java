package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.OrderedMap;

import de.bitowl.space.AnimAction;
import de.bitowl.space.Resources;

public class ConfiguredEnemy {
	public String name;
	public String image;
	public float animTime;
	public float maxSpeed;
	public float accSpeed;
	public float maxAccAngle;
	public float life;
	public float strength;
	ConfiguredWeapon weapon;
	public float frequency;
	
	@SuppressWarnings("unchecked")
	public ConfiguredEnemy(OrderedMap<String, Object> config) {
		name=(String) config.get("name");
		image=(String) config.get("image");
		animTime=(Float) config.get("animTime",-1f);
		maxSpeed=(Float) config.get("maxSpeed");
		accSpeed=(Float) config.get("accSpeed");
		maxAccAngle=(Float) config.get("maxAccAngle");
		life=(Float) config.get("life");
		strength=(Float) config.get("strength");
		
		if(config.get("weapon")!=null){
			weapon=new ConfiguredWeapon((OrderedMap<String, Object>) config.get("weapon"));
		}
		frequency=(Float) config.get("frequency");
		Resources.ingame.totalEnemyFrequency+=frequency;
	}
	
	public Enemy create(float pX,float pY,float pAngle){
		Enemy enemy=new Enemy(Resources.atlas.findRegion(image));
		if(animTime!=-1){
			enemy.addAction(new AnimAction(new Animation(animTime,Resources.atlas.findRegions(image)),true));
		}
		
		enemy.setX(pX-enemy.getOriginX());
		enemy.setY(pY-enemy.getOriginY());
		
		enemy.angle=pAngle;
		enemy.setRotation(MathUtils.radDeg*pAngle-90);
		
		enemy.MAX_SPEED=maxSpeed;
		enemy.accSpeed=accSpeed;
		enemy.ANGLE_ACC_SPEED=maxAccAngle;
		enemy.life=life;
		enemy.strength=strength;
		
		if(weapon!=null){
			enemy.weapon=(new ConfiguredWeapon(weapon));
		}
		return enemy;
	}
}
