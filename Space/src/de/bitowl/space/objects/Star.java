package de.bitowl.space.objects;

import de.bitowl.space.Resources;

/**
 * a simple fix-star
 * 
 * @author bitowl
 *
 */
public class Star extends GameObject {
	public Star(int pType,int pX,int pY){
		// choose a random star image
		super(Resources.atlas.findRegion("star"+pType));

		setX(pX);
		setY(pY);
	}
}
