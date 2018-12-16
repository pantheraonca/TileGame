package tilegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import tilegame.Handler;
import tilegame.states.State;



public abstract class Entity { //abstractwhy

	protected Handler handler;
	protected int x, y; //protected is like private but classes that extend this class also have access; float is so the caracter moves smoothly
	protected int width, height;
	protected Rectangle bounds;
	protected int energy;
	protected boolean active = true; //whether entity is there or not
	public static final int DEFAULT_ENERGY = 2;
	
	public Entity(Handler handler, int x, int y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		energy = DEFAULT_ENERGY;
		
		bounds = new Rectangle(0, 0, width, height); //puts a rectangle on an entity that is the same size and position of the entity
	}

	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public void hurt(int amt) {
		energy -= amt;
		if(energy <= 0) {
			active = false;
			die();
			State.setYourScore(x = State.getYourScore() + 40);
			//for(Entity e : handler.getField().getEntityManager().getEntities()) { //for every entity e in our arraylist(the part after the doppelpunkt
			//	if(e.equals(handler.getField().getEntityManager().getPlayer())) { //if the entity is not the player then add 10 points to score
			//		return;
			//	}
			//} //klappt vllt noch nicht ganz aber ich will nicht das wenn spieler stirbt das wir 10 punkte geben
		}	
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e : handler.getField().getEntityManager().getEntities()) {
			if(e.equals(this))//so it does not check for collisions with itself
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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
