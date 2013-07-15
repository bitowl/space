package de.bitowl.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;

public class AnimationDrawable extends BaseDrawable {
	private Animation animation;
	private float stateTime;
	private boolean looping;


	public AnimationDrawable(Animation animation, boolean looping) {
		setAnimation(animation, looping);
	}

	public AnimationDrawable(AnimationDrawable drawable) {
		super(drawable);
		setAnimation(drawable.animation, drawable.looping);
	}

	public void draw(SpriteBatch batch, float x, float y, float width,
			float height) {

		stateTime += Gdx.graphics.getDeltaTime();
		batch.draw(animation.getKeyFrame(stateTime, this.looping), x, y, width,
				height);
	}
	public void draw(SpriteBatch batch, float x, float y, float width,
			float height,float originX,float  originY,float scaleX,float scaleY,float rotation) {

		stateTime += Gdx.graphics.getDeltaTime();
		batch.draw(animation.getKeyFrame(stateTime, this.looping), x, y, width,
				height,originX,originY,scaleX,scaleY,rotation);
/*		batch.draw(region, x + getX(), y + getY(), getOriginX()
				- getX(), getOriginY() - getY(), getWidth(),
				getHeight(), scaleX, scaleY, rotation);*/
	}
	public void setAnimation(Animation animation, boolean looping) {
		this.animation = animation;
		this.looping = looping;
		setMinWidth(Math.abs(animation.getKeyFrame(0).getRegionWidth()));
		setMinHeight(Math.abs(animation.getKeyFrame(0).getRegionHeight()));
	}

	public Animation getAnimation() {
		return animation;
	}
}