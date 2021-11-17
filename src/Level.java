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
	
	
	public void run() {
		addKeyListeners();
		float temp = 315;
		Projectile bullet1 = new Projectile(new GPoint(100, 50), temp);
		Projectile t = new Projectile(new GPoint(200, 200), player.getRect());
		
		add(player.getRect());
		
	}    
	
	
	
	//Console Functions
	
	void pause() {
		
	}
	
	
	void play() {
		
	}
	
	//Listeners
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		int id = e.getID();
		System.out.println(e.getKeyChar());
			
			if (key == KeyEvent.VK_W) {
				//move player up
				player.move(90);
				System.out.println("W key pressed.");
			}
		
			if (key == KeyEvent.VK_A) {
				//move player left
				player.move(180);
				System.out.println("A key pressed.");

			}
		
			if (key == KeyEvent.VK_S) {
				//move player down
				player.move(270);
				System.out.println("S key pressed.");

			}
		
			if (key == KeyEvent.VK_D) {
				//move player right
				player.move(0);
				System.out.println("D key pressed.");

			}
			
			/*Bullet Movers
			 *Moves the Player's Bullets
			 *Will check the ID of the key pressed to check if it corresponds to Arrow Keys
			 *Arrow Key ID: Left:37, Up:38, Right:39, Down:40
			*/
			
			if (key == KeyEvent.VK_UP) {
				//shoot up
				player.shoot(90);
			}
			
			if (key == KeyEvent.VK_LEFT) {
				//shoot left
				player.shoot(180);
			}
			
			if (key == KeyEvent.VK_DOWN) {
				//shoot down
				player.shoot(270);
			}
			
			if (key == KeyEvent.VK_RIGHT) {
				//shoot right
				player.shoot(0);
			}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		int id = e.getID();
		
		//Move Stoppers
		//Stops the Movement of the Player's Ship
		//Will check the Char of the Key Released to check if it corresponds to WASD Keys 
		//If this fails, we may need to take in a string and check for key combinations
		if (key == KeyEvent.VK_W) {
			//move player up
		}
	
		if (key == KeyEvent.VK_A) {
			//move player left

		}
	
		if (key == KeyEvent.VK_S) {
			//move player down

		}
	
		if (key == KeyEvent.VK_D) {
			//move player right

		}
		
		/*Bullet Movers
		 *Moves the Player's Bullets
		 *Will check the ID of the key pressed to check if it corresponds to Arrow Keys
		 *Arrow Key ID: Left:37, Up:38, Right:39, Down:40
		*/
		
		if (key == KeyEvent.VK_UP) {
			//shoot up
		}
		
		if (key == KeyEvent.VK_LEFT) {
			//shoot left
		}
		
		if (key == KeyEvent.VK_DOWN) {
			//shoot down
		}
		
		if (key == KeyEvent.VK_RIGHT) {
			//shoot right
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
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
	
	
	public void init() {
		setSize(800, 600);
	}
	
	public static void main(String args[]) {
		new Level(new playerShip(new GPoint(200, 200))).start();
	}
}
