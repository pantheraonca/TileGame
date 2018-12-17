package tilegame.entities.statics;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import tilegame.Handler;
import tilegame.graphics.Assets;
import tilegame.states.State;
import tilegame.tiles.Tile;

public class Grail extends StaticEntity {

	public Grail(Handler handler, int x, int y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

	}

	@Override
	public void update() {
		
	}	
	
	@Override
	public void die() {
		JOptionPane.showMessageDialog(null, "You won!", null, 1);
		State.setYourScore(x = State.getYourScore() + 100);
		handler.getMouseInputManager().setUIManager(handler.getUIManager()); //setting this back to the UiManager is important to the buttons can be pressed again 
		State.setState(handler.getGame().menuState); //goes back to menu state but does not reset menustate so start button is pressed
		State.CheckScore(); //checks if new highscore has to be set
		State.setYourScore(0);//aber erst wenn mit highscore verglichen ist passt aber 
	}

		
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.grail, (int) (x), (int) (y), width, height, null);
		
	}
}
