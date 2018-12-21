package tilegame.states;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import tilegame.Handler;


// This class is to be extended by the different Classes corresponding to a instantiatable state of the game
public abstract class State { 

	
	private static State currentState = null;
	// This variable is set to null and can be set to the currenState run in the game by the setState method.
	
	
	//this method sets the currenState variable
	public static void setState(State state) {
		currentState = state;
	}
	
	
	//this method sets the currenState variable
	public static State getState() {
		return currentState;
	}

	
	protected Handler handler;
	
	private static String highScore = "";
	// this string variable the stored highScore will be passed to
	
	private static int yourScore = 0; //things need to be global if you want to access them from anywhere
	//this is the score variable updated while the game is running which must be compaed against the highscore 

	//CONSTRUCTOR
	public State(Handler handler) {
		this.handler = handler;
	}

	public abstract void update();
	
	
	public abstract void render(Graphics g);
	

	// this method reads the file which contains the highScore data and returns it as a string
	public String ReadHighScore() {
		FileReader readFile =null; //read file
		BufferedReader reader = null;//extract what we need from the file

		try {
			readFile = new FileReader("highscore.dat");
			reader = new BufferedReader(readFile);
			return reader.readLine(); //read first line of file which should contain highScore
		}
		catch (Exception e) {
			return "noname:0";
		}
		finally {
			try {
				if (reader != null)
					reader.close();
			}
			catch (IOException e) {
				e.printStackTrace();	
			}
		}
	}

	//this method checks if the stored highScore equals nothing, if yes it returns. This prevents errors. If the highScore equals sth. then it will
	//check if the current score is lower than the new YourScore by splitting the highScore string. If yes it sets the highScore to the new score 
	//creates a newFile and writes the new highScore to it.
	public static void CheckScore() {
		if (highScore.equals(""))
			return; //

		if (yourScore > Integer.parseInt(highScore.split(":")[1])) { //converts the part that contains the highScore string to an integer
			//user has set new highScore
			String name = JOptionPane.showInputDialog("New Highscore. Enter name:");
			highScore = name + ":" + yourScore;

			File scoreFile = new File("highscore.dat");
			if(!scoreFile.exists()) { //using file variable to check if the file exists
				try {
					scoreFile.createNewFile();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			
			FileWriter writeFile = null; //creating file writer stores the file to write to
			BufferedWriter writer = null; // allows to write to the file
			try {
				writeFile = new FileWriter(scoreFile); //
				writer = new BufferedWriter(writeFile);
				writer.write(State.highScore); //write to the file 
			}
			
			catch (Exception e) {
				//errors
			}
			
			finally {
				if(writer != null)
					try {
						writer.close(); // close the file
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
	}

	//GETTERS SETTERS
	
	public static String getHighScore() {
		return highScore;
	}
	public static void setHighScore(String highScore) {
		State.highScore = highScore;
	}
	public static int getYourScore() {
		return yourScore;
	}
	public static void setYourScore(int yourScore) {
		State.yourScore = yourScore;
	}
}