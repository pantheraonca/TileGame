package tilegame.states;

import java.awt.Graphics;
import tilegame.Handler;
import tilegame.graphics.Assets;
import tilegame.userinterface.ClickListener;
import tilegame.userinterface.UIButton;
import tilegame.userinterface.UIImageButton;
import tilegame.userinterface.UIManager;


//this class contains the objects that are needed in the menuState of the game.
public class MenuState extends State {

	//VARIABLES
	private UIManager uiManager;
	//the UIManager object holds different buttons that need to be in the menuState
	

	//CONSTRUCTOR initiates an UiManager object, adds buttons to the uiManager and holds the functionality of these buttons
	public MenuState(Handler handler) { 
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseInputManager().setUIManager(uiManager); //sets the uiManager so the mouse input works.

		uiManager.addObject(new UIImageButton(200, 200, 64, 64, Assets.btn_start, new ClickListener() {
			//adds an start button in this case an oven
			@Override
			public void onClick() {
				handler.getMouseInputManager().setUIManager(null);  //this way when states are switched you cannot give mouse input anymore
																	//otherwise you would still be able to press the buttons of the menustate 
																	//in the gamestate.
				
				State.setState(handler.getGame().gameState); 		//when start button is clicked sets the State to gameState.
			}
		}));

		uiManager.addObject(new UIButton(400, 200, 64, 64, Assets.settings, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseInputManager().setUIManager(handler.getSettingsUiManager());
				State.setState(handler.getGame().settingsState); 	//sets the state to setting state if the settings button is pressed.
			}
		}));

	}
	
	
	//updates the uiManager
	@Override
	public void update() {
		uiManager.update();
	}

	
	@Override //renders the uiManager this the buttons contained in it also renders different strings that hold the instructions to the game
	public void render(Graphics g) {
		uiManager.render(g);

		if(State.getHighScore().equals("")) {
			State.setHighScore(this.ReadHighScore());
		}
		g.drawString("Highscore: " + State.getHighScore(), 10, 15);
		g.drawString("Start", 200, 280);
		g.drawString("Settings", 400, 280);
		g.drawString("Instructions", 70, 350);
		g.drawString("use W A S D to move", 70, 400);
		g.drawString("use the arrow keys to attack in the direction the enemy stands", 70, 415);
		g.drawString("move over boosters to gain energy", 70, 430);
		g.drawString("avoid mud, you will loose energy ", 70, 445);
		g.drawString("don't move over lava", 70, 460);
		g.drawString("the field is surrounded by lava", 70, 475);
		g.drawString("to win fight the boss", 70, 490);
	
	}

	//GETTERS SETTERS (function of getters and setters described in handler class)
	public UIManager getUiManager() {
		return uiManager;
	}
	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
}

