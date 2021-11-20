import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
import acm.program.GraphicsProgram;
import acm.graphics.GOval;
import acm.graphics.GPoint;

public class Level extends GraphicsProgram implements KeyListener{
	private static final int DELAY_MS = 20;
	
	//TODO: Change these values below to match the bounds of the playable margin of the screen
	public static final int LEVEL_BOUNDS_BOTTOM = 600;
	public static final int LEVEL_BOUNDS_RIGHT = 600;
	public static final int LEVEL_BOUNDS_TOP = 0;
	public static final int LEVEL_BOUNDS_LEFT = 0;
	
	private ArrayList<Enemy> enemies = new ArrayList<> ();
	private ArrayList<Projectile> allBullets = new ArrayList<>();
	
	private playerShip player;
	private Projectile newBullet;
	private Timer uniTimer = new Timer(DELAY_MS, this);;
	
	//Using variable to store when timer is called
	private int shootDelay;

	
	//if we need playerShip as a component of the ArrayList
	//rewrite the code to iterate through the list looking for instance of playerShip
	private boolean isPaused = false;
	
	//Level Constructor
	public Level(ArrayList<Enemy> enemies, playerShip player) {
		this.player = player;
		this.enemies = enemies;
		
		initLevel();
	}
	
	/**level constructor (for Luke to play around with PlayerShip)
	 * 
	 * @return
	 */
	Level(playerShip player){
		enemies.add(new Enemy(new GPoint(500, 100), EntityType.SHOOTER));
		this.player = player;
	}
	
	/**Called inside the Level constructor. Adds playerShip and all enemies to the screen, activates
	 * KeyListeners, then starts the game timer.
	 */
	private void initLevel() {
		addKeyListeners(new TAdapter());
		add(player.getRect());
		for(int i = 0; i < enemies.size(); i++) {
			add(enemies.get(i).getRect());
		}
		
		uniTimer.start();
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
		Projectile t = new Projectile(new GPoint(200, 200), player.getRect());
		initLevel();
		
		uniTimer.start();
	}    
	
	//Console Functions
	
	public void pause() {
		
	}
	
	void play() {
		
	}
	
	/**This helper function is used by the timer to move every single projectile once. 
	 * This is done by iterating through the allBullets ArrayList
	 * 
	 */
	public void moveAllProjectiles() {
		 for(int i = 0; i < allBullets.size(); i++) {
			 if(allBullets.get(i) != null)
				 allBullets.get(i).operateProjectile();
		 }
	 }
	
	
	/**This helper function is used by the timer to control the playerShip based on the
	 * current keyboard inputs
	 */
	public void controlPlayer() {
		player.operatePlayer();
		float fireAngle = player.getFiringAngle();
		if(fireAngle != -1 && player.canShoot()) {
			newBullet = player.shootProjectile(newBullet, fireAngle);
			add(newBullet.getOval());
			allBullets.add(newBullet);
		}
		
	}
	
	/**This helper function is used inside the timer to control a single Enemy inside 
	 * the enemies ArrayList
	 * @param enemy the current enemy you are controlling
	 */
	public void controlEnemy(Enemy enemy) {
		float towardsPlayer = Logic.getAngle(enemy.getRect(), player.getRect()); //calculates angle towards playerShip
		enemy.operateEnemy(towardsPlayer);
		if(enemy.canShoot()) {
			newBullet = enemy.shootProjectile(newBullet, towardsPlayer);
			allBullets.add(newBullet);
			add(newBullet.getOval());
			System.out.println(towardsPlayer);
		}
	}
	
	//Listeners
	/** As long as the game isn't paused, This is all of what the clock is going to execute once the
	 *  timer is triggered
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		moveAllProjectiles();
		
		//System.out.println("Timer ticked");
		controlPlayer();
			for(int i = 0; i < enemies.size(); i++) {
				if(enemies.get(i) != null) {
					controlEnemy(enemies.get(i));
				}
			}
			
	}
	
	/**This class was taken from an outside source. It overrides the KeyListeners of your choosing.
	 * In our case, we are overriding Level's KeyListener methods with the ones inside PlayerShip
	 * 
	 * @author lukeb
	 *
	 */
	private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
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
