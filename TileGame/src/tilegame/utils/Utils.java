package tilegame.utils;

public class Utils {

	public static int parseInt(String number) { //void if doesn't return anything int if returns int etc.
		try { //this method converts string into integers
			return Integer.parseInt(number);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;//return 0 so program does not crash and it can continue
		}
	}
}
