package com.lifemm.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lifemm.game.LifeMM;

public class DesktopLauncher {
    private DesktopLauncher() {
        // Do nothing. We don't want to create a launcher again.
    }

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Life--";
		config.width = 1440;
		config.height = 810;
		LwjglApplication app = new LwjglApplication(new LifeMM(), config);
        System.out.println(app);
	}
}
