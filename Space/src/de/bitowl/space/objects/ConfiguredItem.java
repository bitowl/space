package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.OrderedMap;

import de.bitowl.space.Resources;
import de.bitowl.space.objects.Item.Type;

public class ConfiguredItem {
	Item.Type type;
	float amount;
	Weapon weapon;// only for LIFE
	AtlasRegion image;
	float frequency;
	
	public ConfiguredItem(OrderedMap<String, Object> config){
		type=getType((String) config.get("type"));

		frequency=(Float) config.get("frequency");
		Resources.ingame.totalItemFrequency+=frequency;
		if(type!=Type.NONE){
			amount=(Float)config.get("amount");
			if(type==Type.AMMO){
				weapon=getWeapon(config.get("weapon"));
			}
			image=Resources.atlas.findRegion((String) config.get("image"));
		}
	}
	
	public static Type getType(String type){
		if(type.equals("ammo")){
			return Type.AMMO;
		}
		if(type.equals("life")){
			return Type.LIFE;
		}
		if(type.equals("money")){
			return Type.MONEY;
		}
		return Type.NONE;
	}
	public static Weapon getWeapon(Object object){
		for(int i=0;i<GameObjects.player.weapons.size;i++){
			if(GameObjects.player.weapons.get(i).name.equals(object)){
				return GameObjects.player.weapons.get(i);
			}
		}
		System.err.println("no weapon called "+object+" found :(");
		return null;
	}

	public Item create(float pX, float pY) {
		Item item=new Item(image);
		item.setX(pX-item.getOriginX());
		item.setY(pY-item.getOriginY());
		item.type=type;
		item.amount=amount;
		item.weapon=weapon;
		return item;
	}
}
