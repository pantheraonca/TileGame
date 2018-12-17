package tilegame.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame frame; //this is the window itself??
	private Canvas canvas; //this is all the graphics

	private String title;
	private int width, height;//private because other classes do not have to access to them

	public Display(String title, int width, int height) { //this is the constructor
		this.title = title;
		this.width = width; 
		this.height = height; //this. so the class variables can be the same name as the parameters

		createDisplay(); //why this??
	}

	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //so game closes along with window
		frame.setResizable(false); //not able to resize window
		frame.setLocationRelativeTo(null); //starts up in the center of the screen
		frame.setVisible(true); //window is visible		

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height)); //makes sure canvas is always the size of our frame
		canvas.setFocusable(false); //what is this, JFrame is only thing that can have focus??

		frame.add(canvas);
		frame.pack(); //so you see all the canvas 

	}

	public Canvas getCanvas() { //getter method so we can access canvas from outside the class
		return canvas;
	}

	public JFrame getFrame() { //same here
		return frame;
	}
}
