package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyInputManager implements KeyListener{

	//VARIABLES
	private boolean[] keys;
	public boolean up, down, left, right;
	public boolean aUp, aDown, aLeft, aRight;

	public KeyInputManager() {
		keys = new boolean[256]; //why 256? if its too low game does not run
	}
	public void update() {
		
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