import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
import acm.program.GraphicsProgram;
import acm.graphics.GPoint;

public class Level extends GraphicsProgram implements KeyListener{
	private static final int DELAY_MS = 20;
	
	private ArrayList<Enemy> enemies = new ArrayList<> ();
	private playerShip player;
	private Projectile newBullet;
	private Timer gameTimer = new Timer(DELAY_MS, this);
	
	//if we need playerShip as a component of the ArrayList
	//rewrite the code to iterate through the list looking for instance of playerShip
	private boolean isPaused = false;
	private boolean wKeyDown = false;
	private boolean aKeyDown = false;
	private boolean sKeyDown = false;
	private boolean dKeyDown = false;
	private boolean upKeyDown = false;
	private boolean leftKeyDown = false;
	private boolean downKeyDown = false;
	private boolean rightKeyDown = false;
	
	//Level Constructor
	public Level(ArrayList<Enemy> enemies, playerShip player, GPoint playerStartLocation) {
		player.setEntityLocation(playerStartLocation);
		
		for(int i = 0; i < enemies.size(); i++) {
			add(enemies.get(i).getRect());
		}
		add(player.getRect());
		gameTimer.start();
	}
	
	/**level constructor (for Luke to play around with PlayerShip)
	 * 
	 * @return
	 */
	Level(playerShip player){
		this.player = player;
		gameTimer.start();
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
	
	public void pause() {
		
	}
	
	
	void play() {
		
	}
	
	
	
	
	//Listeners
	
	/** This is all of what the clock is going to execute once the timer is triggered
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.print(e.getKeyChar());
			
			if (key == KeyEvent.VK_W) {
				//move player up
				wKeyDown = true;
				if(aKeyDown)
					player.move(135);
				else if(dKeyDown)
					player.move(45);
				else
					player.move(90);
			}
		
			if (key == KeyEvent.VK_A) {
				//move player left
				aKeyDown = true;
				if(wKeyDown)
					player.move(135);
				else if(sKeyDown)
					player.move(225);
				else
					player.move(180);
			}
		
			if (key == KeyEvent.VK_S) {
				//move player down
				sKeyDown = true;
				if(aKeyDown)
					player.move(225);
				else if(dKeyDown)
					player.move(315);
				else
					player.move(270);

			}
		
			if (key == KeyEvent.VK_D) {
				//move player right
				dKeyDown = true;
				if(wKeyDown)
					player.move(45);
				else if(sKeyDown)
					player.move(315);
				else
					player.move(0);
			}
			
			/*Bullet Movers
			 *Moves the Player's Bullets
			 *Will check the ID of the key pressed to check if it corresponds to Arrow Keys
			 *Arrow Key ID: Left:37, Up:38, Right:39, Down:40
			*/
			
			if (key == KeyEvent.VK_UP) {
				//shoot up
				upKeyDown = true;
				if(leftKeyDown)
					player.shoot(135);
				else if(rightKeyDown)
					player.shoot(45);
				else {
					player.shoot(90);
				}
				newBullet = player.getNewBullet();
				add(newBullet.getOval());
			}
			
			if (key == KeyEvent.VK_LEFT) {
				//shoot left
				leftKeyDown = true;
				if(downKeyDown)
					player.shoot(225);
				else if(upKeyDown)
					player.shoot(135);
				else {
					player.shoot(180);
				}
				newBullet = player.getNewBullet();
				add(newBullet.getOval());
			}
			
			if (key == KeyEvent.VK_DOWN) {
				//shoot down
				downKeyDown = true;
				if(leftKeyDown)
					player.shoot(225);
				else if(rightKeyDown)
					player.shoot(315);
				else {
					player.shoot(270);
				}
				newBullet = player.getNewBullet();
				add(newBullet.getOval());
			}
			
			if (key == KeyEvent.VK_RIGHT) {
				//shoot right
				rightKeyDown = true;
				if(downKeyDown)
					player.shoot(315);
				else if(upKeyDown)
					player.shoot(45);
				else {
					player.shoot(0);
				}
				
				newBullet = player.getNewBullet();
				add(newBullet.getOval());
			}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		System.out.print(e.getKeyChar());
		//Move Stoppers
		//Stops the Movement of the Player's Ship
		//Will check the Char of the Key Released to check if it corresponds to WASD Keys 
		//If this fails, we may need to take in a string and check for key combinations
		if (key == KeyEvent.VK_W) {
			//move player up
			wKeyDown = false;
		}
	
		if (key == KeyEvent.VK_A) {
			//move player left
			aKeyDown = false;

		}
	
		if (key == KeyEvent.VK_S) {
			//move player down
			sKeyDown = false;

		}
	
		if (key == KeyEvent.VK_D) {
			//move player right
			dKeyDown = false;

		}
		
		/*Bullet Movers
		 *Moves the Player's Bullets
		 *Will check the ID of the key pressed to check if it corresponds to Arrow Keys
		 *Arrow Key ID: Left:37, Up:38, Right:39, Down:40
		*/
		
		if (key == KeyEvent.VK_UP) {
			//shoot up
			upKeyDown = false;
		}
		
		if (key == KeyEvent.VK_LEFT) {
			//shoot left
			leftKeyDown = false;
		}
		
		if (key == KeyEvent.VK_DOWN) {
			//shoot down
			downKeyDown = false;
		}
		
		if (key == KeyEvent.VK_RIGHT) {
			//shoot right
			rightKeyDown = false;
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
