package tilegame.utils;

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;

public class Utils {

	/*public static String loadFileAsString(String path) { //returns file content
		StringBuilder builder = new StringBuilder(); //add characters to a string easily
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line; //current line we are working on
			while((line = br.readLine()) != null) //as long as line doesnt equal null(end of file) will continue to read
				builder.append(line + "\n"); //appends string with whatever the line is and also adds a new line character to the string for every line
			
			br.close();//close filestream ???
		}catch(IOException e) {
			e.printStackTrace(); //prints error to screen
		}
		System.out.println(builder.toString()); //just to see what this returns 
		return builder.toString(); //converts everything we added to the builder object to a string
	}*/	
	
	public static int parseInt(String number) { //void if doesnt return anything int if returns int etc.
		try { //this method converts string into integers
			return Integer.parseInt(number);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;//return 0 so program does not crash and it can continue
		}
	}
}
