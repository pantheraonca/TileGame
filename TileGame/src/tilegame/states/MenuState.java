package tilegame.states;

import java.awt.Graphics;
import tilegame.Handler;
import tilegame.graphics.Assets;
import tilegame.userinterface.ClickListener;
import tilegame.userinterface.UIButton;
import tilegame.userinterface.UIImageButton;
import tilegame.userinterface.UIManager;

public class MenuState extends State {

	//VARIABLES
	private UIManager uiManager;

	//CONSTRUCTOR
	public MenuState(Handler handler) { 
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseInputManager().setUIManager(uiManager);

		uiManager.addObject(new UIImageButton(200, 200, 64, 64, Assets.btn_start, new ClickListener() {
			//adds an start button in my case an oven
			@Override
			public void onClick() {
				handler.getMouseInputManager().setUIManager(null); //this way when states are switched you cannot give mouse input anymore otherwise you would still be able to press the buttons in the background
				State.setState(handler.getGame().gameState); //when clicked then switches from menu to game	
			}
		}));

		uiManager.addObject(new UIButton(400, 200, 64, 64, Assets.settings, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseInputManager().setUIManager(handler.getSettingsUiManager());
				State.setState(handler.getGame().settingsState);
			}
		}));

	}
	@Override
	public void update() {
		uiManager.update();
	}

	@Override
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
		g.drawString("use the arrow keys to attack", 70, 415);
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

