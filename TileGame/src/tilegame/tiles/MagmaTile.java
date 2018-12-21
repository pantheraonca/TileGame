package tilegame.tiles;

import tilegame.graphics.Assets;

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

