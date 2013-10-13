package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.OrderedMap;

import de.bitowl.space.Res;
import de.bitowl.space.objects.Item.Type;

public class ConfiguredItem {
	Item.Type type;
	float amount;
	String weapon;// only for LIFE
	AtlasRegion image;
	float frequency;
	
	public ConfiguredItem(JsonValue config){
		type=getType(config.getString("type"));

		frequency=config.getFloat("frequency");
		Res.ingame.totalItemFrequency+=frequency;
		
		System.out.println("config item");
		if(type!=Type.NONE){
			amount=config.getFloat("amount");
			if(type==Type.AMMO){
				weapon=config.getString("weapon");//getWeapon(config.getString("weapon"));
			}
			image=Res.atlas.findRegion(config.getString("image"));
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
	/**
	 * searchs a weapon by name
	 * @param weapon
	 * @return
	 */
	public static Weapon getWeapon(String weapon){
		System.out.println("----"+weapon+"---- "+Res.weapons.size);
		for(int i=0;i<Res.weapons.size;i++){
			if(Res.weapons.get(i).name.equals(weapon)){
				System.err.println("---- found at "+i);
				return Res.weapons.get(i).getCurrent();
			}
		}
		System.err.println("no weapon called "+weapon+" found :(");
		return null;
	}

	public Item create(float pX, float pY) {
		Item item=new Item(image);
		item.setX(pX-item.getOriginX());
		item.setY(pY-item.getOriginY());
		item.type=type;
		item.amount=amount;
		if(weapon!=null){
			item.weapon=getWeapon(weapon);
			if(item.weapon==null){
				item.type=Type.NONE;
				item.setDrawable(null);
				//return null; // we don't own this weapon yet :/
			}
		}
		
		System.out.println(type+":"+amount+"-"+weapon);
		return item;
	}
}
