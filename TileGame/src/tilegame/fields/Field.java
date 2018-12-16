package tilegame.fields;

import java.awt.Graphics;
import java.util.Random;
import tilegame.Handler;
import tilegame.entities.EntityManager;
import tilegame.entities.individuals.Player;
import tilegame.entities.statics.Tree;
import tilegame.tiles.Tile;
import tilegame.utils.Utils;

public class Field {
	

	@SuppressWarnings("unused") //suppresses warning that handler is not used
	private Handler handler;
	private int width = 20, height = 20;
	private int spawnX = 320, spawnY = 32;
	private int[][] fieldTiles; //multidimensional array

	//Entities
	private EntityManager entityManager;
	
	
	public Field(Handler handler, String path) { 
		this.handler = handler; //was: this.setHandler(handler); before
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		entityManager.addEntity(new Tree(handler, 100 ,100));
		entityManager.addEntity(new Tree(handler, 100 ,164));
		entityManager.addEntity(new Tree(handler, 164 ,164));
		entityManager.addEntity(new Tree(handler, 164 ,100));
		
		loadField(path); 
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
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
			return Tile.grassTile; //telling the game if player is out of map that he is standing on a grass tile so that the game doesnt crash
		
		
		Tile t = Tile.tiles[fieldTiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t; //if tile id number 5 when you dont have that id in the array you will end up of that tile being null so programm has a problem 
	}
	

	private void loadField(String path) {
		String identities = "0000000000000";
		String randomString = "";
		int length = 800;
	
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
			System.out.print(randomString);//kann wech glaub
		}
		
		String[] randomArray = randomString.split("\\s+");
	
		
		fieldTiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				fieldTiles[x][y] = Utils.parseInt(randomArray[x + y * width]);
				System.out.println(fieldTiles);
			}
		}
	}
		
	

	//Getters Setters
	
	
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
	
	
	
}
