package tilegame.skills;

import tilegame.Handler;

public class Stamina extends Skills {

	//VARIABLES
	private int skillLevel; //variable which will be increased by increaseSkillmethod and will have influence of ???
	private int skillPosition;
	private Handler handler;
	private int energyBonus;

	//CONSTRUCTOR
	public Stamina(Handler handler) {
		super(handler);

		this.handler = handler;		
		skillPosition = 1;
		skillLevel = 0;
	}

	@Override 
	public boolean testForSkill() {  // this method can come in handy if more methods which influence skill-level are written a skill 
		if (handler.getSkillArray() [skillPosition] == true) {
			return true;
		}
		else { 
			return false;
		}
	}

	@Override
	public void increaseSkill() {

		if (testForSkill()) {
			skillLevel += 1;
			if (skillLevel % 15 == 0) {
				skillImpact();
			}
		}		
	}

	public void skillImpact() {

		energyBonus += 1;
		handler.getField().getEntityManager().getPlayer().setEnergy( handler.getField().getEntityManager().getPlayer().getEnergy() + energyBonus); 

	}
}