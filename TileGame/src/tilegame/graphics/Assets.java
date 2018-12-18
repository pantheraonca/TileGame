package tilegame.graphics;

import java.awt.image.BufferedImage;

public class Assets {

	public static final int width = 16, height = 16;

	public static BufferedImage player, dirt, grass, rock, enemy, shadow, energy, boss, easy, medium, hard, back, settings, magma, fighter, stamina;
	public static BufferedImage[] btn_start;


	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/terrain.png"));

		player = sheet.crop(width * 7, height * 7, width, height);
		dirt = sheet.crop(width * 2, 0, width, height);
		grass = sheet.crop(width * 2, height * 9, width, height);
		rock = sheet.crop(0, 0, width, height);
		enemy = sheet.crop(width * 8, height * 7, width, height);
		shadow = sheet.crop(width * 1, height * 7, width, height);
		energy = sheet.crop(width * 2, height * 10, width, height);
		boss = sheet.crop(width * 7, height * 1, width, height);
		easy = sheet.crop(width * 12, height * 8, width, height);
		medium = sheet.crop(width * 6, height * 4, width, height);
		hard = sheet.crop(width * 8, 0, width, height);
		back = sheet.crop(width * 3, height * 3, width, height);
		settings = sheet.crop(4* width, height * 4, width, height);
		magma = sheet.crop(width * 15, height * 15, width, height);
		fighter = sheet.crop(width * 15, height * 15, width, height);
		stamina = sheet.crop(width * 2, height * 10, width, height);
		
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 12, height * 2, width, height);
		btn_start[1] = sheet.crop(width * 13, height * 3, width, height);
	}

}
