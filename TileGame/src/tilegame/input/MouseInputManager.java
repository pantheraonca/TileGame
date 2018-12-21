package tilegame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import tilegame.userinterface.UIManager;

//class that enables mouse input in the game and stores the mouse keys used in the game.
public class MouseInputManager implements MouseListener, MouseMotionListener {

	//VARIABLES
	private boolean leftPressed, rightPressed;
	//left and right mouse key
	
	private int mouseX, mouseY;
	//position of the cursor
	
	private UIManager uiManager;
	//a uiManager object used to enable and disable the mouse input

	
	public MouseInputManager() {
	}

	//initiates the UIManager object.
	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}

	// IMPLEMENTED METHODS
	
	//returns the x and y position of the cursor
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		if(uiManager != null) //if the uiManager exists, if not mouse input is disabled
			uiManager.onMouseMove(e);
	}
	
	//method that is invoked if one of the specified mouse keys is pressed.
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}
	
	//method that is invoked if one of the specified mouse keys is released.
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;

		if(uiManager != null) //if the uiManager exists, if not mouse input is disabled
			uiManager.onMouseRelease(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	//GETTERS SETTERS (function of getters and setters described in handler class)

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
}
