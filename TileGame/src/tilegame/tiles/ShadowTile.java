package tilegame.tiles;

import tilegame.graphics.Assets;

public class ShadowTile extends Tile {
	
	public ShadowTile(int id) {
		super(Assets.shadow, id);
	}
	
	@Override //just shows that this method overrides another one
	public boolean isSolid() {
		return false;
	}
}



