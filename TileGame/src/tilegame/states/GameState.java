package tilegame.states;

import java.awt.Color;
import java.awt.Graphics;
import tilegame.Handler;
import tilegame.fields.Field;
import tilegame.fields.Shadow;

public class GameState extends State {

	//VARIABLES
	private Field field;
	private Shadow shadow;

	//CONSTRUCTOR
	public GameState(Handler handler) {
		super(handler);
		field = new Field(handler);
		handler.setField(field);
		shadow = new Shadow(handler, handler.getGame(), 20, 20); //MARIAN in shadow class noch size automatisch anpassen??
	}
	
	@Override
	public void update() {
		field.update();
		shadow.update();
	}

	@Override
	public void render(Graphics g) {

		field.render(g);
		//shadow.render(g);

		if(State.getHighScore().equals("")) {
			State.setHighScore(this.ReadHighScore());
		}
		
		g.setColor(Color.white);
		g.drawString("Highscore: " + State.getHighScore(), 10, 15);
		g.drawString("Your score: " + State.getYourScore(), 10, 30);
		g.drawString("Energy" + handler.getField().getEntityManager().getPlayer().getEnergy(), 10, 45);
	}

	//GETTERS SETTERS
	
	public Shadow getShadow() {
		return shadow;
	}
	public void setShadow(Shadow shadow) {
		this.shadow = shadow;
	}
}
