import java.util.ArrayList;
import acm.graphics.GPoint;

/**
 * ~~~~ Further clarification please: which class displays game play? can we add this to the UML?
 * 
 * Use this class as a cleaner TestArena. 
 * 1. Create levels here (use Level constructors) --- will need to be cleaned up
 * 2. Determine if levelBuild functions are easier to implement in main or not
 * 3. Display game play here?
 *
 */


public class PlayArena {
	//************************************* public variables *************************************//
	static Level TEST;	// not sure if static is necessary
	Level ONE;
	Level TWO;
	Level THREE;
	Level FOUR;
	Level FIVE;
	
	//************************************* level building *************************************//
	void levelBuildTEST(){
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();										
		Enemy a = new Enemy(2, 200, EntityType.SCOOTER, new GPoint(50,50)); 					
		enemies.add(a);
		playerShip playerONE = new playerShip(5, 500, EntityType.PLAYER, new GPoint (10,10));
		//TEST = new Level(enemies, playerONE);
	}
	static void levelStart(Level theLevel) {
		while (theLevel.isLevelLost() == false && theLevel.isLevelWon() == false) {		// constant check of win/lose conditions
			theLevel.run();
		}
	}
	
	public static void main(String args[]) {
		// check if isPlayable in Level Select
		// call respective levelBuild function - switch / if statement needed?
		levelStart(TEST);
	}
}
