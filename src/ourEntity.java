/**
 * @author MeganA
 * This class will be the parent class to all the entities in the game (enemies and player).
 * Objects of this class can move, fire projectiles, and take damage. This class will create the framework 
 * for the game's Enemy ships and the player's playerShip. It will hold an array list of Projectiles 
 * that can access projectile objects.
 */

import java.util.ArrayList;										// import the ArrayList class
import java.util.Timer;											// refer to TimerLab for use
import java.util.TimerTask;

import acm.graphics.GImage;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.graphics.GPoint;

public class ourEntity extends GraphicsProgram {
	//***** private variables *****//	
	protected static final int DELAY_MS = 20;
	//TODO: Change these values below to match the bounds of the playable margin of the screen
	protected static final int BOARD_BOUNDS_BOTTOM = 600;
	protected static final int BOARD_BOUNDS_RIGHT = 600;
	protected static final int BOARD_BOUNDS_TOP = 0;
	protected static final int BOARD_BOUNDS_LEFT = 0;

	
	protected int fireDelay;
	protected int curFireTime = 0;
	
	protected boolean canMove = true;
	protected boolean canShoot = true; //prevents enemies that cannot fire from firing
	
	protected int health;
	protected int speed;
	protected boolean friendly;									// IsFriendly should be here instead of logic
	
	protected GImage image;
	public GRect rect; //placeholder for image
	protected Projectile newBullet; //used for creating/firing projectiles

	
	
	
	
	
	//***** public variables *****//
	public ArrayList<Projectile> bullets = new ArrayList<Projectile>();	
	public EntityType type = null;								// Entity Type needs to be defined when object is made
	public static final String IMG_FILENAME_PATH = "ships/";
	public static final String IMG_EXTENSION = ".png";
	
	//***** constructor *****//
	public ourEntity() {
		GPoint entityLocation = new GPoint(0,0);					// originally on line 17
	}
	
	//**** Set Functions *****//
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
	void setEntityLocation(GPoint location) {				// check for legal location elsewhere (?) Could be in logic
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
	void setImage(EntityType shipType) {										// help with graphics
		this.image.setImage(IMG_FILENAME_PATH + shipType + IMG_EXTENSION);		// PLEASE MAKE IMAGE NAMES SAME AS EntityTypes
	}
	
	//***** Get Functions *****//
	GPoint getEntityLocation() {
		return new GPoint(rect.getX(), rect.getY());
	}
	int getHealth() {
		return this.health;
	}
	int getSpeed() {
		return this.speed;
	}
	boolean getIsFriendly() {
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
	public boolean canShoot() {
		return curFireTime >= fireDelay;
	}
	
	//***** Fundamental Functions *****//
	
	/**TimerTasks are a type of function that are called once a timer goes off. Whenever the timer 
	 * is triggered or hits a certain delay, the TimerTask run() function is executed. Specific TimerTask
	 * classes are accessed by name. This is why the shotTimer uses the ShootTask while the moveTimer uses
	 * the MoveTask
	 * 
	 * 
	 * @author lukeb
	 *
	 */
	class MoveTask extends TimerTask
	{
	    public void run() { 
	    	canMove = true;
	    }
	}
	class ShootTask extends TimerTask
	{
		public void run() {
			canShoot = true;
		}
	}
	
	/**This function iterates through all of the Projectiles inside of the bullets ArrayList and
	 * moves them all once.
	 */
	public void operateProjectiles() {
		for(int i = 0; i < bullets.size(); i++) {
			if(bullets.get(i) != null)
				bullets.get(i).operateProjectile();
		}
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
		if (nextPosition.getX() > BOARD_BOUNDS_RIGHT - nextPosition.getWidth()) {
			nextPosition.setLocation(BOARD_BOUNDS_RIGHT - nextPosition.getWidth(), nextPosition.getY());
		}
		//if the ship is too far to the left
		else if (nextPosition.getX() < BOARD_BOUNDS_LEFT) {
			nextPosition.setLocation(BOARD_BOUNDS_LEFT, nextPosition.getY());
		}
		//if the ship is below the board
		if(nextPosition.getY() > BOARD_BOUNDS_BOTTOM - nextPosition.getHeight()){
			nextPosition.setLocation(nextPosition.getX(), BOARD_BOUNDS_BOTTOM - nextPosition.getHeight());
		}
		//if the ship is above the board
		else if(nextPosition.getY() < BOARD_BOUNDS_TOP){
			nextPosition.setLocation(nextPosition.getX(), BOARD_BOUNDS_TOP);
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
			newBullet = new Projectile(new GPoint(rect.getX() + (rect.getWidth() / 2), rect.getY() + (rect.getHeight() / 2)), angle);
			//bullets.add(newBullet);
			curFireTime = 0;
			return true;
		}
		else {
			return false;
		}
	}
	
	public Projectile shootProjectile(Projectile bullet, float angle) {
		bullet = new Projectile(new GPoint(rect.getX() + (rect.getWidth() / 2), rect.getY() + (rect.getHeight() / 2)), angle);
		curFireTime = 0;
		return bullet;
	}
	
	public void init() {
		setSize(700, 700);
	}
	
	public void run() {
		ourEntity newEntity = new ourEntity(EntityType.SCOOTER);
		add(newEntity.getImage());
	}
	
	public static void main(String[] args) {
		new ourEntity(EntityType.SCOOTER).start();
	}
}