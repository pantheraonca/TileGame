package tilegame.entities.individuals;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.tiles.Tile;

public abstract class Individual extends Entity {

	
	public static final float DEFAULT_SPEED = 32;
	public static final int DEFAULT_INDIVIDUAL_WIDTH = 32,
							DEFAULT_INDIVIDUAL_HEIGHT = 32;
	
	
	
	protected float speed;
	protected float xMove, yMove;
	
	public Individual(Handler handler, int x, int y, int width, int height) {
		super(handler, x, y, width, height); //leads to entity classes contructor
		
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f))
			moveX(); //this way you can still move up and down and are not stuck in the y axis when collision in x direction happens
		if(!checkEntityCollisions(0f, yMove))
			moveY();
	}
	
	//find solution for sliding problem then you can remove the - 1 stuff and should be able to move from tile to tile truely
	
	public void moveX() {
		if(xMove > 0) {// moving right
			
			int tx = (int) ((x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH); //gives you the tile coordinates of the tile you are trying to move into
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
				!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			}else {
				x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1; //check ich nicht episode 23
			}
			
		}else if(xMove < 0) {//moving left
			int tx = (int) ((x + xMove + bounds.x) / Tile.TILE_WIDTH); //gives you the tile coordinates of the tile you are trying to move into
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
				!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			}else {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
				
			}
		}
	}
	
	public void moveY() {
		if(yMove < 0) {//UP
			
			int ty = (int) (y + yMove + bounds.y)/ Tile.TILE_HEIGHT; 
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				y += yMove;
			}else {
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
			}
			
		}else if(yMove > 0) {//DOWN
			int ty = (int) (y + yMove + bounds.y + bounds.height)/ Tile.TILE_HEIGHT; 
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				y += yMove;
			}else {
				y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1; //minus one because of sliding problem aber check ich ncith ganz
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getField().getTile(x, y).isSolid();
	}
	
	//GETTERS SETTERS
	
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
