package de.bitowl.space.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import de.bitowl.space.Res;

/**
 * points to a certain point on the map
 * 
 * @author bitowl
 *
 */
public class Marker extends GameObject{
	int x,y;
	public Marker(int pX,int pY) {
		super(Res.atlas.findRegion("marker"));
		x=pX;
		y=pY;
		setOrigin(16,16);
	}
	public void draw(Rectangle viewRectangle,SpriteBatch batch) {
		if(viewRectangle.contains(x, y)){
			setRotation(-90);
			setX(x-16);setY(y);
		}else{
			float centerX=viewRectangle.x+viewRectangle.width/2;
			float centerY=viewRectangle.y+viewRectangle.height/2;
			
			
			
			float angle=(float) Math.atan2(y-centerY, x-centerX);
			
			
		//	System.out.println(y+"-"+centerY+" | "+x+"-"+centerX+"  -> "+angle);
		//	int radius=Math.min(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2)-16;
			setRotation(MathUtils.radDeg*angle);	
			//float bildschirmAngle = MathUtils.atan2(Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()/2);
			
			float bildschirmAngle = MathUtils.atan2(viewRectangle.height/2,viewRectangle.width/2);
			
		//	System.out.println("Bildschrim "+Math.toDegrees(bildschirmAngle)+" angle: "+Math.toDegrees(angle));
		//	System.out.println(Math.tan(angle)+" - "+(centerY+Math.tan(angle)*(viewRectangle.width/2)));
			if(Math.abs(angle) <= bildschirmAngle) {
				setX(centerX+viewRectangle.width/2-32);
				setY( (centerY+(float)
						Math.tan(angle)*(viewRectangle.width/2)));
				if(getY()<viewRectangle.y){
					setY(viewRectangle.y);
				}else if(getY()>viewRectangle.y+viewRectangle.height-32){
					setY(viewRectangle.y+viewRectangle.height-32);
				}
			} else if(angle <= MathUtils.PI-bildschirmAngle && angle > 0){
				setX(centerX+(float) Math.tan(MathUtils.PI/2-angle)*(viewRectangle.height/2)-16);
				if(getX()<viewRectangle.x){
					setX(viewRectangle.x);
				}else if(getX()>viewRectangle.x+viewRectangle.width-32){
					setX(viewRectangle.x+viewRectangle.width-32);
				}
				setY(centerY+viewRectangle.height/2-32);
			} else if(angle >= -MathUtils.PI+bildschirmAngle && angle < 0){
				setX(centerX+(float) Math.tan(angle-MathUtils.PI/2)*(viewRectangle.height/2)-16);
				if(getX()<viewRectangle.x){
					setX(viewRectangle.x);
				}else if(getX()>viewRectangle.x+viewRectangle.width-32){
					setX(viewRectangle.x+viewRectangle.width-32);
				}
				
				setY(centerY-viewRectangle.height/2);
				//System.out.println("Unten bis "+(-MathUtils.PI+bildschirmAngle));
			} else {
				//System.out.println("Links");
				setX(centerX-viewRectangle.width/2);
				setY( (centerY-(float)Math.tan(angle)*(viewRectangle.width/2)) );
				
				if(getY()<viewRectangle.y){
					setY(viewRectangle.y);
				}else if(getY()>viewRectangle.y+viewRectangle.height-32){
					setY(viewRectangle.y+viewRectangle.height-32);
				}
			}
			
		}
		super.draw(batch, 1f);
		
	}
}
