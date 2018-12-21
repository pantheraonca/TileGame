package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//class that enables keyboard input and stores the keys that can be used in the game.
public class KeyInputManager implements KeyListener{

	//VARIABLES
	private boolean[] keys;
	//boolean array that holds all keyboard keys that can be used in the game.
	
	public boolean up, down, left, right;
	public boolean aUp, aDown, aLeft, aRight;
	//different key events that will be set bellow.
	//If one of the keys is pressed the boolean will be set to true.

	
	//initiates the keys array and sets the array size to 256 (256 because of the amount of keys in a regular keyboard)
	public KeyInputManager() {
		keys = new boolean[256]; 
	}
	
	public void update() {
		
		//keys used for moving (W,A,S,D) 
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];

		//keys used for attacking (arrowkeys)
		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aLeft = keys[KeyEvent.VK_LEFT];
		aRight = keys[KeyEvent.VK_RIGHT];
	}
	
	// IMPLEMENTED METHODS
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	//method that returns true if one of the specified keys is pressed.
	@Override
	public void keyPressed(KeyEvent e) {
			keys[e.getKeyCode()] = true;
	}

	//method that returns false once one of the specified keys is released.
	@Override
	public void keyReleased(KeyEvent e) {
			keys[e.getKeyCode()] = false;
	}
}