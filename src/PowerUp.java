import acm.graphics.GImage;
import acm.graphics.GPoint;
import java.util.Random;

public class PowerUp {
	PowerUpType pT;
	static Random rand = new Random();
	static int HPUP = 100;
	
	static GImage HPUPP = new GImage("media/healthup.png");
	static GImage SPDUPP = new GImage("media/speedup.png");
	static GImage MEGAUPP = new GImage("media/megaup.png");
	
	static void setHPUP(playerShip player) {
		int health = player.getHealth() + HPUP;
		player.setHealth(health);
	}
	
	static void setSPDUP(playerShip player) {
		double spd = player.getSpeed() * 1.2;
		player.setSpeed(spd);
	}
	
	static void setMEGAUP(playerShip player) {
		int health = player.getHealth() + HPUP;
		player.setHealth(health);
		double spd = player.getSpeed() * 1.2;
		player.setSpeed(spd);
	}
	
	/**selects a random power up. Power ups have a weighted drop chance, meaning stronger power ups are more rare
	 * 
	 * @return the powerup type
	 */
	static PowerUpType pickPowerUP() {
		int randPower = rand.nextInt(40);
		if(randPower == 5 || randPower == 10) {
			return PowerUpType.HP;
		}
		if(randPower == 15 || randPower == 20) {
			return PowerUpType.SPD;
		}
		else{
			return PowerUpType.SPD;
		}
		//return null;
	}
	
	static GImage dropPowerUp(GPoint location) {
		switch(pickPowerUP()){
			case HP:
				HPUPP.setSize(35,35);
				HPUPP.setLocation(location);
				return HPUPP;
			case SPD:
				SPDUPP.setSize(35,35);
				SPDUPP.setLocation(location);
				return SPDUPP;
			case MEGA:
				MEGAUPP.setSize(35,35);
				MEGAUPP.setLocation(location);
				return MEGAUPP;
		}
		return null;
	}
	
}
