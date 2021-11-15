import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

//import java.util.Timer;

import acm.graphics.GImage;
import acm.graphics.GRect;

public class playerShip extends ourEntity implements ActionListener{
	
	private static final int DELAY_MS = 25;
	
	//TODO: Change these values to match the bounds of the playable margin of the screen
	private static final int BOARD_BOUNDS_BOTTOM = 600;
	private static final int BOARD_BOUNDS_RIGHT = 600;
	private static final int BOARD_BOUNDS_TOP = 0;
	private static final int BOARD_BOUNDS_LEFT = 0;

	
	private int fireDelay = 100;
	private boolean canMove = true;
	private boolean canShoot = true;
	private Timer moveTimer; //this timer is a cooldown for movement
	private Timer shotTimer; //this timer is a cooldown for shooting
	private Projectile newBullet; 
	playerShip() {
		//player = new ourEntity(EntityType.PLAYER);
		health = 300;
		speed = 3;
		friendly = true;
		rect = new GRect(20, 20, 200, 200);
		type = EntityType.PLAYER;
		
		moveTimer = new Timer(DELAY_MS, this);
		shotTimer = new Timer(DELAY_MS, this);
	}

	@Override
	  public void actionPerformed(ActionEvent e) {
		  canMove = true;
		  canShoot = true;
		  moveTimer.stop();
		  shotTimer.stop();
	  }
	
	
	/**Checks if the ship is within the bounds of the level, and pushes the ship into place if it is not
	 * 
	 * @return true & does nothing if the ship was already in bounds
	 * @return false if the ship was adjusted
	 */
	private boolean isInBounds () {
		//if the ship is too far to the right
		if (entityLocation.getX() > BOARD_BOUNDS_RIGHT - rect.getWidth()) {
			rect.setLocation(BOARD_BOUNDS_RIGHT - rect.getWidth(), entityLocation.getY());
			return false;
		}
		//if the ship is too far to the left
		else if (entityLocation.getX() < BOARD_BOUNDS_LEFT) {
			rect.setLocation(BOARD_BOUNDS_LEFT, entityLocation.getY());
			return false;
		}
		//if the ship is below the board
		else if(entityLocation.getY() > BOARD_BOUNDS_BOTTOM - rect.getHeight()){
			rect.setLocation(entityLocation.getX(), BOARD_BOUNDS_BOTTOM - rect.getHeight());
			return false;
		}
		//if the ship is above the board
		else if(entityLocation.getY() < BOARD_BOUNDS_TOP){
			rect.setLocation(entityLocation.getX(), BOARD_BOUNDS_TOP);
			return false;
		}
		//if everything is fine
		else {
			return true;
		}
	}
	
	/**
	 * 
	 * @param angle the angle at which you are moving the ship
	 * @return false & does nothing if the move timer has not reset
	 */
	public boolean movePolar(float angle) {
		if(canMove == false) { //checks if enough time has passed since the last move
			return false; //returns false if not enough time has passed
		}
		else {
			moveTimer.start(); //starts movement cooldown timer
			rect.movePolar(speed, angle);
			entityLocation = new Locations(rect.getX(),rect.getY());
			
			if(!isInBounds()) { //runs isInBounds and corrects entityLocation to sit within bounds of board if needed
				entityLocation = new Locations(rect.getX(),rect.getY());
			}
			canMove = false; //set canMove to false so it cannot be immediately called again
			return true;
		}
	}
	/**
	 * 
	 * @param angle the angle at which the player wants to fire the projectile
	 * @return true if a projectile has been successfully fired
	 * @return false if the firing cooldown has not finished
	 */
	public boolean shootPolar(float angle) {
		//shoots a projectile based on the angle input to the function
		if(canShoot == false) {
			return false;
		}
		else {
			shotTimer.start();
			newBullet = new Projectile(entityLocation, angle);
			bullets.add(newBullet);
			canShoot = false;
			return true;
		}
	}
	
	/* none of this code is important right now
	boolean moveX() {
		//Updates the Position of the Player's Ship with respect to the X Axis
		//Will need a check for if the Player is in bounds, can do in here or in level (discuss)
		if(canMove == false) {
			return false;
		}
		else {
			moveTimer.start();
			int distMX = getEntityLocation().getX() + x;
			playerLocation = new Locations(distMX ,getEntityLocation().getY());
			setEntityLocation(playerLocation);
			canMove = false;
			return true;
		}
		
		
	}
	
	boolean moveY(int y) {
		//Updates the Position of the Player's Ship with respect to the Y Axis
		//Will need a check for if the Player is in bounds, can do in here or in level (discuss)
		
		int distMY = player.getEntityLocation().getY() + y;
		playerLocation = new Locations(player.getEntityLocation().getX(), distMY);
		player.setEntityLocation(playerLocation);
		return true;
	}
	
	void shootX(int x) {
		//Updates the Position of the Player's Bullets with respect to the X Axis
		
		int bullX = bullet.getProjectileLocation().getX() + x;
		bullet.setProjectileLocation(bullX, bullet.getProjectileLocation().getY());
		
	}
	
	void shootY(int y) {
		//Updates the Position of the Player's Bullets with respect to the Y Axis
		
		int bullY = bullet.getProjectileLocation().getY() + y;
		bullet.setProjectileLocation(bullet.getProjectileLocation().getX(), bullY);
		
	}
	*/
}
