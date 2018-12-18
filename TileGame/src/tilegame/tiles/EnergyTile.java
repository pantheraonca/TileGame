package tilegame.tiles;

import tilegame.graphics.Assets;

public class EnergyTile extends Tile{


	public EnergyTile (int id) {
		super(Assets.energy, id);
	}
	
	@Override //just shows that this method overrides another one
	public boolean isSpecial() {
		return true;
	}
}

