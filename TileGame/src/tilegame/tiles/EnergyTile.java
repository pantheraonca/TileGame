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
	
	public void tileSpecialty() {
		System.out.println("tilespecialty of energy tile was called");
		//handler.getField().getEntityManager().getPlayer().setEnergy(handler.getField().getEntityManager().getPlayer().getEnergy() + 2)
	}
	
}

