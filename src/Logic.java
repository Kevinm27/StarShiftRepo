import java.util.ArrayList;

import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GRect;

public class Logic {
	//	private boolean friendly; should be in ourEntity 
	private boolean collision;
	private double angleRotation = 90;
	
	
	public Logic(boolean friendly, boolean collision) {
	//	this.friendly = friendly;
		this.collision = collision;
	}
	public void setCollisionStatus(boolean collision) {
		this.collision = collision;
	}
	public boolean getCollisionStatus() {
		return this.collision;
	}
	/*
	public void setFriendly(boolean friendly) {
		//	this.friendly = friendly;
	}
	
	
	public boolean getFriendly() {
		return this.friendly;
	}
	*/
	
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
	public static boolean isCollided(GObject one, GObject two) {
		
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
	public static float getAngle(GObject start, GObject target) {
		float angle = (float) Math.toDegrees(Math.atan2(
	    		(target.getX() + (target.getWidth() / 2)) - (start.getX() + (start.getWidth() / 2)),
	    		(target.getY() + (target.getHeight() / 2)) - (start.getY() + (start.getHeight() / 2)))) - 90;
		if(angle < 0){
	        angle += 360;
	    }
		return angle;
	}
	
	public boolean collides(ourEntity playerShip, ArrayList<ourEntity> enemies, ArrayList<Projectile> bullets) {
		//Check Ship with all bullets and enemy ships
		//Start writing once we can see the ship on the screen
		for(int i = 0; i < enemies.size(); i++) {
			for(int j = 0; j < bullets.size(); j++) {
				//if(playerShip.getEntityLocation() == )) {
					
				}
		}
		
		return false;

	}
	
	

}
