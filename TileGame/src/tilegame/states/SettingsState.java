package tilegame.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import tilegame.Handler;
import tilegame.difficulty.Difficulty;
import tilegame.difficulty.DifficultyState;
import tilegame.userinterface.ClickListener;
import tilegame.userinterface.UIButton;
import tilegame.userinterface.UIImageButton;
import tilegame.userinterface.UIManager;
import tilegame.graphics.Assets;
import java.awt.Graphics;

public class SettingsState extends State{

	private UIButton imageButtonEasy, imageButtonMedium, imageButtonHard, imageButtonBack;
	private BufferedImage easy, medium, hard, back;
	private UIManager settingsUiManager;

	//Difficulty
	private Difficulty difficulty;


	public SettingsState(Handler handler) { 
		super(handler);

		settingsUiManager = new UIManager (handler);
		handler.getMouseInputManager().setUIManager(settingsUiManager); 

		easy = Assets.easy;
		medium = Assets.medium;
		hard = Assets.hard;
		back = Assets.back;



		imageButtonEasy = 	new UIButton (100, 100, 32, 32, easy, new ClickListener() {

			@Override
			public void onClick () { 

				handler.getDifficultyState().setDifficulty(Difficulty.EASY);
				handler.getDifficultyState().setDifficultyParameters();
			}
		});

		imageButtonMedium = new UIButton (200, 100, 32, 32, medium, new ClickListener(){

			@Override
			public void onClick () { 

				handler.getDifficultyState().setDifficulty(Difficulty.MEDIUM);
				handler.getDifficultyState().setDifficultyParameters();
			}
		});

		imageButtonHard = new UIButton (300, 100, 32, 32, hard, new ClickListener(){

			@Override
			public void onClick () { 

				handler.getDifficultyState().setDifficulty(Difficulty.HARD);
				handler.getDifficultyState().setDifficultyParameters();
			}
		});

		imageButtonBack = new UIButton (300, 500, 32, 32, back, new ClickListener(){

			@Override
			public void onClick () { 

				handler.getMouseInputManager().setUIManager(handler.getGame().menuState.getUiManager());
				State.setState(handler.getGame().menuState);
			}
		});

		settingsUiManager.addObject(imageButtonEasy);
		settingsUiManager.addObject(imageButtonMedium);
		settingsUiManager.addObject(imageButtonHard);
		settingsUiManager.addObject(imageButtonBack);

	}

	@Override
	public void update() {
		settingsUiManager.update();

	}

	@Override
	public void render(Graphics g) {
		settingsUiManager.render(g);
	}

	public UIManager getSettingsUiManager() {
		return settingsUiManager;
	}

	public void setSettingsUiManager(UIManager settingsUiManager) {
		this.settingsUiManager = settingsUiManager;
	}


}