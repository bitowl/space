package de.bitowl.space.objects;

import de.bitowl.space.Res;

/**
 * a simple fix-star
 * 
 * @author bitowl
 *
 */
public class Star extends GameObject {
	public Star(int pType,int pX,int pY){
		// choose a random star image
		super(Res.atlas.findRegion("star"+pType));

		setX(pX);
		setY(pY);
	}
}
