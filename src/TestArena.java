import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.KeyListener;
import java.security.KeyStore.TrustedCertificateEntry;
import java.util.ArrayList;

import javax.print.attribute.standard.Media;

// should be a blank white console where a Level can run and be tested
// Constructor added to Level - construct 5 levels inside here; plug list in here; access levels to play from here
// this class should have a level, sets up the level (# entities)
// get a 19 20 by 10 80 window to pop up on screen
// or 800 600



public class TestArena extends GraphicsProgram implements KeyListener {
	
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;	
	Level myLevel;
	
	public static void main(String[] args) {
		new TestArena().start();
	}

	public void run() {												
		myLevel = new Level(new playerShip(5, 300, EntityType.PLAYER, new GPoint(200, 200)));
		add(myLevel.getPlayer().getRect());
		//Projectile bullet1 = new Projectile(new GPoint(100,50), (float)315);
		//add(bullet1.getOval());
		
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
		
	}
	
}
