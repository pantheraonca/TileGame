package tilegame.skills;

import tilegame.Handler;
import tilegame.entities.individuals.Player;

public class Stamina extends Skills {

	private int skillLevel; //variable which will be incresed by increaseSkillmethod and will have influence of 
	private int skillPosition;
	private Handler handler;
	private int energyBonus;

	public Stamina(Handler handler) {
		super(handler);

		this.handler = handler;		
		skillPosition = 1;
		skillLevel = 0;
	}

	@Override 
	public boolean testForSkill() {  // this method can come handy if more methods which influence skilllevel are written a skill 
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
			if (skillLevel %15 == 0) {
				skillImpact();
			}
		}		
	}

	public void skillImpact() {

		energyBonus += 1;
		handler.getField().getEntityManager().getPlayer().setEnergy( handler.getField().getEntityManager().getPlayer().getEnergy() + energyBonus); 

	}
}