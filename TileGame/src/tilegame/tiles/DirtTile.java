package tilegame.tiles;

import tilegame.graphics.Assets;

//this class allows for construction of dirt tiles it takes the cropped image from the assets class and assigns it to the dirt tile object
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
