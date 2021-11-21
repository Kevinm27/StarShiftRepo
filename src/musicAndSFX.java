
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
//import javax.swing.JOptionPane;
import java.io.File;
import java.util.Scanner;

public class musicAndSFX{
	private Clip c;
	private long clipTimePosition = 0;
	public static boolean pause = false;
	public static boolean mute = false;
	 public static void main(String[] args) {
			Scanner myObj = new Scanner(System.in);
	        String songName = "02 FIRE.wav";
	        musicAndSFX song = new musicAndSFX(songName);
	        String sfx = "shortBulletSFX.wav";
	        String sfx1 = "longBulletSFX.wav";
	        musicAndSFX sound = new musicAndSFX(sfx);
	        musicAndSFX sound1 = new musicAndSFX(sfx1);
	        
	        song.playMusic();
	        System.out.print("Enter 1 if you want to pause: ");
	        int num = myObj.nextInt();
	        if(num == 1) {
	        	song.pauseMusic();
	        }
	        
	        
	        System.out.print("Enter 1 if you want to play the sfx: ");
	        num = myObj.nextInt();
	        if(num == 1) {
	        	sound.playSFX();
	        	sound1.playSFX();
	        }
	        muteSFX();
	        System.out.print("Enter 0 if you want to play the sfx: ");
	        num = myObj.nextInt();
	        if(num == 0) {
	        	sound.playSFX();
	        	sound1.playSFX();
	        }
	        
	        unmuteSFX();
	        System.out.print("Enter 1 if you want to play the sfx: ");
	        num = myObj.nextInt();
	        if(num == 1) {
	        	sound.playSFX();
	        	sound1.playSFX();
	        }
	        while(true) {
	        	
	        }
	        
	      
	    }
	 //@Author someone on stackoverflow for the code inside the try catch
	 public musicAndSFX(String song) {
		 String path = new File("").getAbsolutePath() + "\\media\\" + song;
		 File sound = new File(path);
	        try {
	            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
	           this.c = AudioSystem.getClip();
	            this.c.open(ais); //Clip opens AudioInputStream

	            //c.start(); //Start playing audio
	            

	           //JOptionPane.showMessageDialog(null, "Press OK to stop playing");	//Basically just shows a text box
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        } 
	 }
	 public void lowerVolume(float num) {
	     FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
	     gainControl.setValue(10.0f); // Reduce volume by 10 decibels.
	 }


	 public void pauseMusic() {

		 if(pause == false) {
			 pause = true;
			 clipTimePosition = c.getMicrosecondPosition();
			 c.stop();
		 }
		 
	 }
	 public void playMusic() {

		 if(pause == true) {
			 pause = false;
			 c.setMicrosecondPosition(clipTimePosition);
			 c.start();
		 }
	 }
	 
	 public void playSFX() {
		 if(mute == false) {
			 c.setMicrosecondPosition(0);
			 c.start();
		 }
	 }
	 
	 public static void muteSFX() {
		 mute = true;
	 }
	 public static void unmuteSFX() {
		 mute = false;
	 }
	 public void loop() {
		 c.loop(Clip.LOOP_CONTINUOUSLY);
	 }

}
