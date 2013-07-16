package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import de.bitowl.space.Resources;

public class Planet extends Item{
	
	/**
	 * which team inhabits this planet
	 */
	int team;

	public Planet(int pX,int pY,int pTeam) {
		super(Resources.atlas.findRegion("earth")); // does not matter, we're overriding everything
		team=pTeam;
		//((TextureRegionDrawable)getDrawable()).setRegion(Resources.atlas.findRegion("earth"));
		/*setDrawable(new TextureRegionDrawable());
		setWidth(getDrawable().getMinWidth());
		setHeight(getDrawable().getMinHeight());
		
		
		setOrigin(271/2, 293/2);*/
		setX(pX);setY(pY);
		
		// create a marker for this planet :D
		// TODO do not create a new one, if there is already one for this place ^^
	//	Resources.ingame.marker.add(new Marker(pX+271/2,pY+293/2));
	}
	@Override
	public void collected() {
		System.out.println("LÄND ON ÖRTH");
		Resources.ingame.avaiableToLandOnThisFrame=this;// you may land on me :D
		// TODO maybe do not check for this every frame?
	}
	

	float timesincelastadd;
	@Override
	public void act(Chunk pChunk, float delta) {
		super.act(pChunk, delta);
		
		timesincelastadd+=delta;
		if(timesincelastadd>MathUtils.random(3,5)&&MathUtils.random(1)>0.5f){
			timesincelastadd=0;
			spawnNewEnemy();
		}
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		Resources.smallfont.draw(batch,""+team,getX(),getY());
	}
	/**
	 * spawns an enemy on this planet
	 */
	public void spawnNewEnemy(){
/*		Enemy e1;
		switch(MathUtils.random(5)){
			case 0:
				e1=new Skull();
				break;
			case 1:
				e1=new QuadEnemy();
				break;
			case 2:
				e1=new Ghost();
				break;
			case 3:
				e1=new CivilianShip();
				((CivilianShip)e1).home=this;
				e1.aim=Resources.ingame.getChunk(getCenterX(), getCenterY()).main;// fly to the main planet of this chunk
				break;
			default:
				e1=new Enemy();
				break;
		}*/
		int radius=(int) (271/2);//+e1.getHeight()/2); TODO somehow get the height of the player :/
		// spawn an enemy at a random position around the player
		float angle=MathUtils.random(-MathUtils.PI,MathUtils.PI);
		
		float type=MathUtils.random(Resources.ingame.totalEnemyFrequency);
		float already=0; // sum up the frequencies
		for(int i=0;i<Resources.ingame.enemyTypes.size;i++){
			if(already+Resources.ingame.enemyTypes.get(i).frequency>type){
				Enemy e1=Resources.ingame.enemyTypes.get(i).create(getCenterX()+MathUtils.cos(angle)*radius, getCenterY()+MathUtils.sin(angle)*radius, angle);
				e1.team=team; // only out ships will spawn here :D
				
				Resources.ingame.addShip(e1);
				break;	
			}
			already+=Resources.ingame.enemyTypes.get(i).frequency;
		}
	}
}
