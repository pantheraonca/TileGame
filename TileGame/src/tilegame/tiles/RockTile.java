package tilegame.tiles;

import tilegame.graphics.Assets;

//this class allows for construction of rock tiles it takes the cropped image from the assets class and assigns it to the rock tile object
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
