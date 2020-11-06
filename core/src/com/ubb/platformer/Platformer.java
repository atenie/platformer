package com.ubb.platformer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
