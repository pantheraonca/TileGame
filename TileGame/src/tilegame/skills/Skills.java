package tilegame.skills;

import tilegame.Handler;

/* This class is abstract and will detemines which methods SkillSubclasses must implement. It cant be instantiated. 
 * To add a skill object to the game which will influence the gameplay a subclass must be created.
 *asses. Also it contains a boolean which determines if the skill 
 * is active and if the increaseSkill method can be called. */
public abstract class Skills {

	Skills(Handler handler){
	}
	
	//This method test if a skill is active or selected
	public abstract boolean testForSkill();
	
	
	//This method increases the skill
	public abstract void increaseSkill();
}