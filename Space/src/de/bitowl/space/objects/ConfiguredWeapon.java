package de.bitowl.space.objects;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import de.bitowl.space.objects.ConfiguredShot.Type;

/**
 * a weapon that is configured via a config-file
 * 
 * @author bitowl
 *
 */
public class ConfiguredWeapon{
	public String name;
	public Array<Weapon> upgrades;
	public int bought=0; // which upgrade has the player bought
	// TODO -1: not bought instead of 0  --- might make things easier
	
	public ConfiguredWeapon(JsonValue config) {
		name=config.getString("name");
		
		JsonValue upgradesConfig=config.get("upgrades");
		upgrades=new Array<Weapon>(upgradesConfig.size);
		
		
		for(int i=0;i<upgradesConfig.size;i++){
			upgrades.add(initUpgrade(upgradesConfig.get(i)));
		}
		//System.out.println(config);
		// read the configuration
	
	}

	public static Weapon initUpgrade(JsonValue config){
		Weapon weapon=new Weapon(); // set up this upgrade level
		weapon.name="TODO WHO THE HELL NEEDS DIS STUFF?";
		//weapon.name=name; // TODO any need to set this?
		
		
		weapon.price=config.getInt("price",-1);
		weapon.maxAmmo=config.getInt("maxAmmo");
		weapon.ammo=weapon.maxAmmo; // fill the weapon :) TODO read from savegame
		weapon.autoShootDelay=config.getFloat("autoShootDelay");
		weapon.manualDelay=config.getFloat("manualDelay");
		
		weapon.shots=new Array<ConfiguredShot>();
		JsonValue shotsConfig=config.get("shots");
		for(int i=0;i<shotsConfig.size;i++){
			ConfiguredShot shot=new ConfiguredShot();
			JsonValue shotConfig=shotsConfig.get(i);
			shot.angle			= shotConfig.getFloat("angle");
			shot.type			= ConfiguredShot.getType(shotConfig.getInt("type"));
			shot.speed			= shotConfig.getFloat("speed");
			shot.strength		= shotConfig.getFloat("strength");
			shot.image			= shotConfig.getString("image");
			shot.animTime		= shotConfig.getFloat("animTime", -1.0f);
			shot.bulletSpeed	= shotConfig.getFloat("bSpeed",0);
			shot.bulletAmplitude= shotConfig.getFloat("bAmplitude",0);
			if(shot.type==Type.STEERED){
				shot.maxAccAngle=shotConfig.getFloat("maxAccAngle");
			}else if(shot.type==Type.EXPLOSIVE){
				shot.explosionRadius=shotConfig.getFloat("explosionRadius");
			}
			
			weapon.shots.add(shot);
		}
		
		return weapon;
		
	}
	
/*	public ConfiguredWeapon(ConfiguredWeapon w){
		maxAmmo=w.maxAmmo;
		ammo=w.ammo;
		autoShootDelay=w.autoShootDelay;
		manualDelay=w.manualDelay;
		shots=w.shots;
	}*/

	public Weapon getCurrent(){
		if(bought==0||bought>upgrades.size){
			return null;
		}
		return upgrades.get(bought-1);
	}

}
