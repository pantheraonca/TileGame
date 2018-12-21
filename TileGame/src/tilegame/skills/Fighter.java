package tilegame.skills;

import tilegame.Handler;

/* This class extends the skill class. It can be instantiated to add a skill object to the game which will influence the gameplay.
 * It contains variables storing the current skillLevel and methods setting it. It also contains a method to apply the changes 
 * resulting from an updated skillLevel to variables of other classes. Also it contains a boolean which determines if the skill 
 * is active and if the increaseSkill method can be called. */
public class Fighter extends Skills {

	//VARIABLES
	private int skillLevel; 
	//This level reflects the level of skill that your player is currently on. It can only be increased if the skill is selected.
	//Surpassing certain skillLevel thresholds will cause the skillImpact method to be called.
	
	private final int SKILL_POSITION;
	//This variable represents the index of the skill in an boolean array used for turning skills of and on.
	
	private Handler handler;
	
	private int attackBonus;
	// This variable is increased when the skillImpact method is called. Its value will then be added to the damage variable.

	
	//Constructor for the Fighter Class which takes in a handler object
	public Fighter (Handler handler) {
		super (handler);

		this.handler = handler;		
		SKILL_POSITION = 0;
		skillLevel = 0;
	}
	
	
	//Tests if a skill is selected by probing a boolean array at the index specified by the SKILLPOSITION variable 
	//is true or false and returns its value.
	@Override
	public boolean testForSkill() {
		if (handler.getSkillArray() [SKILL_POSITION] == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	//If testForSkill returns true increases the skillLevel by one every time its called. In this case its called every time the
	// die method of an enemy object is called. When the threshhold of skillLevel = multiple of 2 is 
	//reached, the skillimpact method is called.
	@Override
	public void increaseSkill() {

		if (testForSkill()) {
			skillLevel += 1;

			if (skillLevel % 2 == 0) {
				skillImpact();
			}
		}
	}
	
	
	//The skillImpact method in this case increases the attackbonus variable by one and adds its value to the damage variable.
	// This leads to more damage to enemies and boss and makes them be killed easier.
	public void skillImpact() {

		attackBonus += 1;
		handler.setDamage(handler.getDamage() + 1);
		System.out.println("Damage:" + handler.getDamage());
	}
	
	
	//GETTERS SETTERS

	public int getAttackBonus() { //die sind hier weil sonst throws error oben das attack bonus nicht benutzt wird 
		return attackBonus;
	}

	public void setAttackBonus(int attackBonus) {
		this.attackBonus = attackBonus;
	}
}