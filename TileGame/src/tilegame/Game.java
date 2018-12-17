package tilegame;


import java.awt.Graphics;
import java.awt.image.BufferStrategy;
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

	private Display display;
	private int width, height; //so this class also has access to the width and height easily
	public String title;

	private boolean running = false;
	private Thread thread; //this is a thread object
	
	
	private BufferStrategy bs; //tells the computer how to draw things to the screen
	private Graphics g;

	//States
	public State gameState; //can access easily when public might not be best practice
	public MenuState menuState;
	public State settingsState;

	//Input
	private KeyInputManager keyInputManager;
	private MouseInputManager mouseInputManager;

	//Handler
	private Handler handler;
	
	//PlayerCamera
	
	private PlayerCamera playerCamera;

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
		
		//PlayerCamera
		
		playerCamera = new PlayerCamera (handler, 0,0);

		handler = new Handler(this);

		gameState = new GameState(handler); //what object do we pass it? we are in the game class so we use this 
		menuState = new MenuState(handler);
		settingsState = new SettingsState(handler);
		State.setState(menuState); //put back to menu state to start at menu state	
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
		g = bs.getDrawGraphics(); //creating the paintbrush whut??
		//clear screen put width and height so it clears everything
		g.clearRect(0, 0, width, height);
		//draw here

		if(State.getState() != null)
			State.getState().render(g);

		//end drawing
		bs.show(); //what does this do
		g.dispose(); //graphic object needs this
		
		//if (highscore == -1) {
		//	//init highscore
		//}
			
	}

	public void run() { //needed for thread usage

		init();

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

			if(timer >= 1000000000) { //how many ticks have occured in one second
				System.out.println("Ticks and Frames:" + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop(); //when running equals false while loop will exit and stop method is called
	}


	public synchronized void start() { //initializes thread and "this" so it runs this class
		if(running)
			return; //this stops java from running the lines bellow so that if the game is running already and this method gets called somehow it does not give a mess
		running = true;
		thread = new Thread(this);
		thread.start(); //will call run method
	}

	public synchronized void stop() { //closes down thread properly
		if(!running)
			return; //same safety as in the start method
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace(); //wtf is this try catch statement
		}
	}

	public KeyInputManager getKeyInputManager() { //method that return private variable so other methods can access it
		return keyInputManager;
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
	
	
	
	
}