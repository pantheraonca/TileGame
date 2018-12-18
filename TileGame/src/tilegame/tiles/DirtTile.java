package tilegame.tiles;

import tilegame.graphics.Assets;

public class DirtTile extends Tile {

	public DirtTile(int id) {
		super(Assets.dirt, id);
	}
	
	@Override //just shows that this method overrides another one
	public boolean isSpecial() {
		return true;
	}

	public void tileSpecialty() {
		System.out.println("tilespecialty of dirt tile was called");
		//hanlder.getField().getEntityManager().getPlayer().hurt(2);
	}
}
