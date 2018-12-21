package tilegame.fields;

import java.awt.Graphics;
import java.util.Random;
import tilegame.Handler;
import tilegame.entities.EntityManager;
import tilegame.entities.individuals.Player;
import tilegame.entities.statics.Boss;
import tilegame.entities.statics.Enemy;
import tilegame.tiles.Tile;

//this class contains the field of the game, a method to randomly generate a field, a method to assign random positions to the spawned enemies
public class Field {

	//VARIABLES
	private Handler handler;
	private int positioner = 32; 
	/* this variable is used to simplify the positioning of entities 
	 * (so you dont have to give the position in pixels). */
	
	private int fieldWidth = 20, fieldHeight = 20;
	// field-size in tiles (20 by 20 tiles).
	
	private int spawnX = positioner * 10, spawnY = positioner * 2;
	// spawn position of the Player.
	
	private int energy = 200;
	// sets the energy the player starts with.
	
	private int[][] fieldTiles; 
	/* multidimensional array storing the tile identities. Each position in the Array
	 * represents a position in the field. */
	
	private int enemyAmt = 10; 
	// amount of enemies that are spawned in the game.
	
	private int amtCorrection = enemyAmt + 1; 
	// correction factor because there is a mistake in the for-loop and it returns one enemy less.
	
	private int[] spawnArray = new int[amtCorrection]; 
	// spawn array that stores randomly generated integers that will determine the enemy positions.
	
	private EntityManager entityManager;
	// An entityManager object takes in a handler and a player object.
	

	/* CONSTRUCTOR instantiates a new entityManager and a new Player, sets the player energy, 
	 * adds a Boss entity, calls the randomSpawn method that returns the SpawnArray, and adds 
	 * the specified amount of enemies to the entityManager and loads the field */
	public Field(Handler handler) { 
		this.handler = handler;
		
		//PLAYER
		entityManager = new EntityManager(handler, new Player(handler, spawnX, spawnY));
		entityManager.getPlayer().setEnergy(energy); 
		
		//BOSS
		entityManager.addEntity(new Boss(handler, positioner * 10, positioner * 19));

		//ENEMIES
		randomSpawn();
		for(int i = 0; i < spawnArray.length; i++) {
			for(int j = 1; j < amtCorrection; j++) 
				entityManager.addEntity(new Enemy(handler, positioner * spawnArray[i], positioner * spawnArray[i=i+1]));
		}
		
		//FIELD
		loadField();
	}
	
	
	// updates the entityManager.
	public void update() {
		entityManager.update();
	}

	
	//renders the fieldTiles and entities.
	public void render(Graphics g) {
		
		//TILES
		for(int y = 0; y < fieldHeight; y++) {
			for(int x = 0; x < fieldWidth; x++) {
				obtainTile(x, y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);	
			}
		}
		
		//ENTITIES
		entityManager.render(g);
		handler.getPlayer().render(g);
	}

	/* this method is there to avoid errors. When the player moves out of the field a 
	 * magma tile gets returned at the position the player moved to and the player dies in return.
	 * if a tile has an id that is not specified in the tiles class the program returns a shadowTile. */
	public Tile obtainTile(int x, int y) {
		if(x < 0 || y < 0 || x >= fieldWidth || y >= fieldHeight)
			return Tile.magmaTile;
		Tile t = Tile.tiles[fieldTiles[x][y]];
		
		if(t == null)
			return Tile.shadowTile;
		return t; 
	}

	/* RANDOM FIELD GENERATOR first a string (randomString) gets created consisting of numbers (0,1,2,4 and 5)
	 * each second character in the string is a space. The string gets split by the spaces into strings and 
	 * each of the new strings is an element of a stringArray (randomArray). The parseInt method from the Utils class 
	 * is used to convert each string in the stringArray into an integer that can be set equal to the field tiles
	 * multidimensional array. */
	
	public void loadField() {
		String identities = "00000000000000001245"; //around 5% each are stone dirt and energy and magma tiles
		String randomString = "";
		int length = fieldWidth * fieldHeight * 2; //this way one can change the field size above

		Random r = new Random();

		char[] text = new char[length];

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
		

		String[] randomArray = randomString.split("\\s+");

		fieldTiles = new int[fieldWidth][fieldHeight];
		for(int y = 0; y < fieldHeight; y++) {
			for(int x = 0; x < fieldWidth; x++) {
				fieldTiles[x][y] = parseInt(randomArray[x + y * fieldWidth]);
			}
		}
	}
	
	//RANDOM SPAWN POSITION GENERATOR
	//returns an integer Array filled with random values from 0 to 19.
	private int[] randomSpawn() {
		Random rand_nr = new Random();

		for(int i = 0; i < spawnArray.length; i++) {
			spawnArray[i] = rand_nr.nextInt(19);
		}
		return spawnArray;
	}
	
	//this method converts string into integers
	public static int parseInt(String number) {
		try { 
			return Integer.parseInt(number);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	//GETTERS SETTERS (function of getters and setters described in handler class)
	public int getWidth() {
		return fieldWidth;
	}
	
	public int getHeight() {
		return fieldHeight;
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

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int[][] getFieldTiles() {
		return fieldTiles;
	}

	public void setFieldTiles(int[][] fieldTiles) {
		this.fieldTiles = fieldTiles;
	}
}