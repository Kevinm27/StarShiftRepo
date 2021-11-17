import acm.graphics.GPoint;
import acm.graphics.GRect;

/**
 * @author MeganA
 * This class will account for the functionality of both Enemy types, SHOOTERS and SCOOTERS.
 * Objects of this class have specified movement patters, move projectiles, and take damage. 
 * Objects can access projectile objects and will utilize timers to control all types of its movement.
 */

public class Enemy extends ourEntity {
	
	Enemy(GPoint entityLocation, EntityType type) {						// just to get rid of red squiggly
		health = 200;
		friendly = false;
		this.type = type;
		rect = new GRect(entityLocation.getX(), entityLocation.getY(), 30, 30);
		rect.setFilled(true);
		if (type == EntityType.SCOOTER) {
			speed = 3;
			canShoot = false;
		}
		else if (type == EntityType.SHOOTER) {
			speed = 2;
			fireDelay = 1000;
		}
	}
													// move rates need to be a thing in the first place
	void movePattern(EntityType type) {				// takes in Type to change move rates
		
	}

}
