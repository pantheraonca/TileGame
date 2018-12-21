package tilegame.userinterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

//this class allows for the construction of Buttons
public class UIButton extends UIObject {

	//VARIABLES
	private BufferedImage image;
	private ClickListener clicker;

	//CONSTRUCTOR
	public UIButton(float x, float y, int width, int height, BufferedImage image, ClickListener clicker) {
		super(x, y, width, height);
		this.image = image;
		this.clicker = clicker;
	}

	@Override
	public void update() {
	}

	@Override 
	public void render(Graphics g) {
		g.drawImage(image, (int) x, (int) y, width, height, null);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}
}