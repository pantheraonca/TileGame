package tilegame.skills;

import tilegame.Handler;


/* This class extends the skill class. It can be instantiated to add a skill object to the game which will influence the gameplay.
 * It contains variables storing the current skillLevel and methods setting it. It also contains a method to apply the changes 
 * resulting from an updated skillLevel to variables of other classes. Also it contains a boolean which determines if the skill 
 * is active and if the increaseSkill method can be called. */
public class Stamina extends Skills {

	//VARIABLES
	private int skillLevel; 
	//This level reflects the level of skill that your player is currently on. It can only be increased if the skill is selected.
	//Surpassing certain skillLevel thresholds will cause the skillImpact method to be called.
	
	private int skillPosition;
	//This variable represents the index of the skill in an boolean array used for turning skills of and on.
	
	private Handler handler;
	
	private int energyBonus;
	// This variable is increased when the skillImpact method is called. It will then be added to the energy variable.

	
	//Constructor for the Stamina Class which takes in a handler object
	public Stamina(Handler handler) {
		super(handler);

		this.handler = handler;		
		skillPosition = 1;
		skillLevel = 0;
	}
	
	
	//Tests if a skill is selected by probing a boolean array at the index specified by the SKILLPOSITION variable 
	//is true or false and returns its value
	@Override 
	public boolean testForSkill() {  // this method can come in handy if more methods which influence skill-level are written a skill 
		if (handler.getSkillArray() [skillPosition] == true) {
			return true;
		}
		else { 
			return false;
		}
	}
	
	
	//If testForSkill returns true increases the skillLevel by one every time its called. In this case its called every time the
	// move method is carried out until the end by the player object. When the threshhold of skillLevel = multiple of 15 is 
	//reached, the skillImpact method is called.
	@Override
	public void increaseSkill() {

		if (testForSkill()) {
			skillLevel += 1;
			if (skillLevel % 15 == 0) {
				skillImpact();
			}
		}		
	}
	
	
	//The skillImpact method in this case increases the energyBonus variable by one and adds its value to the energy variable in the player object.
	// This leads to more energy and therefore more actions the player can carry out before dying.
	public void skillImpact() {

		energyBonus += 1;
		handler.getField().getEntityManager().getPlayer().setEnergy( handler.getField().getEntityManager().getPlayer().getEnergy() + energyBonus); 
	}
}