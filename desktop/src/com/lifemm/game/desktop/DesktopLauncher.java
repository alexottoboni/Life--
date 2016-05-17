package com.lifemm.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lifemm.game.Logic.LifeMM;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Life--";
		config.width = 1440;
		config.height = 810;
		new LwjglApplication(new LifeMM(), config);
	}
}
