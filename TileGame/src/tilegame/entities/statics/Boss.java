package tilegame.entities.statics;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import tilegame.Game;
import tilegame.Handler;
import tilegame.graphics.Assets;
import tilegame.states.State;
import tilegame.tiles.Tile;


//The Boss class contains a constructor an update a render and a die method. It extends the EntityClass.//
public class Boss extends StaticEntity {

	//The Boss constructor takes in a Handler object, and the x and y position of the boss entity.
	public Boss(Handler handler, int xOfBoss, int yOfBoss) {
		super(handler, xOfBoss, yOfBoss , Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
	}


	@Override
	public void update() {
	}	


	/* The render method of the boss is called by the render method of the EntityManager
	 * The render method of the boss calls the drawImage method of the graphics object 
	 * and passes it the corresponding image from the assets class, as well as the coordinates
	 * at which the boss has to be drawn on the canvas object.*/
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.boss, (int) (xOfEntity), (int) (yOfEntity), entityWidth, entityHeight, null);
	}


	/* This method overrides the die method of its superclasses. The die method specifies what 
	 * happens when the boss is defeated. The Boss can only die if the player has a score higher than 190,
	 * otherwise a pop-up message "Your Score is to low!" will pop up and the Boss will respawn at the 
	 * other side of the field. After this respawn the boss will respawn
	 * at the same position if the player attacks the boss again with a score lower than 190.
	 * If the player attacks the boss with a score bigger than 190 The score variable is increased by 100, a pop-up message
	 * "You won!" is generated, the Score is checked to see if a new highscore has been set. 
	 * It sets the current score back to zero, closes the current window, sets the Game to a new Game
	 * so everything is set back to when the game first started and calls the run method so a new window opens. */
	@Override
	public void die() {
		if((State.getYourScore()) >= 190) {
			JOptionPane.showMessageDialog(null, "You won!", null, 1);
			State.setYourScore(xOfEntity = State.getYourScore() + 100);
			//handler.getMouseInputManager().setUIManager(handler.getUIManager()); //setting this back to the UiManager is important to the buttons can be pressed again 
			State.setState(handler.getGame().menuState); //goes back to menu state
			State.CheckScore(); //checks if new high-score has to be set
			State.setYourScore(0);
			handler.getGame().getDisplay().getFrame().dispose();
			handler.setGame(new Game("BE SQUARE OR DON'T BE THERE", 640, 640));
			handler.getGame().run();
		}
		else {
			handler.getField().getEntityManager().addEntity(new Boss(handler, 288, 0));	
			JOptionPane.showMessageDialog(null, "Your Score is to low!", null, 1);
		}
	}
}