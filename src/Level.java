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
