import javax.sound.sampled.*;
import java.io.*;

public class MusicPlayer {
	private Clip clip;
	
	public MusicPlayer(String fileName) {
		
	}
	
	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}
