package tilegame.entities.individuals;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.tiles.Tile;

public abstract class Individual extends Entity {


	public static final int DEFAULT_SPEED = 32;
	public static final int DEFAULT_INDIVIDUAL_WIDTH = 32;
	public static final int DEFAULT_INDIVIDUAL_HEIGHT = 32;

	protected int speed;
	protected int xMove, yMove;

	public Individual(Handler handler, int x, int y, int width, int height) {
		super(handler, x, y, width, height); //leads to entity classes contructor

		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	//COLLISION WITH SOLID TILES
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0))
			moveX(); //this way you can still move up and down and are not stuck in the y axis when collision in x direction happens
		if(!checkEntityCollisions(0, yMove))
			moveY();
	}

	public void moveX() {
		if(xMove > 0) {//RIGHT

			int tx = (x + xMove ) / Tile.TILE_WIDTH; //gives you the tile coordinates of the tile you are trying to move into
			if(collisionWithTile(tx, y / Tile.TILE_HEIGHT)) {
				x = tx * Tile.TILE_WIDTH - bounds.width;
			}
			
			else if(collisionSpecialTile(tx, y / Tile.TILE_HEIGHT)) {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH;
				handler.getField().getTile(tx, y / Tile.TILE_HEIGHT).tileSpecialty();
			}
			
			else {
				x += xMove;
			}

		}else if(xMove < 0) {//LEFT
			int tx = (x + xMove) / Tile.TILE_WIDTH; //gives you the tile coordinates of the tile you are trying to move into

			if(collisionWithTile(tx, y / Tile.TILE_HEIGHT)) {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH;
			}
			
			else if(collisionSpecialTile(tx, y / Tile.TILE_HEIGHT)) {
				x = tx * Tile.TILE_WIDTH - Tile.TILE_WIDTH;
				handler.getField().getTile(tx, y / Tile.TILE_HEIGHT).tileSpecialty();
			}
			
			else {
				x += xMove;

			}
		}
	}

	public void moveY() {
		if(yMove < 0) {//UP

			int ty = (y + yMove)/ Tile.TILE_HEIGHT; 

			if(collisionWithTile(x / Tile.TILE_WIDTH, ty)) {
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT;
			}
			
			else if(collisionSpecialTile(x / Tile.TILE_WIDTH, ty)) {
				y = ty * Tile.TILE_HEIGHT - Tile.TILE_HEIGHT;
				handler.getField().getTile(x / Tile.TILE_WIDTH, ty).tileSpecialty();
			}
			
			else {
				y += yMove;
			}

		}else if(yMove > 0) {//DOWN
			int ty = (y + yMove)/ Tile.TILE_HEIGHT; 

			if(collisionWithTile(x / Tile.TILE_WIDTH, ty)) {
				y = ty * Tile.TILE_HEIGHT - bounds.height;
			}
			
			else if(collisionSpecialTile(x / Tile.TILE_WIDTH, ty)) {
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT;
				handler.getField().getTile(x / Tile.TILE_WIDTH, ty).tileSpecialty();
			}
			
			else {
				y += yMove;
			}
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getField().getTile(x, y).isSolid(); //returns true if tile is solid
	}
	
	protected boolean collisionSpecialTile(int x, int y) {
		return handler.getField().getTile(x, y).isSpecial(); //returns true if tile is solid
	}
	

	//GETTERS SETTERS

	public int getxMove() {
		return xMove;
	}

	public void setxMove(int xMove) {
		this.xMove = xMove;
	}

	public int getyMove() {
		return yMove;
	}

	public void setyMove(int yMove) {
		this.yMove = yMove;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
