package tilegame.entities.individuals;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JOptionPane;

import tilegame.Game;
import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.graphics.Assets;
import tilegame.skills.Fighter;
import tilegame.skills.Skills;
import tilegame.skills.Stamina;
import tilegame.states.State;


//
public class Player extends Individual {

	// VARIABLES
	private long lastAttackTimer;
	// specifies the time (in milliseconds) that has passed since the last time the player has attacked.
	
	private long attackCooldown = 200;
	// specifies the time (in milliseconds) that the player has to wait until he can attack again.
	
	private long attackTimer = attackCooldown;
	// reference for the attackCooldown (in milliseconds).
	
	private long lastMoveTimer;
	// specifies the time (in milliseconds) that has passed since the last time the player has moved.
	
	private long moveCooldown = 200;
	// specifies the time (in milliseconds) that the player has to wait until he can move again.
	
	private long moveTimer = moveCooldown;
	// reference for the moveCooldown (in milliseconds).
	
	private Skills stamina, fighter;
	// the skills that the player can have.
	
	private int playerDamage;
	// the damage the player invokes on an entity if the player attacks this entity.

	
	/* super CONSTRUCTOR for the player the initial x and y position of each entity are 
	 * passed to this super constructor by the constructor of each extending class. 
	 * The width and height are passed in by the Individual class. */
	public Player(Handler handler, int x, int y) {
		super(handler, x, y, Individual.DEFAULT_INDIVIDUAL_WIDTH, Individual.DEFAULT_INDIVIDUAL_HEIGHT);
		
		stamina = new Stamina (handler);
		fighter = new Fighter (handler);
		playerDamage = 1; 
		//the Damage a player has at the start of the game.
	}
	
	/* The update method is called by the update method of the entityManager.
	 * it calls the update, checkMovement, move and checkAttacks method of the player.
	 * It also calls the playerCameras centerOn method to ensure the Camera is constantly 
	 * centered on the player*/
	@Override
	public void update() {
	
	//movement
	checkMovement();
	move();

	//attack
	checkAttacks();

	//this method tells the playerCamera that this is the entity it should center itself on
	handler.getPlayerCamera().centerOn(this);
	}
	
	/* The render method of the enemy is called by the render method of the EntityManager
	 * The render method of the enemy calls the drawImage method of the graphics object 
	 * and passes it the corresponding image from the assets class, as well as the coordinates
	 * at which the enemy has to be drawn on the canvas object.*/
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player,  xOfEntity, yOfEntity, entityWidth, entityHeight, null);
		//width and height re-scale the player image
	}
	
	
	/* the die method first takes the remaining energy of the player, divides it by two and 
	 * adds this amount to the current score. it then pops up a message in the game that informs
	 * the user that the player has died and what the current score is. It then calls the CheckScore method
	 * to see if a new highscore has been set. It sets the current score back to zero, 
	 * closes the current window, sets the Game to a new Game so everything is set back to when the 
	 * game first started and calls the run method so a new window opens. */
	@Override
	public void die() {
		State.setYourScore(State.getYourScore() + (handler.getPlayer().getEnergy()) / 2); // adds half the remaining energy to your score
		JOptionPane.showMessageDialog(null, "You died! Your Score is " + State.getYourScore(), null, 1);
		State.CheckScore(); //checks if new high-score has to be set
		State.setYourScore(0);
		handler.getGame().getDisplay().getFrame().dispose();
		handler.setGame(new Game("BE SQUARE OR DON'T BE THERE", 640, 640));
		handler.getGame().run();
	}
		
	
	/*  
	 * The checkAttacks method ensures that the player must wait a certain time (200ms) between two subsequent attacking actions 
	 * and it checks if an entity that can be attacked is in range. If so it will substract energy from the entity.
	 * If the attackTimer variable is smaller than the attackCooldown variable not enough time has passed and the method
	 * will return. If the attackTimer is larger, then the method proceeds and at the end will set the attack timer to 0.
	 * If the attackTimer was larger than the checking if the player is in attacking range by creating a new rectangle with
	 * the getColllisionbounds method of the Entity Class. It then checks if the field next to it, in the direction of which
	 * the attack key has been pressed is populated by an entity. It does so by first creating a second rectangle shifted in 
	 * position depending on the key that has been pressed. When this rectangle overlaps with the position of another entity 
	 * it will call the hurt method of that entity and on the player.*/
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown) //if attack timer hasn't been running for 200 milliseconds he has to wait until he can attack again
			return; //returns and does not run any of the code bellow

		Rectangle cb = getCollisionBounds(0, 0);	// cb stands for collisionbounds
		Rectangle ar = new Rectangle(); 			// ar stands for attack rectangle
		int arSize = 20;							// sets the dimensions of the
		ar.width = arSize;							// attack rectangle 
		ar.height = arSize;							// to 20 by 20 pixels

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
			return; 
		}

		attackTimer = 0; 

		for(Entity e : handler.getField().getEntityManager().getEntities()) { 
			if(e.equals(this))
				continue; 
			if(e.getCollisionBounds(0, 0).intersects(ar)) { 
				e.hurt(playerDamage); 
				handler.getPlayer().hurt(1); 
				return;
			}
		}
	}
	
	/* The checkMovement method ensures that the player must wait a certain time between two subsequent steps in any direction.
	 * it first sets the xMove and yMove variables to zero. similarly to the checkAttacks method it counts the time since the last
	 * time it has been carried out in full. If enough time has passed and the if statement is fulfilled, the xMove or yMove variable 
	 * will be increased or decreased depending on which key was pressed. Then one is substracted from the energy amount of the player,
	 * the the increaseSkill method of the Stamina object is called and the moveTimer is reset to 0. */
		private void checkMovement() { 
		xMove = 0;
		yMove = 0;

		moveTimer += System.currentTimeMillis() - lastMoveTimer;
		lastMoveTimer = System.currentTimeMillis();
		if(moveTimer < moveCooldown) 
			return; 

		if(handler.getKeyInputManager().up)
			yMove =- speed;
		else if(handler.getKeyInputManager().down)
			yMove =+ speed;
		else if(handler.getKeyInputManager().left)
			xMove =- speed;
		else if(handler.getKeyInputManager().right)
			xMove =+ speed;
		else {
			return; 
		}
		
		handler.getPlayer().hurt(1); 
		stamina.increaseSkill(); 
		moveTimer = 0; 
	}

	
	
	//GETTERS SETTERS (function of getters and setters described in handler class)
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
		return playerDamage;
	}

	public void setDamage(int damage) {
		this.playerDamage = damage;
	}
}