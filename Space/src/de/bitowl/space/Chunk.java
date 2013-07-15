package de.bitowl.space;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import de.bitowl.space.objects.Explosion;
import de.bitowl.space.objects.GameObject;
import de.bitowl.space.objects.GameObjects;
import de.bitowl.space.objects.Item;
import de.bitowl.space.objects.Planet;
import de.bitowl.space.objects.Ship;
import de.bitowl.space.objects.Shot;
import de.bitowl.space.objects.Star;

/**
 * a chunk is a 1000x1000 part of the world
 * 
 * @author bitowl
 *
 */
public class Chunk {
	int x;
	int y;
	public static int width=4000;
	public static int height=4000;
	Random random;
	public Rectangle rect;
	
	/**
	 * a list containing all the ships
	 */
	public ArrayList<Ship> ships;
	
	public ArrayList<Shot> shots;
	
	public ArrayList<Item> items;
	
	public ArrayList<GameObject> objects;
	
	/*
	 * a main planet for this chunk
	 */
	public Planet main;
	
	public Chunk(int pX,int pY){
		x=pX;
		y=pY;
		random=new Random(pX+1000*pY);// a seed, so that this chunk is always generated the same
		
		ships=new ArrayList<Ship>();
		shots=new ArrayList<Shot>();
		items=new ArrayList<Item>();
		
		objects=new ArrayList<GameObject>();
		
		/*(for(int i=0;i<10;i++){spawnNewEnemy();
		}*/
		
		// fix stars on the screen
		for(int i=0;i<100;i++){
			objects.add(new Star(random.nextInt(8)+1,x+random.nextInt(width),y+random.nextInt(height)));
		}
		
		// planets in this System
		int planetCount=random.nextInt(3);
		for(int i=0;i<planetCount;i++){
			main=new Planet(random.nextInt(width-271)+x,random.nextInt(height-293)+y,random.nextInt(2)+1);
			items.add(main); // enemies are just in the enemy team :D)); // do not spawn them at the edge of a chunk
		}
		rect=new Rectangle(x, y, width, height);
	}

	public void checkRemove(){
		for(int i=0;i<ships.size();i++){
			if(!isInChunk(ships.get(i).getX(),ships.get(i).getY())){// this ship is not in this chunk anymore
				Resources.ingame.addShip(ships.get(i));// add the ship to the right chunk
				ships.remove(i);// remove it from this chunk
				i--;
				continue;
			}
			if(ships.get(i).toremove){// ship does not exist anymore
				ships.remove(i);
				i--;
				continue;
			}
		}
		for(int i=0;i<shots.size();i++){
			if(!isInChunk(shots.get(i).getX(),shots.get(i).getY())){// this ship is not in this chunk anymore
				Resources.ingame.addShot(shots.get(i));// add the ship to the right chunk
				
				shots.remove(i);// remove it from this chunk
				i--;
				continue;
			}
			if(shots.get(i).toremove){// if the shot hit a ship or is outside of the world
				shots.remove(i);
				i--;
				continue;
			}
		}
		for(int i=0;i<items.size();i++){
			if(!isInChunk(items.get(i).getX(),items.get(i).getY())){// this ship is not in this chunk anymore
				Resources.ingame.addItem(items.get(i));// add the ship to the right chunk
				
				items.remove(i);// remove it from this chunk
				i--;
				continue;
			}
			if(items.get(i).toremove){// if the shot hit a ship or is outside of the world
				items.remove(i);
				i--;
				continue;
			}
		}
		for(int i=0;i<objects.size();i++){
			if(objects.get(i).toremove){
				objects.remove(i);
				i--;
				continue;
			}
		}
	}
	public void act(float delta){
		// call act for all the ships TODO only ships that are visible/in a certain radius
		for(int i=0;i<ships.size();i++){

			ships.get(i).act(this,delta);
			// check for collision with every shot
			for(int j=0;j<shots.size();j++){
				ships.get(i).checkColWithShot(this,shots.get(j));
			}
			// check for collision with player
			ships.get(i).checkColWithPlayer();
		}
		for(int i=0;i<shots.size();i++){
			shots.get(i).act(this,delta);
		}
		for(int i=0;i<items.size();i++){
			items.get(i).act(this,delta);
		}
		for(int i=0;i<objects.size();i++){
			objects.get(i).act(this,delta);
		}
	}
	
	public void draw(Rectangle rect,SpriteBatch batch){
		for(int i=0;i<objects.size();i++){
			if(objects.get(i).getRectangle().overlaps(rect)){
				objects.get(i).draw(batch, 1);
			}
		}
		// draw all ships TODO only visible :)
		for(int i=0;i<ships.size();i++){
			if(ships.get(i).getRectangle().overlaps(rect)){
				ships.get(i).draw(batch,1);
			}
		}
		for(int i=0;i<shots.size();i++){
			if(shots.get(i).getRectangle().overlaps(rect)){
				shots.get(i).draw(batch,1);
			}	
		}
		for(int i=0;i<items.size();i++){
			if(items.get(i).getRectangle().overlaps(rect)){
				items.get(i).draw(batch,1);
			}	
		}
	}
	public Ship getNearestEnemy(int team,float pX,float pY){
		if(ships.size()==0){return null;}
		
		Ship nearest=null;
		if(team!=GameObjects.player.team&&rect.contains(GameObjects.player.getCenterX(),GameObjects.player.getCenterY())){ // player is in this chunk
			nearest=GameObjects.player;
		}
			
			
		float dist=width*width;
		if(nearest!=null){
			dist=Utils.distance(pX, pY, nearest.getCenterX(), nearest.getCenterY());
		}
		for(int i=0;i<ships.size();i++){
			if(ships.get(i).team==team){continue;}
			float ndist=Utils.distance(pX, pY, ships.get(i).getCenterX(), ships.get(i).getCenterY());
			if(ndist<dist){
				nearest=ships.get(i);
				dist=ndist;
			}
		}
		
		//if(nearest.team==team){return null;} // in case that ships.get(0) was in our team and there is no other ship
		return nearest;
	}

	/**
	 * does this point is inside this chunk?
	 * @param f
	 * @param g
	 * @return
	 */
	public boolean isInChunk(float f, float g) {
		return f>x&&g>y&&f<x+width&&g<y+height;
	}

	/**
	 * returns all ships in a certain radius
	 * @param centerX
	 * @param centerY
	 * @param radius
	 * @return
	 */
	public ArrayList<Ship> getShipsInRadius(float centerX, float centerY, float radius) {
		ArrayList<Ship> nearShips=new ArrayList<Ship>();
		for(int i=0;i<ships.size();i++){
			// TODO use distance ^2
			if(Utils.distance(ships.get(i).getCenterX(),ships.get(i).getCenterY() , centerX, centerY)<radius){
				nearShips.add(ships.get(i));
			}
		}
		return nearShips;
	}
	
	
	
	
	
	
	public void addExplosion(Explosion expl,float pX,float pY){
		expl.setX(pX-expl.getOriginX());
		expl.setY(pY-expl.getOriginY());
		objects.add(expl);
	}
}
