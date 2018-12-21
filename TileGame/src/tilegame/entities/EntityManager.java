package tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import tilegame.Handler;
import tilegame.entities.individuals.Player;


/* The EntityManager class contains an array of all entities currently present in the game. 
 * It contains an update and a render method which iterate through this array. An entityManager object takes in 
 * a handler and a player object. */
public class EntityManager {

	//VARIABLES
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities; 
	/* this ArrayList contains all entities, entities can be added and removed and iterated over.
	 * The advantage of using an ArrayList is that it has no size limit. */

	/* CONSTRUCTOR takes in handler and player object. The player object is the first element 
	 * added to a newly instantiated array list of type entity. */
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player); //what does this do? why not enough to put the player in field? because player object is needed in entity manager???
	}
	
	
	/* The update method gets constantly called through the field class.
	 * It contains a for-loop iterating through the entities in the Entities ArrayList.
	 * If the boolean isActive of the respective entity is false the entity will be removed from the ArrayList.
	 * In case the player dies it does not get removed. This might be helpful when a player has multiple lives.
	 * but does not have a use in out case */
	public void update() {	
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update();
			if(!e.isActive())
				if(e.equals(player))
					continue;
			
				else { 
					entities.remove(e);
				}
		}
	} 
	

	//iterates through the ArrayList and calls the render method of each entity present.
	public void render(Graphics g) {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
	}
	
	
	//method to enable the adding of entities to the ArrayList.
	public void addEntity(Entity e) {
		entities.add(e);

	}


	//GETTERS SETTERS (function of getters and setters described in handler class)
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}