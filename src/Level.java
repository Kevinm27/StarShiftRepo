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
	 * @return true if all enemies are dead
	 */
	boolean isLevelWon() {
		int numEnemies = 0;
		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i) != null)
				numEnemies++;
		}
		if (numEnemies < 1) {
			System.out.println("You won!");
			return true;
		}
		else 
			return false;
	}
	
	/**Level lost check
	 * 
	 * @return true if the player has less than 1 health
	 */
	boolean isLevelLost() {
		if(player.getHealth() < 1) {
			System.out.println("Game over");
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
	
	/**pauses the game once the P key is pressed. Basically just stops the game timer
	 * 
	 */
	public void pause() {
		System.out.println("Game paused");
		uniTimer.stop();
		isPaused = true;
	}
	
	/**Starts the game after being paused
	 * 
	 */
	void play() {
		uniTimer.start();
		isPaused = false;
	}
	
	/**This helper function is used by the timer to move every single projectile once. 
	 * This is done by iterating through the allBullets ArrayList. It also checks if a
	 * projectile is colliding with a ship of the opposite team.
	 */
	public void moveAllProjectiles() {
		for(int i = 0; i < allBullets.size(); i++) {
			if(allBullets.get(i) != null)
				if(allBullets.get(i).operateProjectile()) { //if the bullet collides with a wall it is deleted
					remove(allBullets.get(i).getOval());
					allBullets.remove(i);
				}
				//projectiles check if they can collide with the player
				else if(allBullets.get(i).getFriendly() != player.isFriendly()) { //sees if the bullet is able to collide w/ player
					if(Logic.isCollided(allBullets.get(i).getOval(), player.getRect())) {
						player.setHealth(player.getHealth() - allBullets.get(i).getDamage());
						if(isLevelLost()) { //checks if player has died
							//TODO: make this if statement trigger some sort of game over function
							remove(player.getRect());
							
						}
						remove(allBullets.get(i).getOval());
						allBullets.remove(i);
					}
				}
				else { //projectiles now check if they can collide with enemies
					for(int j = 0; j < enemies.size(); j++) {
						if(enemies.get(j) != null) {
							if(allBullets.get(i).getFriendly() != enemies.get(j).isFriendly()) { //checks if the current enemy is on the opposing team of the bullet
								if(Logic.isCollided(allBullets.get(i).getOval(), enemies.get(j).getRect())) { //checks if the enemy and projectile are colliding
									enemies.get(j).setHealth(enemies.get(j).getHealth() - allBullets.get(i).getDamage());
									if(enemies.get(j).isDead()) {
										remove(enemies.get(j).getRect());
										enemies.remove(j);
									}
									remove(allBullets.get(i).getOval());
									allBullets.remove(i);
								}
							}
						}
						 
					}
				}
		 }
	 }
	
	
	/**This helper function is used by the timer to control the playerShip based on the
	 * current keyboard inputs
	 */
	public void controlPlayer() {
		player.operatePlayer(); //executes movement for timer and triggers curFireTime
		
		float fireAngle = player.getFiringAngle(); //checks the angle the player is trying to shoot in
		if(fireAngle != -1 && player.canShoot()) {
			newBullet = player.shootProjectile(newBullet, fireAngle);
			add(newBullet.getOval());
			allBullets.add(newBullet);
		}
		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i) != null) {
				if(Logic.isCollided(player.getRect(), enemies.get(i).getRect())){
					//TODO: what is going to happen when the player collides with an enemy?
				}
			}
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
            int key = e.getKeyCode();
			if (key == KeyEvent.VK_P) {
				if(isPaused == false) {
					pause();
				}
				else {
					play();
				}
			}
        }
        
        @Override
        public void keyTyped(KeyEvent e) {
        	//TODO: doesn't work
		//Pauses the Game
				//Will check if the player hit the Escape Key, pausing the game accordingly
				
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_P) {
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
    }
	
	public void init() {
		setSize(800, 600);
	}
	
	public static void main(String args[]) {
		
		new Level(new playerShip(new GPoint(200, 200))).start();
	}
}
