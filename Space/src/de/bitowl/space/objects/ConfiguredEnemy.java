package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.OrderedMap;

import de.bitowl.space.AnimAction;
import de.bitowl.space.Res;

public class ConfiguredEnemy {
	public String name;
	public String image;
	public float animTime;
	public float maxSpeed;
	public float accSpeed;
	public float maxAccAngle;
	public float life;
	public float strength;
	Weapon weapon;
	public float frequency;
	
	@SuppressWarnings("unchecked")
	public ConfiguredEnemy(JsonValue config) {
		name		= config.getString("name");
		image		= config.getString("image");
		animTime	= config.getFloat("animTime",-1f);
		maxSpeed	= config.getFloat("maxSpeed");
		accSpeed	= config.getFloat("accSpeed");
		maxAccAngle	= config.getFloat("maxAccAngle");
		life		= config.getFloat("life");
		strength	= config.getFloat("strength");
		
		if(config.get("weapon")!=null){
			weapon=ConfiguredWeapon.initUpgrade(config.get("weapon"));//new ConfiguredWeapon(config.get("weapon"));
		}
		frequency=config.getFloat("frequency");
		Res.ingame.totalEnemyFrequency+=frequency;
	}
	
	public Enemy create(float pX,float pY,float pAngle){
		Enemy enemy=new Enemy(Res.atlas.findRegion(image));
		if(animTime!=-1){
			enemy.addAction(new AnimAction(new Animation(animTime,Res.atlas.findRegions(image)),true));
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
			enemy.weapon=new Weapon(weapon);
		}
		return enemy;
	}
}
