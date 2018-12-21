package tilegame.userinterface;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import tilegame.Handler;


//This class stores all UI objects in an array and allows calling their commmon methods by just calling the respective method in the Uimanager 
public class UIManager {

	//VARIABLES
	private Handler handler;
	
	private ArrayList<UIObject> objects;
	//this arrayList stores all the objects added to the UImanager e.g buttons; 

	
	//the constructor takes in an handler object to pass along variables and instantiates the arrayList
	public UIManager(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}
	
	// this function iterates through all objects of the objects Arraylist and calls their update method
	public void update() {
		for(UIObject o : objects)
			o.update();
	}
	
	// this function iterates through all objects of the objects Arraylist and calls their render method
	public void render(Graphics g) {
		for(UIObject o : objects)
			o.render(g);
	}
	
	// this function iterates through all objects of the objects Arraylist and calls their onMouseMove method 
	public void onMouseMove(MouseEvent e) {
		for(UIObject o : objects)
			o.onMouseMove(e);
	}
	
	// this function iterates through all objects of the objects Arraylist and calls their onMouserelease method 
	public void onMouseRelease(MouseEvent e) {
		for(UIObject o : objects)
			o.onMouseRelease(e);
	}
	
	// this function adds an object to the ArrayList objects
	public void addObject(UIObject o) {
		objects.add(o);
	}
	
	// this function removes an object from the ArrayList objects
	public void removeObject(UIObject o) {
		objects.remove(o);
	}

	//GETTERS SETTERS (function of getters and setters described in handler class)
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
