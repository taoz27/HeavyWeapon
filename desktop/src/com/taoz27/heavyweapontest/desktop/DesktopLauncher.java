package com.taoz27.heavyweapontest.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.taoz27.heavyweapontest.MainGame;

public class DesktopLauncher {
	private static boolean rebuildAtlas=false;
	private static boolean drawDebugOutline=false;
	public static void main (String[] arg) {
		if (rebuildAtlas){
			TexturePacker.Settings settings=new TexturePacker.Settings();
			settings.maxWidth=4096;
			settings.maxHeight=1024;
			settings.debug=drawDebugOutline;
			settings.duplicatePadding=true;
			TexturePacker.process(settings,"tank_raw_img","../assets/images","plane_images");
		}
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=950;
		config.height=450;
		config.title="Heavy Weapon";
		new LwjglApplication(new MainGame(), config);
	}
}
