package tilegame.userinterface;

//Interface that together with the mouseInputManager enables mouse input
// and will pass on its method to subclasses, is implemented in the buttonclasses wher the onclick method is overwritten
public interface ClickListener {

	public void onClick(); 
}
