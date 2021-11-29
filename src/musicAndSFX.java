
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
//import javax.swing.JOptionPane;
import java.io.File;
import java.util.Scanner;

public class musicAndSFX{
	//two clips, one is static which represents the one song
	//the other clip is so we can make objects for the various sfx
	private static Clip c;
	private Clip sfxClip;
	private static long clipTimePosition = 0;	//clip time position just remembers when the song paused
	public static boolean pause = false;
	public static boolean mute = false;
	public static void main(String[] args) {
			Scanner myObj = new Scanner(System.in);
	        musicAndSFX song = new musicAndSFX();
	        String sfx = "shortBulletSFX.wav";
	        String sfx1 = "longBulletSFX.wav";
	        musicAndSFX sound = new musicAndSFX(sfx);
	        musicAndSFX sound1 = new musicAndSFX(sfx1);
	        
	        playMusic();
	        System.out.print("Enter 1 if you want to pause: ");
	        int num = myObj.nextInt();
	        if(num == 1) {
	        	pauseMusic();
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
	
	 //Default constructor plays the song with no arguments
	 public musicAndSFX() {
		 String path = new File("").getAbsolutePath() + "\\media\\song1.wav";
		 File sound = new File(path);
	        try {
	            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
	            c = AudioSystem.getClip();
	            c.open(ais); //Clip opens AudioInputStream
	            c.start(); //Start playing audio
	            c.loop(Clip.LOOP_CONTINUOUSLY);

	            
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        } 
	 }
	 //Seconday constructor takes in string to play initialize certain sfx
	 public musicAndSFX(String sfxName) {
		 String path = new File("").getAbsolutePath() + "\\media\\" + sfxName;
		 File sound = new File(path);
	        try {
	            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
	            this.sfxClip = AudioSystem.getClip();
	            this.sfxClip.open(ais); //Clip opens AudioInputStream
	        } 
	        catch (Exception e) {
	            System.out.println(e.getMessage());
	        } 
	 }
	 /*
	  * Wont need to lower the volume so i just commented these out for now
	 public void lowerSFXVolume(float num) {
	     FloatControl gainControl = (FloatControl) this.sfxClip.getControl(FloatControl.Type.MASTER_GAIN);
	     gainControl.setValue(10.0f); // Reduce volume by 10 decibels.
	 }
	 public static void lowerMusicVolum(){
	  	 FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
	     gainControl.setValue(10.0f); // Reduce volume by 10 decibels.
	 }
	 */
	 //Static since we can just call the function and pause the song without an object
	 //This only works if we call the default cosntructor first
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
	 
	 public void playSFX() {
		 if(mute == false) {
			 c.setMicrosecondPosition(0);
			 c.start();
		 }
	 }
	 //This will mute all the sfx since all sfx objects need to check the static variable mute first
	 public static void muteSFX() {
		 mute = true;
	 }
	 public static void unmuteSFX() {
		 mute = false;
	 }

}
