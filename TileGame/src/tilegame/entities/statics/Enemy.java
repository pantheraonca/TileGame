package tilegame.entities.statics;

import java.awt.Graphics;
import java.util.Random;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.graphics.Assets;
import tilegame.states.State;
import tilegame.tiles.Tile;


public class Enemy extends StaticEntity {
	
	//VARIABLES
	private Entity enemy;
	private int randomEnergy;
	
	//CONSTRUCTOR
	public Enemy(Handler handler, int x, int y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

		Random rand_nr = new Random();
		randomEnergy = rand_nr.nextInt(10);
		
		enemy = this;
		enemy.setEnergy(randomEnergy);
	}

	@Override
	public void update() {
	}	

	@Override
	public void die() {
		State.setYourScore(x = State.getYourScore() + 20);
		handler.getFighter().increaseSkill();  // for each enemy that dies a fighter increases its skill-level
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.enemy, (int) (x), (int) (y), width, height, null);
	}
}
