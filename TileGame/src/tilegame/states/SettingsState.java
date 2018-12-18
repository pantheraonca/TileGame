package tilegame.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tilegame.Handler;
import tilegame.difficulty.Difficulty;
import tilegame.userinterface.ClickListener;
import tilegame.userinterface.UIButton;
import tilegame.userinterface.UIManager;
import tilegame.graphics.Assets;


public class SettingsState extends State{

	private UIButton imageButtonEasy, imageButtonMedium, imageButtonHard, imageButtonBack, imageButtonFighter, imageButtonStamina;
	private BufferedImage easy, medium, hard, back, fighter, stamina;
	private UIManager settingsUiManager;
	private boolean [] skillArray;
	private final int maxSkills = 1;
	private int numberOfSkills;


	public SettingsState(Handler handler) { 
		super(handler);

		settingsUiManager = new UIManager (handler);
		handler.getMouseInputManager().setUIManager(settingsUiManager); 

		easy = Assets.easy;
		medium = Assets.medium;
		hard = Assets.hard;
		back = Assets.back;
		fighter = Assets.fighter;
		stamina = Assets.stamina;

		skillArray = new boolean [3];
		skillArray [0] = true;   //default skill is fighter at position 1
		skillArray [1] = false;
		skillArray [2] = false;




		imageButtonEasy = 	new UIButton (100, 100, 64, 64, easy, new ClickListener() {

			@Override
			public void onClick () { 

				handler.getDifficultyState().setDifficulty(Difficulty.EASY);
				handler.getDifficultyState().setDifficultyParameters();
			}
		});

		imageButtonMedium = new UIButton (200, 100, 64, 64, medium, new ClickListener(){

			@Override
			public void onClick () { 

				handler.getDifficultyState().setDifficulty(Difficulty.MEDIUM);
				handler.getDifficultyState().setDifficultyParameters();
			}
		});

		imageButtonHard = new UIButton (300, 100, 64, 64, hard, new ClickListener(){

			@Override
			public void onClick () { 

				handler.getDifficultyState().setDifficulty(Difficulty.HARD);
				handler.getDifficultyState().setDifficultyParameters();
			}
		});

		imageButtonBack = new UIButton (300, 500, 64, 64, back, new ClickListener(){

			@Override
			public void onClick () { 

				handler.getMouseInputManager().setUIManager(handler.getGame().menuState.getUiManager());
				State.setState(handler.getGame().menuState);
			}
		});

		imageButtonFighter = new UIButton (100, 200, 64, 64, fighter, new ClickListener(){

			@Override
			public void onClick () { 


				numberOfSkills = 0;

				for (int i=0; i< skillArray.length; i++ ) {
					if (skillArray[i] == true) {
						numberOfSkills += 1;
					}
				}

				if (numberOfSkills < maxSkills || skillArray[0] == false) {

					skillArray[0] = true;
					System.out.println("Skill has been set to \"Fighter\"");

				}
				else {
					skillArray[0] = false;
					System.out.println("The Skill \"Fighter\" has been deselected");
				}
			}
		});

		imageButtonStamina = new UIButton (200, 200, 64, 64, stamina, new ClickListener(){

			@Override
			public void onClick () { 

				numberOfSkills = 0;

				for (int i=0; i< skillArray.length; i++ ) {
					if (skillArray[i] == true) {
						numberOfSkills += 1;
					}
				}

				if (numberOfSkills < maxSkills || skillArray[0] == false) {

					skillArray[1] = true;
					System.out.println("Skill has been set to \"Stamina\"");

				}
				else {
					skillArray[1] = false;
					System.out.println("The Skill \"Stamina\" has been deselected"); //muss noch perfektioniert werden weil jetzte wenn fighter selected ist kann stamina nicht selected werden
				}
			}
		});


		settingsUiManager.addObject(imageButtonEasy);
		settingsUiManager.addObject(imageButtonMedium);
		settingsUiManager.addObject(imageButtonHard);
		settingsUiManager.addObject(imageButtonBack);
		settingsUiManager.addObject(imageButtonFighter);
		settingsUiManager.addObject(imageButtonStamina);

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

	public boolean[] getSkillArray() {
		return skillArray;
	}

	public void setSkillArray(boolean[] skillArray) {
		this.skillArray = skillArray;
	}
}