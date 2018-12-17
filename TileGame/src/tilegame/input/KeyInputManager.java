package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyInputManager implements KeyListener{
	
	private boolean[] keys;
	public boolean up, down, left, right; //isMoving;
	public boolean aUp, aDown, aLeft, aRight;
	
	public KeyInputManager() {
		keys = new boolean[256]; //change to 8 ??
	}
	public void update() {
		//isMoving = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_S] || keys[KeyEvent.VK_A] || keys[KeyEvent.VK_D];
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aLeft = keys[KeyEvent.VK_LEFT];
		aRight = keys[KeyEvent.VK_RIGHT];
	}
	@Override
	public void keyTyped(KeyEvent e) {
	
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		

	}

}
