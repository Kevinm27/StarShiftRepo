import java.awt.Color;
import acm.graphics.GOval;
import acm.graphics.GPoint;

public class Projectile {
	//************************************* Variables *************************************//
	private static final int PROJECTILE_SPEED = 5;
	private static final int PROJECTILE_SIZE = 10;
	private static final int PROJECTILE_DAMAGE = 100;
	
	private int speed;
	private int damage;
	private boolean friendly;
	  
	private GOval oval;
	
	private float angle;
	  
	//************************************* Constructors *************************************//
	/*
	 * this is the default constructor for player projectiles. they're going to derive the
	 * angle of travel for the projectile based on keyboard inputs.
	 */
	Projectile(GPoint projectileLocation, float angle, boolean friendly){
		damage = PROJECTILE_DAMAGE;
		this.friendly = friendly;
		speed = PROJECTILE_SPEED;
		oval = new GOval(projectileLocation.getX(), projectileLocation.getY(), PROJECTILE_SIZE, PROJECTILE_SIZE);
		oval.setFilled(true);
		if(friendly) 
			oval.setColor(Color.BLUE);
		else
			oval.setColor(Color.RED);
		this.angle = angle;
	}
	
	//************************************* Setter & Getters *************************************//
	public int getDamage(){
		return this.damage;
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	public void setFriendly(boolean friendly) {
		this.friendly = friendly;
	}
	
	public boolean getFriendly() {
		return this.friendly;
	}
	
	public GOval getOval() {
		return oval;
	}
	
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	//************************************* Functions *************************************//
	
	// Edited so the projectiles return true if out of bounds
	public boolean operateProjectile() {
		oval.movePolar(speed, angle);
		if (oval.getX() > Level.LEVEL_BOUNDS_RIGHT - oval.getWidth()) {
			return true;
		}
		//if the ship is too far to the left
		else if (oval.getX() < Level.LEVEL_BOUNDS_LEFT) {
			  return true;
		}
		//if the ship is below the board
		if(oval.getY() > Level.LEVEL_BOUNDS_BOTTOM - oval.getHeight()){
			  return true;
		}
		//if the ship is above the board
		else if(oval.getY() < Level.LEVEL_BOUNDS_TOP){
			  return true;
		}
		return false;
	}
	
}