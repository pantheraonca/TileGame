package tilegame.entities.individuals;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.tiles.Tile;


/* The Individual class is an abstract class, because an Individual is not an object instantiated as such, 
 * but different classes could fall under the category Individual and can be instantiated. 
 * For example when there would be moving enemies or in a multiplayer setting. 
 * In this case the only individual is the player.
 * It also contains two methods to detect collisions between Individuals and Tiles. */
public abstract class Individual extends Entity {

	// VARIABLES
	public static final int DEFAULT_SPEED = 32;
	/* this determines the speed with which the individual moves. In our case only the player entity
	 * can move and it does so in steps of 32 pixels, this ensures tile by tile movement. */
	
	public static final int DEFAULT_INDIVIDUAL_WIDTH = 32;
	// Width of an Individual.
	
	public static final int DEFAULT_INDIVIDUAL_HEIGHT = 32;
	// Height of an Individual.

	protected int speed;
	protected int xMove, yMove;
	// specifies the amount of pixels that the individual tries to move in x and y direction.
	
	private int[][] updatedFieldTiles;
	/* a multidimensional array, each position in the array corresponds to a fieldTile in the field.
	 * It is used each time an individual moves over an energyTile. When a player moves over an energyTile it is 
	 * set equal to the fieldTiles Array, then is changed at the position corresponding to the energyTile moved over 
	 * and then passed in the setter of the fieldTiles Array. */

	
	/* super CONSTRUCTOR for an Individual the initial x and y position of each entity are 
	 * passed to this super constructor by the constructor of each extending class. */
	public Individual(Handler handler, int x, int y, int individualWidth, int individualHeight) {
		super(handler, x, y, individualWidth, individualHeight);

		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0; 
		//is set to 0 because initially the individual does not move
	}
	
	
	/* this method checks if the individual collides with other entities 
	 * by calling the checkEntityCollisions method of the superclass (entity class) */
	public void move() { 
		if(!checkEntityCollisions(xMove, 0))
			moveX();
		if(!checkEntityCollisions(0, yMove))
			moveY();
	}
	
	
	/* this method checks for collisions with tiles in the x direction. First the x position to which the 
	 * individual tries to move (in units of tiles) is calculated and stored in the variable
	 * tx (x coordinate of the tile the player tries to move into), then the collisionWithTile method is called.
	 * This method returns true if the tile the player tries to move into is a solid tile. 
	 * If the tile is solid the position of the individual gets set back to the position from which it tried to 
	 * move into the solid tile. 
	 * Then the next if statement calls the collidionSpecialTile method which returns true if the tile 
	 * the individual tries to move into is a special tile. If the tile is special the type of tile gets checked
	 * and the respective effect is invoked (dirt tile: player energy - 2;
	 * energy tile: player energy + 2 and tile gets set to a magma tile; magma tile player dies).
	 * For all collisions with special tiles the individual moves two tiles into the direction he moved into the tile */
	public void moveX() {
		if(xMove > 0) {//RIGHT

			int tx = (xOfEntity + xMove ) / Tile.TILE_WIDTH; //gives you the tile coordinates of the tile you are trying to move into
			if(collisionWithTile(tx, yOfEntity / Tile.TILE_HEIGHT)) {
				xOfEntity = tx * Tile.TILE_WIDTH - bounds.width;
			}
			
			else if(collisionSpecialTile(tx, yOfEntity / Tile.TILE_HEIGHT)) {
					xOfEntity = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH;
				
				if(handler.getField().obtainTile(tx, yOfEntity / Tile.TILE_HEIGHT).getId() == 2) {
					handler.getPlayer().hurt(2);
				}
				else if(handler.getField().obtainTile(tx, yOfEntity / Tile.TILE_HEIGHT).getId() == 4) {
					handler.getPlayer().setEnergy((handler.getPlayer().getEnergy()) + 2); 
					updatedFieldTiles = handler.getField().getFieldTiles();
					updatedFieldTiles [tx][yOfEntity / Tile.TILE_HEIGHT] = 5;
					handler.getField().setFieldTiles(updatedFieldTiles);
				}
				else if(handler.getField().obtainTile(tx, yOfEntity / Tile.TILE_HEIGHT).getId() == 5) {
					handler.getPlayer().die();
				}
			}
			
			else {
				xOfEntity += xMove;
			}

		}
		
		//this is the same as above but for the case that the player moves left into tiles.
		else if(xMove < 0) {
			int tx = (xOfEntity + xMove) / Tile.TILE_WIDTH; //gives you the tile coordinates of the tile you are trying to move into

			if(collisionWithTile(tx, yOfEntity / Tile.TILE_HEIGHT)) {
				xOfEntity = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH;
			}
			
			else if(collisionSpecialTile(tx, yOfEntity / Tile.TILE_HEIGHT)) {
				xOfEntity = tx * Tile.TILE_WIDTH - Tile.TILE_WIDTH;
				if(handler.getField().obtainTile(tx, yOfEntity / Tile.TILE_HEIGHT).getId() == 2) {
					handler.getPlayer().hurt(2);
				}
				else if(handler.getField().obtainTile(tx, yOfEntity / Tile.TILE_HEIGHT).getId() == 4) {
					handler.getPlayer().setEnergy((handler.getPlayer().getEnergy()) + 2);     
					updatedFieldTiles = handler.getField().getFieldTiles();
					updatedFieldTiles [tx][yOfEntity / Tile.TILE_HEIGHT] = 5;
					handler.getField().setFieldTiles(updatedFieldTiles);
				}
				else if(handler.getField().obtainTile(tx, yOfEntity / Tile.TILE_HEIGHT).getId() == 5) {
					handler.getPlayer().die();
				}
			}
			
			else {
				xOfEntity += xMove;
			}
		}
	}
	
	
	//this method checks for collisions with tiles in the y direction
	public void moveY() {
		if(yMove < 0) { //this is the same as above but for the case that the player moves up into tiles.

			int ty = (yOfEntity + yMove)/ Tile.TILE_HEIGHT; 

			if(collisionWithTile(xOfEntity / Tile.TILE_WIDTH, ty)) {
				yOfEntity = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT;
			}
			
			else if(collisionSpecialTile(xOfEntity / Tile.TILE_WIDTH, ty)) {
				yOfEntity = ty * Tile.TILE_HEIGHT - Tile.TILE_HEIGHT;
				if(handler.getField().obtainTile(xOfEntity / Tile.TILE_WIDTH, ty).getId() == 2) {
					handler.getPlayer().hurt(2);
				}
				else if(handler.getField().obtainTile(xOfEntity / Tile.TILE_WIDTH, ty).getId() == 4) {
					handler.getPlayer().setEnergy((handler.getPlayer().getEnergy()) + 2);     
					updatedFieldTiles = handler.getField().getFieldTiles();
					updatedFieldTiles [xOfEntity / Tile.TILE_WIDTH][ty] = 5;
					handler.getField().setFieldTiles(updatedFieldTiles);
				}
				else if(handler.getField().obtainTile(xOfEntity / Tile.TILE_WIDTH, ty).getId() == 5) {
					handler.getPlayer().die();
				}
			}
			
			else {
				yOfEntity += yMove;
			}

		}
		
		else if(yMove > 0) { //this is the same as above but for the case that the player moves down into tiles.
			int ty = (yOfEntity + yMove)/ Tile.TILE_HEIGHT; 

			if(collisionWithTile(xOfEntity / Tile.TILE_WIDTH, ty)) {
				yOfEntity = ty * Tile.TILE_HEIGHT - bounds.height;
			}
			
			else if(collisionSpecialTile(xOfEntity / Tile.TILE_WIDTH, ty)) {
				yOfEntity = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT;
				if(handler.getField().obtainTile(xOfEntity / Tile.TILE_WIDTH, ty).getId() == 2) {
					handler.getPlayer().hurt(2);
				}
				else if(handler.getField().obtainTile(xOfEntity / Tile.TILE_WIDTH, ty).getId() == 4) {
					handler.getPlayer().setEnergy((handler.getPlayer().getEnergy()) + 2);     
					updatedFieldTiles = handler.getField().getFieldTiles();
					updatedFieldTiles [xOfEntity / Tile.TILE_WIDTH][ty] = 5;
					handler.getField().setFieldTiles(updatedFieldTiles);
				}
				else if(handler.getField().obtainTile(xOfEntity / Tile.TILE_WIDTH, ty).getId() == 5) {
					handler.getPlayer().die();
				}
			}
			
			else {
				yOfEntity += yMove;
			}
		}
	}
	
	
	/* This method checks if the tile at the coordinates put into the method is a solid tile or not by 
	 * getting the tileObject from the obtainTile method and then calling the isSolid method on the tile
	 * which returns the boolean isSolid. If this boolean is true, this method returns true. */
	protected boolean collisionWithTile(int x, int y) {
		return handler.getField().obtainTile(x, y).isSolid();
	}
	
	
	/* This method checks if the tile at the coordinates put into the method is a special tile or not by 
	 * getting the tileObject from the obtainTile method and then calling the isSolid method on the tile
	 * which returns the boolean isSpecial. If this boolean is true, this method returns true. */
	protected boolean collisionSpecialTile(int x, int y) {
		return handler.getField().obtainTile(x, y).isSpecial();
	}
	

	//GETTERS SETTERS (function of getters and setters described in handler class)
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
