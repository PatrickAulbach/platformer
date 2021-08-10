package com.missingking.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.missingking.game.VincentAndTheMissingKing;
import com.missingking.game.config.GameConfig;
import com.missingking.game.level.TestLevel;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) GameConfig.WIDTH;
		config.height = (int) GameConfig.HEIGHT;
		// new LwjglApplication(new VincentAndTheMissingKing(), config);
		new LwjglApplication(new VincentAndTheMissingKing(), config);
	}
}
