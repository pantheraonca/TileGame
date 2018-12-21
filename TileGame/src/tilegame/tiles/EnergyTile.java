package tilegame.tiles;

import tilegame.graphics.Assets;

//this class allows for construction of energy tiles it takes the cropped image from the assets class and assigns it to the energy tile object
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

