/**
 * @author MeganA
 * This class will be the parent class to all the entities in the game (enemies and player).
 * Objects of this class can move, fire projectiles, and take damage. This class will create the framework 
 * for the game's Enemy ships and the player's playerShip. It will hold an array list of Projectiles 
 * that can access projectile objects.
 */

import java.util.ArrayList;										// import the ArrayList class
import java.util.Timer;											// refer to TimerLab for use
import acm.graphics.GImage;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class ourEntity extends GraphicsProgram {
	//***** private variables *****//
	protected Locations entityLocation;			
	protected int health;
	protected int speed;
	protected boolean friendly;									// IsFriendly should be here instead of logic
	protected boolean isVertical, isHorizontal;
	protected GImage image;
	protected GRect rect; //placeholder for image
	
	
	//***** public variables *****//
	public ArrayList<Projectile> bullets = new ArrayList<Projectile>();	
	public EntityType type = null;								// Entity Type needs to be defined when object is made
	public static final String IMG_FILENAME_PATH = "ships/";
	public static final String IMG_EXTENSION = ".png";
	
	//***** constructor *****//
	public ourEntity() {
		entityLocation = new Locations(0,0);					// originally on line 17
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
	void setEntityLocation(Locations location) {				// check for legal location elsewhere (?) Could be in logic
		this.entityLocation = location;
	}
	void setHealth(int hp) {
		this.health = hp;
	}
	void setSpeed(int howFast) {
		this.speed = howFast;
	}
	void setIsVertical(boolean isVert) {
		this.isVertical = isVert;
	}
	void setIsHorizontal(boolean isHoriz) {
		this.isHorizontal = isHoriz;
	}
	void setIsFriendly(boolean isFriendly) {
		this.friendly = isFriendly; 
	}
	void setEntityType(EntityType type) {
		this.type = type;
	}
	void setImage(EntityType shipType) {										// help with graphics
		//this.image.setImage(IMG_FILENAME_PATH + shipType + IMG_EXTENSION);		// PLEASE MAKE IMAGE NAMES SAME AS EntityTypes
		rect = new GRect(20, 20, 200, 200);
	}
	
	//***** Get Functions *****//
	Locations getEntityLocation() {
		return this.entityLocation;
	}
	int getHealth() {
		return this.health;
	}
	int getSpeed() {
		return this.speed;
	}
	boolean getIsVertical() {
		return this.isVertical;
	}
	boolean getIsHorizontal() {
		return this.isHorizontal;
	}
	boolean getIsFriendly() {
		return this.friendly;
	}
	GImage getImage() {
		return this.image; 	
	}
	
	ArrayList<Projectile> getBulletArrayList() {//Added getter for the array list
		return bullets;
	}
	
	//***** Fundamental Functions *****//
	void move() {								// write move! 
												// takes in an ActionEvent e for press and hold WASD
												// MAYBE - event listeners needs to be included in this class 
												// calls canMove check: if (go thru with move) else (return)
	}											// ^ should be in PlayerShip, since Enemy doesn't need this function
	boolean fire() {
		if(type != EntityType.SCOOTER) {
			
			return true;
		} else {
			return false;	
		}
		
	}
	
	Timer fireRateTimer() {
		return null;
	}

	public void init() {
		setSize(700, 700);
	}
	
	public void run() {
		ourEntity newEntity = new ourEntity(EntityType.SCOOTER);
		add(image);
	}
	
	public static void main(String[] args) {
		new ourEntity(EntityType.SCOOTER).start();
	}
}