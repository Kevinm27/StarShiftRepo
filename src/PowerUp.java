import acm.graphics.GImage;
import acm.graphics.GPoint;
import java.lang.Math;

public class PowerUp {
	PowerUpType pT;
	static int HPUP = 100;
	
	//TODO: change the static GImages to string
	private GImage image;
	static String HPUPP = "media/healthup.png";
	static GImage SPDUPP = new GImage("media/speedup.png");
	static GImage MEGAUPP = new GImage("media/megaup.png");
	
	static String getHPUP() {
		return HPUPP;
	}
	
	static GImage getSPDUP() {
		return SPDUPP;
	}
	
	static GImage getMEGAUP() {
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
		//TODO: finish the constructor foe different image types
		image.setSize(35, 35);
	}
	
	static void setHPUP(playerShip player) {
		if(player.getHealth() < 1000) {
			int health = player.getHealth() + HPUP;
			player.setHealth(health);
		}
	}
	
	static void setSPDUP(playerShip player) {
		double spd = player.getSpeed() * 1.2;
		player.setSpeed(spd);
	}
	
	static void setMEGAUP(playerShip player) {
		if(player.getHealth() < 1000) {
			int health = player.getHealth() + HPUP;
			player.setHealth(health);
		}
		double spd = player.getSpeed() * 1.2;
		player.setSpeed(spd);
	}
	
	/**selects a random power up. Power ups have a weighted drop chance, meaning stronger power ups are more rare
	 * 
	 * @return the powerup type
	 */
	private PowerUpType pickPowerUP() {
		int randPower =  (int)(Math.random()*(40-1+1)+1);
		if(randPower <= 500) {
			return PowerUpType.HP;
		}
		else if(randPower <= 30) {
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
