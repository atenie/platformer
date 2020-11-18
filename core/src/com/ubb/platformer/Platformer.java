package com.ubb.platformer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Platformer extends ApplicationAdapter {
	private Stage stage;
	private Button startButton;
	private Button aboutButton;
	private Skin skin;
	private Table table;
	private Label titleLabel;
	 Button CloseCredits;

	public void create () {
		stage = new Stage(new ScreenViewport());
		skin = new Skin(
				Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

		titleLabel = new Label("PlatformerB", skin);
		titleLabel.setFontScale(5);

		startButton = new TextButton("Start",skin);
		startButton.setTransform(true);

		aboutButton = new TextButton("About",skin);
		aboutButton.setTransform(true);


		aboutButton.addListener( new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				final Window pause = new Window("", skin);
				Table creditsTable = new Table();

				creditsTable.add(new Label("Patrick Badea",skin));
				creditsTable.row();

				creditsTable.add(new Label("Mark Gati",skin)).center();
				creditsTable.row().fillX().fillY().expandX().expandY();

				creditsTable.add(new Label("Alexandru Tenie",skin)).center();
				creditsTable.row().fillX().fillY().expandX().expandY();

				CloseCredits=new TextButton("GG", skin);

				creditsTable.add(CloseCredits).center();
				creditsTable.row().fillX().fillY().expandX().expandY();

				pause.add(creditsTable ); //Add a new text button that unpauses the game.
				pause.pack(); //Important! Correctly scales the window after adding new elements.
				float newWidth = 600, newHeight = 400;
				pause.setBounds((Gdx.graphics.getWidth() - newWidth ) / 2,
						(Gdx.graphics.getHeight() - newHeight ) / 2, newWidth , newHeight ); //Center on screen.
				stage.addActor(pause);
				CloseCredits.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y){
						pause.clear();
						pause.remove();
					}
				});
			}
		} );

//		CloseCredits.addListener(new ClickListener(){
//			@Override
//			public void clicked(InputEvent event, float x, float y) {
//				pause.remove();
//			}
//		});


		table = new Table();
		//Just in case:
//		table.setDebug(true);
		table.add(titleLabel).center();
		table.row().fillX().fillY().expandX().expandY();
		table.add(startButton).fillX().fillY().expandX().expandY();
		table.row().fillX().fillY().expandX().expandY();
		table.add(aboutButton).fillX().fillY().expandX().expandY();


		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}

	public void resize (int width, int height) {
		// See below for what true means.
		table.setSize(stage.getWidth()/3,stage.getWidth()/3);
		table.setPosition(stage.getWidth()/2-(table.getWidth()/2),stage.getHeight()/2-(table.getHeight()/2));
		stage.getViewport().update(width, height, true);
	}

	public void render () {
		Gdx.gl.glClearColor(0.3F,0.5F,0.35F,1);
		float delta = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	public void dispose () {
		stage.dispose();
	}
}
