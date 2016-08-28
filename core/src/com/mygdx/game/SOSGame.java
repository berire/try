package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.stages.Main_Menu;

public class SOSGame extends Game {
	public static final int WIDTH=480;
	public static final int HEIGHT=760;

	public final static String TITLE= "SOS GAME";

	public static SpriteBatch batch;
	public static ScreenViewport view;
	public OrthographicCamera cam;

	public void create() {
		view=new ScreenViewport();
		batch=new SpriteBatch();
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		view.setCamera(cam);
		setScreen(new Main_Menu(this));
	}

	@Override
	public void render() {
		super.render();
	}


}
