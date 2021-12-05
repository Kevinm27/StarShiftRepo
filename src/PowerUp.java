import acm.graphics.GImage;
import acm.graphics.GPoint;
import java.util.Random;

public class PowerUp {
	PowerUpType pT;
	Random rand = new Random();
	int HPUP = 100;
	
	GImage HPUPP = new GImage("media/healthup.png");
	GImage SPDUPP = new GImage("media/speedup.png");
	GImage MEGAUPP = new GImage("media/megaup.png");
	
	void setHPUP(playerShip player) {
		int health = player.getHealth() + HPUP;
		player.setHealth(health);
	}
	
	void setSPDUP(playerShip player) {
		double spd = player.getSpeed() * 1.2;
		player.setSpeed(spd);
	}
	
	void setMEGAUP(playerShip player) {
		int health = player.getHealth() + HPUP;
		player.setHealth(health);
		double spd = player.getSpeed() * 1.2;
		player.setSpeed(spd);
	}
	
	PowerUpType pickPowerUP() {
		int randPower = rand.nextInt(40);
		if(randPower == 5 || randPower == 0) {
			return PowerUpType.HP;
		}
		if(randPower == 10 || randPower == 15) {
			return PowerUpType.SPD;
		}
		if(randPower == 30) {
			return PowerUpType.MEGA;
		}
		return null;
	}
	
	void dropPowerUp(GPoint location, PowerUpType pT) {
		switch(pickPowerUP()){
			case HP:
				HPUPP.setLocation(location);
			case SPD:
				SPDUPP.setLocation(location);
			case MEGA:
				MEGAUPP.setLocation(location);
		}
	}
	
}
