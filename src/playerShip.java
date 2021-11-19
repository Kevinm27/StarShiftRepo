//import javax.swing.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import acm.graphics.GImage;
import acm.graphics.GPoint;
import acm.graphics.GRect;

public class playerShip extends ourEntity implements KeyListener{
	
	private boolean wKeyDown = false;
	private boolean aKeyDown = false;
	private boolean sKeyDown = false;
	private boolean dKeyDown = false;
	private boolean upKeyDown = false;
	private boolean leftKeyDown = false;
	private boolean downKeyDown = false;
	private boolean rightKeyDown = false;
	
	/**Our default constructor for playerShip
	 * 
	 * @param entityLocation the starting location of the playerShip
	 */
	playerShip(GPoint entityLocation) {
		fireDelay = 5;
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
	
	/**This is the function that the gameTimer in Level will be calling to operate the playerShip. It 
	 * checks to see if the playerShip is actively trying to move or shoot. It also advances the curFireDelay
	 * every call so the cooldown for firing continues.
	 * 
	 */
	public void operatePlayer() {
		curFireDelay++;
		
		/*this series of if statements checks if the player is holding a key to move. If he is, 
		 * the player moves. The diagonal movement statements MUST come first or else the series of
		 * checks would be exited by whatever single key input was read first
		 */
		if(dKeyDown && wKeyDown) //move player up-right
			move(45);
		else if(wKeyDown && aKeyDown) //move player up-left
			move(135);
		else if(aKeyDown && dKeyDown) //move player down-left
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
		
		if(rightKeyDown && upKeyDown) //shoot up-right
			shoot(45);
		else if(upKeyDown && leftKeyDown) //shoot up-left
			shoot(135);
		else if(leftKeyDown && downKeyDown) //shoot down-left
			shoot(225);
		else if(downKeyDown && rightKeyDown) //shoot down-right
			shoot(315);
		else if(rightKeyDown) //shoot right
			shoot(0);
		else if(upKeyDown) //shoot up
			shoot(90);
		else if(leftKeyDown) //shoot left
			shoot(180);
		else if(downKeyDown) //shoot down
			shoot(270);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.print(e.getKeyChar());
			
			if (key == KeyEvent.VK_W) {
				//move player up
				wKeyDown = true;
			}
		
			if (key == KeyEvent.VK_A) {
				//move player left
				aKeyDown = true;
			}
		
			if (key == KeyEvent.VK_S) {
				//move player down
				sKeyDown = true;

			}
		
			if (key == KeyEvent.VK_D) {
				//move player right
				dKeyDown = true;
			}
			
			/*Bullet Movers
			 *Moves the Player's Bullets
			 *Will check the ID of the key pressed to check if it corresponds to Arrow Keys
			 *Arrow Key ID: Left:37, Up:38, Right:39, Down:40
			*/
			
			if (key == KeyEvent.VK_UP) {
				//shoot up
				upKeyDown = true;
				if(leftKeyDown)
					shoot(135);
				else if(rightKeyDown)
					shoot(45);
				else {
					shoot(90);
				}
				newBullet = getNewBullet();
				add(newBullet.getOval());
			}
			
			if (key == KeyEvent.VK_LEFT) {
				//shoot left
				leftKeyDown = true;
				if(downKeyDown)
					shoot(225);
				else if(upKeyDown)
					shoot(135);
				else {
					shoot(180);
				}
				newBullet = getNewBullet();
				add(newBullet.getOval());
			}
			
			if (key == KeyEvent.VK_DOWN) {
				//shoot down
				downKeyDown = true;
				if(leftKeyDown)
					shoot(225);
				else if(rightKeyDown)
					shoot(315);
				else {
					shoot(270);
				}
				newBullet = getNewBullet();
				add(newBullet.getOval());
			}
			
			if (key == KeyEvent.VK_RIGHT) {
				//shoot right
				rightKeyDown = true;
				if(downKeyDown)
					shoot(315);
				else if(upKeyDown)
					shoot(45);
				else {
					shoot(0);
				}
				
				newBullet = getNewBullet();
				add(newBullet.getOval());
			}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		//Move Stoppers
		//Stops the Movement of the Player's Ship
		//Will check the Char of the Key Released to check if it corresponds to WASD Keys 
		//If this fails, we may need to take in a string and check for key combinations
		if (key == KeyEvent.VK_W) {
			//move player up
			wKeyDown = false;
		}
	
		if (key == KeyEvent.VK_A) {
			//move player left
			aKeyDown = false;

		}
	
		if (key == KeyEvent.VK_S) {
			//move player down
			sKeyDown = false;

		}
	
		if (key == KeyEvent.VK_D) {
			//move player right
			dKeyDown = false;

		}
		
		/*Bullet Movers
		 *Moves the Player's Bullets
		 *Will check the ID of the key pressed to check if it corresponds to Arrow Keys
		 *Arrow Key ID: Left:37, Up:38, Right:39, Down:40
		*/
		
		if (key == KeyEvent.VK_UP) {
			//shoot up
			upKeyDown = false;
		}
		
		if (key == KeyEvent.VK_LEFT) {
			//shoot left
			leftKeyDown = false;
		}
		
		if (key == KeyEvent.VK_DOWN) {
			//shoot down
			downKeyDown = false;
		}
		
		if (key == KeyEvent.VK_RIGHT) {
			//shoot right
			rightKeyDown = false;
		}
		
	}
}
