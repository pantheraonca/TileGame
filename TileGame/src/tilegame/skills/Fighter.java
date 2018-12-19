package tilegame.skills;

import tilegame.Handler;

public class Fighter extends Skills {

	//VARIABLES
	private int skillLevel; //variable which will be increased by increaseSkillmethod and will have influence of 
	private int skillPosition;
	private Handler handler;
	private int attackBonus;

	//CONSTRUCTOR
	public Fighter (Handler handler) {
		super (handler);

		this.handler = handler;		
		skillPosition = 0;
		skillLevel = 0;
	}

	@Override
	public boolean testForSkill() {
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

			if (skillLevel % 2 == 0) {
				skillImpact();
			}
		}
	}

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