package tilegame.graphics;

import tilegame.Handler;
import tilegame.entities.Entity;

public class PlayerLocator {

	private float xOffset;
	private float yOffset;
	private Handler handler;

	public PlayerLocator (Handler handler, float xOffset, float yOffset) {

		this.handler= handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void centerOn(Entity player) {

		xOffset = player.getX() + (float) (0.5 * player.getWidth());
		yOffset = player.getY() + (float) (0.5 * player.getHeight());
	}

	public void move ( float xAmt, float yAmt) {

		xOffset += xAmt;
		yOffset += yAmt;
	}


	//GETTERS SETTERS (function of getters and setters described in handler class)
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}