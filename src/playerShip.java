import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.Timer;

import java.util.Timer;
import java.util.TimerTask;

import acm.graphics.GImage;
import acm.graphics.GPoint;
import acm.graphics.GRect;

public class playerShip extends ourEntity implements ActionListener{
	
	private Projectile newBullet; //used for creating/firing projectiles
	playerShip(GPoint entityLocation) {
		fireDelay = 100;
		
		health = 300;
		speed = 3;
		friendly = true;
		rect = new GRect(entityLocation.getX(), entityLocation.getY(), 30, 30);
		rect.setFilled(true);
		type = EntityType.PLAYER;
	}

	@Override
	public boolean movePolar(float angle) {
		if(ourEntity.movePolar(angle)) {
			
		}
			
		return true;
	}
	
}
