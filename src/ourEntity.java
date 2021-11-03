/**
 * @author MeganA
 * This class will be the parent class to all the entities in the game (enemies and player).
 * Objects of this class can move, fire projectiles, and take damage. This class will create the framework 
 * for the game�s Enemy ships and the player�s playerShip. It will hold an array list of Projectiles 
 * that can access projectile objects.
 */

import java.util.ArrayList;										// import the ArrayList class
import java.util.Timer;											// refer to TimerLab for use
import acm.graphics.GImage;

public class ourEntity {
	//***** private variables *****//
	private Locations entityLocation = new Locations(0,0);		// arrayList of Locations	
	private int health;
	private int speed;
	private boolean isVertical, isHorizontal;
	private GImage image;
	
	//***** public variables *****//
	public ArrayList<Projectile> bullets = new ArrayList<Projectile>();	
	public EntityType type = null;								// Entity Type needs to be defined
	
	
	//**** Set Functions *****//
	void setEntityLocation(Locations location) {				// check for legal location elsewhere (?)
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
	void setImage(EntityType shipType) {
		this.image.setImage("SHIP IMAGE"); 						// help with graphic please
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
	GImage getImage() {
		return this.image; 	
	}
	
	//***** Fundamental Functions *****//
	void move() {								// write move! 
												// takes in an ActionEvent e for press and hold WASD
												// MAYBE - event listeners needs to be included in this class 
												// calls canMove check: if (go thru with move) else (return)
	}
	boolean fire() {
		if(entityType != EntityType.SCOOTER) {
			
			return true;
		} else {
			return false;	
		}
		
	}
	Timer fireRateTimer() {
		return null;
	}
	
	
}
