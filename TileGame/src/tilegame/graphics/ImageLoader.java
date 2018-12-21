package tilegame.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//used to Load an Image in from a location in the resources folder.
public class ImageLoader {

	//CONSTRUCTOR
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {//try and catch statement in case errors happen
			e.printStackTrace();
			System.exit(1); //if we don't load image into game we don't want to run our game
		}
		return null;
	}
}
