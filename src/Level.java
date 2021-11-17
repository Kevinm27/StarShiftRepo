import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import acm.program.GraphicsProgram;
import acm.graphics.GPoint;

public class Level extends GraphicsProgram implements KeyListener{

	private ArrayList<Enemy> enemies = new ArrayList<> ();
	private playerShip player;
	

	//if we need playerShip as a component of the ArrayList
	//rewrite the code to iterate through the list looking for instance of playerShip
	private boolean isPaused = false;
	
	
	//Level Constructor
	public Level(ArrayList<Enemy> enemies, ArrayList<GPoint> enemyStartLocations, playerShip player, GPoint playerStartLocation) {
		player.setEntityLocation(playerStartLocation);
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).setEntityLocation(enemyStartLocations.get(i));
		}
	}
	
	/**level constructor (for Luke to play around with PlayerShip)
	 * 
	 * @return
	 */
	Level(playerShip player){
		this.player = player;
	}
	
	public playerShip getPlayer() {
		return player;
	}

	public void setPlayer(playerShip player) {
		this.player = player;
	}
	
	/**Level Win Check
	 * 
	 * @return
	 */
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

	public void KeyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		int id = e.getID();
		System.out.println(key);

		
		while (true) {
			
			//Ship Movers
			//Moves the Player's Ship
			//Will Check the Char of the key pressed to check if it corresponds to WASD Keys 
			//If this fails, we may need to take in a string and check for key combinations
			
			if (key == 'w') {
				//move player up
				player.movePolar(90);
			}
		
			if (key == 'a') {
				//move player left
				player.movePolar(180);
			}
		
			if (key == 's') {
				//move player down
				player.movePolar(270);
			}
		
			if (key == 'd') {
				//move player right
				player.movePolar(0);
			}
			
			
			//Bullet Movers
			//Moves the Player's Bullets
			//Will check the ID of the key pressed to check if it corresponds to Arrow Keys
			//Arrow Key ID: Left:37, Up:38, Right:39, Down:40
			
			if (id == 38) {
				//shoot up
				player.shootPolar(90);
			}
			
			if (id == 37) {
				//shoot left
				player.shootPolar(180);
			}
			
			if (id == 40) {
				//shoot down
				player.shootPolar(270);
			}
			
			if (id == 39) {
				//shoot right
				player.shootPolar(0);
			}
			
		}
	}
	
	void KeyReleased(KeyEvent e) {
		
		char key = e.getKeyChar();
		int id = e.getID();
		
		//Move Stoppers
		//Stops the Movement of the Player's Ship
		//Will check the Char of the Key Released to check if it corresponds to WASD Keys 
		//If this fails, we may need to take in a string and check for key combinations
		/*
		if (key == 'w') {
			//stop move up
			player.moveY(0);
		}
		
		if (key == 'a') {
			//stop left down
			player.moveX(0);
		}
		
		if (key == 's') {
			//stop move down
			player.moveY(0);
		}
		
		if (key == 'd') {
			//stop move right
			player.moveX(0);
		}
		
		
		//Shoot Stoppers
		//Stops the Movement of the Player's Bullets
		//Will check the ID of the Key Released to check if it corresponds to Arrow Keys
		//Arrow Key IDs: Left:37, Up:38, Right:39, Down:40
		
		if (id == 38) {
			//stop shoot up
			player.shootY(0);
		}
		
		if (id == 37) {
			//stop shoot left
			player.shootX(0);
		}
		
		if (id == 40) {
			//stop shoot down
			player.shootY(0);
		}
		
		if (id == 39) {
			//stop shoot right
			player.shootX(0);
		}
		
	
	*/
	}
	
	void KeyTyped(KeyEvent e) {
		//Pauses the Game
		//Will check if the player hit the Escape Key, pausing the game accordingly
		
		int id = e.getID();
		if (id == 27) {
			if(isPaused == false) {
				pause();
				isPaused = true;
			}
			else {
				play();
				isPaused = false;
			}
		}
		
	}
	
	
	public void run() {
		addKeyListeners();
		Projectile bullet1 = new Projectile(new Locations(100, 50), 315);
		
		add(player.getRect());
	}
	public void init() {
		setSize(800, 600);
	}
	
	public static void main(String args[]) {
		new Level(new playerShip(new Locations(200, 200))).start();
	}
}
