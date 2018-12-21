package tilegame.tiles;

import tilegame.graphics.Assets;

//this class allows for construction of magma tiles it takes the cropped image from the assets class and assigns it to the magma tile object
public class MagmaTile extends Tile{


	public MagmaTile(int id) {
		super(Assets.magma, id);
	}
	
	//method that specifies that this tile is special
	@Override 
	public boolean isSpecial() {
		return true;
	}
}

