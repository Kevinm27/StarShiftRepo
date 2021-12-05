import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class musicAndSFX {

    static Clip clip;
    static boolean muteSFX;
    static long backgroundSongclipTimePosition = 0;
    public static boolean pause = false;
    public static File song = new File("Media/song1.wav");
 

    musicAndSFX() {}

    static void playMusic () {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(song));
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            //Thread.sleep(Audio.getMicrosecondLength()/1000);
        }
        catch (Exception e) {}
    }
    
    static void playSFX (File SelectedAudio) {
        try {
        	if(muteSFX == false) {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(SelectedAudio));
                clip.start();
                
        	}
        }
        catch (Exception e) {}
    }

    static void pauseMusic () {
       if(pause == false){
    	   pause = true;
    	   backgroundSongclipTimePosition = clip.getMicrosecondPosition();
    	   clip.stop();
       }
    }
    static void resumeMusic(){
    	if(pause == true){
    		pause = false;
    		
    		try {
    			clip = AudioSystem.getClip();
    			clip.open(AudioSystem.getAudioInputStream(song));
    			System.out.print(backgroundSongclipTimePosition);
        		clip.setMicrosecondPosition(backgroundSongclipTimePosition);
        		clip.start();
            }
            catch (Exception e) {}
    	}
    }
    

    public static void main (String [] args) {
    	
    	musicAndSFX.playMusic();
		Scanner myObj = new Scanner(System.in);

    	System.out.print("Enter 1 if you want to pause: ");
        int num = myObj.nextInt();
        if(num == 1) {
        	musicAndSFX.pauseMusic();
        }
        
        
        
        num = myObj.nextInt();
        if(num == 1) {
        	musicAndSFX.resumeMusic();

        }
        
        num = myObj.nextInt();
        if(num == 1) {
        	musicAndSFX.pauseMusic();
        }
        
    	
    }

}