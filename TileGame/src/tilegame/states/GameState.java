package tilegame.states;

import java.awt.Color;
import java.awt.Graphics;
import tilegame.Handler;
import tilegame.fields.Field;
import tilegame.fields.Shadow;

//this class is a subclass of the state class. It 
public class GameState extends State {

	//VARIABLES
	private Field field;
	private Shadow shadow;


	//CONSTRUCTOR takes in a handler object. initiates the field and the shadow and 
	public GameState(Handler handler) {
		super(handler);
		field = new Field(handler);
		handler.setField(field);
		shadow = new Shadow(handler, handler.getGame(), 20, 20);
	}
	
	//updates the field and the shadow
	@Override
	public void update() {
		field.update();
		shadow.update();
	}

	// renders the field and the shadow. Then sets the color to white and renders 
	// the Highscore, the players current score and energy to the game state.
	public void render(Graphics g) {

		field.render(g);
		shadow.render(g);
		
		g.setColor(Color.white);
		g.drawString("Highscore: " + State.getHighScore(), 10, 15);
		g.drawString("Your score: " + State.getYourScore(), 10, 30);
		g.drawString("Energy" + handler.getField().getEntityManager().getPlayer().getEnergy(), 10, 45);
	}

	//GETTERS SETTERS (function of getters and setters described in handler class)
	public Shadow getShadow() {
		return shadow;
	}
	public void setShadow(Shadow shadow) {
		this.shadow = shadow;
	}
}
