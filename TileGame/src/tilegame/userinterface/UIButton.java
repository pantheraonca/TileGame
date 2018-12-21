package tilegame.userinterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

//this class allows for the construction of Buttons
public class UIButton extends UIObject {

	//VARIABLES
	private BufferedImage image;
	// buffered image object which will be used to render at the position the button is displayed
	
	private ClickListener clicker;
	//ClickListener object of which the on click method will be called 

	//CONSTRUCTOR
	public UIButton(float x, float y, int width, int height, BufferedImage image, ClickListener clicker) {
		super(x, y, width, height);
		this.image = image;
		this.clicker = clicker;
	}

	@Override
	public void update() {
	}
	//draws one of the bufferedImages stored in the image variable to the canvas  
	@Override 
	public void render(Graphics g) {
		g.drawImage(image, (int) x, (int) y, width, height, null);
	}
	
	// executes the onClick method of the clickListener
	@Override
	public void onClick() {
		clicker.onClick();
	}
}