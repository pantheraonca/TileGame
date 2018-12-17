package tilegame.difficulty;

import tilegame.Handler;
import tilegame.difficulty.Difficulty;

public class DifficultyState {
	
	private Handler handler;
	private Difficulty difficulty;
	
	
	public DifficultyState(Handler handler, Difficulty difficulty) {
		
		this.handler = handler; 
		this.difficulty = difficulty;
	
	
	}
	
	public void setDifficultyParameters(){
		switch (difficulty) {
			case EASY:
				handler.setVisibilityLevel(3);
				break;
			case MEDIUM:
				handler.setVisibilityLevel(2);
				break;
			case HARD:
				handler.setVisibilityLevel(1);
				break;
			default:
				handler.setVisibilityLevel(3);
				break;
		}
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	
}