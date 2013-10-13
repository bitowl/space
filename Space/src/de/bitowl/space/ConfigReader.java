package de.bitowl.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.OrderedMap;

import de.bitowl.space.objects.ConfiguredEnemy;
import de.bitowl.space.objects.ConfiguredItem;
import de.bitowl.space.objects.ConfiguredWeapon;
import de.bitowl.space.objects.GameObjects;
import de.bitowl.space.objects.Weapon;

/**
 * reads configurations for enemies and weapons
 * 
 * @author bitowl
 *
 */
public class ConfigReader {
	@SuppressWarnings("unchecked")
	public static void initGameValues(){
		
		JsonReader reader=new JsonReader();
		
		// read weapons
		JsonValue values=reader.parse(Gdx.files.internal("space/weapons.json"));
		JsonValue weapons=values.get("weapons");
		
		Res.weapons=new Array<ConfiguredWeapon>(weapons.size);
		
		for(int i=0;i<weapons.size;i++){
			
			if(weapons.get(i).get("disabled")!=null){continue;} // dis weapon is not used in da game
			System.err.println("WEAPON "+i+": "+weapons.get(i).getString("name"));
			
			
			//JsonValue upgrades		= weapons.get(i).get("upgrades");
			ConfiguredWeapon weapon	= new ConfiguredWeapon(weapons.get(i)); // let the weapons configure themselves
			//weapon.name				= weapons.get(i).getString("name");
			Res.weapons.add(weapon);
		}
		
		Res.player.weapon=Res.weapons.get(Res.player.currentWeapon).upgrades.get(0);
		//GameObjects.player.weapon=GameObjects.player.weapons.get(GameObjects.player.currentWeapon);
		
		// read enemy
		JsonValue enemVals	= reader.parse(Gdx.files.internal("space/enemies.json"));
		JsonValue enemies	= enemVals.get("enemies");
		
		Res.ingame.enemyTypes=new Array<ConfiguredEnemy>(enemies.size);
		for(int i=0;i<enemies.size;i++){
			if(enemies.get(i).get("disabled")!=null){continue;} // dis enemy is not used in da game
			Res.ingame.enemyTypes.add(new ConfiguredEnemy(enemies.get(i)));
		}
		
		
		// read items
		JsonValue itemVals	= reader.parse(Gdx.files.internal("space/items.json"));
		JsonValue items		= itemVals.get("items");
		Res.ingame.itemTypes=new Array<ConfiguredItem>(items.size);
		for(int i=0;i<items.size;i++){
			if(items.get(i).get("disabled")!=null){continue;} // dis item is not used in da game
			Res.ingame.itemTypes.add(new ConfiguredItem(items.get(i)));
		}
		
	}
}
