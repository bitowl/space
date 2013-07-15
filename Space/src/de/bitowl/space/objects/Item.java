package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import de.bitowl.space.Chunk;
import de.bitowl.space.Resources;
import de.bitowl.space.Weapon;

public class Item extends GameObject {
	enum Type{
		AMMO,LIFE,MONEY, NONE
	}
	Type type;
	float amount;
	Weapon weapon;// only for LIFE
	
	public Item(AtlasRegion pImage) {
		super(pImage);
		setOrigin(getWidth()/2,getHeight()/2);
	}
	
	@Override
	public void act(Chunk pChunk, float delta) {
		super.act(pChunk, delta);
		if(GameObjects.player.getRectangle().overlaps(getRectangle())){
			collected();
		}
	}
	public void collected(){
		toremove=true;
		switch (type){
		case AMMO:
			weapon.addAmmo((int) amount);
			break;
		case LIFE:
			GameObjects.player.addLife(amount);
			break;
		case MONEY:
			GameObjects.player.addMoney(amount);
			break;
		default:
			System.out.println("this item does nothing...");
			break;
		}
	}
}
