import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class musicAndSFX {

    static Clip clip;
    static Clip music;
    static boolean muteSFX;
    static long backgroundSongclipTimePosition = 0;
    public static boolean pause = false;
    public static File song = new File("Media/song1.wav");
 

    public musicAndSFX () {
        try {
            music = AudioSystem.getClip();
            music.open(AudioSystem.getAudioInputStream(song));
            music.start();
            music.loop(Clip.LOOP_CONTINUOUSLY);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
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
                FloatControl gainControl = (FloatControl) 
                clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-20.0f);
        	}
        }
        catch (Exception e) {}
    }

    static void pauseMusic () {
       if(pause == false){
    	   pause = true;
    	   backgroundSongclipTimePosition = music.getMicrosecondPosition();
    	   music.stop();
       }
    }
    static void resumeMusic(){
    	if(pause == true){
    		pause = false;
    		
    		try {
    			music = AudioSystem.getClip();
    			music.open(AudioSystem.getAudioInputStream(song));
    			FloatControl gainControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-20.0f);        		
                music.setMicrosecondPosition(backgroundSongclipTimePosition);
        		music.start();
            }
            catch (Exception e) {}
    	}
    }
    

    public static void main (String [] args) {
    	
    	//musicAndSFX.playMusic();
		/*Scanner myObj = new Scanner(System.in);

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
        
    	*/
    }

}