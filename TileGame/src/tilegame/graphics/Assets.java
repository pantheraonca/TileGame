package tilegame.graphics;

import java.awt.image.BufferedImage;


//this class handles the sprite sheet of the game and crops it into different Images that are then used.
public class Assets {
	

	// the height and width of the image that will be cropped out in pixels.
	public static final int IMAGE_WIDTH = 16, IMAGE_HEIGHT = 16;

	//Image with an accessible buffer of image data.
	public static BufferedImage player, dirt, grass, rock, enemy, shadow, energy, 
								boss, easy, medium, hard, back, settings, magma, 
								fighter, stamina;
	
	//BufferedImage array with an accessible buffer of image data.
	public static BufferedImage[] btn_start;

	// this method takes in a new sprite sheet from the specified path and crops 
	// out different parts that are then assigned to the different BufferedImage variables
	public static void crop() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/terrain.png"));

		player = sheet.crop(IMAGE_WIDTH * 7, IMAGE_HEIGHT * 7, IMAGE_WIDTH, IMAGE_HEIGHT);
		dirt = sheet.crop(IMAGE_WIDTH * 2, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		grass = sheet.crop(IMAGE_WIDTH * 2, IMAGE_HEIGHT * 9, IMAGE_WIDTH, IMAGE_HEIGHT);
		rock = sheet.crop(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		enemy = sheet.crop(IMAGE_WIDTH * 8, IMAGE_HEIGHT * 7, IMAGE_WIDTH, IMAGE_HEIGHT);
		shadow = sheet.crop(IMAGE_WIDTH * 1, IMAGE_HEIGHT * 7, IMAGE_WIDTH, IMAGE_HEIGHT);
		energy = sheet.crop(IMAGE_WIDTH * 2, IMAGE_HEIGHT * 10, IMAGE_WIDTH, IMAGE_HEIGHT);
		boss = sheet.crop(IMAGE_WIDTH * 7, IMAGE_HEIGHT * 1, IMAGE_WIDTH, IMAGE_HEIGHT);
		easy = sheet.crop(IMAGE_WIDTH * 12, IMAGE_HEIGHT * 8, IMAGE_WIDTH, IMAGE_HEIGHT);
		medium = sheet.crop(IMAGE_WIDTH * 6, IMAGE_HEIGHT * 4, IMAGE_WIDTH, IMAGE_HEIGHT);
		hard = sheet.crop(IMAGE_WIDTH * 8, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		back = sheet.crop(IMAGE_WIDTH * 3, IMAGE_HEIGHT * 3, IMAGE_WIDTH, IMAGE_HEIGHT);
		settings = sheet.crop(4* IMAGE_WIDTH, IMAGE_HEIGHT * 4, IMAGE_WIDTH, IMAGE_HEIGHT);
		magma = sheet.crop(IMAGE_WIDTH * 15, IMAGE_HEIGHT * 15, IMAGE_WIDTH, IMAGE_HEIGHT);
		fighter = sheet.crop(IMAGE_WIDTH * 15, IMAGE_HEIGHT * 15, IMAGE_WIDTH, IMAGE_HEIGHT);
		stamina = sheet.crop(IMAGE_WIDTH * 2, IMAGE_HEIGHT * 10, IMAGE_WIDTH, IMAGE_HEIGHT);

		btn_start = new BufferedImage[2]; //used so the start button can change appearance
		btn_start[0] = sheet.crop(IMAGE_WIDTH * 12, IMAGE_HEIGHT * 2, IMAGE_WIDTH, IMAGE_HEIGHT);
		btn_start[1] = sheet.crop(IMAGE_WIDTH * 13, IMAGE_HEIGHT * 3, IMAGE_WIDTH, IMAGE_HEIGHT);
	}
}