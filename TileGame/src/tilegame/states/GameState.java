package tilegame.states;

import java.awt.Graphics;
import tilegame.Handler;
import tilegame.fields.Field;
import tilegame.fields.Shadow;

public class GameState extends State {
	
	
	private Field field;
	private Shadow shadow;
	
	
	public GameState(Handler handler) { //constructor
		super(handler);
		field = new Field(handler, null);
		handler.setField(field);
		shadow = new Shadow(handler, handler.getGame(), 20, 20); //in shadow class noch size automatisch anpassen
		
		
	
	}
	@Override //what is this don't have to have them
	public void update() {
		field.update();
		shadow.update();
		
	}

	@Override
	public void render(Graphics g) {

		field.render(g);
		shadow.render(g);
		
		if(State.getHighScore().equals("")) {
			State.setHighScore(this.ReadHighScore());
		}
		g.drawString("Highscore: " + State.getHighScore(), 32, 32);
		g.drawString("Your score: " + State.getYourScore(), 32, 64);

		
	}
	
	public Shadow getShadow() {
		return shadow;
	}
	public void setShadow(Shadow shadow) {
		this.shadow = shadow;
	}

}
