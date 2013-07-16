package de.bitowl.space.objects;

import de.bitowl.space.Resources;

public class CivilianShip extends Enemy {
	Planet home;
	public CivilianShip() {
		super(Resources.atlas.findRegion("civilian"));
		setOrigin(26, 30);
		MAX_SPEED=100;
		ANGLE_ACC_SPEED=3;
		accSpeed=10;
	}
	@Override
	public void act(Chunk pChunk, float delta) {
		super.act(pChunk, delta);
		if(getRectangle().overlaps(aim.getRectangle())){
			// shuttle between the two planets
			Planet at=(Planet)aim;
			aim=home;
			home=at;
		}
	}
}
