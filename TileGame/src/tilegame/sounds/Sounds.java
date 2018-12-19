package tilegame.sounds;

public class Sounds {

	public static final Sounds[] sound = {
			new Sound("sound1.wav"),
			new Sound("sound2.wav")
	};

	private AudioClip clip;

	private Sound(String name) {
		clip = Applet.newAudioClip(getClass().getClassLoader().getResource(name));
	}

	public void play() {
		new Thread() {
			public void run() {
				clip.play();
			}
		}.start();
	}
}

	/*
	 * 
	 * Sound.sounds[0].play(); //use this outside to play a sound
Here is how I implement the AudioClip class:

AudioClip clip = Applet.newAudioClip(url);

Where url is the URL object that points to my sound file. You can get the URL object multiple ways, but I use this (because it always works for me):

URL url = getClass().getClassLoader().getResource("sound1.wav");

And then to play the sound, call the clip's play method on a new Thread:

new Thread() {
    public void run() {
        clip.play();
    }
}.start();

Here's an example of a Sound class you could use to play your audio clips:

public class Sound {

    public static final Sound[] sounds = {
        new Sound("sound1.wav"),
        new Sound("sound2.wav")
    };

    private AudioClip clip;

    private Sound(String name) {
        clip = Applet.newAudioClip(getClass().getClassLoader().getResource(name));
    }

    public void play() {
        new Thread() {
            public void run() {
                clip.play();
            }
        }.start();
    }

}
}
*/
