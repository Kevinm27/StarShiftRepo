import java.util.ArrayList;
import java.awt.Color;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GRect;

public class Projectile {
	//************************************* Variables *************************************//
	public static final int PROJECTILE_SPEED = 5;
	public static final int PROJECTILE_SIZE = 10;
	private static final int PROJECTILE_DAMAGE = 100;
	
	private int speed;
	private int damage;
	private boolean friendly;
	  
	private GOval oval; //placeholder for projectile image
	public static ArrayList<GOval> allProjOvals = new ArrayList<GOval>();
	
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
		GOval projOval = makeProjOval(projectileLocation.getX(), projectileLocation.getY());
		allProjOvals.add(projOval);
		oval = new GOval(projectileLocation.getX(), projectileLocation.getY(), PROJECTILE_SIZE, PROJECTILE_SIZE);
		oval.setFilled(true);
		if(friendly) 
			oval.setColor(Color.BLUE);
		else
			oval.setColor(Color.RED);
		this.angle = angle;
	}
	  
	  
	/*
	 * This is the default constructor for enemy projectiles. The firing method is going to work
	 * by grabbing the player's starting angle at the time the projectile fired, and it will then
	 * continue traveling in that direction until it hits the player or leaves the screen
	 */
	Projectile(GPoint projectileLocation, GRect target){
		damage = PROJECTILE_DAMAGE;
		friendly = false;
		speed = PROJECTILE_SPEED;
		oval = new GOval(projectileLocation.getX(), projectileLocation.getY(), PROJECTILE_SIZE, PROJECTILE_SIZE);
		GOval projOval = makeProjOval(projectileLocation.getX(), projectileLocation.getY());
		allProjOvals.add(projOval);
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
	public GOval makeProjOval(double x, double y) {
		GOval temp = new GOval(x, y, PROJECTILE_SIZE, PROJECTILE_SIZE);
		temp.setColor(Color.BLUE);
		temp.setFilled(true);
		return temp;
	}
	 
	public void moveAllProjOval() {
		for(GOval projOval:allProjOvals) {
			projOval.movePolar(PROJECTILE_SPEED, angle);
		}
	}
	
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
	
	/*this class grabs the angle of a target relative to the position of the projectile
	 * 
	 */
	public float getAngle(GRect target) {
		return (float) Math.toDegrees(Math.atan2(
				(target.getX() + (target.getWidth() / 2)) - (oval.getX() + (oval.getWidth() / 2)),
				(target.getY() + (target.getHeight() / 2)) - (oval.getY() + (oval.getHeight() / 2))));
	}
}