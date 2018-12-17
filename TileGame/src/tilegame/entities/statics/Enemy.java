package tilegame.entities.statics;

import java.awt.Graphics;

import tilegame.Handler;
import tilegame.graphics.Assets;
import tilegame.states.State;
import tilegame.tiles.Tile;


public class Enemy extends StaticEntity {

	public Enemy(Handler handler, int x, int y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

	}

	@Override
	public void update() {

	}	

	@Override
	public void die() {
		State.setYourScore(x = State.getYourScore() + 20);
	}


	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.enemy, (int) (x), (int) (y), width, height, null);

	}

}
