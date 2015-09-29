package br.grupointegrado.ads.flappyBird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import br.grupointegrado.ads.flappyBird.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 728;
		config.width = 1028;

		new LwjglApplication(new MainGame(), config);
	}
}
