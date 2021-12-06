import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import acm.graphics.GImage;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.graphics.GLabel;

public class Level extends GraphicsPane implements KeyListener, ActionListener{
	
	//************************************* Variables *************************************//
	private MainApplication program;
	
	private static final int DELAY_MS = 20;
	private int timeCounter = 0;
	private int secondCounter = 0;
	
	//Change these values below to match the bounds of the playable margin of the screen
	public static final int LEVEL_BOUNDS_BOTTOM = 500;
	public static final int LEVEL_BOUNDS_RIGHT = 800;
	public static final int LEVEL_BOUNDS_TOP = 0;
	public static final int LEVEL_BOUNDS_LEFT = 0;
	
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<Projectile> allBullets = new ArrayList<>();
	
	private Score score;
	private playerShip player;
	private PlayerHealthBar playerHP;
	private Projectile newBullet;
	private Timer uniTimer;
	private EnemySpawner enemySpawner;
	private GRect playArea;		//outlines the playable margin of the screen in black
	private GRect backDrop;
	private GImage background = new GImage("media/background.jpg");
	private GLabel pauseLabel = new GLabel("Paused", LEVEL_BOUNDS_RIGHT / 2 - 55, LEVEL_BOUNDS_BOTTOM / 2);
	private GLabel gameOverLabel = new GLabel("Game Over", LEVEL_BOUNDS_RIGHT / 2 -55, LEVEL_BOUNDS_BOTTOM / 2);
	
	private boolean isPaused = false;
	private boolean isInfinite = true;
	File bullet = new File("Media/longBulletSFX.wav");
	File damage = new File("Media/takingDamageSFX.wav");
	File giveDamage = new File("Media/takingDamageSFX.wav"); // find new audio?
	File enemyBullet = new File("Media/shortBulletSFX.wav");
	
	//************************************* Constructors *************************************//
	
	/**level constructor (for designed levels)
	 * 
	 * @return
	 */
	public Level(ArrayList<Enemy> enemies, playerShip player, boolean infinite) {
		this.player = player;
		this.enemies = enemies;
		isInfinite = infinite;
		
		initLevel();
	}
	public Level(MainApplication app, playerShip player, ArrayList<Enemy> enemies) {
		this.player = player;
		this.enemies = enemies;
		isInfinite = false;
		program = app;
		initLevel();
	}
	
	/**level constructor (for infinite levels)
	 * 
	 * @return
	 */
	Level(MainApplication app, playerShip player){
		super();
		program = app;
		this.player = player;
	}
	
	//************************************* Setter & Getters *************************************//
	public playerShip getPlayer() {
		return player;
	}

	public void setPlayer(playerShip player) {
		this.player = player;
	}
	
	//************************************* Functions *************************************//
	
	/**Level Win Check
	 * Will only ever be used for the designed levels
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
			//System.out.println("You won!");
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
			return true;
		}
		return false;
	}
	
	/**pauses the game once the P or Esc key is pressed. Basically just stops the game timer
	 * 
	 */
	public void pause() {
		//System.out.println("Game paused");
		pauseLabel.setVisible(true);
		uniTimer.stop();
		isPaused = true;
	}
	
	/**Starts the game after being paused
	 * 
	 */
	void play() {
		pauseLabel.setVisible(false);
		uniTimer.start();
		isPaused = false;
	}
	
	/**This function is used by the timer to move every single projectile once. 
	 * This is done by iterating through the allBullets ArrayList. It also checks if a
	 * projectile is colliding with a ship of the opposite team.
	 */
	public void moveAllProjectiles() {
		for(int i = 0; i < allBullets.size(); i++) {
			if(allBullets.get(i) != null) {
				if(allBullets.get(i).operateProjectile()) { //if the bullet collides with a wall it is deleted
					program.remove(allBullets.get(i).getOval());
					allBullets.remove(i);
				}
				//projectiles check if they can collide with the player
				else if(allBullets.get(i).getFriendly() != player.getFriendly()) { //sees if the bullet is able to collide w/ player
					if(Logic.isCollided(allBullets.get(i).getOval(), player.getImage())) {
						musicAndSFX.playSFX(damage);
						damagePlayer(allBullets.get(i).getDamage());
						program.remove(allBullets.get(i).getOval());
						allBullets.remove(i);
					}
				}
				else { //projectiles now check if they can collide with enemies
					for(int j = 0; j < enemies.size(); j++) {
						if(enemies.get(j) != null) {
							//used try-catch to catch the index error we were getting for projectiles
							try {
								if(allBullets.get(i).getFriendly() != enemies.get(j).getFriendly()) { //checks if the current enemy is on the opposing team of the bullet
									if(Logic.isCollided(allBullets.get(i).getOval(), enemies.get(j).getImage())) { //checks if the enemy and projectile are colliding
										enemies.get(j).setHealth(enemies.get(j).getHealth() - allBullets.get(i).getDamage());
										if(enemies.get(j).isDead()) {
											musicAndSFX.playSFX(giveDamage);
											program.remove(enemies.get(j).getImage());
											enemies.remove(j);
											score.updateScore(10);
										}
										program.remove(allBullets.get(i).getOval());
										allBullets.remove(i);
									}
								}
							}
							catch(Exception e) {
								//System.out.println("Index Error");
							}
						}	 
					}
				}
			}
		}
	}
	
	/**A function for damaging the player. This is mainly to help with decluttering
	 * other functions where the player may be damaged
	 * @param damage
	 */
	private void damagePlayer(int damage) {
		player.setHealth(player.getHealth() - damage);
		if(player.getHealth() < 0) {
			player.setHealth(0);
		}
		playerHP.modifyHealthBar(player.getHealth());
	}
	
	/**This function is used by the timer to control the playerShip based on the
	 * current keyboard inputs
	 */
	public void controlPlayer() {
		player.operatePlayer(); //executes movement for timer and triggers curFireTime
		
		float fireAngle = player.getFiringAngle(); //checks the angle the player is trying to shoot in
		
		if(fireAngle != -1 && player.canShoot()) {
			musicAndSFX.playSFX(bullet);
			newBullet = player.shootProjectile(newBullet, fireAngle);
			program.add(newBullet.getOval());
			allBullets.add(newBullet);
		}
		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i) != null) {
				if(Logic.isCollidedEnemy(player.getImage(), enemies.get(i).getImage())){
					//When the player collides with an enemy currently the player is damaged by 100
					musicAndSFX.playSFX(damage);
					damagePlayer(200);
					program.remove(enemies.get(i).getImage());
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
		float towardsPlayer = Logic.getAngle(enemy.getImage(), player.getImage()); //calculates angle towards playerShip
		enemy.operateEnemy(towardsPlayer);
		if(enemy.canShoot()) {
			musicAndSFX.playSFX(enemyBullet);
			newBullet = enemy.shootProjectile(newBullet, towardsPlayer);
			allBullets.add(newBullet);
			program.add(newBullet.getOval());
		}
	}
	
	//Listeners
	/** As long as the game isn't paused, This is all of what the clock is going to execute once the
	 *  timer is triggered
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(isLevelLost()) { //checks if player has died
			uniTimer.stop();
			gameOverLabel.sendToFront();
			gameOverLabel.setVisible(true);
			
			program.switchToGameOver();
		}
		
		moveAllProjectiles();
		
		controlPlayer();
		
		if(secondCounter % 50 == 0) {
			
			if(isInfinite  == true) {
	            if(timeCounter % 5 == 3) {
	                enemySpawner.setTime(timeCounter);
	                enemySpawner.setPlayerLocation(player.getPlayerLocation());
	                
	                ArrayList<Enemy> temp = enemySpawner.spawnEnemies();
	                for(Enemy t:temp) {
	                  enemies.add(t);
	                  program.add(enemies.get(enemies.indexOf(t)).getImage());
	                }
	                
	               // System.out.println(enemies.size());
	            }
			}
            //resetting secondCounter because every 50 passes through
            //actionPerformed is 1 second so just resetting back to 0
            secondCounter = 0;
            timeCounter++;
            score.controlComboTimer();
        }
        secondCounter++;
		
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
        	if (key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_P) {
        		if(isPaused == false) {
					pause();
				}
				else {
					play();
				}
			}
        }
	}
	
	/**Called inside the Level constructor. Adds playerShip and all enemies to the screen, activates
	 * KeyListeners, then starts the game timer.
	 */
	private void initLevel() {
		Score.resetScore();
		program.addKeyListeners(new TAdapter());
		playArea = new GRect(LEVEL_BOUNDS_LEFT, LEVEL_BOUNDS_TOP, LEVEL_BOUNDS_RIGHT, LEVEL_BOUNDS_BOTTOM);
		playArea.setLineWidth(2);
		playArea.setColor(Color.red);		
		background.sendBackward();
		player.setImage(ShipCustomPane.shipColor);
		background.setSize(LEVEL_BOUNDS_RIGHT, LEVEL_BOUNDS_BOTTOM);	// x,y		// Scales the background down to playArea
		backDrop = new GRect(0, 0, 800, 600);
		backDrop.setFilled(true);
		program.add(backDrop);
		program.add(background);
		program.add(playArea);
		program.add(player.getImage());
		initHUD();
		
		//Initializes EnemySpawner
		enemySpawner = new EnemySpawner(timeCounter, player.getPlayerLocation(), LEVEL_BOUNDS_BOTTOM, LEVEL_BOUNDS_RIGHT);
	}
	
	/**Function to initialize the player's health bar.
	 * 
	 */
	private void initHUD() {
		program.setSize(800, 600);
		playerHP = new PlayerHealthBar(new GPoint(30, 530), 100, 20, player.getHealth());
		pauseLabel.setVisible(false);
		pauseLabel.setColor(Color.white);
		pauseLabel.setFont("Roboto-30");
		program.add(pauseLabel);
		gameOverLabel.setVisible(false);
		gameOverLabel.setColor(Color.white);
		gameOverLabel.setFont("Roboto-30");
		program.add(gameOverLabel);
		program.add(playerHP.getHealthText());
		program.add(playerHP.getHpBack());
		program.add(playerHP.getCurHealthBar());
		score = new Score(new GPoint(500, 530), 15);
		program.add(score.getText());
		program.add(score.getComboText());
		
	}
	
	public void run() {		
		initLevel();
		
		uniTimer.start();
	}
	
	public void init() {
		//setSize(800, 600);
	}
	
	//reset clears the screen and removes everything
	public void reset() {
		if(enemies.size() != 0) {
			for(Enemy rE:enemies) {
				program.remove(rE.getImage());
			}
			enemies.clear();
		}
		
		if(allBullets.size() != 0) {
			for(Projectile p:allBullets) {
				program.remove(p.getOval());
			}
			allBullets.clear();
		}
		
		if(isLevelLost() == true) {
			program.remove(backDrop);
			program.remove(background);
			program.remove(playArea);
			program.remove(player.getImage());
			program.remove(pauseLabel);
			program.remove(gameOverLabel);
			program.remove(playerHP.getHealthText());
			program.remove(playerHP.getHpBack());
			program.remove(playerHP.getCurHealthBar());
			program.remove(score.getText());
			program.remove(score.getComboText());
		}
		
		timeCounter = 0;
		player.setHealth(1000);
	}
	
	@Override
	public void showContents() {
		reset();
		initLevel();
		
		uniTimer = new Timer(DELAY_MS, this);
		uniTimer.start();
	}
	
	@Override
	public void hideContents() {
		reset();
	}
	
	public static void main(String args[]) {
		//musicAndSFX.playMusic();
		//new Level(new playerShip(8, 1000, EntityType.PLAYER, new GPoint(200, 200))).start();
	}
}




