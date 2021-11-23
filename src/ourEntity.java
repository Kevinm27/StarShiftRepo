/**
 * @author MeganA
 * This class will be the parent class to all the entities in the game (enemies and player).
 * Objects of this class can move, fire projectiles, and take damage. This class will create the framework 
 * for the game's Enemy ships and the player's playerShip. It will hold an array list of Projectiles 
 * that can access projectile objects.
 */

import java.util.ArrayList;
import acm.graphics.GImage;
import acm.graphics.GRect;
import acm.graphics.GPoint;

public class ourEntity {
	//************************************* private variables *************************************//	
	protected int fireDelay;
	protected int curFireTime = 0;
	protected EntityType eType;
	protected boolean canMove = true;
	protected boolean canShoot = true; 							//prevents enemies that cannot fire from firing
	protected int health;
	protected int speed;
	protected boolean friendly;									// IsFriendly should be here instead of logic
	protected GImage image;
	protected Projectile newBullet; 							//used for creating/firing projectiles
	
	
	//************************************* public variables *************************************//
	public ArrayList<Projectile> bullets = new ArrayList<Projectile>();	
	public EntityType type = null;								// Entity Type needs to be defined when object is made
	public static final String IMG_FILENAME_PATH = "ships/";
	public static final String IMG_EXTENSION = ".png";
	public GRect rect; //placeholder for image
	
	//************************************* constructor *************************************//
	public ourEntity(int fD, int life, EntityType eT) {
		fireDelay = fD;
		health = life;
		eType = eT;
		switch(eT) {
			case PLAYER:
				speed = 3;
				friendly = true;
				break;
			case SCOOTER:
				speed = 4;
				friendly = false;
				break;
			case SHOOTER:
				speed = 2;
				friendly = false;
				break;
		}
	}
	
	ourEntity(EntityType type){
		if(type == EntityType.PLAYER) {
			health = 300;
			speed = 3;
			friendly = true;
			rect = new GRect(50, 50, 200, 200);
		}
		else {
			this.type = type;
			image = new GImage("milleniumFalcon.png", 200, 200);
		}
	}
	
	//************************************* Setter & Getters *************************************//
	void setEntityLocation(GPoint location) {					// check for legal location elsewhere (?) Could be in logic
		rect.setLocation(location);
	}
	void setHealth(int hp) {
		this.health = hp;
	}
	void setSpeed(int howFast) {
		this.speed = howFast;
	}
	void setIsFriendly(boolean isFriendly) {
		this.friendly = isFriendly; 
	}
	void setEntityType(EntityType type) {
		this.type = type;
	}
	void setImage(EntityType shipType) {						// help with graphics
		this.image.setImage(IMG_FILENAME_PATH + shipType + IMG_EXTENSION);		// PLEASE MAKE IMAGE NAMES SAME AS EntityTypes
	}
	
	GPoint getEntityLocation() {
		return new GPoint(rect.getX(), rect.getY());
	}
	int getHealth() {
		return this.health;
	}
	int getSpeed() {
		return this.speed;
	}
	boolean getFriendly() {
		return this.friendly;
	}
	GImage getImage() {
		return this.image; 	
	}
	GRect getRect() {
		return this.rect;
	}
	Projectile getNewBullet() {
		return this.newBullet;
	}
	ArrayList<Projectile> getBulletArrayList() {//Added getter for the array list
		return bullets;
	}
	
	//************************************* Functions *************************************//	
	/**This function iterates through all of the Projectiles inside of the bullets ArrayList and
	 * moves them all once.
	 */
	public void operateProjectiles() {
		for(int i = 0; i < bullets.size(); i++) {
			if(bullets.get(i) != null)
				bullets.get(i).operateProjectile();
		}
	}
	
	public boolean canShoot() {
		return (curFireTime >= fireDelay && canShoot);
	}
	public boolean isDead() {
		return health < 1;
	}
	
	/**Creates a copy of the ship that moves out ahead of the ship image to find the ship's 
	 * new position after movePolar. Using this new position, the method checks if the new position
	 * for the ship would be within the bounds of the level. If it is outside, the ship's position is
	 * adjusted. The location of the ship after moving is returned.
	 * 
	 * @param angle the direction you're going to move your ship
	 * @return the next location of the ship
	 */
	protected GPoint moveWithinBounds(float angle) {
		GRect nextPosition = rect;
		nextPosition.movePolar(speed, angle);
	
		//if the ship is too far to the right
		if (nextPosition.getX() > Level.LEVEL_BOUNDS_RIGHT - nextPosition.getWidth()) {
			nextPosition.setLocation(Level.LEVEL_BOUNDS_RIGHT - nextPosition.getWidth(), nextPosition.getY());
		}
		//if the ship is too far to the left
		else if (nextPosition.getX() < Level.LEVEL_BOUNDS_LEFT) {
			nextPosition.setLocation(Level.LEVEL_BOUNDS_LEFT, nextPosition.getY());
		}
		//if the ship is below the board
		if(nextPosition.getY() > Level.LEVEL_BOUNDS_BOTTOM - nextPosition.getHeight()){
			nextPosition.setLocation(nextPosition.getX(), Level.LEVEL_BOUNDS_BOTTOM - nextPosition.getHeight());
		}
		//if the ship is above the board
		else if(nextPosition.getY() < Level.LEVEL_BOUNDS_TOP){
			nextPosition.setLocation(nextPosition.getX(), Level.LEVEL_BOUNDS_TOP);
		}
		return new GPoint(nextPosition.getX(), nextPosition.getY());
	}
	
	/**
	 * 
	 * @param angle the angle at which you are moving the ship
	 * @return false & does nothing if the move timer has not reset
	 */
	protected boolean movePolar(float angle) {
		if(canMove == false) { //checks if enough time has passed since the last move
			return false; //returns false if not enough time has passed
		}
		else {
			//moves the ship to a position within the bounds of the screen
			rect.setLocation(moveWithinBounds(angle));//runs isInBounds and corrects entityLocation to sit within bounds of board if needed
				
			return true;
		}
	}
	
	/**Fires a projectile in the direction of parameter angle. Also adds this new Projectile to the 
	 * ArrayList bullets.
	 * 
	 * @param angle the angle at which the player wants to fire the projectile
	 * @return true if a projectile has been successfully fired
	 * @return false if the firing cooldown has not finished
	 */
	protected boolean shootPolar(float angle) {
		//shoots a projectile based on the angle input to the function
		if(canShoot) {
			newBullet = new Projectile(new GPoint(rect.getX() + (rect.getWidth() / 2), 
					rect.getY() + (rect.getHeight() / 2)), angle, friendly);
			//bullets.add(newBullet);
			curFireTime = 0;
			return true;
		}
		else {
			return false;
		}
	}
	
	public Projectile shootProjectile(Projectile bullet, float angle) {
		bullet = new Projectile(new GPoint(rect.getX() + (rect.getWidth() / 2), 
				rect.getY() + (rect.getHeight() / 2)), angle, friendly);
		curFireTime = 0;
		return bullet;
	}
}




