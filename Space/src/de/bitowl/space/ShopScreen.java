package de.bitowl.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import de.bitowl.space.objects.ConfiguredWeapon;
import de.bitowl.space.objects.Weapon;

public class ShopScreen implements Screen {
	Stage stage;
	
	Array<TextButton> weaponButtons;
	
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
		
		weaponButtons=new Array<TextButton>();
		
		for(int i=0;i<Res.weapons.size;i++){
			ConfiguredWeapon weapon=Res.weapons.get(i);
			Label laser=new Label(weapon.name,Res.skin);
			table.add(laser);
			
			Label desc=new Label("ein einfacher Laser, den jeder gerne nutzt, um nützliche Sachen zu erschaffen ohne kekse ist die welt doch auch nur halb so cool",Res.skin);
			desc.setWrap(true);
			desc.setWidth(180);
			table.add(desc).expandX().fill();
			
			
			// get the next possible upgrade
			if(weapon.bought<weapon.upgrades.size){
				Weapon nextOne=weapon.upgrades.get(weapon.bought);
			
				Label price=new Label(nextOne.price+"",Res.skin);
				table.add(price).padLeft(10);
			
				TextButton upgrade=new TextButton("TODO", Res.skin);
				weaponButtons.add(upgrade);
				
				table.add(upgrade).pad(5).row();
			}else{
				table.add();
				TextButton finished=new TextButton("fully upgraded",Res.skin,"default-disabled");
				finished.setDisabled(true);
				weaponButtons.add(finished);
				table.add(finished).row();
				
			}
			
			
		}
		
		//table.add().expandY(); // alles nach oben verschieben
		
		ScrollPane pane=new ScrollPane(table,Res.skin);
		pane.setFillParent(true);
		pane.setFadeScrollBars(false);
		pane.setOverscroll(false, false);
		stage.addActor(pane);
		
		
	//	table.debug();
	}
	
	/**
	 * updates the "upgrade"-Buttons concerning how much money you have
	 */
	public void updateButtons(){
		for(int i=0;i<Res.weapons.size;i++){
			ConfiguredWeapon weapon=Res.weapons.get(i);
			if(weapon.bought<weapon.upgrades.size){// weapon can be upgraded
				Weapon nextOne=weapon.upgrades.get(weapon.bought);
			
				System.out.println("you have "+Res.player.money+" million dollarZ!");
				if(nextOne.price>Res.player.money){
					weaponButtons.get(i).setText("not enough money");
					weaponButtons.get(i).setDisabled(true);
					weaponButtons.get(i).setStyle(Res.skin.get("default-disabled", TextButtonStyle.class));
				}else{
					if(weapon.bought==0){
						weaponButtons.get(i).setText("buy!");// we don't have this weapon yet
					}else{
						weaponButtons.get(i).setText("upgrade!");
					}
					weaponButtons.get(i).setDisabled(false);
					weaponButtons.get(i).setStyle(Res.skin.get("default", TextButtonStyle.class));
				}
			}
		}
	}

	public void show(){
        Gdx.input.setInputProcessor(stage);
        updateButtons(); // see if we now have enough money

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
