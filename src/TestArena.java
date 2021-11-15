import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.security.KeyStore.TrustedCertificateEntry;
import java.util.ArrayList;

import javax.print.attribute.standard.Media;

// should be a blank white console where a Level can run and be tested
// Constructor added to Level - construct 5 levels inside here; plug list in here; access levels to play from here
// this class should have a level, sets up the level (# entities)
// get a 19 20 by 10 80 window to pop up on screen
// or 800 600



public class TestArena extends GraphicsProgram {
	
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;	
	playerShip thePlayer;
	Locations thePlayerStart;
	ArrayList<Enemy> theEnemies;
	ArrayList<Locations> theEnemyStart;
	Level myLevel = new Level(theEnemies, theEnemyStart, thePlayer, thePlayerStart);
	
	public static void main(String[] args) {
		new TestArena().start();
	}

	public void run() {												// checking that myLevel and theLevel is accessible
		Level theLevel = new Level();
		System.out.println("For theLevel: declared as a local variable: ");
		System.out.println(theLevel.getPlayerShip().getHealth());
		System.out.println(theLevel.isLevelWon());
		
		System.out.println("For myLevel: declared as a member variable: ");
		System.out.println(myLevel.getPlayerShip().getHealth());
		System.out.println(myLevel.isLevelWon());
		
		System.out.println("TESTING MOVE:");
		System.out.println("current x of ship: " + myLevel.getPlayerShip().getEntityLocation().getX());
		myLevel.getPlayerShip().moveX(12);
		System.out.println("should move 50 right");
		System.out.println("new x of ship: " + myLevel.getPlayerShip().getEntityLocation().getX());		// null pointer exception
		
		
		
		visualArena();
		
	}
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		requestFocus();
	}
	
	public void visualArena() {
		
		GRect box = new GRect(19, 20, 100, 100);
		box.setFilled(true);
		box.setColor(Color.gray);
		add(box);
		
		GLabel text = new GLabel("TESTING", 12, 12);
		add(text);
		
		//add(myLevel.getPlayerShip().getImage()); // gives error
		
		
	}
	
}
