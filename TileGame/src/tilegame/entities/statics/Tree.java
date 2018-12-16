package tilegame.entities.statics;

import java.awt.Graphics;

import tilegame.Handler;
import tilegame.graphics.Assets;
import tilegame.tiles.Tile;


public class Tree extends StaticEntity {

	public Tree(Handler handler, int x, int y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		
	}

	@Override
	public void update() {
	
	}	
	
	@Override
	public void die() {
		
	}

		
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x), (int) (y), width, height, null);
		//check nicht warum das x -handler.getGamecameraETC. ist
	}

}
