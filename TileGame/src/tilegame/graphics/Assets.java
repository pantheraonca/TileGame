package tilegame.graphics;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static final int width = 16, height = 16;
	
	public static BufferedImage player, dirt, grass, rock, tree, shadow;
	public static BufferedImage[] btn_start;
	
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/terrain.png"));
		
		player = sheet.crop(width * 7, height * 7, width, height);
		dirt = sheet.crop(width * 2, 0, width, height);
		grass = sheet.crop(width * 2, height * 9, width, height);
		rock = sheet.crop(0, 0, width, height);
		tree = sheet.crop(width * 5, height * 4, width, height);
		shadow = sheet.crop(width * 1, height * 7, width, height);
	
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 12, height * 2, width, height);
		btn_start[1] = sheet.crop(width * 13, height * 3, width, height);
	}

}
