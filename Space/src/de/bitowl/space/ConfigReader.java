package de.bitowl.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
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
		Object values=reader.parse(Gdx.files.internal("space/weapons.json"));
		OrderedMap<String, Object> head=(OrderedMap<String,Object>)values;
		Array<OrderedMap<String, Object>> weapons=(Array<OrderedMap<String, Object>>) head.get("weapons");
		
		GameObjects.player.weapons=new Array<Weapon>(weapons.size);
		
		for(int i=0;i<weapons.size;i++){
			
			Array<OrderedMap<String, Object>> upgrades=(Array<OrderedMap<String, Object>>) weapons.get(i).get("upgrades");
			ConfiguredWeapon weapon=new ConfiguredWeapon(upgrades.get(0)); // let the weapons configure themselves
			weapon.name=(String) weapons.get(i).get("name");
			GameObjects.player.weapons.add(weapon);
		}
		
		GameObjects.player.weapon=GameObjects.player.weapons.get(GameObjects.player.currentWeapon);
		
		// read enemy
		Object enemVals=reader.parse(Gdx.files.internal("space/enemies.json"));
		Array<OrderedMap<String, Object>> enemies= (Array<OrderedMap<String, Object>>) ((OrderedMap<String,Object>)enemVals).get("enemies");
		
		Resources.ingame.enemyTypes=new Array<ConfiguredEnemy>(enemies.size);
		for(int i=0;i<enemies.size;i++){
			Resources.ingame.enemyTypes.add(new ConfiguredEnemy(enemies.get(i)));
		}
		
		
		// read items
		Object itemVals=reader.parse(Gdx.files.internal("space/items.json"));
		Array<OrderedMap<String, Object>> items= (Array<OrderedMap<String, Object>>) ((OrderedMap<String,Object>)itemVals).get("items");
		Resources.ingame.itemTypes=new Array<ConfiguredItem>(items.size);
		for(int i=0;i<items.size;i++){
			Resources.ingame.itemTypes.add(new ConfiguredItem(items.get(i)));
		}
		
	}
}
