import acm.graphics.GPoint;
import acm.graphics.GRect;

public class PowerUp {
	PowerUpType pT;
	
	int HPUP = 100;
	int DMGUP = 100;
	double SPDUP = 0.5;
	
	GRect rect;
	
	void setHPUP(playerShip player) {
		int health = player.getHealth() + HPUP;
		player.setHealth(health);
	}
	
	
	void setSPDUP(playerShip player) {
		double spd = player.getSpeed() + SPDUP;
		player.setSpeed(spd);
	}
	
	void setMEGAUP(playerShip player) {
		int health = player.getHealth() + HPUP;
		player.setHealth(health);
		double spd = player.getSpeed() + SPDUP;
		player.setSpeed(spd);
	}
	
	void dropPowerUp(GPoint location, PowerUpType pT) {
		switch(pT){
			case HP:
				rect.setLocation(location);
				
			case SPD:
				rect.setLocation(location);
				
			case MEGA:
				rect.setLocation(location);
				
		}
	}
}
