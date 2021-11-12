import acm.graphics.*;


// should be a blank white console where a Level can run and be tested
// Constructor added to Level - construct 5 levels inside here; plug list in here; access levels to play from here
// this class should have a level, sets up the level (# entities)
// get a 19 20 by 10 80 window to pop up on screen
// or 800 600

public class TestArena extends Level {
	
	Level myLevel = new Level();
	
	public static void main(String[] args) {
		new TestArena().run();
		
	}

	public void run() {												// checking that myLevel is accessible
		Level theLevel = new Level();
		System.out.println(theLevel.getPlayerShip().getHealth());
		System.out.println(myLevel.isLevelWon());
	}
	
}
