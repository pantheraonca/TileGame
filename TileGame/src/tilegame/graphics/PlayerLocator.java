package tilegame.graphics;

import tilegame.Handler;
import tilegame.entities.Entity;

/*This class can be instantiated to create an object which will store two variables which correspond to pixel
 * position in the middle of the player object. it also contains a method to update these variables. These 
 * variables are in our case used to by the Shadow class to determine where not to render the shadow.*/
public class PlayerLocator {

	private float xOffset;
	// float value which in our case corresponds the x value at the center of the player object in units of pixels
	// more general the amount the camera is shifted in x direction on the field
	
	private float yOffset;
	// float value which corresponds the x value at the center of the player object in units of pixels
	// more general the amount the camera is shifted in x direction on the field
	
	private Handler handler;
	
	
	// Constructor for the playerCamera object. It takes in an xOffset and yOffset value which specify
	// which pixel the playerCamera will initially be centered on 
	public PlayerLocator (Handler handler, float xOffset, float yOffset) {

		this.handler= handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	
	/*This method centers the playerCamera on the middle of the player object by getting the player position, which 
	 *gives the player objects upper left position in units of pixels, and then adding half the player width and height
	 to the x and y position respectively.*/
	public void centerOn(Entity player) {

		xOffset = player.getX() + (float) (0.5 * player.getWidth());
		yOffset = player.getY() + (float) (0.5 * player.getHeight());
	}
	


	//GETTERS SETTERS

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}