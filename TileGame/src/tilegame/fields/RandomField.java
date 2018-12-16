/*package tilegame.fields;

import java.util.Random;

public class RandomField {

	String identities = "0";
	String randomString = "";
	int length = 800;
	private int [] randomArray;
	
	public RandomField() {

	Random r = new Random();

	char[] text = new char[length];{

		for(int i = 0; i < length; i++) {
			if ((i+2)%2 == 1) {	
				text[i] = ' ';					
			}									
			else {								
				
			text[i] = identities.charAt(r.nextInt(identities.length()));
			}
		}

		for(int i = 0; i < text.length; i++) {
			randomString += text[i];
		}
	}
	
	String[] randomArray = randomString.split("\\s+");
	}

	public int[] getRandomArray() {
		return randomArray;
	}

	public void setRandomArray(int[] randomArray) {
		this.randomArray = randomArray;
	}
}*/
