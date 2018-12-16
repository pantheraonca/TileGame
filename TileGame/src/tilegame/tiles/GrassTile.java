package tilegame.tiles;

import tilegame.graphics.Assets;

public class GrassTile extends Tile {

	public GrassTile(int id) {
		super(Assets.grass, id);
	}
}
//we dont need a isSolid method in here because grass is walkable and it will use the default in the tile class