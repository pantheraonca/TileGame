package tilegame.sounds;

import java.applet.Applet;

public interface AudioClip {
	
	AudioClip clip = Applet.newAudioClip(url);

	URL url = getClass().getClassLoader().getResource("sound1.wav");
	
	new Thread() {
	    public void run() {
	        clip.play();
	    }
	}.start();
}
