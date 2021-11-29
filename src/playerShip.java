import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import acm.graphics.GPoint;
import acm.graphics.GRect;

//import java.awt.geom.AffineTransform;		// ************* TESTING WITH IMAGE WORK
//import java.awt.Graphics2D;
//import java.awt.image.*;
//
//import javax.imageio.ImageIO;

public class playerShip extends ourEntity implements KeyListener{
	//These booleans tell us whether or not one of the keys on the keyboard is currently held down
	private boolean wKeyDown = false;
	private boolean aKeyDown = false;
	private boolean sKeyDown = false;
	private boolean dKeyDown = false;
	private boolean upKeyDown = false;
	private boolean leftKeyDown = false;
	private boolean downKeyDown = false;
	private boolean rightKeyDown = false;
		
	private GPoint playerLocation;

	
	/**Our default constructor for playerShip
	 * 
	 * @param entityLocation the starting location of the playerShip
	 */
	playerShip(int fD, int life, EntityType eT, GPoint entityLocation) {
		super(fD, life, eT);
		playerLocation = entityLocation;
		rect = new GRect(entityLocation.getX(), entityLocation.getY(), 30, 30);
		image.setLocation(entityLocation.getX(), entityLocation.getY());			// ****** ADDED WITH IMAGE WORK
//		image.setLocation(entityLocation);
		image.setColor(Color.red);
		rect.setFilled(true);
		rect.setColor(Color.cyan);
	}
	
	//getPlayerLocation is for the EnemySpawner Class
	public GPoint getPlayerLocation() {
		return playerLocation;
	}

	/**This is the move function that playerShip will be using. It mostly just runs through movePolar 
	 * from ourEntity, but this is needed to start the moveTimer for playerShip's movement cooldown
	 * 
	 * @param angle the angle at which you plan on moving the ship
	 * @return true if the ship moved, otherwise false
	 */
	public boolean move(float angle) {				// ************* WILL NOT ROTATE IMAGES - FUNCTION CHANGED
		return(movePolar(angle));
		
//		if(movePolar(angle)) {
//			return true;
//		}
//		else
//			return false;
	}
	
	/**this is the shoot function that playerShip will be using. It essentially runs ourEntity's movePolar
	 * function, only that this one starts the shootTimer once a bullet is fired.
	 * 
	 * @param angle the direction the shot is fired
	 * @return true if a bullet was fired
	 * @return false if no bullet was fired
	 */
	public boolean shoot(float angle) {
		if(shootPolar(angle))
			return true;
		else 
			return false;
	}
	
	/**This is the function that the gameTimer in Level will be calling to operate the playerShip. It 
	 * checks to see if the playerShip is actively trying to move or shoot. It also advances the curFireDelay
	 * every call so the cooldown for firing continues.
	 * 
	 */
	public void operatePlayer() {
		curFireTime++;
		
		/*this series of if statements checks if the player is holding a key to move. If he is, 
		 * the player moves. The diagonal movement statements MUST come first or else the series of
		 * checks would be exited by whatever single key input was read first
		 */
		if(dKeyDown && wKeyDown) //move player up-right
			move(45);
		else if(wKeyDown && aKeyDown) //move player up-left
			move(135);
		else if(aKeyDown && sKeyDown) //move player down-left
			move(225);
		else if(sKeyDown && dKeyDown) //move player down-right
			move(315);
		else if(dKeyDown) //move player right
			move(0);
		else if(wKeyDown) //move player up
			move(90);
		else if(aKeyDown) //move player left
			move(180);
		else if(sKeyDown) //move player down
			move(270);
		
		operateProjectiles();
	}
	
	public float getFiringAngle() {
		if(rightKeyDown && upKeyDown) //shoot up-right
			return 45;
		else if(upKeyDown && leftKeyDown) //shoot up-left
			return 135;
		else if(leftKeyDown && downKeyDown) //shoot down-left
			return 225;
		else if(downKeyDown && rightKeyDown) //shoot down-right
			return 315;
		else if(rightKeyDown) //shoot right
			return 0;
		else if(upKeyDown) //shoot up
			return 90;
		else if(leftKeyDown) //shoot left
			return 180;
		else if(downKeyDown) //shoot down
			return 270;
		else
			return -1;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
			
		switch(key) {
		//Move Stoppers
		//Stops the Movement of the Player's Ship
		//Will check the Char of the Key Released to check if it corresponds to WASD Keys 
		//If this fails, we may need to take in a string and check for key combinations
			case KeyEvent.VK_W:
				//move player up
	            wKeyDown = true;
				break;
			
			case KeyEvent.VK_A:
				//move player left
	            aKeyDown = true;
				break;
			
			case KeyEvent.VK_S:
				//move player down
	            sKeyDown = true;
				break;
			
			case KeyEvent.VK_D:
				//move player right
	            dKeyDown = true;
				break;
		
		/*Bullet Movers
		*Moves the Player's Bullets
		*Will check the ID of the key pressed to check if it corresponds to Arrow Keys
		*Arrow Key ID: Left:37, Up:38, Right:39, Down:40
		*/
			case KeyEvent.VK_UP:
				//shoot up
	            upKeyDown = true;
				break;
			
			case KeyEvent.VK_DOWN:
				//shoot down
	            downKeyDown = true;
				break;
			
			case KeyEvent.VK_LEFT:
				//shoot left
	            leftKeyDown = true;
				break;
			
			case KeyEvent.VK_RIGHT:
				//shoot right
	            rightKeyDown = true;
				break;
		}

	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		//Move Stoppers
		//Stops the Movement of the Player's Ship
		//Will check the Char of the Key Released to check if it corresponds to WASD Keys 
		//If this fails, we may need to take in a string and check for key combinations
			case KeyEvent.VK_W:
				wKeyDown = false;
				break;
			
			case KeyEvent.VK_A:
				aKeyDown = false;
				break;
			
			case KeyEvent.VK_S:
				sKeyDown = false;
				break;
			
			case KeyEvent.VK_D:
				dKeyDown = false;
				break;
		
		/*Bullet Movers
		*Moves the Player's Bullets
		*Will check the ID of the key pressed to check if it corresponds to Arrow Keys
		*Arrow Key ID: Left:37, Up:38, Right:39, Down:40
		*/
			case KeyEvent.VK_UP:
				upKeyDown = false;
				break;
			
			case KeyEvent.VK_DOWN:
				downKeyDown = false;
				break;
			
			case KeyEvent.VK_LEFT:
				leftKeyDown = false;
				break;
			
			case KeyEvent.VK_RIGHT:
				rightKeyDown = false;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
