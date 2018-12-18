package tilegame.entities.statics;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import tilegame.Handler;
import tilegame.graphics.Assets;
import tilegame.states.State;
import tilegame.tiles.Tile;

public class Boss extends StaticEntity {

	public Boss(Handler handler, int x, int y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

	}

	@Override
	public void update() {

	}	

	@Override
	public void die() {
		if((State.getYourScore()) >= 190) {
		JOptionPane.showMessageDialog(null, "You won!", null, 1);
		State.setYourScore(x = State.getYourScore() + 100); //check if this is true!!! (gives you 100 points when the grail dies)
		handler.getMouseInputManager().setUIManager(handler.getUIManager()); //setting this back to the UiManager is important to the buttons can be pressed again 
		State.setState(handler.getGame().menuState); //goes back to menu state but does not reset menustate so start button is pressed
		State.CheckScore(); //checks if new highscore has to be set
		State.setYourScore(0);//aber erst wenn mit highscore verglichen ist passt aber 
		}
		else {
			handler.getField().getEntityManager().addEntity(new Boss(handler, 288, 0));	
			JOptionPane.showMessageDialog(null, "Your Score is to low!", null, 1);
		}
	}
		


	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.boss, (int) (x), (int) (y), width, height, null);

	}
}
