import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GRect;

public class playerShip extends ourEntity implements ActionListener{
	private static final int DELAY_MS = 25;
	
	//Locations bulletLocation;
	
	private int fireDelay = 100;
	private boolean canMove = true;
	private Timer moveTimer;
	private Timer shotTimer;
	
	playerShip() {
		//player = new ourEntity(EntityType.PLAYER);
		health = 300;
		speed = 3;
		friendly = true;
		rect = new GRect(20, 20, 200, 200);
		type = EntityType.PLAYER;
		
		moveTimer = new Timer(DELAY_MS, this);
		shotTimer = new Timer(fireDelay, this);
	}

	@Override
	  public void actionPerformed(ActionEvent e) {
		  canMove = true;
		  moveTimer.stop();
	  }
	
	//Check if the Player is in Bounds
	
	boolean isInBounds (int programHeight, int programWidth, ourEntity entity) {
		if (entity.getEntityLocation().getX() > programWidth || entity.getEntityLocation().getY() > programHeight) {
			return false;
		}
		else {
			return true;
		}
	}
	

	boolean movePolar(int angle) {
		//Updates the Position of the Player's Ship with respect to the X Axis
		//Will need a check for if the Player is in bounds, can do in here or in level (discuss)
		if(canMove == false) {
			return false;
		}
		else {
			moveTimer.start();
			rect.movePolar(speed, angle);
			entityLocation = new Locations((int) rect.getX(),(int) rect.getY());
			canMove = false;
			return true;
		}
		
		
	}
	
	boolean moveX() {
		//Updates the Position of the Player's Ship with respect to the X Axis
		//Will need a check for if the Player is in bounds, can do in here or in level (discuss)
		if(canMove == false) {
			return false;
		}
		else {
			moveTimer.start();
			int distMX = getEntityLocation().getX() + x;
			playerLocation = new Locations(distMX ,getEntityLocation().getY());
			setEntityLocation(playerLocation);
			canMove = false;
			return true;
		}
		
		
	}
	
	boolean moveY(int y) {
		//Updates the Position of the Player's Ship with respect to the Y Axis
		//Will need a check for if the Player is in bounds, can do in here or in level (discuss)
		
		int distMY = player.getEntityLocation().getY() + y;
		playerLocation = new Locations(player.getEntityLocation().getX(), distMY);
		player.setEntityLocation(playerLocation);
		return true;
	}
	
	void shootX(int x) {
		//Updates the Position of the Player's Bullets with respect to the X Axis
		
		int bullX = bullet.getProjectileLocation().getX() + x;
		bullet.setProjectileLocation(bullX, bullet.getProjectileLocation().getY());
		
	}
	
	void shootY(int y) {
		//Updates the Position of the Player's Bullets with respect to the Y Axis
		
		int bullY = bullet.getProjectileLocation().getY() + y;
		bullet.setProjectileLocation(bullet.getProjectileLocation().getX(), bullY);
		
	}
	
	int getHealth() {
		return player.getHealth();
	}
	
	void setHealth(int h) {
		player.setHealth(h);
	}
}
