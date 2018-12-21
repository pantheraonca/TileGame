package tilegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

// This class holds the different Tile types in an array and assigns Tile IDs.
public class Tile {

	//STATIC COMPONENTS 
	public static Tile[] tiles = new Tile[100];
	//Tile array holding maximally 100 Tile types.
	
	public static Tile grassTile = new GrassTile(0);
	public static Tile rockTile = new RockTile(1); 
	public static Tile dirtTile = new DirtTile(2);
	public static Tile shadowTile = new ShadowTile(3);
	public static Tile energyTile = new EnergyTile(4); 
	public static Tile magmaTile = new MagmaTile(5);
	// initiates Tiles of different types and sets an TILE_ID for them.
	

	
	//VARIABLES
	public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
	//the size of a tile

	protected BufferedImage texture; 
	//the image of a tile
	
	protected final int TILE_ID; 
	//the id of a tile

	//CONSTRUCTOR takes in a tile Image and ID.
	public Tile(BufferedImage texture, int TILE_ID) {
		this.texture = texture;
		this.TILE_ID = TILE_ID;

		tiles[TILE_ID] = this; //the tile at this index in the Tile array is equal to the tile constructed.
	}

	public void update() {
	}

	//renders the tile 
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	//all tile subclasses that do not have a isSolid method overwriting this class will return 
	//false if the isSolid method is called on them.
	public boolean isSolid() {
		return false;
	}
	
	//all tile subclasses that do not have a isSpecial method overwriting this class will return 
	//false if the isSolid method is called on them.
	public boolean isSpecial() {
		return false;
	}

	//GETTERS SETTERS(function of getters and setters described in handler class)
	public int getId() {
		return TILE_ID;
	}	
}