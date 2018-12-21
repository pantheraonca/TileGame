package tilegame.display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;


// A display object combines a JFrame and a Canvas and sets them to the same size.
public class Display {

	// VARIABLES
	private JFrame frame; 
	/* JFrame object instantiated by the game object which passes along displayWidth, 
	 * displayHeight and a game title. The JFrame is the window that opens when you start the application. */

	private Canvas canvas; 
	/* Represents a blank rectangular area of the screen onto which the application can draw. 
	 * displayWidth and displayHeight specified in the game object are passed to it. */

	private String title;
	private int width, height;

	// CONSTRUCTOR
	public Display(String title, int width, int height) { //this is the constructor
		this.title = title;
		this.width = width; 
		this.height = height; //this. so the class variables can be the same name as the parameters

		createDisplay();
	}


	/* instantiates the JFrame and the Canvas and sets the JFrame so that it is not re-sizable,
	 * is in the center of the screen and closable by pressing the x button of the window. */
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);		

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);

		frame.add(canvas);
		// adds the Canvas to the JFrame. 

		frame.pack(); 
		// causes the window to be sized to fit the preferred size and layouts of its subcomponents
	}


	// GETTERS SETTERS (function of getters and setters described in handler class)
	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return frame;
	}
}