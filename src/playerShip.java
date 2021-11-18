//import javax.swing.Timer;

import java.util.Timer;
import java.util.TimerTask;

import acm.graphics.GImage;
import acm.graphics.GPoint;
import acm.graphics.GRect;

public class playerShip extends ourEntity{
	/**Our default constructor for playerShip
	 * 
	 * @param entityLocation the starting location of the playerShip
	 */
	playerShip(GPoint entityLocation) {
		fireDelay = 100;
		health = 300;
		speed = 3;
		friendly = true;
		rect = new GRect(entityLocation.getX(), entityLocation.getY(), 30, 30);
		rect.setFilled(true);
		type = EntityType.PLAYER;
		
		moveTimer.schedule(moveTask, 0, DELAY_MS); //starts movement cooldown timer
		shootTimer.schedule(shootTask, 0, fireDelay);
	}

	/**This is the move function that playerShip will be using. It mostly just runs through movePolar 
	 * from ourEntity, but this is needed to start the moveTimer for playerShip's movement cooldown
	 * 
	 * @param angle the angle at which you plan on moving the ship
	 * @return true if the ship moved, otherwise false
	 */
	public boolean move(float angle) {
		if(movePolar(angle)) {
			moveTimer = new Timer();
			return true;

		}else {return false;}
	}
	
	/**this is the shoot function that playerShip will be using. It essentially runs ourEntity's movePolar
	 * function, only that this one starts the shootTimer once a bullet is fired.
	 * 
	 * @param angle the direction the shot is fired
	 * @return true if a bullet was fired
	 * @return false if no bullet was fired
	 */
	public boolean shoot(float angle) {
		if(shootPolar(angle)) {

			return true;
			
		}else {return false;}
	}
	
}
