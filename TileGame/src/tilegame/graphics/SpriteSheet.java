package tilegame.graphics;

import java.awt.image.BufferedImage;

/*The Spritesheet class can store a buffered image and contains a method which takes in 4 integers corresponding to the x and y
 * position in units of pixels and height and width of a subimage which will be cropped out from the Buffered Image and returned.
 * It is useful to not have to load in every image needed in the game seperately, but to get them all from one object.*/
public class SpriteSheet {

	//VARIABLES

	private BufferedImage sheet;
	//This is the sheet variable which stores the BufferedImage which contains several subimages

	//The constructor takes in a BufferedImage and store it in the sheet variable
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}


	//This method crops out a subimage from the sheet object and returns it 
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x,  y, width, height);
	}
}