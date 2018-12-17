package tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import tilegame.Handler;
import tilegame.entities.individuals.Player;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities; //arraylist has no size could also be an array


	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player); // should tree be here and not in field ??!!
	}

	public void update() {	
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update();
			if(!e.isActive())
				entities.remove(e);
		}
	}

	public void render(Graphics g) {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
	}


	public void addEntity(Entity e) {
		entities.add(e);

	}

	//GETTERS SETTERS

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