package tilegame.tiles;

import tilegame.graphics.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.rock, id);

	}
	@Override //just shows that this method overrides another one
	public boolean isSolid() {
		return true;
	}
}
