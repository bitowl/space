package de.bitowl.space;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * 
 * following the idea of nexsoftware in IRC to subclass Image for giving the
 * rotation to our animationDrawable
 * 
 * @author bitowl
 * 
 */
public class SpaceImage extends Image {
	
	public SpaceImage(Drawable drawable) {
		super(drawable);
	}

	public SpaceImage(AtlasRegion findRegion) {
		super(findRegion);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		validate();

		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

		float x = getX();
		float y = getY();
		float scaleX = getScaleX();
		float scaleY = getScaleY();

		if (getDrawable()!= null) {
			if (getDrawable().getClass() == TextureRegionDrawable.class) {
				TextureRegion region = ((TextureRegionDrawable) getDrawable())
						.getRegion();
				float rotation = getRotation();
				if (scaleX == 1 && scaleY == 1 && rotation == 0)
					batch.draw(region, x +getX(), y + getY(), getWidth(),
							getHeight());
				else {
					batch.draw(region, x + getX(), y + getY(), getOriginX()
							- getX(), getOriginY() - getY(), getWidth(),
							getHeight(), scaleX, scaleY, rotation);
				}
			} else if(getDrawable().getClass()==AnimationDrawable.class){
				AnimationDrawable draw=(AnimationDrawable)getDrawable();
				draw.draw(batch,  x, y , getOriginX()
						- getX(), getOriginY() - getY(), getWidth(),
						getHeight(), scaleX, scaleY, getRotation());
			}else {
				getDrawable().draw(batch, x+getImageX() , y + getImageY(), getWidth()
						* scaleX, getHeight() * scaleY);
			}
		}
	}
}
