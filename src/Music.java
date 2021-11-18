
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;
import java.io.File;

public class Music {
	private static Clip c;
	//All of a sudden it doesn't wanna play
	 public static void main(String[] args) {
	        //This gets the path to the project, but not into /src for eclipse
	        String path = new File("").getAbsolutePath() + "\\media\\Road Runners.wav";
	        //Make a File object with a path to the audio file.
	        File sound = new File(path);

	        try {
	            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
	            c = AudioSystem.getClip();
	            c.open(ais); //Clip opens AudioInputStream
	            FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
	            gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
	            c.start(); //Start playing audio
	            c.loop(Clip.LOOP_CONTINUOUSLY);


	            JOptionPane.showMessageDialog(null, "Press OK to stop playing");
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }

	        
	        
	    }

}
