package tilegame.skills;

import tilegame.Handler;

public abstract class Skills {

	Skills(Handler handler){

	}

	public abstract boolean testForSkill();

	public abstract void increaseSkill();
}