
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
//import javax.swing.JOptionPane;
import java.io.File;
import java.util.Scanner;

public class Music {
	private static Clip c;
	static long clipTimePosition = 0;
	static boolean pause = false;
	 public static void main(String[] args) {
			Scanner myObj = new Scanner(System.in);
	        String songName = "02 FIRE.wav";
	        Music song = new Music(songName);
	        
	        System.out.print("Enter 1 if you want to pause: ");
	        int num = myObj.nextInt();
	        if(num == 1) {
	        	pauseMusic();
	        }
	        
	        System.out.print("Enter 0 if you want to pause: ");
	        num = myObj.nextInt();
	        if(num == 0) {
	        	playMusic();
	        }
	        
	        while(true) {
	        	System.out.println("Hopefully its playing");
	        }
	        
	      
	    }
	 //@Author someone on stackoverflow for the code inside the try catch
	 public Music(String song) {
		 String path = new File("").getAbsolutePath() + "\\media\\" + song;
		 File sound = new File(path);

	        try {
	            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
	            c = AudioSystem.getClip();
	            c.open(ais); //Clip opens AudioInputStream
	            FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
	            gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
	            c.start(); //Start playing audio
	            c.loop(Clip.LOOP_CONTINUOUSLY);
	            

	           // JOptionPane.showMessageDialog(null, "Press OK to stop playing");	//Basically just shows a text box
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        } 
	 }
	 public static void pauseMusic() {

		 if(pause == false) {
			 pause = true;
			 clipTimePosition = c.getMicrosecondPosition();
			 c.stop();
		 }
		 
	 }
	 public static void playMusic() {

		 if(pause == true) {
			 pause = false;
			 c.setMicrosecondPosition(clipTimePosition);
			 c.start();
		 }
	 }
	 public void loop() {
		 c.loop(Clip.LOOP_CONTINUOUSLY);
	 }

}
