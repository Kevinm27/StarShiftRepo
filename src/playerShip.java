import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.Timer;

import java.util.Timer;
import java.util.TimerTask;

import acm.graphics.GImage;
import acm.graphics.GPoint;
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
	
	/* HOW THESE TIMERS WORK
	 * 
	 * moveTimer is started by using a schedule function, like this: moveTimer.schedule(TimerTask, initialDelay)
	 * The TimerTask is explained near the TimerTask classes
	 * the initial delay is how long you want to wait until triggering the TimerTask.
	 * 
	 * If you want your timer to loop, you call it like this: shootTimer.schedule(TimerTask, initial delay, loop delay)
	 * the first 2 parameters are the same
	 * loop delay is how long you wish to wait before triggering the TimerTask again after the initial delay.
	 * it will continue to loop afterwards.
	 * 
	 * example: if you declared it like this: moveTimer.schedule(moveTask, 200, 50), here's how the delays work
	 * 1st move: 200 delay
	 * 2nd move: 50 delay
	 * 3rd move -> infinity: 50 delay
	 * 
	 */
	private Timer moveTimer = new Timer(); //this timer is a cooldown for movement
	private Timer shotTimer = new Timer(); //this timer is a cooldown for shooting
	private TimerTask moveTask = new MoveTask();
	private TimerTask shootTask = new ShootTask();
	
	private Projectile newBullet; 
	playerShip(GPoint entityLocation) {
		//player = new ourEntity(EntityType.PLAYER);
		health = 300;
		speed = 3;
		friendly = true;
		rect = new GRect(entityLocation.getX(), entityLocation.getY(), 30, 30);
		rect.setFilled(true);
		type = EntityType.PLAYER;
	}

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
	    public void run()
	    {
	        canMove = true;
	    }
	}
	class ShootTask extends TimerTask
	{
	    public void run()
	    {
	        canShoot = true;
	    }
	}
	
	/**Checks if the ship is within the bounds of the level, and pushes the ship into place if it is not
	 * 
	 * @return true & does nothing if the ship was already in bounds
	 * @return false if the ship was adjusted
	 */
	private boolean isInBounds () {
		//if the ship is too far to the right
		if (rect.getX() > BOARD_BOUNDS_RIGHT - rect.getWidth()) {
			rect.setLocation(BOARD_BOUNDS_RIGHT - rect.getWidth(), rect.getY());
			return false;
		}
		//if the ship is too far to the left
		else if (rect.getX() < BOARD_BOUNDS_LEFT) {
			rect.setLocation(BOARD_BOUNDS_LEFT, rect.getY());
			return false;
		}
		//if the ship is below the board
		else if(rect.getY() > BOARD_BOUNDS_BOTTOM - rect.getHeight()){
			rect.setLocation(rect.getX(), BOARD_BOUNDS_BOTTOM - rect.getHeight());
			return false;
		}
		//if the ship is above the board
		else if(rect.getY() < BOARD_BOUNDS_TOP){
			rect.setLocation(rect.getX(), BOARD_BOUNDS_TOP);
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
			
			rect.movePolar(speed, angle);
			
			isInBounds();  //runs isInBounds and corrects entityLocation to sit within bounds of board if needed
				
			canMove = false; //set canMove to false so it cannot be immediately called again
			moveTimer.schedule(moveTask, DELAY_MS); //starts movement cooldown timer
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
			//newBullet = new Projectile(new GPoint(rect.getX() + (rect.getWidth() / 2), rect.getY() + (rect.getHeight() / 2)), angle);
			bullets.add(newBullet);
			canShoot = false;
			shotTimer.schedule(shootTask, fireDelay);
			return true;
		}
	}
}
