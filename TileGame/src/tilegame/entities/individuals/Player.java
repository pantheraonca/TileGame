package tilegame.entities.individuals;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.graphics.Assets;
import tilegame.skills.Fighter;
import tilegame.skills.Skills;
import tilegame.skills.Stamina;
import tilegame.states.State;

public class Player extends Individual { //somehow becasue this isnt an abstract class we need the tick and render method this was not needed in the player class which extends the entity class which has a tick render method 


	//Attack timer
	private long lastAttackTimer, attackCooldown = 200, attackTimer = attackCooldown;
	private long lastMoveTimer, moveCooldown = 200, moveTimer = moveCooldown;
	
	//Skills
	private Skills stamina, fighter;
	private int damage;
	
	//make a variable playerpower  that gets stronger per killed enemy feed it in instead of amt in hurtmethod

	public Player(Handler handler, int x, int y) {
		super(handler, x, y, Individual.DEFAULT_INDIVIDUAL_WIDTH, Individual.DEFAULT_INDIVIDUAL_HEIGHT);
		
		stamina = new Stamina (handler);
		fighter = new Fighter (handler);
		damage = 1; //damage will be increased by fighter skill when skillImpact method is called
	}

	@Override
	public void update() {
		//Movement
		getInput();
		move();

		//Attack
		checkAttacks();

		//PlayerCamera
		handler.getPlayerCamera().centerOn(this);
	}

	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown) //if attack timer hasnt been running for 800 milliseconds he has to wait until he can attack again
			return; //returns and does not run any of the code bellow

		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle(); //attack rectangle
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;

		if(handler.getKeyInputManager().aUp) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}
		else if(handler.getKeyInputManager().aDown) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}
		else if(handler.getKeyInputManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else if(handler.getKeyInputManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else {
			return; //if none of those are pressed then just return from this method without doing anything
		}

		attackTimer = 0; //player has attacked now and should wait another 800 milliseconds

		for(Entity e : handler.getField().getEntityManager().getEntities()) { //for every entity e in our arraylist(the part after the doppelpunkt
			if(e.equals(this))
				continue; //means if the entity is ourself just continue to the next entity in the arraylist b/c we dont want to hurt ourselves

			if(e.getCollisionBounds(0, 0).intersects(ar)) { //if we are in attack range basically??
				e.hurt(damage); // amount that is substracted from the entities energy
				handler.getPlayer().hurt(1); //if in fight with entity then player gets also hurt
				return;
			}
		}
	}

	@Override
	public void die() {
		JOptionPane.showMessageDialog(null, "You died!", null, 1); //popup when you die
		handler.getMouseInputManager().setUIManager(handler.getUIManager()); //setting this back to the UiManager is important to the buttons can be pressed again 
		State.setState(handler.getGame().menuState); //goes back to menu state but does not reset menustate so start button is pressed
		State.CheckScore(); //checks if new highscore has to be set
		State.setYourScore(0);//aber erst wenn mit highscore verglichen ist passt aber 
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		moveTimer += System.currentTimeMillis() - lastMoveTimer;
		lastMoveTimer = System.currentTimeMillis();
		if(moveTimer < moveCooldown) //if move timer hasn't been running for 800 milliseconds he has to wait until he can move again
			return; //returns and does not run any of the code bellow

		if(handler.getKeyInputManager().up)
			yMove =- speed;
		else if(handler.getKeyInputManager().down)
			yMove =+ speed;
		else if(handler.getKeyInputManager().left)
			xMove =- speed;
		else if(handler.getKeyInputManager().right)
			xMove =+ speed;
		else {
			return; //if none of those are pressed then just return from this method without doing anything
		}

		handler.getField().getEntityManager().getPlayer().hurt(1); //sucks energy when player is moving
		stamina.increaseSkill(); // every input stamina skill gets increased by 1
		moveTimer = 0; //player has moved now and should wait another 800 milliseconds
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player,  x, y, width, height, null);
		//width and height rescales the player image
	}

	public Skills getStamina() {
		return stamina;
	}

	public void setStamina(Skills stamina) {
		this.stamina = stamina;
	}

	public Skills getFighter() {
		return fighter;
	}

	public void setFighter(Skills fighter) {
		this.fighter = fighter;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}

