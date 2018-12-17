package tilegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	//STATIC COMPONENTS 
	//stores tiles in an array
	public static Tile[] tiles = new Tile[256]; //static is accessible from everywhere 
	public static Tile grassTile = new GrassTile(0); //this sets the id of the tile
	public static Tile rockTile = new RockTile(1); //Tile so we know rocktile is a tile and then the method on its own (RockTile())
	public static Tile dirtTile = new DirtTile(2); //spieler loost 2 energy statt einer auf dirt tiles
	public static Tile shadowTile = new ShadowTile(3);
	public static Tile energyTile = new EnergyTile(4); //spieler gains 2 energy by moving over this tile
	public static Tile magmaTile = new MagmaTile(5); //player dies


	//CLASS
	public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;

	protected BufferedImage texture; //protected because we will have classes that extend this class otherwise would be private??
	protected final int id; //final because every tile will have an id but it wont be changed ever

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;

		tiles[id] = this; //the tile at this index in the array is equal to this tile (the one we are creating
	}

	public void update() {

	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}

	public boolean isSolid() {
		return false;
	}

	public int getId() {
		return id;
	}
}
