package tilegame;


import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import tilegame.difficulty.Difficulty;
import tilegame.difficulty.DifficultyLevel;
import tilegame.display.Display;
import tilegame.graphics.Assets;
import tilegame.graphics.PlayerCamera;
import tilegame.input.KeyInputManager;
import tilegame.input.MouseInputManager;
import tilegame.states.GameState;
import tilegame.states.MenuState;
import tilegame.states.SettingsState;
import tilegame.states.State;

public class Game implements Runnable { //imp runnable so it can run on a thread
	
	//VARIABLES
	private Display display;
	private int width, height; //so this class also has access to the width and height easily
	public String title;

	private boolean running = false;
	private Thread thread; //this is a thread object


	private BufferStrategy bs; //tells the computer how to draw things to the screen
	private Graphics g;

	//States
	public GameState gameState; //can access easily when public might not be best practice
	public MenuState menuState;
	public SettingsState settingsState;

	//Difficulty
	private DifficultyLevel difficultyLevel;
	private Difficulty difficulty;

	//Input
	private KeyInputManager keyInputManager;
	private MouseInputManager mouseInputManager;

	//Handler
	private Handler handler;

	//PlayerCamera
	private PlayerCamera playerCamera;

	//CONSTRUCTOR
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyInputManager = new KeyInputManager();
		mouseInputManager = new MouseInputManager();
	}

	private void init() { //initializes graphics
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyInputManager); //addKeyListener allows to get input from the keyboard
		display.getFrame().addMouseListener(mouseInputManager);
		display.getFrame().addMouseMotionListener(mouseInputManager);
		display.getCanvas().addMouseListener(mouseInputManager);
		display.getCanvas().addMouseMotionListener(mouseInputManager);
		//need getCanvas and getFrame so whatever is focused will be able to recognize mouse events 
		Assets.init();

		playerCamera = new PlayerCamera (handler, 0,0);

		handler = new Handler(this);
		
		gameState = new GameState(handler);
		settingsState = new SettingsState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState); //sets the state you start the game with

		//DIFFICULTY LEVEL
		difficulty = Difficulty.EASY;
		difficultyLevel = new DifficultyLevel(handler, difficulty);
		//Marian this does nothing???
	}

	private void update() { //updates variables positions etc.
		keyInputManager.update();

		if(State.getState() != null)
			State.getState().update();
	}

	private void render() { //renders changes to the GUI
		bs = display.getCanvas().getBufferStrategy(); //how many buffers the canvas uses
		
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3); //use 3 buffers
			return;
		}
		
		g = bs.getDrawGraphics(); //creating the paint brush what does that do??
		//clear screen put width and height so it clears everything
		g.clearRect(0, 0, width, height);

		//DRAW HERE
		if(State.getState() != null)
			State.getState().render(g);

		//END DRAWING
		bs.show(); //what does this do??
		g.dispose(); //graphic object needs this
	}

	public void run() { //needed for thread usage
		
		init();
		
		running =true;

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;


		while(running) { //game loop 
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1) {
				update();
				render();
				ticks++;
				delta--;
			}

			if(timer >= 1000000000) { //how many ticks have occurred in one second
				System.out.println("Ticks and Frames:" + ticks); //should we add more print out stuff??
				ticks = 0;
				timer = 0;
			}
		}
	}

		


	


	//GETTERS SETTERS

	public KeyInputManager getKeyInputManager() { //method that return private variable so other methods can access it
		return keyInputManager;
	}

	public void setKeyInputManager(KeyInputManager keyInputManager) {
		this.keyInputManager = keyInputManager;
	}

	public MouseInputManager getMouseInputManager() {
		return mouseInputManager;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public MenuState getMenuState() {
		return menuState;
	}

	public void setMenuState(MenuState menuState) {
		this.menuState = menuState;
	}

	public PlayerCamera getPlayerCamera() {
		return playerCamera;
	}

	public void setPlayerCamera(PlayerCamera playerCamera) {
		this.playerCamera = playerCamera;
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