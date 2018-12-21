package tilegame.fields;

import java.awt.Graphics;

import tilegame.Handler;
import tilegame.tiles.Tile;
import tilegame.Game;

/*This class can be instantiated to obtain a shadow object which will be rendered on top of the field object and the entities.
//It contains a method to not render the shadow at certain tile positions and thus to create a hole in the shadow which represents
//the players field of visibility. Also it contains a variable which and variable which determines the "radius" of the whole in tiles.*/
public class Shadow {
	
	//VARIABLES
	private int shadowWidth, shadowHeight; 
	// this variables set the shadows width and height in tile units
	
	private int[][] shadowTiles; 
	//multidimensional array storing only integer values corresponding to ShadowTile id
	// this array is populated via a for loop in the constructor;
	
	protected Game game;
	//current game object 
	
	private Handler handler;
	
	private int dontRenderX;
	//this variable determines the x position of the tile at and around which the shadow is not rendered
	//corresponds to player position
	
	private int dontRenderY;
	// this variable determines the y position of the tile at and around which the shadow is not rendered
	//corresponds to player position
	
	private int visibilityLevel = 5;
	// this variable determines how many shadow tile in x and y direction around the player are not rendered
	//this variable is changed by difficultyLevel objects 

	
	//Constructor for the shadow object which takes in a handler and a game object and two integer values that
	//determine the shadowWidth and shadowHeight 
	public Shadow (Handler handler, Game game, int shadowWidth, int shadowHeight) {

		this.handler = handler;
		this.game = game;
		this.shadowWidth = shadowWidth ;
		this.shadowHeight = shadowHeight; 
		shadowTiles = new int [shadowWidth] [shadowHeight];
		for (int x = 0; x < shadowWidth; x++) {
			for (int y = 0; y < shadowHeight; y++) {
				shadowTiles [x][y] = 3; 
			}
		}
	}
	
	
	/*This method returns the tile stored corresponding to the id integer stored in 
	 * the shadowTiles array at the position specified by the inputs x and y.
	 * The returned tile will always be a shadow tile since inly shadow tile ids are stored.*/
	public Tile obtainTile(int x, int y) {

		Tile shadowTile = Tile.tiles[shadowTiles[x][y]];
		return shadowTile; 
	}

	
	public void update() {
	}

	
	/*This method renders ShadowTiles all over the field except around in the position the player is currently at.
	 * It gets the players x and y position through the playerCamera object.
	 * Then it renders tiles everywhere on the field except around the player, this is ensured by a nested for loop.*/
	public void render(Graphics g) {

		dontRenderY = (int) (handler.getPlayerCamera().getyOffset()/Tile.TILE_HEIGHT);	
		dontRenderX = (int) (handler.getPlayerCamera().getxOffset()/Tile.TILE_WIDTH);

		for(int y = 0; y < shadowWidth; y++) {
			for(int x = 0; x < shadowHeight; x++) {
				if ((y < dontRenderY -visibilityLevel || y >  dontRenderY + visibilityLevel) || ( x < dontRenderX - visibilityLevel || x > dontRenderX + visibilityLevel )) {
					obtainTile(x, y).render(g,(x * Tile.TILE_WIDTH), (y * Tile.TILE_HEIGHT));
				}	
			}
		}
	}

	
	//GETTERS SETTERS
	
	public int getVisibilityLevel() {
		return visibilityLevel;
	}

	public void setVisibilityLevel(int visibilityLevel) {
		this.visibilityLevel = visibilityLevel;
	}
}