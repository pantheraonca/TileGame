package tilegame.fields;

import java.awt.Graphics;

import tilegame.Handler;
import tilegame.tiles.Tile;
import tilegame.Game;


public class Shadow {
	
	//VARIABLES
	private int width, height; // die muss ich noch getten aus der field class und nicht selbst setten
	private int[][] shadowTiles; //multidimensional array
	protected Game game;
	private Handler handler;
	private int dontRenderX;
	private int dontRenderY;
	private int visibilityLevel = 5;

	//CONSTRUCTOR
	public Shadow (Handler handler, Game game, int width, int height) {

		this.handler = handler;
		this.game = game;
		this.width = width ; // find away to get width and height from field and make method not take in anything
		this.height = height; 
		shadowTiles = new int [width] [height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				shadowTiles [x][y] = 3; // wie kann ich hier shadowTile ID getten?
			}
		}
	}
	
	//DO WE NEED THIS METHOD????
	public Tile obtainTile(int x, int y) {

		Tile shadowTile = Tile.tiles[shadowTiles[x][y]];
		//if(shadowTile == null)
		//	return Tile.shadowTile;
		return shadowTile; //if tile id number 5 when you don't have that id in the array you will end up of that tile being null so program has a problem 
	}

	public void update() {
	}

	public void render(Graphics g) {

		dontRenderY = (int) (handler.getPlayerCamera().getyOffset()/Tile.TILE_HEIGHT);	
		dontRenderX = (int) (handler.getPlayerCamera().getxOffset()/Tile.TILE_WIDTH);

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
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