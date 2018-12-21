package tilegame.userinterface;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

//this class is abstract so it must be extended to instantiate an object. it provides methods a UIobject must contain
public abstract class UIObject {

	//VARIABLES
	protected float x, y;
	//upper left x and y coordinate of the rectangle defining the UIObject 
	
	protected int width, height;
	//width and height of the rectangle defining the UIObject
	
	protected Rectangle bounds;
	//rectangle which defines the dimensions and position of the UIObject
	
	protected boolean hovering = false;
	//boolean which is set to true when 

	
	//takes in the coordinates of the object and its dimensions and creates a rectangle with these values which will later be used to 
	//detect mouseEvents happening in the rectangles boundaries
	public UIObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int) x, (int) y, width, height);
	}
	
	
	// abstract methods which specify which methods must be implemented by non abstract subclasses
	public abstract void update();

	
	public abstract void render(Graphics g);

	
	public abstract void onClick();

	
	//if the mouse hovers over an the area of equal to the area occupied by the object the boolean hovering is set to true;
	public void onMouseMove(MouseEvent e) {
		if(bounds.contains(e.getX(), e.getY()))
			hovering = true;
		else 
			hovering = false;
	}
	
	//if the mouse hovers over the respective object an onclick method can be specified which in turn specifies the events taking place 
	public void onMouseRelease(MouseEvent e) {
		if(hovering) 
			onClick();
	}

	//GETTERS SETTERS (function of getters and setters described in handler class)
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
}
