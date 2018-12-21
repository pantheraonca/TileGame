package tilegame.difficulty;

// enumeration containing the difficulties that can be set.
public enum Difficulty {

	HARD (2),
	MEDIUM (1),
	EASY (0);

	private int difficulty;


	// CONSTRUCTOR which is private so that only the specified amount of instances can be created.
	private Difficulty (int difficulty) {
		this.difficulty = difficulty;
	}


	// GETTERS SETTERS (function of getters and setters described in handler class)
	public int getDifficulty () {
		return this.difficulty;
	}
}