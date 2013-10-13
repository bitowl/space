package de.bitowl.space.objects;

import com.badlogic.gdx.utils.Array;

import de.bitowl.space.Res;

/**
 * a weapon in a certain upgraded state
 * 
 * @author bitowl
 *
 */
public class Weapon {
	
	
	public String name;
	/**
	 * time to wait between two automatic shots
	 */
	public float autoShootDelay=0.2f;
	/**
	 * time to wait between two hand shots
	 */
	public float manualDelay=0.1f;
	
	public float timeToNextShot;
	
	public int ammo;
	public int maxAmmo;
	public float minTimeToNextShot;
	
	
	Array<ConfiguredShot> shots;
	
	public int price;
	
	/**
	 * a shot is given by a certain ship
	 * @param weapon 
	 * @param pTeam ship team
	 * @param pX ship position
	 * @param pY ship position
	 * @param pAngle ship angle
	 * @param aim 
	 */
	//public abstract void shoot(int pTeam, float pX,float pY,float pAngle, GameObject aim);
	public Weapon() {}
	/**
	 * copys this weapon (e.g. for a new enemy)
	 * @param w
	 */
	public Weapon(Weapon w){
		maxAmmo=w.maxAmmo;
		ammo=w.ammo;
		autoShootDelay=w.autoShootDelay;
		manualDelay=w.manualDelay;
		shots=w.shots;
	}
	public Weapon(float pRecharge, float pMinRecharge,int pMaxAmmo){
		autoShootDelay=pRecharge;
		manualDelay=pMinRecharge;
		maxAmmo=pMaxAmmo;
		ammo=maxAmmo;// at the beginning the weapon is fully charged
	}
	
	
	public void addAmmo(int pAmount){
		ammo+=pAmount;
		if(ammo>maxAmmo){ammo=maxAmmo;}
	}
	
	public static void init(){
		/*GameObjects.player.gun=new Weapon(0.2f,0.1f,-1) {
			
			@Override
			public void shoot(int pTeam) {
				//ammo--; // no need to... infinite :D
				Shot shot=new Shot();
				shot.team=pTeam;
				// center the shot in the middle of the ship (TODO center it at the weapon that was firing it)
				shot.setX(GameObjects.player.getCenterX()-shot.getOriginX()+MathUtils.cos(GameObjects.player.angle)*48);
				shot.setY(GameObjects.player.getCenterY()-shot.getOriginY()+MathUtils.sin(GameObjects.player.angle)*48);
				shot.setAngle(GameObjects.player.angle);
				Resources.ingame.addShot(shot);
			}
		};
		GameObjects.player.mg=new Weapon(0.1f,0.1f,1000) {
			
			@Override
			public void shoot(int pTeam) {
				ammo-=2;
				Shot shotl=new Shot();
				shotl.team=pTeam;
				shotl.setX(GameObjects.player.getCenterX()-shotl.getOriginX()+MathUtils.cos(GameObjects.player.angle-0.3f)*48);
				shotl.setY(GameObjects.player.getCenterY()-shotl.getOriginY()+MathUtils.sin(GameObjects.player.angle-0.3f)*48);
				shotl.setAngle(GameObjects.player.angle);
				Shot shotr=new Shot();
				shotr.team=pTeam;
				shotr.setX(GameObjects.player.getCenterX()-shotr.getOriginX()+MathUtils.cos(GameObjects.player.angle+0.3f)*48);
				shotr.setY(GameObjects.player.getCenterY()-shotr.getOriginY()+MathUtils.sin(GameObjects.player.angle+0.3f)*48);
				shotr.setAngle(GameObjects.player.angle);
				
				Resources.ingame.addShot(shotl);
				Resources.ingame.addShot(shotr);
			}
		};
		GameObjects.player.rocketlauncher=new Weapon(0.7f,0.5f,30){
			 @Override
			public void shoot(int pTeam) {
				 ammo--;
				Rocket rocket=new Rocket();
				rocket.team=pTeam;
				rocket.setX(GameObjects.player.getCenterX()-rocket.getOriginX()+MathUtils.cos(GameObjects.player.angle)*48);
				rocket.setY(GameObjects.player.getCenterY()-rocket.getOriginY()+MathUtils.sin(GameObjects.player.angle)*48);
				rocket.setAngle(GameObjects.player.angle);
				// the rocket will find its aim in the next frame
				//rocket.aim=Resources.ingame.getNearestShip(rocket.getCenterX(), rocket.getCenterY());

				Resources.ingame.addShot(rocket);
			}
			
		};
		
		GameObjects.player.zapper=new Weapon(0.2f,0.1f,500){
			 @Override
			public void shoot(int pTeam) {
				 ammo--;
				Zap zap=new Zap();
				zap.team=pTeam;
				zap.setX(GameObjects.player.getCenterX()-zap.getOriginX()+MathUtils.cos(GameObjects.player.angle)*48);
				zap.setY(GameObjects.player.getCenterY()-zap.getOriginX()+MathUtils.sin(GameObjects.player.angle)*48);
				zap.setAngle(GameObjects.player.angle);

				Resources.ingame.addShot(zap);
			}
			
		};
		
		GameObjects.player.plasma=new Weapon(1.5f,0.9f,100){
			 @Override
			public void shoot(int pTeam) {
				 ammo--;
				Plasma plasma=new Plasma();
				plasma.team=pTeam;
				plasma.setX(GameObjects.player.getCenterX()-plasma.getOriginX()+MathUtils.cos(GameObjects.player.angle)*48);
				plasma.setY(GameObjects.player.getCenterY()-plasma.getOriginX()+MathUtils.sin(GameObjects.player.angle)*48);
				plasma.setAngle(GameObjects.player.angle);
				
				Resources.ingame.addShot(plasma);
			}
			
		};*/
	}
	public void shoot(int pTeam, float pX,float pY,float pAngle,GameObject pAim) {
		//System.out.println("SHOOT");
		for(int i=0;i<shots.size;i++){// shoot all our shots :D
			ammo--;
			Shot shot=shots.get(i).create(pX,pY,pAngle);
			if(shot instanceof SteeredShot){ // aim steered shots
				((SteeredShot)shot).aim=pAim;
			}
			shot.team=pTeam;
			Res.ingame.addShot(shot);
		}
	}
}
