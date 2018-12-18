package tilegame.tiles;

import tilegame.graphics.Assets;

public class DirtTile extends Tile {

	public DirtTile(int id) {
		super(Assets.dirt, id);
	}
	
	@Override //just shows that this method overrides another one
	public boolean isSpecial() {
		return true;
	}
}
