package tilegame.tiles;

import tilegame.graphics.Assets;

//this class allows for construction of grass tiles it takes the cropped image from the assets class and assigns it to the grass tile object
public class GrassTile extends Tile {

	public GrassTile(int id) {
		super(Assets.grass, id);
	}
}