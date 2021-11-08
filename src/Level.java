import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level {

	private ArrayList<ourEntity> enemies = new ArrayList<> ();
	private playerShip player;
	//if we need playerShip as a component of the ArrayList
	//rewrite the code to iterate through the list looking for instance of playerShip
	private boolean isPaused;
	private boolean levelWon;
	private int pauseCount = 0;
	
	//Level Win Check
	
	boolean isLevelWon() {
		if (enemies.size() < 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	boolean isLevelLost(playerShip player) {
		if(player.getHealth() < 1) {
			return true;
		}
		return false;
	}
	
	//Console Functions
	
	void pause() {
		
	}
	
	void play() {
		
	}
	
	//Listeners
	
	void KeyPressed(KeyEvent e) {
		//moves ship
		//will check the key pressed and update the player's position accordingly
		
		char key = e.getKeyChar();
		//if this fails, we may need to take in a string and check for key combinations
		
		if (key == 'w') {
			//move player up
			player.moveY(1);
		}
		
		if (key == 'a') {
			//move player left
			player.moveX(-1);
		}
		
		if (key == 's') {
			//move player down
			player.moveY(-1);
		}
		
		if (key == 'd') {
			//move player right
			player.moveX(1);
		}
		
	}
	
	void KeyReleased(KeyEvent e) {
		//stops ship
		//will check when the key is released and stop moving the player
		
		char key = e.getKeyChar();
		//if this fails, we may need to take in a string and check for key combinations
		
		if (key == 'w') {
			//stop move up
		}
		
		if (key == 'a') {
			//stop left down
		}
		
		if (key == 's') {
			//stop move down
		}
		
		if (key == 'd') {
			//stop move right
		}
		
	}
	
	void KeyTyped(KeyEvent e) {
		//pauses game
		//will check if the player hit the escape key and pause the game 
		
		int id = e.getID();
		
		if (id == 27) {
			pauseCount++;
			if(pauseCount % 2 != 0) {
				pause();
			}
			else {
				play();
			}
		}
		
	}
	
}
