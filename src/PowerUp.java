import acm.graphics.GImage;
import acm.graphics.GPoint;
import java.lang.Math;

public class PowerUp {
	
	private static final String HPUPP = "media/healthup.png";
	private static final String SPDUPP = "media/speedup.png";
	private static final String MEGAUPP = "media/megaup.png";
	
	PowerUpType pT;
	static int HPUP = 200;
	private GImage image;
	
	/**default constructor for PowerUp
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
	
	static void setHPUP(PlayerShip player) {
			int health = player.getHealth() + HPUP;
			player.setHealth(health);
	}
	
	static void setSPDUP(PlayerShip player) {
		if(player.getSpeed() > 10)
			player.setSpeed(player.getSpeed() * 1.2);
	}
	
	static void setMEGAUP(PlayerShip player) {
		int health = player.getHealth() + HPUP;
		player.setHealth(health);
			
		if(player.getSpeed() < 10)
			player.setSpeed(player.getSpeed() * 1.2);

		if(player.getFireDelay() > 5)
			player.setFireDelay(player.getFireDelay() - 1);
	}
	
	/**selects a random power up. Power ups have a weighted drop chance, meaning stronger power ups are more rare.
	 * There is a 5 in 8 chance of spawning a HP PowerUp. 
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
