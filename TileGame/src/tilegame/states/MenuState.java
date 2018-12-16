package tilegame.states;

import java.awt.Graphics;
import tilegame.Handler;
import tilegame.graphics.Assets;
import tilegame.userinterface.ClickListener;
import tilegame.userinterface.UIImageButton;
import tilegame.userinterface.UIManager;

public class MenuState extends State {

	private UIManager uiManager;
	
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
		g.drawString("Highscore: " + State.getHighScore(), 32, 32);
	}
	
	public UIManager getUiManager() {
		return uiManager;
	}
	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
}

