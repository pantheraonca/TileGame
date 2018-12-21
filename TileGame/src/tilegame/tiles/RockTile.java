package tilegame.tiles;

import tilegame.graphics.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.rock, id);

	}
	
	//method that specifies that this tile is solid
	@Override 
	public boolean isSolid() {
		return true;
	}
}
