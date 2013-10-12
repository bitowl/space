package de.bitowl.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ShopScreen implements Screen {
	Stage stage;
	
	public ShopScreen() {
		stage=new Stage();
		
		// UI building
		Table table=new Table();
		//table.setFillParent(true);
		//stage.addActor(table);
		
		
		TextButton back=new TextButton("back", Res.skin);
		back.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				SpaceGame.screen(Res.ingame);
			}
		});
		back.pad(5);
		table.add(back).pad(5);
		
		Label title=new Label("Shop", Res.skin,"title");
		table.add(title).expandX().align(Align.center).colspan(3).row();
		
		for(int i=0;i<18;i++){
		
		Label laser=new Label("Laser"+i,Res.skin);
		table.add(laser);
		
		Label desc=new Label("ein einfacher Laser, den jeder gerne nutzt, um nützliche Sachen zu erschaffen ohne kekse ist die welt doch auch nur halb so cool",Res.skin);
		desc.setWrap(true);
		desc.setWidth(180);
		table.add(desc).expandX().fill();
		
		Label price=new Label("1 Million DollarZ",Res.skin);
		table.add(price).padLeft(10);
		
		TextButton upgrade=new TextButton("upgrade!", Res.skin);
		table.add(upgrade).pad(5).row();
		}
		
		//table.add().expandY(); // alles nach oben verschieben
		
		ScrollPane pane=new ScrollPane(table,Res.skin);
		pane.setFillParent(true);
		pane.setFadeScrollBars(false);
		pane.setOverscroll(false, false);
		stage.addActor(pane);
		
		
	//	table.debug();
	}

	public void show(){
        Gdx.input.setInputProcessor(stage);

	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
		Table.drawDebug(stage);
		
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		
	}

}
