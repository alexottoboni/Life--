package com.lifemm.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lifemm.game.LifeMM;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DesktopLauncher {

   private static final Logger LOGGER = Logger.getLogger(DesktopLauncher.class.getName()); 

    private DesktopLauncher() {
        // Do nothing. We don't want to create a launcher again.
    }

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Life--";
		config.width = 1440;
		config.height = 810;
		LwjglApplication app = new LwjglApplication(new LifeMM(), config);
        LOGGER.log(Level.INFO, app.toString());
	}
}
