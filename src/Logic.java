import java.util.ArrayList;

import acm.graphics.GObject;
import acm.graphics.GRect;

public class Logic {
	//	private boolean friendly; should be in ourEntity 
	private boolean collision;
	
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
	
	public static float getAngle(GObject start, GObject target) {
		return (float) Math.toDegrees(Math.atan2(
	    		(target.getX() + (target.getWidth() / 2)) - (start.getX() + (start.getWidth() / 2)),
	    		(target.getY() + (target.getHeight() / 2)) - (start.getY() + (start.getHeight() / 2))));
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
