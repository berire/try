package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.stages.Main_Menu;
import com.mygdx.game.stages.StageHandle;

public class SOSGame extends Game {
	public static final int WIDTH=480;
	public static final int HEIGHT=760;

	public final static String TITLE= "SOS GAME";

	public static SpriteBatch batch;
	public static ScreenViewport view;
	public OrthographicCamera cam;

	private StageHandle handler;
	private Main_Menu menu;

	public void create() {
		view=new ScreenViewport();
		batch=new SpriteBatch();
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		view.setCamera(cam);
		//handler=new StageHandle();
		menu=new Main_Menu();

	}

	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);
		menu.render();
		//handler.creator();
	}
	public void dispose() {

	}

}
