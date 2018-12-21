package tilegame.tiles;

import tilegame.graphics.Assets;

//this class 
public class DirtTile extends Tile {

	public DirtTile(int id) {
		super(Assets.dirt, id);
	}
	
	//method that specifies that this tile is special
	@Override 
	public boolean isSpecial() {
		return true;
	}
}
