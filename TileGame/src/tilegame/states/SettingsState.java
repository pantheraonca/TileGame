package tilegame.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import tilegame.Handler;
import tilegame.difficulty.Difficulty;
import tilegame.userinterface.ClickListener;
import tilegame.userinterface.UIButton;
import tilegame.userinterface.UIManager;
import tilegame.graphics.Assets;


public class SettingsState extends State{

	//VARIABLES
	private UIButton imageButtonEasy, imageButtonMedium, imageButtonHard, imageButtonBack, imageButtonFighter, imageButtonStamina;
	private BufferedImage easy, medium, hard, back, fighter, stamina;
	private UIManager settingsUiManager;
	private boolean [] skillArray;
	private final int maxSkills = 1;
	private int numberOfSkills;

	//CONSTRUCTOR
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
		skillArray [0] = true;   //default skill is fighter at position 1 change
		skillArray [1] = false;
		skillArray [2] = false;

		imageButtonEasy = 	new UIButton (100, 100, 64, 64, easy, new ClickListener() {

			@Override
			public void onClick () { 
				JOptionPane.showMessageDialog(null, "Difficulty has been set to Easy", null, 1);
				handler.getDifficultyState().setDifficulty(Difficulty.EASY);
				handler.getDifficultyState().setDifficultyParameters();
				
			}
		});

		imageButtonMedium = new UIButton (200, 100, 64, 64, medium, new ClickListener(){

			@Override
			public void onClick () { 
				JOptionPane.showMessageDialog(null, "Difficulty has been set to Medium", null, 1);
				handler.getDifficultyState().setDifficulty(Difficulty.MEDIUM);
				handler.getDifficultyState().setDifficultyParameters();
			}
		});

		imageButtonHard = new UIButton (300, 100, 64, 64, hard, new ClickListener(){

			@Override
			public void onClick () { 
				JOptionPane.showMessageDialog(null, "Difficulty has been set to Hard", null, 1);
				handler.getDifficultyState().setDifficulty(Difficulty.HARD);
				handler.getDifficultyState().setDifficultyParameters();
			}
		});

		imageButtonBack = new UIButton (430, 170, 64, 64, back, new ClickListener(){

			@Override
			public void onClick () { 

				handler.getMouseInputManager().setUIManager(handler.getGame().menuState.getUiManager());
				State.setState(handler.getGame().menuState);
			}
		});

		for (int i=0; i< skillArray.length; i++ ) {     // this for loop counts the current number of skills selected 
			if (skillArray[i] == true) {				// it iterates through the skillArray and adds 1 to number ofSkills for every true element
				numberOfSkills += 1;
			}
		}

		imageButtonFighter = new UIButton (100, 260, 64, 64, fighter, new ClickListener(){
			
			@Override
			public void onClick () { 
				
				if (skillArray[0] == false) {   		// this if statement checks if the position of Fighter in skillArray is set to false
					numberOfSkills += 1;				//if it was it increases the amount of selected skills by 1 because it will next set the skill to true	
				}
				skillArray[0] = true;					// here it sets skillArray at position of Skill to true and then prints that it has done so	
				JOptionPane.showMessageDialog(null, "Skill has been set to Fighter", null, 1);
				if (numberOfSkills > maxSkills) {					//If the number of skills selected is too high then for loop will be carried out to unset akills
					
					for (int i=0; i < skillArray.length; i++) {		//This for loop iterates through skillArray and sets first position in array which is not the current skills position and true to false. Then it breaks the for loop because numberOfSkills is now equal to maxSkills. 	
						
						if (i != 0 && numberOfSkills > maxSkills && skillArray[i]== true) {
						
							skillArray[i] =false;
							numberOfSkills -= 1;
							break;
					
						}

					}
				}
			}
		});

		imageButtonStamina = new UIButton (200, 260, 64, 64, stamina, new ClickListener(){

			@Override
			public void onClick () { 

				if (skillArray[1] == false) {   		// this if statement checks if the position of Fighter in skillArray is set to false
					numberOfSkills += 1;				//if it was it increases the amount of selected skills by 1 because it will next set the skill to true	
				}
				skillArray[1] = true;					// here it sets skillArray at position of Skill to true and then prints that it has done so
				JOptionPane.showMessageDialog(null, "Skill has been set to Stamina", null, 1);;	
				
				if (numberOfSkills > maxSkills) {					//If the number of skills selected is too high then for loop will be carried out to unset akills
					
					for (int i=0; i < skillArray.length; i++) {		//This for loop iterates through skillArray and sets first position in array which is not the current skills position and true to false. Then it breaks the for loop because numberOfSkills is now equal to maxSkills. 	
						
						if (i != 1 && numberOfSkills > maxSkills && skillArray[i]== true) {
						
							skillArray[i] =false;
							numberOfSkills -= 1;
							break;
					
						}
					}
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
		g.drawString("Easy", 100, 180);
		g.drawString("Medium", 200, 180);
		g.drawString("Hard", 300, 180);
		g.drawString("Fighter", 100, 340);
		g.drawString("Stamina", 200, 340);
		g.drawString("Back to Menu", 430, 250);
		g.drawString("Choose Skill", 150, 240);
		g.drawString("Choose Difficulty", 200, 80);
	}
	
	//GETTERS SETTERS

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