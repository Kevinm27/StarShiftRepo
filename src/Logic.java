import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;

public class Logic {
	//	private boolean friendly; should be in ourEntity 
	private double angleRotation = 90;
	
	public void rotateShip(GObject ship,double angle) {
		GPoint shipStart;
		if(angleRotation != angle) {
			shipStart = ship.getLocation();
			ship.rotate(Math.abs(angleRotation - angle));
			angleRotation = angle;
			ship.setLocation(shipStart);
		}
	}
	
	/*This function will take in two GObjects. The first being the player ship and the second being
	 * the enemy ship. There is a nested for loop which will check if there is any overlap between
	 * the aforementioned GObjects along any point of their length or width.
	 * 
	 * @param GObject representing player ship
	 * @param GObject representing enemy ship
	 * @return If objects overlap
	 */	
	public static boolean isCollided(GOval one, GImage two) {
		
		for (int i = (int) one.getX(); i < one.getX() + one.getWidth(); i++) {
			for (int j = (int) one.getY(); j < one.getY() + one.getHeight(); j++) {	
				if (two.contains(new GPoint(i, j))) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean isCollidedEnemy(GImage one, GImage two) {
		
		for (int i = (int) one.getX(); i < one.getX() + one.getWidth(); i++) {
			for (int j = (int) one.getY(); j < one.getY() + one.getHeight(); j++) {	
				if (two.contains(new GPoint(i, j))) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	/**This function calculates the angle between 2 objects. It is a static function, so it does not 
	 * require a logic object to be called. Simply call it by typing "Logic.getAngle(start, target)"
	 * 
	 * @param start the object your starting at
	 * @param target whatever direction you want your angle to focus in
	 * @return angle of trajectory
	 */
	public static float getAngle(GImage start, GImage target) {
		float angle = (float) Math.toDegrees(Math.atan2(
	    		(target.getX() + (target.getWidth() / 2)) - (start.getX() + (start.getWidth() / 2)),
	    		(target.getY() + (target.getHeight() / 2)) - (start.getY() + (start.getHeight() / 2)))) - 90;
		if(angle < 0){
	        angle += 360;
	    }
		return angle;
	}
}
