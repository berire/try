package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.stages.Main_Menu;

public class SOSGame extends Game {
	public static int WIDTH=480;
	public static int HEIGHT= 800;


	public final static String TITLE= "SOS GAME";

	public static SpriteBatch batch;
	public static ScreenViewport view;
	public OrthographicCamera cam;

	public void create() {
		SoundAssets.loadSound();
		view=new ScreenViewport();
		batch=new SpriteBatch();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		// Constructs a new OrthographicCamera, using the given viewport width and height
		// Height is multiplied by aspect ratio.
		cam = new OrthographicCamera(30, 30 * (h / w));
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		WIDTH=(int)Gdx.graphics.getWidth();
		HEIGHT=(int)Gdx.graphics.getHeight();

		cam.update();
		setScreen(new Main_Menu(this));
	}

	@Override
	public void render() {
		super.render();
	}


}
