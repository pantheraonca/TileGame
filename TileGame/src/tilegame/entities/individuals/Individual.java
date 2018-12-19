package tilegame.entities.individuals;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.tiles.Tile;

public abstract class Individual extends Entity {

	//VARIABLES
	public static final int DEFAULT_SPEED = 32; //ensures tile by tile movement
	public static final int DEFAULT_INDIVIDUAL_WIDTH = 32;
	public static final int DEFAULT_INDIVIDUAL_HEIGHT = 32;

	protected int speed;
	protected int xMove, yMove;

	//CONSTRUCTOR
	public Individual(Handler handler, int x, int y, int width, int height) {
		super(handler, x, y, width, height); //leads to entity classes constructor

		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() { //this is important otherwise you move through entities eventually why i dont know
		if(!checkEntityCollisions(xMove, 0))
			moveX();
		if(!checkEntityCollisions(0, yMove))
			moveY();
	}
	
	//COLLISION WITH TILES
	public void moveX() {
		if(xMove > 0) {//RIGHT

			int tx = (x + xMove ) / Tile.TILE_WIDTH; //gives you the tile coordinates of the tile you are trying to move into
			if(collisionWithTile(tx, y / Tile.TILE_HEIGHT)) {
				x = tx * Tile.TILE_WIDTH - bounds.width;
			}
			
			else if(collisionSpecialTile(tx, y / Tile.TILE_HEIGHT)) {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH;
				if(handler.getField().obtainTile(tx, y / Tile.TILE_HEIGHT).getId() == 2) {
					handler.getPlayer().hurt(2);
				}
				else if(handler.getField().obtainTile(tx, y / Tile.TILE_HEIGHT).getId() == 4) {
					handler.getField().getEntityManager().getPlayer().setEnergy((handler.getField().getEntityManager().getPlayer().getEnergy()) + 2);     
					
					//handler.getField().obtainTile(tx, y / Tile.TILE_HEIGHT).setId(5); //shit b/c sets all to magmaTile
				}
				else if(handler.getField().obtainTile(tx, y / Tile.TILE_HEIGHT).getId() == 5) {
					handler.getField().getEntityManager().getPlayer().hurt((handler.getField().getEntityManager().getPlayer().getEnergy()) + 1);
				}
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
				if(handler.getField().obtainTile(tx, y / Tile.TILE_HEIGHT).getId() == 2) {
					handler.getField().getEntityManager().getPlayer().hurt(2);
				}
				else if(handler.getField().obtainTile(tx, y / Tile.TILE_HEIGHT).getId() == 4) {
					handler.getField().getEntityManager().getPlayer().setEnergy((handler.getField().getEntityManager().getPlayer().getEnergy()) + 2);     
					
				}
				else if(handler.getField().obtainTile(tx, y / Tile.TILE_HEIGHT).getId() == 5) {
					handler.getField().getEntityManager().getPlayer().hurt((handler.getField().getEntityManager().getPlayer().getEnergy()) + 1);
				}
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
				if(handler.getField().obtainTile(x / Tile.TILE_WIDTH, ty).getId() == 2) {
					handler.getField().getEntityManager().getPlayer().hurt(2);
				}
				else if(handler.getField().obtainTile(x / Tile.TILE_WIDTH, ty).getId() == 4) {
					handler.getField().getEntityManager().getPlayer().setEnergy((handler.getField().getEntityManager().getPlayer().getEnergy()) + 2);     
					
				}
				else if(handler.getField().obtainTile(x / Tile.TILE_WIDTH, ty).getId() == 5) {
					handler.getField().getEntityManager().getPlayer().hurt((handler.getField().getEntityManager().getPlayer().getEnergy()) + 1);
				}
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
				if(handler.getField().obtainTile(x / Tile.TILE_WIDTH, ty).getId() == 2) {
					handler.getField().getEntityManager().getPlayer().hurt(2);
				}
				else if(handler.getField().obtainTile(x / Tile.TILE_WIDTH, ty).getId() == 4) {
					handler.getField().getEntityManager().getPlayer().setEnergy((handler.getField().getEntityManager().getPlayer().getEnergy()) + 2);     
					
				}
				else if(handler.getField().obtainTile(x / Tile.TILE_WIDTH, ty).getId() == 5) {
					handler.getField().getEntityManager().getPlayer().hurt((handler.getField().getEntityManager().getPlayer().getEnergy()) + 1);
				}
			}
			
			else {
				y += yMove;
			}
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getField().obtainTile(x, y).isSolid(); //returns true if tile is solid
	}
	
	protected boolean collisionSpecialTile(int x, int y) {
		return handler.getField().obtainTile(x, y).isSpecial(); //returns true if tile is special
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
