package tilegame.difficulty;

public enum Difficulty {

	HARD (2),
	MEDIUM (1),
	EASY (0);

	private int difficulty;

	private Difficulty (int difficulty) {
		this.difficulty = difficulty;
	}

	//GETTERS SETTERS
	
	public int getDifficulty () {
		return this.difficulty;
	}
}