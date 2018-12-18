package tilegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import tilegame.Handler;



public abstract class Entity { //abstract why

	protected Handler handler;
	protected int x, y; //protected is like private but classes that extend this class also have access; float is so the caracter moves smoothly
	protected int width, height;
	protected Rectangle bounds;
	protected int energy;
	protected boolean active = true; //whether entity is there or not

	public static int DEFAULT_ENERGY = 4;

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

	public abstract void die(); //warum isch des hier?
	
	public void hurt(int amt) {
		energy -= amt;
		if(energy <= 0) {
			active = false;
			die(); //bekommen wir punkte wenn wir sterben?
		}
	}

	public boolean checkEntityCollisions(int xOffset, int yOffset) {
		for(Entity e : handler.getField().getEntityManager().getEntities()) {
			if(e.equals(this))//so it does not check for collisions with itself
				continue;
			if(e.getCollisionBounds(0, 0).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	} //kömmer das was durnter und drüber is wegmachen???

	public Rectangle getCollisionBounds(int xOffset, int yOffset) {
		return new Rectangle(x + bounds.x + xOffset, y + bounds.y + yOffset, bounds.width, bounds.height);
	}

	
	//GETTERS SETTERS

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
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
