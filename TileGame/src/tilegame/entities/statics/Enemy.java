package tilegame.entities.statics;

import java.awt.Graphics;
import java.util.Random;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.graphics.Assets;
import tilegame.states.State;
import tilegame.tiles.Tile;

/* The Enemy class contains a constructor an update a render and a die method. It extends the EntityClass.
 * It has a method in its constructor which sets its energy to a random value. */
public class Enemy extends StaticEntity {

	//VARIABLES
	private Entity enemy;
	// This is a variable in which the current enemy instance stores itself as an entity to assess its Entity interface
	// and be able to set its energy with the setEnergy method.

	private int randomEnergy;
	// this is a variable in which the output of the rand_nr method is stored to then pass it to the setEnergy method to set a 
	// random enemy energy

	/* The constructor of the Enemy class takes in a handler object and the x an y coordinate of the 
	 * enemy in units of pixels. Also it sets the energy of the itself to a random value between one and 10. */
	public Enemy(Handler handler, int x, int y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

		Random rand_nr = new Random();
		randomEnergy = rand_nr.nextInt(10);

		enemy = this;
		enemy.setEnergy(randomEnergy);
	}

	@Override
	public void update() {
	}	


	/* This method overrides the die method of its superclasses. The die method specifies what 
	 * happens when an enemy is defeated. The score variable is increased by 20, and the increaseSkill
	 * method of the Fighter Skill is called to potentially increase its skill. */
	@Override
	public void die() {
		State.setYourScore(xOfEntity = State.getYourScore() + 20);
		handler.getFighter().increaseSkill();  // for each enemy that dies a fighter increases its skill-level
	}


	/* The render method of the enemy is called by the render method of the EntityManager
	 * The render method of the enemy calls the drawImage method of the graphics object 
	 * and passes it the corresponding image from the assets class, as well as the coordinates
	 * at which the enemy has to be drawn on the canvas object.*/
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.enemy, (int) (xOfEntity), (int) (yOfEntity), entityWidth, entityHeight, null);
	}
}