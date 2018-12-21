package tilegame.userinterface;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import tilegame.Handler;

public class UIManager {

	//VARIABLES
	private Handler handler;
	private ArrayList<UIObject> objects;

	//CONSTRUCTOR
	public UIManager(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}

	public void update() {
		for(UIObject o : objects)
			o.update();
	}

	public void render(Graphics g) {
		for(UIObject o : objects)
			o.render(g);
	}

	public void onMouseMove(MouseEvent e) {
		for(UIObject o : objects)
			o.onMouseMove(e);
	}

	public void onMouseRelease(MouseEvent e) {
		for(UIObject o : objects)
			o.onMouseRelease(e);
	}
	
	public void addObject(UIObject o) {
		objects.add(o);
	}

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
