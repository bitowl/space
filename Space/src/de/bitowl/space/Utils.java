package de.bitowl.space;

import com.badlogic.gdx.math.MathUtils;

public class Utils {
	public static float distance(float x1,float y1,float x2,float y2){
		return (float) Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	}
	public static float distanceSq(float x1,float y1,float x2,float y2){
		return (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1);
	}
	
	public static float angle(float x1,float y1,float x2,float y2){
		return MathUtils.atan2((y2-y1),(x2-x1));
	}
	
	public static float differenceAngles(float angle1,float angle2){
		float diff=angle1-angle2;
		// 0.1f is a buffer so that we have no problem with ships that are 180° away from the player
		if(diff>MathUtils.PI+0.1f){diff-=MathUtils.PI;}
		if(diff<-MathUtils.PI-0.1f){diff+=MathUtils.PI;}
		return Math.abs(diff);
	}
}
