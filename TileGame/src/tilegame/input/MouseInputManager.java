package tilegame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import tilegame.userinterface.UIManager;

public class MouseInputManager implements MouseListener, MouseMotionListener {

	//VARIABLES
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private UIManager uiManager;

	public MouseInputManager() {
	}

	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}

	// IMPLEMENTED METHODS //passt die überschrift ?
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		if(uiManager != null) //if the uiManager exists
			uiManager.onMouseMove(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;

		if(uiManager != null) //if the uiManager exists
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

	//GETTERS SETTERS

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
