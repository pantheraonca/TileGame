package tilegame.tiles;

import tilegame.graphics.Assets;

public class MagmaTile extends Tile{


	public MagmaTile(int id) {
		super(Assets.magma, id);
	}
	@Override //just shows that this method overrides another one
	public boolean isSpecial() {
		return true;
	}
	
	public void tileSpecialty() {
		System.out.println("tilespecialty of magma tile was called");
		//hanlder.getField().getEntityManager().getPlayer().hurt(hanlder.getField().getEntityManager().getPlayer().getEnergy() + 1)
	}
}

