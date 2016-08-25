package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.SOSGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = SOSGame.TITLE;
		config.width = SOSGame.WIDTH;
		config.height = SOSGame.HEIGHT;
		new LwjglApplication(new SOSGame(), config);
	}
}
