package tilegame.entities.statics;

import tilegame.Handler;
import tilegame.entities.Entity;

/* The staticEntity class is an abstract class, extended by other classes that fall 
 * under the category staticEntity. Static entities are entities that are not able to move
 * like for example trees. This class is not used to its full potential but can be useful if the game 
 * has a clearer differentiation between individuals and static entities. */
public abstract class StaticEntity extends Entity {
	
	//super CONSTRUCTOR for a static Entity.
	public StaticEntity(Handler handler, int x, int y, int width, int height) {
		super(handler, x, y, width, height);	
	}
}
