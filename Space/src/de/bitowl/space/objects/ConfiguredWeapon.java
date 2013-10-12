package de.bitowl.space.objects;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.OrderedMap;

import de.bitowl.space.Res;
import de.bitowl.space.objects.ConfiguredShot.Type;

/**
 * a weapon that is configured via a config-file
 * 
 * @author bitowl
 *
 */
public class ConfiguredWeapon extends Weapon{
	Array<ConfiguredShot> shots;
	
	@SuppressWarnings("unchecked")
	public ConfiguredWeapon(JsonValue config) {
		//System.out.println(config);
		// read the configuration
		maxAmmo=config.getInt("maxAmmo");
		ammo=maxAmmo; // fill the weapon :)
		autoShootDelay=config.getFloat("autoShootDelay");
		manualDelay=config.getFloat("manualDelay");
		
		shots=new Array<ConfiguredShot>();
		JsonValue shotsConfig=config.get("shots");
		for(int i=0;i<shotsConfig.size;i++){
			ConfiguredShot shot=new ConfiguredShot();
			JsonValue shotConfig=shotsConfig.get(i);
			shot.angle		= shotConfig.getFloat("angle");
			shot.type		= ConfiguredShot.getType(shotConfig.getInt("type"));
			shot.speed		= shotConfig.getFloat("speed");
			shot.strength	= shotConfig.getFloat("strength");
			shot.image		= shotConfig.getString("image");
			shot.animTime	= shotConfig.getFloat("animTime", -1.0f);
			if(shot.type==Type.STEERED){
				shot.maxAccAngle=shotConfig.getFloat("maxAccAngle");
			}else if(shot.type==Type.EXPLOSIVE){
				shot.explosionRadius=shotConfig.getFloat("explosionRadius");
			}
			
			shots.add(shot);
		}
	}

	
	public ConfiguredWeapon(ConfiguredWeapon w){
		maxAmmo=w.maxAmmo;
		ammo=w.ammo;
		autoShootDelay=w.autoShootDelay;
		manualDelay=w.manualDelay;
		shots=w.shots;
	}
	
	@Override
	public void shoot(int pTeam, float pX,float pY,float pAngle,GameObject pAim) {
		System.out.println("SHOOT");
		for(int i=0;i<shots.size;i++){// shoot all our shots :D
			ammo--;
			Shot shot=shots.get(i).create(pX,pY,pAngle);
			if(shot instanceof SteeredShot){ // aim steered shots
				((SteeredShot)shot).aim=pAim;
			}
			shot.team=pTeam;
			Res.ingame.addShot(shot);
		}
	}

}
