package tilegame.fields;

import java.awt.Graphics;

import tilegame.Handler;
import tilegame.entities.EntityManager;
import tilegame.entities.individuals.Player;
import tilegame.graphics.PlayerCamera;
import tilegame.tiles.Tile;
import tilegame.entities.Entity;
import tilegame.Game;
import tilegame.Launcher;
import tilegame.fields.Field;


public class Shadow {
	
	
	private int width, height; // die muss ich noch getten aus der field class und nicht selbst setten
	private int[][] shadowTiles; //multidimensional array
	private Tile shadowTile;
	private float xOffset;
	private float yOffset;
	protected Game game;
	private Handler handler;
	private int noRenderX;
	private int noRenderY;
	private int visibilityLevel;
	
	
	public Shadow (Handler handler, Game game, int width, int height) {
		
		this.handler = handler;
		this.game = game;
		this.width=width ; // find  away to get width and height from field and make method not take in anything
		this.height=height; 
		shadowTiles = new int [width] [height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				shadowTiles [x][y] = 3; // wie kann ich hier shadowTile ID getten?
			}
		}
				
	}
	
	public Tile getTile(int x, int y) {
		
		Tile shadowTile = Tile.tiles[shadowTiles[x][y]];
		if(shadowTile == null)
			return Tile.shadowTile;
		return shadowTile; //if tile id number 5 when you dont have that id in the array you will end up of that tile being null so programm has a problem 
	}
	
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		
		noRenderY = (int) (handler.getPlayerCamera().getyOffset()/Tile.TILE_HEIGHT);	
		noRenderX = (int) (handler.getPlayerCamera().getxOffset()/Tile.TILE_WIDTH);
		visibilityLevel = 2;
		
		for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					if ((y < noRenderY -visibilityLevel || y >  noRenderY + visibilityLevel) || ( x < noRenderX - visibilityLevel || x > noRenderX + visibilityLevel )) {
						getTile(x, y).render(g,(x * Tile.TILE_WIDTH), (y * Tile.TILE_HEIGHT));
				}	
			}
		}
	}
}