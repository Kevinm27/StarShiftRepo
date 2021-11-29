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
	private int timeCounter = 0;
	private int secondCounter = 0;
	
	//TODO: Change these values below to match the bounds of the playable margin of the screen
	public static final int LEVEL_BOUNDS_BOTTOM = 600;
	public static final int LEVEL_BOUNDS_RIGHT = 600;
	public static final int LEVEL_BOUNDS_TOP = 0;
	public static final int LEVEL_BOUNDS_LEFT = 0;
	
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<Projectile> allBullets = new ArrayList<>();
	
	private playerShip player;
	private PlayerHealthBar playerHP;
	private Projectile newBullet;
	private Timer uniTimer = new Timer(DELAY_MS, this);
	private EnemySpawner enemySpawner;
	
	//Using variable to store when timer is called
	
	//if we need playerShip as a component of the ArrayList
	//rewrite the code to iterate through the list looking for instance of playerShip
	private boolean isPaused = false;
	
	//Level Constructor
	public Level(ArrayList<Enemy> enemies, playerShip player) {
		this.player = player;
		//this.enemies = enemies;
		
		initLevel();
	}
	
	/**level constructor (for Luke to play around with PlayerShip)
	 * 
	 * @return
	 */
	Level(playerShip player){
		//enemies.add(new Enemy(50, 200, EntityType.SHOOTER, new GPoint(500, 100)));
		this.player = player;
	}
	
	/**Called inside the Level constructor. Adds playerShip and all enemies to the screen, activates
	 * KeyListeners, then starts the game timer.
	 */
	private void initLevel() {
		addKeyListeners(new TAdapter());
		add(player.getRect());
		//for(int i = 0; i < enemies.size(); i++) {
		//	add(enemies.get(i).getRect());
		//}
		initHP();
		uniTimer.start();
	}
	
	/**Helper function to help initialize the player's health bar.
	 * 
	 */
	private void initHP() {
		playerHP = new PlayerHealthBar(new GPoint(30, 630), 100, 20, player.getHealth());
		add(playerHP.getHpBack());
		add(playerHP.getCurHealthBar());
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
		initLevel();
		
		enemySpawner = new EnemySpawner(timeCounter, player.getPlayerLocation(), LEVEL_BOUNDS_BOTTOM, LEVEL_BOUNDS_RIGHT);
		
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
				else if(allBullets.get(i).getFriendly() != player.getFriendly()) { //sees if the bullet is able to collide w/ player
					if(Logic.isCollided(allBullets.get(i).getOval(), player.getRect())) {
						damagePlayer(allBullets.get(i).getDamage());
						remove(allBullets.get(i).getOval());
						allBullets.remove(i);
					}
				}
				else { //projectiles now check if they can collide with enemies
					for(int j = 0; j < enemies.size(); j++) {
						if(enemies.get(j) != null) {
							if(allBullets.get(i).getFriendly() != enemies.get(j).getFriendly()) { //checks if the current enemy is on the opposing team of the bullet
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
	
	private void damagePlayer(int damage) {
		player.setHealth(player.getHealth() - damage);
		playerHP.modifyHealthBar(player.getHealth());
		if(isLevelLost()) { //checks if player has died
			//TODO: make this if statement trigger some sort of game over function or screen
			uniTimer.stop();
			remove(player.getRect());
			
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
					//TODO: what is going to happen when the player collides with an enemy? currently the player is damaged by 100
					damagePlayer(100);
					remove(enemies.get(i).getRect());
					enemies.remove(enemies.get(i));
					
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
		
		if(timeCounter % 30 == 0 && secondCounter % 20 == 0) {
			enemySpawner.setTime(timeCounter);
			enemySpawner.setPlayerLocation(player.getPlayerLocation());
			enemies = enemySpawner.spawnEnemies();
			
			System.out.print(enemies.size());
			
			for(Enemy enemy:enemies) {
				add(enemy.getRect());
			}
		}
		
		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i) != null) {
				controlEnemy(enemies.get(i));
			}
		}
		
		secondCounter++;
		if(secondCounter % 20 == 0) {
			secondCounter = 0;
			timeCounter++;
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
		setSize(800, 800);
	}
	
	public static void main(String args[]) {
		new Level(new playerShip(5, 300, EntityType.PLAYER, new GPoint(200, 200))).start();
	}
}




