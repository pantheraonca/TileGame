package tilegame.tiles;

import tilegame.graphics.Assets;
//this class allows for construction of shadow tiles it takes the cropped image from the assets class and assigns it to the shadow tile object
public class ShadowTile extends Tile {

	public ShadowTile(int id) {
		super(Assets.shadow, id);
	}
}



