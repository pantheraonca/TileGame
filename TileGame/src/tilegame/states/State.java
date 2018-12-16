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

public abstract class State { //what is an abstract class

	//GAMESTATEMANAGER has nothing to do with this class
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	//CLASS
	
	
	private static String highScore = "";
	private static int yourScore = 0; //things need to be global if you want to access them from anywhere
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public String ReadHighScore() {
		//format will be:   Marian:100
		FileReader readFile =null; //read file
		BufferedReader reader = null;//extract what we need from the file
		
		try {
			readFile = new FileReader("highscore.dat");
			reader = new BufferedReader(readFile);
			return reader.readLine(); //read first line of file which should contain highscore
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
	

	public static void CheckScore() {
		if (highScore.equals(""))
			return; //if highscore equals nothing then no need to check the score this prevents errors
		
		if (yourScore > Integer.parseInt(highScore.split(":")[1])) { //converts the part that contains the highscore string to an int
			//user has set new highscore
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
