package tilegame.userinterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

//class that allows for the constructions of buttons that change their appearance if the cursor is hovering over them.
public class UIImageButton extends UIObject {

	//VARIABLES
	private BufferedImage[] images;
	// buffered image array of the two buffered images which can be rendered as button
	
	private ClickListener clicker;
	//ClickListener object of which the on click method will be called 

	//Constructor
	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.images = images;
		this.clicker = clicker;
	}

	@Override
	public void update() {
	}
	
	
	//draws one of the bufferedImages stored in the array to the canvas depending if mouse hovers over button or not 
	@Override
	public void render(Graphics g) {
		if(hovering)
			g.drawImage(images[1], (int) x, (int) y, width, height, null);
		else
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}
}
