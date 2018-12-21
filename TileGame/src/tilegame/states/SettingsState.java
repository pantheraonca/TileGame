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


//this class contains the objects that are needed in the settingsState of the game.
public class SettingsState extends State{

	//VARIABLES
	private UIButton imageButtonEasy, imageButtonMedium, imageButtonHard, imageButtonBack, imageButtonFighter, imageButtonStamina;
	// these variables store ButtonObjects and are used to override their onClick are overwritten to specify what happens upon a 
	// click on that button 
	
	private BufferedImage easy, medium, hard, back, fighter, stamina;
	//these variables store buffered images which will be given to the button objects constructed and will set their design
	
	private UIManager settingsUiManager;
	//in the settingsUiManager variable a newly created UImanager is stored that manages the events happening upon mouseinputevents 
	
	private boolean [] skillArray;
	//this array will be set true or false at certain indexes corresponding to which skill has been selected, so which button has been pressed
	
	private final int maxSkills = 1;
	// this integer variable stores the maximum amount of skills which can be selected, so how many positions in the skillArray can be true
	
	private int numberOfSkills;
	//this integer variable stores the amount of number of skills which are currently selected

	
	//The SettinsState constructor only takes in a handler object to pass along variables
	//In the constructor a new uiManager object is created, and passed to the mouseInputmanager.
	//The BufferedImages for the buttons are obtained from the Assets class and stored in variables
	//A new skillArray is created and the first position set to true the rest to zero, so the default skill is fighter.
	//New buttons are created and added to the UImanager object of the SettingsState.
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
		skillArray [0] = true;   
		skillArray [1] = false;
		skillArray [2] = false;

		imageButtonEasy = 	new UIButton (100, 100, 64, 64, easy, new ClickListener() {
			
			//On clicking the button the difficultyStates Difficulty is set to easy,
			//and then the difficultyStates setDifficultyParameters method is called so now the visibility variable
			//in the shadow object has been reset
			@Override
			public void onClick () { 
				JOptionPane.showMessageDialog(null, "Difficulty has been set to Easy", null, 1);
				handler.getDifficultyState().setDifficulty(Difficulty.EASY);
				handler.getDifficultyState().setDifficultyParameters();	
			}
		});

		imageButtonMedium = new UIButton (200, 100, 64, 64, medium, new ClickListener(){

			//On clicking the button the difficultyStates Difficulty is set to medium,
			//and then the difficultyStates setDifficultyParameters method is called so now the visibility variable
			//in the shadow object has been reset
			@Override
			public void onClick () { 
				JOptionPane.showMessageDialog(null, "Difficulty has been set to Medium", null, 1);
				handler.getDifficultyState().setDifficulty(Difficulty.MEDIUM);
				handler.getDifficultyState().setDifficultyParameters();
			}
		});

		imageButtonHard = new UIButton (300, 100, 64, 64, hard, new ClickListener(){
			
			//On clicking the button the difficultyStates Difficulty is set to easy,
			//and then the difficultyStates setDifficultyParameters method is called so now the visibility variable
			//in the shadow object has been reset
			@Override
			public void onClick () { 
				JOptionPane.showMessageDialog(null, "Difficulty has been set to Hard", null, 1);
				handler.getDifficultyState().setDifficulty(Difficulty.HARD);
				handler.getDifficultyState().setDifficultyParameters();
			}
		});

		imageButtonBack = new UIButton (430, 170, 64, 64, back, new ClickListener(){
			
			//On clicking the button the menuStates UImanager is passed along to the MouseInputmanger, to allow for clicking 
			//only the buttons of the menuState which the game objects variable state is next set to
			//the player will now find itself back in the menustate
			@Override
			public void onClick () { 

				handler.getMouseInputManager().setUIManager(handler.getGame().menuState.getUiManager());
				State.setState(handler.getGame().menuState);
			}
		});

		//sets the number of Skills to the number of Skills currently selected(by default)
		for (int i=0; i< skillArray.length; i++ ) {     
			if (skillArray[i] == true) {				
				numberOfSkills += 1;
			}
		}

		
		imageButtonFighter = new UIButton (100, 260, 64, 64, fighter, new ClickListener(){
			
			/*Upon clicking the button the numberOfskills currently set is increased by one if it hadnt been selected before
			 * then the skillArray at the respective position is set to true and a message is displayed in a popup that the skill has been selected
			 * Next, if the number o selected skill exceeds the MAX_SKILLS value, a for loop is run that reduces the number of selected skills by 
			 * setting the first true value in the skillArray (except itself) to false */
			@Override
			public void onClick () { 
				
				if (skillArray[0] == false) {   		
					numberOfSkills += 1;					
				}
				skillArray[0] = true;						
				JOptionPane.showMessageDialog(null, "Skill has been set to Fighter", null, 1);
				if (numberOfSkills > maxSkills) {					
					
					for (int i=0; i < skillArray.length; i++) {		
						
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

			/*Upon clicking the button the numberOfskills currently set is increased by one if it hadnt been selected before
			 * then the skillArray at the respective position is set to true and a message is displayed in a popup that the skill has been selected
			 * Next, if the number o selected skill exceeds the MAX_SKILLS value, a for loop is run that reduces the number of selected skills by 
			 * setting the first true value in the skillArray (except itself) to false */
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
	
	
	// This method updates calls the update method of the Uimanager
	@Override
	public void update() {
		settingsUiManager.update();
	}

	
	//this method calls the render method of the settingsUiManager and then draws string and values over each button 
	//indicating its function
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