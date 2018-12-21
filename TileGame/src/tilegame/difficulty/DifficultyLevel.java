package tilegame.difficulty;

import tilegame.Handler;
import tilegame.difficulty.Difficulty;

/* a difficultyLevel object takes in one of the enum cases of difficulty 
 * and sets certain difficulty parameters according to that value. */
public class DifficultyLevel {

	
	//VARIABLES
	private Handler handler;
	private Difficulty difficulty;

	//CONSTRUCTOR
	public DifficultyLevel(Handler handler, Difficulty difficulty) {

		this.handler = handler; 
		this.difficulty = difficulty;
	}

	
	/* takes in the difficulty and with a switch statement sets the visibilityLevel of the shadow object 
	 * to the the according value other parameters could be added to the switch statement. */
	public void setDifficultyParameters(){
		switch (difficulty) {
		case EASY:
			handler.setVisibilityLevel(5);
			break;
		case MEDIUM:
			handler.setVisibilityLevel(4);
			break;
		case HARD:
			handler.setVisibilityLevel(2);
			break;
		default:
			handler.setVisibilityLevel(5);
			break;
		}
	}

	
	// GETTERS SETTERS (function of getters and setters described in handler class)
	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
}