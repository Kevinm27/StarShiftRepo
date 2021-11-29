import java.awt.Color;

import acm.graphics.GPoint;
import acm.graphics.GRect;

/**
 * @author MeganA
 * This class will account for the functionality of both Enemy types, SHOOTERS and SCOOTERS.
 * Objects of this class have specified movement patters, move projectiles, and take damage. 
 * Objects can access projectile objects and will utilize timers to control all types of its movement.
 */

public class Enemy extends ourEntity {
	
	Enemy(int fD, int life, EntityType eT, GPoint entityLocation) {					// easier to have input be EntityType and location
		super(fD, life, eT);														// based on type, assign fD and life value
		
		rect = new GRect(entityLocation.getX(), entityLocation.getY(), 30, 30);
		rect.setFilled(true);
		
		switch(eT) {
			case PLAYER:
				break;
			case SCOOTER:
				canShoot = false;
				rect.setColor(Color.magenta);
				break;
			case SHOOTER:
				rect.setColor(Color.PINK);
				break;
		}
	}
		
	/**This is the function called by level every time the clock ticks. It's going to move the enemy
	 * towards the player, increase curFireDelay by 1 tick, and will even fire a bullet if enough time
	 * has passed.
	 * 
	 * @param player the playerShip that enemies are trying to move/shoot towards
	 */
	public void operateEnemy(float towardsPlayer) {
		curFireTime++;
		
		movePolar(towardsPlayer);
	}
	
	/**This is the move function that Enemy will be using. It mostly just runs through movePolar 
	 * from ourEntity, but this is needed to start the moveTimer for the Enemy's movement cooldown
	 * 
	 * @param angle the angle at which you plan on moving the ship
	 * @return true if the ship moved, otherwise false
	 */
	public boolean move(float angle) {				// takes in Type to change move rates
		if(movePolar(angle))
			return true;
		else
			return false;
	}
	
	/**this is the shoot function that playerShip will be using. It essentially runs ourEntity's movePolar
	 * function, only that this one starts the shootTimer once a bullet is fired.
	 * 
	 * @param angle the direction the shot is fired
	 * @return true if a bullet was fired
	 * @return false if no bullet was fired
	 */
	public boolean shoot(float angle) {
		if(movePolar(angle))
			return true;
		else
			return false;
	}

}
