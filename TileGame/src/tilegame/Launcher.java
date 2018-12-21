package tilegame;

public class Launcher {

	//MAIN METHOD instantiates a game object and calls its run method, this starts up the game.
	public static void main(String[] args) { 
		
		Game game = new Game("BE SQUARE OR DON'T BE THERE", 640, 640); 
		game.run();
	}
}
