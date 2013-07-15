package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import de.bitowl.space.Chunk;
import de.bitowl.space.Resources;
import de.bitowl.space.Utils;
import de.bitowl.space.Weapon;

/**
 * a very basic Enemy class
 * 
 * TODO merge Enemy and Player to a Ship class
 * 
 * @author bitowl
 *
 */
public class Enemy extends Ship{
	public Weapon weapon;
	public Enemy(AtlasRegion reg){
		super(reg);	
		setOrigin(getWidth()/2,getHeight()/2);
	}
	@Override
	public void act(Chunk pChunk,float delta) {
		super.act(pChunk,delta);
		if(life<=0){
			toremove=true;
			pChunk.addExplosion(new Explosion("expl",0.1f,1.0f), getCenterX(), getCenterY());

			
			// spawn item
		/*	if(MathUtils.random(7)<2){
				Item.Type type=Item.Type.LIFE;
				int random=MathUtils.random(100);
				if(random<30){
					type=Item.Type.MG;
				}else if(random<41){
					type=Item.Type.ZAP;
				}else if(random<46){
					type=Item.Type.ROCKET;
				}else if(random<53){
					type=Item.Type.PLASMA;
				}
				
				
				Item item=new Item(type);
				item.setX(getCenterX()-item.getWidth()/2);
				item.setY(getCenterY()-item.getHeight()/2);
				Resources.ingame.addItem(item);
			}*/
			float type=MathUtils.random(Resources.ingame.totalItemFrequency);
			float already=0; // sum up the frequencies
			for(int i=0;i<Resources.ingame.itemTypes.size;i++){
				if(already+Resources.ingame.itemTypes.get(i).frequency>type){
					if(Resources.ingame.itemTypes.get(i).type!=Item.Type.NONE){
						Resources.ingame.addItem(Resources.ingame.itemTypes.get(i).create(getCenterX(),getCenterY()));
					}
					break;	
				}
				already+=Resources.ingame.itemTypes.get(i).frequency;
			}
		}
		
		if(aim==null||aim.toremove){// we have no aim. Let's search for the next enemy
			if(Resources.ingame.getChunk(getX(),getY())!=null){
				aim=Resources.ingame.getChunk(getX(),getY()).getNearestEnemy(team, getX(),getY());
				accAngle=0;
			}
		}
		
		// check collisions
		
		// check for collision with every shot
		for(int j=0;j<pChunk.shots.size();j++){
			checkColWithShot(pChunk,pChunk.shots.get(j));
		}
		// check for collision with player
		checkColWithPlayer();
		
		// check for collisions with other ships
		for(int i=0;i<pChunk.ships.size();i++){
			if(pChunk.ships.get(i)!=this){ // stupid: collide with youself ^^
				checkColWithShip(pChunk,pChunk.ships.get(i));
			}
		}
		if(weapon!=null){
			// shoot only when enemy is in line of sight
			weapon.timeToNextShot-=delta;
			if(aim!=null && Utils.differenceAngles(angle,Utils.angle(getCenterX(), getCenterY(),aim.getCenterX(),aim.getCenterY()))<0.1 // only shoot, if we have the player "in aim"
					&& weapon.timeToNextShot<=0){
				if(weapon.ammo>0||weapon.maxAmmo==-1){// we still have ammo or our ammo is unlimited
					weapon.timeToNextShot=weapon.autoShootDelay;
					weapon.shoot(team, getCenterX(),getCenterY(),angle,aim);
				}
				
			}

		}
	}
}
