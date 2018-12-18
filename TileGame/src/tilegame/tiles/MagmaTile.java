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
}

