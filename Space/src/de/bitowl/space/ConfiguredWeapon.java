package de.bitowl.space;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;

import de.bitowl.space.objects.ConfiguredShot;
import de.bitowl.space.objects.ConfiguredShot.Type;
import de.bitowl.space.objects.GameObject;
import de.bitowl.space.objects.Shot;
import de.bitowl.space.objects.SteeredShot;

/**
 * a weapon that is configured via a config-file
 * 
 * @author bitowl
 *
 */
public class ConfiguredWeapon extends Weapon{
	Array<ConfiguredShot> shots;
	
	@SuppressWarnings("unchecked")
	public ConfiguredWeapon(OrderedMap<String, Object> config) {
		System.out.println(config);
		// read the configuration
		maxAmmo=(int)(float)(Float) config.get("maxAmmo");
		ammo=maxAmmo; // fill the weapon :)
		autoShootDelay=(Float) config.get("autoShootDelay");
		manualDelay=(Float) config.get("manualDelay");
		
		shots=new Array<ConfiguredShot>();
		Array<Object> shotsConfig=(Array<Object>) config.get("shots");
		for(int i=0;i<shotsConfig.size;i++){
			ConfiguredShot shot=new ConfiguredShot();
			OrderedMap<String, Object> shotConfig=(OrderedMap<String, Object>) shotsConfig.get(i);
			shot.angle=(Float) shotConfig.get("angle");
			shot.type=ConfiguredShot.getType((int)(float)(Float)shotConfig.get("type"));
			shot.speed=(Float) shotConfig.get("speed");
			shot.strength=(Float) shotConfig.get("strength");
			shot.image=(String) shotConfig.get("image");
			shot.animTime=(Float) shotConfig.get("animTime", -1.0f);
			if(shot.type==Type.STEERED){
				shot.maxAccAngle=(Float) shotConfig.get("maxAccAngle");
			}else if(shot.type==Type.EXPLOSIVE){
				shot.explosionRadius=(Float) shotConfig.get("explosionRadius");
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
			Resources.ingame.addShot(shot);
		}
	}

}
