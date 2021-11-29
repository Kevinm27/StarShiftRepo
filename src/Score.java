import java.awt.Color;

import acm.graphics.*;

/**This class is going to keep track of the score
 * 
 * @author lukeb
 *
 */
public class Score {
	private static final int comboReset = 4;
	
	GLabel text;
	int score = 0;
	int combo = 1;
	int comboTimer = 0;
	GLabel comboText;
	
	Score(GPoint location, int fontSize){
		text = new GLabel("Score: " + score, location.getX(), location.getY());
		text.setFont("Broadway-28");
		text.setColor(Color.cyan);
		
		comboText = new GLabel("Combo: x" + combo, location.getX(), location.getY() + 30);
		comboText.setFont("Broadway-28");
		comboText.setColor(Color.red);
		
	}
	
	/**This function is called every time you get a kill with your projectiles. It increases
	 * your score by multiplying the score you get for a kill (which is 10 by default) by
	 * your current combo.
	 * 
	 * @param addedScore
	 */
	public void updateScore(int addedScore) {
		score += combo * addedScore;
		text.setLabel("Score: " + score);
		upCombo();
	}
	
	/**Every time you get a kill with your bullets, your combo increases by 1.
	 * 
	 */
	private void upCombo() {
		combo++;
		comboTimer = 0;
		comboText.setLabel("Combo: x" + combo);
	}
	
	/**Controls the combo timer. After 4 seconds, your combo resets to a 1x multiplier
	 * 
	 */
	public void controlComboTimer() {
		comboTimer++;
		if(comboTimer >= comboReset) {
			comboTimer = 0;
			combo = 1;
			comboText.setLabel("Combo: x" + combo);
		}
	}

	public GLabel getText() {return text;}
	
	public GLabel getComboText() {return comboText;}
}
