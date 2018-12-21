package tilegame.tiles;

import tilegame.graphics.Assets;

public class EnergyTile extends Tile{


	public EnergyTile (int id) {
		super(Assets.energy, id);
	}
	
	//method that specifies that this tile is special
	@Override 
	public boolean isSpecial() {
		return true;
	}
}

