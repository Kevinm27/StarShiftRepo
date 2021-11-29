import java.awt.Color;

import acm.graphics.*;

/**
 * 
 * @author lukeb
 *
 */
public class PlayerHealthBar {
	private static final int MARGIN = 2;
	private GRect hpFrame;
	private GRect hpBack;
	private GRect curHealthBar;
	private double maxHP, hpBarWidth;
	
	
	PlayerHealthBar(GPoint start, int width, int height, int playerHealth){ 
		hpBack = new GRect(start.getX(), start.getY(), width, height);
		hpBack.setFilled(true);
		hpBack.setLineWidth(MARGIN);
		hpBack.setFillColor(Color.red);
		curHealthBar = new GRect(start.getX() + MARGIN, start.getY() + MARGIN, width - (MARGIN * 2), height - (MARGIN * 2));
		curHealthBar.setFilled(true);
		curHealthBar.setFillColor(Color.green);
		maxHP = playerHealth;
		hpBarWidth = hpBack.getWidth();
	}
	
	public void modifyHealthBar(double curHP) {
		if(curHP > 0)
			curHealthBar.setSize(curHealthBar.getWidth() * (curHP / maxHP), curHealthBar.getHeight());
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

	public GRect getHpFrame() {
		return hpFrame;
	}

	public GRect getCurHealthBar() {
		return curHealthBar;
	}
	
}
