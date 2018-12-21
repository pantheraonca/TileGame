package tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import tilegame.difficulty.DifficultyLevel;
import tilegame.display.Display;
import tilegame.graphics.Assets;
import tilegame.graphics.PlayerLocator;
import tilegame.input.KeyInputManager;
import tilegame.input.MouseInputManager;
import tilegame.states.GameState;
import tilegame.states.MenuState;
import tilegame.states.SettingsState;
import tilegame.states.State;


/* Contains the game loop and initiate method which instantiates the three states 
 * menuState, gameState and settingState and other objects needed to run the game.
 * Also contains update and render method which get called over and over by the game loop 
 * and call the render method of the currently set state. */
public class Game { 

	// VARIABLES
	private Display display; 
	/* holds a JFrame object (a window) and 
	 * a Canvas object (something you can draw graphics to). */

	private int displayWidth, displayHeight; 
	/* determines the width and height (in unit of pixels)
	 * of the display, which in turn determines the width and height of JFrame and Canvas. */

	private final String GAME_TITLE; 
	// the title of the game, which is the name of the window.

	private boolean running; 
	/* will later be set to true when run method of the game is called. 
	 * this ensures our game-loop is running until the game is closed. */

	private BufferStrategy bufferStrategy;
	// tells the computer how to draw graphics to the screen without flickering.

	private Graphics graphicsObject; 
	// graphics object encapsulates information needed for rendering operations in Java.

	public GameState gameState; 
	/* States represent the different visible states 
	 * of the game and hold the corresponding objects.
	 * gameState is the state in which you actively play the game. */

	public MenuState menuState; 
	/* menuState is the state that the game starts in where you see the instructions
	 * on how to play and where you can switch to the other states. */

	public SettingsState settingsState; 
	// settingState is the state in which you can select your difficulty and player skill.

	private DifficultyLevel difficultyLevel; 
	/* holds the methods, which set the variables of other 
	 * classes according to the passed in difficulty level. */

	private KeyInputManager keyInputManager; 
	/* Needed for the input of the keyboard. 
	 * Holds a number of boolean variables for each keyboard key which is needed to play our game. */

	private MouseInputManager mouseInputManager; 
	/* holds a number of boolean variables for each mouse key, 
	 * which is needed to navigate through the settings and menu state as well as handling pop-up messages. */

	private Handler handler; 
	// handler object to help accessing variables of this class using the handler class.

	private PlayerLocator playerLocator; 
	// holds variables which change with the location of the player.


	// CONSTRUCTOR for a Game object, taking on a game title and the width and height of the display.
	public Game(String title, int displayWidth, int displayHeight) {

		this.displayWidth = displayWidth;
		this.displayHeight = displayHeight;
		this.GAME_TITLE = title;
		keyInputManager = new KeyInputManager(); 
		mouseInputManager = new MouseInputManager(); 
	}


	/* This is the game-loop. First calls the initialize method (description bellow),
	 * then starts the game-loop (a while-loop). */
	public void run() {

		initialize(); 

		running = true; //so the infinite game-loop can start. It only ends when the game is closed.

		int fps = 60; 
		/* frames per second (here "fps" because it is a widely used abbreviation).
		 * Determines how often the update and render method are called per second. */

		double timePerUpdate = 1000000000 / fps;
		// the amount of nanoseconds that passes between two update() and render() calls.

		double delta = 0; 
		/* time that has passed since update and render method last has been called divided by 
		 * the timePerUpdate. When this value reaches 1 the first if statements will be executed 
		 * and update an render method will be run which in turn resets the delta variable. */

		long now; 
		/* constantly gets updated in the game-loop to the current time 
		 * (in respect to the time of the Java Virtual Machine). */

		long lastTime = System.nanoTime(); 
		/* is the time at which the run method was called the last time 
		 * (in respect to the time of the Java Virtual Machine). */

		long timer = 0; 
		/* time passed since ticks and frames last have been printed and since timer has been set to zero.
		 * When this value reaches 1 the second if statement will be run which prints the fps and updates 
		 * to the console and resets timer. */

		int updates = 0; 
		// amount of  calls per second of the update method.

		while(running) { //game loop
			now = System.nanoTime();  
			// constantly gets updated to current system time (in nanoseconds).

			delta += (now - lastTime) / timePerUpdate;  
			// time that has passed since update and render method last has been called divided by 1 / fps.

			timer += now - lastTime; 
			// time passed since ticks and frames last have been printed and since timer has been set to zero.

			lastTime = now; 
			// time at which while loop has been exited ("or run the if statements").

			if(delta >= 1) {
				update();
				render();
				updates++;
				delta--;
			}

			if(timer >= 1000000000) {
				System.out.println("Updates and Frames:" + updates);
				updates = 0;
				timer = 0;
			}
		}
	}


	// initializes the objects and calls the methods needed to start the game.
	private void initialize() { 

		display = new Display(GAME_TITLE, displayWidth, displayHeight); 
		display.getFrame().addKeyListener(keyInputManager); 
		// allows to get input from the keyboard keys.

		display.getCanvas().addMouseListener(mouseInputManager); 
		// allows to get input from the mouse keys.

		display.getCanvas().addMouseMotionListener(mouseInputManager); 
		// allows to get the position of the cursor.

		Assets.crop(); 
		/* calls the crop() method in the Assets class which crops out images 
		 * from a sprite-sheet and stores them in variables. */

		playerLocator = new PlayerLocator (handler, 0, 0); 
		//instantiates the player locator initial position (0, 0) will be overwritten later.

		handler = new Handler(this);

		gameState = new GameState(handler);
		settingsState = new SettingsState(handler);
		menuState = new MenuState(handler);

		State.setState(menuState); 
		// sets the state you start the game in to menuState.

		difficultyLevel = new DifficultyLevel(handler, null); 
		// null so the difficulty will be set to default.
	}


	/* gets called by the run method in regularly spaced time intervals. 
	 * Whatever method the update method is called upon,
	 * is in return also called in the same time intervals. */
	private void update() {

		keyInputManager.update();

		//if(State.getState() != null) DELETE
		State.getState().update();
	}


	// is called after the update method and renders certain specified updated objects to the canvas.
	private void render() {

		bufferStrategy = display.getCanvas().getBufferStrategy(); 
		// sets the bufferStrategy to the bufferStrategy specified in the Canvas object.

		if(bufferStrategy == null) {
			display.getCanvas().createBufferStrategy(3); 
			/* creates a new strategy for multi-buffering, multi-buffering is useful for rendering performance.
			 * This method attempts to create the best strategy available with the number of buffers supplied. */
			return;
		}

		graphicsObject = bufferStrategy.getDrawGraphics(); 
		// creates a graphics context for the drawing buffer.

		graphicsObject.clearRect(0, 0, displayWidth, displayHeight); 
		// clears the specified rectangle by filling it with the background color of the current drawing surface. 

		//if(State.getState() != null) DELETE
		State.getState().render(graphicsObject); 
		// calls the render method in the state that is currently set.

		bufferStrategy.show(); 
		//makes the next available buffer visible by either copying the memory or changing the display pointer.

		graphicsObject.dispose(); 
		//disposes of this graphics context and releases any system resources that it is using.
	}


	//GETTERS SETTERS (function of getters and setters described in handler class)
	public KeyInputManager getKeyInputManager() {
		return keyInputManager;
	}

	public void setKeyInputManager(KeyInputManager keyInputManager) {
		this.keyInputManager = keyInputManager;
	}

	public MouseInputManager getMouseInputManager() {
		return mouseInputManager;
	}

	public int getWidth() {
		return displayWidth;
	}

	public int getHeight() {
		return displayHeight;
	}

	public MenuState getMenuState() {
		return menuState;
	}

	public void setMenuState(MenuState menuState) {
		this.menuState = menuState;
	}

	public PlayerLocator getPlayerCamera() {
		return playerLocator;
	}

	public void setPlayerCamera(PlayerLocator playerCamera) {
		this.playerLocator = playerCamera;
	}

	public DifficultyLevel getDifficultyState() {
		return difficultyLevel;
	}

	public void setDifficultyState(DifficultyLevel difficultyState) {
		this.difficultyLevel = difficultyState;
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}
}