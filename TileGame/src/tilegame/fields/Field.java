package tilegame.fields;

import java.awt.Graphics;
import java.util.Random;
import tilegame.Handler;
import tilegame.entities.EntityManager;
import tilegame.entities.individuals.Player;
import tilegame.entities.statics.Boss;
import tilegame.entities.statics.Enemy;
import tilegame.tiles.Tile;
import tilegame.utils.Utils;

public class Field {

	//VARIABLES
	private Handler handler;
	private int positioner = 32; //should we make it final variable 32 pixel and use this everywhere we use 32?
	private int width = 20, height = 20; //field-size in tiles
	private int spawnX = positioner * 10, spawnY = positioner * 2;
	private int energy = 200; //player energy gets set here (little weird)
	private int[][] fieldTiles; //multidimensional array
	private int enemyAmt = 10; //amount of enemies in the game
	private int amtCorrection = enemyAmt + 1; //corrects because there is a mistake in the for-loop and it gives one enemy less
	private int[] spawnArray = new int[amtCorrection]; //same size as amtCorrection because somehow the array size and the amtCorrection have to be the same value otherwise get error
	
	private EntityManager entityManager;

	//CONSTRUCTOR
	public Field(Handler handler, String fieldString) { 
		this.handler = handler;
		
		//PLAYER
		entityManager = new EntityManager(handler, new Player(handler, spawnX, spawnY));
		entityManager.getPlayer().setEnergy(energy); //why do we do this here ???
		
		//BOSS
		entityManager.addEntity(new Boss(handler, positioner * 10, positioner * 19));

		//ENEMIES
		randomSpawn();
		for(int i = 0; i < spawnArray.length; i++) {
			for(int j = 1; j < amtCorrection; j++) 
				entityManager.addEntity(new Enemy(handler, positioner * spawnArray[i], positioner * spawnArray[i=i+1]));
		}
		
		//FIELD
		loadField(fieldString);
	}

	public void update() {
		entityManager.update();
	}

	public void render(Graphics g) {
		
		//TILES
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				obtainTile(x, y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);	
			}
		}
		
		//ENTITIES
		entityManager.render(g);
	}
	/*public Tile changeToMagma(int x, int y) {
		if(collisionSpecialTile(x, (y / Tile.TILE_HEIGHT)))
			return Tile.magmaTile;
		else
			return Tile.magmaTile;
	}
	
	protected boolean collisionSpecialTile(int x, int y) {
		return handler.getField().obtainTile(x, y).isSpecial(); //returns true if tile is special
	}*/

	public Tile obtainTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.magmaTile; //telling the game if player is out of map that he is standing on a magmaTile tile so that the game doesn't crash
		Tile t = Tile.tiles[fieldTiles[x][y]];
		if(t == null)
			return Tile.shadowTile;
		return t; //if tile id number does not correspond to a tile b/c we don't have that id in the array you will end up of that tile being null so program would have a problem 
	}

	//RANDOM FIELD GENERATOTR //
	private void loadField(String fieldString) {
		String identities = "00000000000000001245"; //around 5% each are stone dirt and energy tiles
		String randomString = "";
		int length = width * height * 2; //this way you can change field size above

		Random r = new Random();

		char[] text = new char[length];{

			for(int i = 0; i < length; i++) {
				if ((i+2)%2 == 1) {	
					text[i] = ' ';					
				}									
				else {						
					text[i] = identities.charAt(r.nextInt(identities.length()));
				}
			}

			for(int i = 0; i < text.length; i++) {
				randomString += text[i];
			}
		}

		String[] randomArray = randomString.split("\\s+");

		fieldTiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				fieldTiles[x][y] = Utils.parseInt(randomArray[x + y * width]);
			}
		}
	}
	
	//RANDOM SPAWN POSITION GENERATOR
	private int[] randomSpawn() {
		Random rand_nr = new Random();

		for(int i = 0; i < spawnArray.length; i++) {
			spawnArray[i] = rand_nr.nextInt(19);
		}
		return spawnArray;
	}

	//GETTERS SETTERS
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public int getSpawnX() {
		return spawnX;
	}
	
	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}
	
	public int getSpawnY() {
		return spawnY;
	}
	
	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}
	
	public Handler getHandler() { //can this be removed??
		return handler;
	}
	
	public void setHandler(Handler handler) { //can this be removed??
		this.handler = handler;
	}
}
