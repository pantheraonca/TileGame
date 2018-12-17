package tilegame.fields;

import java.awt.Graphics;
import java.util.Random;
import tilegame.Handler;
import tilegame.entities.EntityManager;
import tilegame.entities.individuals.Player;
import tilegame.entities.statics.Grail;
import tilegame.entities.statics.Enemy;
import tilegame.tiles.Tile;
import tilegame.utils.Utils;

public class Field {

	private Handler handler;
	private int positioner = 32; //am besten zu nem final variable 32 pixel oder sowat und das überall benutzen so das man das ganze game scalen kann
	private int width = 20, height = 20; //fieldsize
	private int spawnX = positioner * 10, spawnY = positioner * 2;
	private int energy = 200; //energy gets set here
	private int[][] fieldTiles; //multidimensional array
	private int[] spawnArray = new int[24];

	//Entities
	private EntityManager entityManager;


	public Field(Handler handler, String path) { 
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, spawnX, spawnY));

		entityManager.addEntity(new Grail(handler, positioner * 10, positioner * 19));

		randomSpawn(); //maybe make forloop and be able to set number of enemies
		entityManager.addEntity(new Enemy(handler, positioner * spawnArray[0], positioner * spawnArray[1]));
		entityManager.addEntity(new Enemy(handler, positioner * spawnArray[2], positioner * spawnArray[3]));
		entityManager.addEntity(new Enemy(handler, positioner * spawnArray[4], positioner * spawnArray[5]));
		entityManager.addEntity(new Enemy(handler, positioner * spawnArray[6], positioner * spawnArray[7]));
		entityManager.addEntity(new Enemy(handler, positioner * spawnArray[8], positioner * spawnArray[9]));
		entityManager.addEntity(new Enemy(handler, positioner * spawnArray[10], positioner * spawnArray[11]));
		entityManager.addEntity(new Enemy(handler, positioner * spawnArray[12], positioner * spawnArray[13]));
		entityManager.addEntity(new Enemy(handler, positioner * spawnArray[14], positioner * spawnArray[15]));
		entityManager.addEntity(new Enemy(handler, positioner * spawnArray[16], positioner * spawnArray[17]));
		entityManager.addEntity(new Enemy(handler, positioner * spawnArray[18], positioner * spawnArray[19]));
		entityManager.addEntity(new Enemy(handler, positioner * spawnArray[20], positioner * spawnArray[21]));
		entityManager.addEntity(new Enemy(handler, positioner * spawnArray[22], positioner * spawnArray[23]));

		loadField(path); //sollte der string path heißen? ist kein path mehr

		entityManager.getPlayer().setEnergy(energy); //why do we do this here ???
	}


	public void update() {
		entityManager.update();

	}

	public void render(Graphics g) {

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				getTile(x, y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);	
			}
		}

		//Entities
		entityManager.render(g);

	}
	

	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.magmaTile; //telling the game if player is out of map that he is standing on a magma tile so that the game doesnt crash
		//handler.getField().getEntityManager().getPlayer().die(); //player dies when he moves out of the field

		Tile t = Tile.tiles[fieldTiles[x][y]];
		if(t == null)
			return Tile.grassTile;
		return t; //if tile id number eg. 8 when you dont have that id in the array you will end up of that tile being null so programm has a problem 
	}


	private void loadField(String path) {
		String identities = "00000000000000001245"; //around 5% each are stone dirt and energy tiles
		String randomString = "";
		int length = width * height * 2; //so kann man oben die fieldsize verändern

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
			//System.out.print(randomString);//kann wech glaub
		}

		String[] randomArray = randomString.split("\\s+");


		fieldTiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				fieldTiles[x][y] = Utils.parseInt(randomArray[x + y * width]);
			}
		}
	}

	private int[] randomSpawn() {
		Random rand_nr = new Random();

		for(int i = 0; i < spawnArray.length; i++) {
			spawnArray[i] = rand_nr.nextInt(20);
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
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
