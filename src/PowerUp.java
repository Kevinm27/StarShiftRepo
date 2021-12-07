import acm.graphics.GImage;
import acm.graphics.GPoint;
import java.lang.Math;

public class PowerUp {
	PowerUpType pT;
	static int HPUP = 200;
	
	//TODO: change the static GImages to string
	private GImage image;
	static String HPUPP = "media/healthup.png";
	static String SPDUPP = "media/speedup.png";
	static String MEGAUPP = "media/megaup.png";
	
	static String getHPUP() {
		return HPUPP;
	}
	
	static String getSPDUP() {
		return SPDUPP;
	}
	
	static String getMEGAUP() {
		return MEGAUPP;
	}
	
	/**
	 * 
	 * @param location
	 */
	PowerUp(GPoint location){
		pT = pickPowerUP();
		if(pT == PowerUpType.HP) {
			image = new GImage(HPUPP, location.getX(), location.getY());
		}
		if(pT == PowerUpType.SPD) {
			image = new GImage(SPDUPP, location.getX(), location.getY());
		}
		if(pT == PowerUpType.MEGA) {
			image = new GImage(MEGAUPP, location.getX(), location.getY());
		}
		image.setSize(35, 35);
	}
	
	static void setHPUP(playerShip player) {
//		if(player.getHealth() < 1000) {
			int health = player.getHealth() + HPUP;
			player.setHealth(health);
//		}
	}
	
	static void setSPDUP(playerShip player) {
		double spd = player.getSpeed() * 1.2;
		player.setSpeed(spd);
	}
	
	static void setMEGAUP(playerShip player) {
//		if(player.getHealth() < 1000) {
			int health = player.getHealth() + HPUP;
			player.setHealth(health);
//		}
		double spd = player.getSpeed() * 1.2;
		player.setSpeed(spd);
		player.setFireDelay(player.getFireDelay() - 1);
	}
	
	/**selects a random power up. Power ups have a weighted drop chance, meaning stronger power ups are more rare.
	 * There is a 5/8ths chance of spawning a HP PowerUp. 
	 * There is a 1 in 4 chance of spawning a speed PowerUp. 
	 * Finally, there is a 1 in 8 chance of spawning a MEGA PowerUp.
	 * 
	 * @return the powerup type
	 */
	private PowerUpType pickPowerUP() {
		int randPower =  (int)(Math.random()*(40-1+1)+1);
		if(randPower <= 25) {
			return PowerUpType.HP;
		}
		else if(randPower <= 35) {
			return PowerUpType.SPD;
		}
		else{
			return PowerUpType.MEGA;
		}
	}
	
	public PowerUpType getpT() {
		return pT;
	}

	GImage getImage() {
		return image;
	}
	
}
