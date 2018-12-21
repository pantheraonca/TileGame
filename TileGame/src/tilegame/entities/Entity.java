package tilegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import tilegame.Handler;

/* The Entity class is an abstract class, because an entity is not an object instantiated as such, 
 * but different classes fall under the category entity and can be instantiated. 
 * This class sets abstract methods which extending classes must contain.
 * It also contains a method to detect collisions between entities.
 * An entity is any object which will be rendered in the gameState but is not a Tile. */
public abstract class Entity {

	// VARIABLES some are protected so that the extending classes have access to them.
	protected Handler handler;
	protected int xOfEntity, yOfEntity;
	// x and y position in units of pixels of an entity is used in collision detection and to render the entity.

	protected int entityWidth, entityHeight;
	// width and height in units of pixels of an entity is used in collision detection and to render the entity.

	protected Rectangle bounds;
	/* a rectangle object that overlays each entity and is used for collision detection, 
	 * this is useful if an entity's graphics are bigger in size than its collision rectangle 
	 * (e.g. a tree that you can move behind). In our case the collision rectangle is always 
	 * the same size as the entity. */

	protected int energy;
	// the amount of energy an entity has.

	protected boolean active = true; 
	// if true an entity exists in the entities array, once set to false the entity gets removed from said array.

	public static int DEFAULT_ENERGY = 10;
	// the energy that an entity has by default.


	/* super CONSTRUCTOR for an entity the initial x and y position of each entity are 
	 * passed to this super constructor by the constructor of each extending class. */
	public Entity(Handler handler, int xOfEntity, int yOfEntity, int entityWidth, int entityHeight) {
		this.handler = handler;
		this.xOfEntity = xOfEntity;
		this.yOfEntity = yOfEntity;
		this.entityWidth = entityWidth;
		this.entityHeight = entityHeight;
		energy = DEFAULT_ENERGY;

		bounds = new Rectangle(0, 0, entityWidth, entityHeight);
		/* here the collision rectangle gets created. 
		 * the zero values (x and y position of the rectangle) will later be used in the 
		 * collision detection to specify the upper left corner of the rectangle in respect to the entity. */
	}


	//each extending class must contain a update method.
	public abstract void update();


	//each extending class must contain a render method.
	public abstract void render(Graphics g);


	//each extending class must contain a die method.
	public abstract void die();


	/* the hurt method when called on an entity, reduces the entity's energy
	 *  by the amount of the integer passed to the method.
	 *  If the energy of an entity becomes 0 the boolean active is set to false,
	 *  then the die method specified in the respective extending class is called */
	public void hurt(int damage) {
		energy -= damage;
		if(energy <= 0) {
			active = false;
			die();
		}
	}


	/* Generally this method checks for collisions between entities. 
	 * It takes in the xOffset and yOffset which specify the amount an entity tries to move
	 * in x or y direction. It iterates over every object in the entities array and checks the entities 
	 * position on which this method is called, on overlap with all other entities in the entities array.
	 * If any entities overlap the method returns true, if not false. To avoid errors the first if statement 
	 * is there so that whenever the player entity is checked with itself the method just continues. 
	 * This is important for smaller step sizes of the player, but as we move tile by tile the 
	 * player will never overlap with itself */
	public boolean checkEntityCollisions(int xOffset, int yOffset) {
		for(Entity e : handler.getField().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			
			if(e.getCollisionBounds(0, 0).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}


	/* this method takes in the amount that the entity tries to move in any direction and returns a rectangle 
	 * which is shifted from current position of the entity by the xOffset and yOffset amount put in to the method.
	 * It is called in the check entities method. It represents the position that the entity would be at 
	 * after moving by xOffset and yOffset. We are aware this is an overkill in terms of collision detection 
	 * but can be useful for further development of the game. */
	public Rectangle getCollisionBounds(int xOffset, int yOffset) {
		return new Rectangle(xOfEntity + bounds.x + xOffset, yOfEntity + bounds.y + yOffset, bounds.width, bounds.height);
	}


	//GETTERS SETTERS (function of getters and setters described in handler class)
	public int getX() {
		return xOfEntity;
	}

	public void setX(int x) {
		this.xOfEntity = x;
	}

	public int getY() {
		return yOfEntity;
	}

	public void setY(int y) {
		this.yOfEntity = y;
	}

	public int getWidth() {
		return entityWidth;
	}

	public void setWidth(int width) {
		this.entityWidth = width;
	}

	public int getHeight() {
		return entityHeight;
	}

	public void setHeight(int height) {
		this.entityHeight = height;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}