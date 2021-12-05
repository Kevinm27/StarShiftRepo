import java.awt.Color;

import acm.graphics.*;

/**This simple class controls the health bar for the player
 * 
 * @author lukeb
 *
 */
public class PlayerHealthBar {
	private static final int MARGIN = 2;
	private GRect hpBack; //the "background" of the health bar
	private GRect curHealthBar; //visually represents the current HP of the player
	private double maxHP;
	private GLabel healthText;
	
	public GLabel getHealthText() {
		return healthText;
	}

	//Constructor\\
	PlayerHealthBar(GPoint start, int width, int height, int playerHealth){ 
		hpBack = new GRect(start.getX(), start.getY(), width, height);
		hpBack.setFilled(true);
		hpBack.setLineWidth(MARGIN);
		hpBack.setFillColor(Color.red);
		curHealthBar = new GRect(start.getX() + MARGIN, start.getY() + MARGIN, width - (MARGIN * 2), height - (MARGIN * 2));
		curHealthBar.setFilled(true);
		curHealthBar.setFillColor(Color.green);
		maxHP = playerHealth;
		healthText = new GLabel("Health: " + maxHP, start.getX(), start.getY() - 2);
		healthText.setFont("Broadway-30");
		healthText.setColor(Color.green);
	}
	
	/**After initializing the class, this will be the only method used. It recieves the player's 
	 * current health as a parameter, and adjusts the length of the currentHealthBar based
	 * on the current percentage of the player's max hp
	 * 
	 * @param curHP the player's current health
	 */
	public void modifyHealthBar(double curHP) {
		healthText.setLabel("Health: " + curHP);
		if(curHP > 0) {
			curHealthBar.setSize(hpBack.getWidth() * (curHP / maxHP), curHealthBar.getHeight());
			
		}
		else
			curHealthBar.setVisible(false);
	}
	
	public GRect getHpBack() {
		return hpBack;
	}

	public void setHpBack(GRect hpBack) {
		this.hpBack = hpBack;
	}

	public double getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(double maxHP) {
		this.maxHP = maxHP;
	}


	public GRect getCurHealthBar() {
		return curHealthBar;
	}
	
}
