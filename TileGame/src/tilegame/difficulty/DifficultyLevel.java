package tilegame.difficulty;

import tilegame.Handler;
import tilegame.difficulty.Difficulty;

public class DifficultyLevel {

	//VARIABLES
	private Handler handler;
	private Difficulty difficulty;

	//CONSTRUCTOR
	public DifficultyLevel(Handler handler, Difficulty difficulty) {

		this.handler = handler; 
		this.difficulty = difficulty;
	}

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
			handler.setVisibilityLevel(5); //Marian what does this default do ??
			break;
		}
	}

	//GETTERS SETTERS

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
}