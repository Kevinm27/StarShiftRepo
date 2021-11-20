import java.util.ArrayList;
import java.awt.Color;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

class Projectile extends GraphicsProgram {
	public static final int PROJECTILE_SPEED = 5;
	private static final int PROJECTILE_SIZE = 10;
	private static final int DELAY_MS = 20;
	private static final int PROJECTILE_DAMAGE = 100;
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 600;
	protected static final int BOARD_BOUNDS_BOTTOM = 600;
	protected static final int BOARD_BOUNDS_RIGHT = 400;
	protected static final int BOARD_BOUNDS_TOP = 110;
	protected static final int BOARD_BOUNDS_LEFT = 110;
	
	private int speed;
	private int damage;
	private boolean friendly;
	  
	private GOval oval; //placeholder for projectile image
	public static ArrayList<GOval> allProjOvals = new ArrayList<GOval>();
	
	private float angle;
	  
	  /*
	   * this is the default constructor for player projectiles. they're going to derive the
	   * angle of travel for the projectile based on keyboard inputs.
	   */
	  Projectile(GPoint projectileLocation, float angle){
		  damage = PROJECTILE_DAMAGE;
		    friendly = true;
		    speed = PROJECTILE_SPEED;
		    GOval projOval = makeProjOval(projectileLocation.getX(), projectileLocation.getY());
		    allProjOvals.add(projOval);
		    oval = new GOval(projectileLocation.getX(), projectileLocation.getY(), PROJECTILE_SIZE, PROJECTILE_SIZE);
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
	    
	    //angle = Logic.getAngle(oval, target);
	  }
	  

	  private void addProj(GPoint p, float a) {
		  Projectile proj = new Projectile(p, a);
	  }
	  
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

	  public boolean operateProjectile() {
		  oval.movePolar(speed, angle);
		  if (oval.getX() > BOARD_BOUNDS_RIGHT - oval.getWidth()) {
				//oval.setLocation(BOARD_BOUNDS_RIGHT - oval.getWidth(), oval.getY());
			 // remove(oval);
			  return true;
		  }
			//if the ship is too far to the left
			else if (oval.getX() < BOARD_BOUNDS_LEFT) {
				//oval.setLocation(BOARD_BOUNDS_LEFT, oval.getY());
				 // remove(oval);
				  return true;
			}
			//if the ship is below the board
			if(oval.getY() > BOARD_BOUNDS_BOTTOM - oval.getHeight()){
				//oval.setLocation(oval.getX(), BOARD_BOUNDS_BOTTOM - oval.getHeight());
				 // remove(oval);
				  return true;
			}
			//if the ship is above the board
			else if(oval.getY() < BOARD_BOUNDS_TOP){
				//oval.setLocation(oval.getX(), BOARD_BOUNDS_TOP);
				  //remove(oval);
				  return true;
			}
		//	return new GPoint(oval.getX(), oval.getY());
			return false;
	  }
	  
	  public int getDamage(){
	    return this.damage;
	  }
	  public void setDamage(int damage){
	    this.damage = damage;
	  }
	  
	  public void setFriendly(boolean friendly) {
		  this.friendly = friendly;
	  }
	  public boolean isFriendly() {
		  return this.friendly;
	  }
	  public GOval getOval() {
		  return oval;
	  }
	  public void setAngle(float angle) {
		  this.angle = angle;
	  }

	  
	  /*this class grabs the angle of a target relative to the position of the projectile
	   * 
	   */
	  public float getAngle(GRect target) {
		    return (float) Math.toDegrees(Math.atan2(
		    		(target.getX() + (target.getWidth() / 2)) - (oval.getX() + (oval.getWidth() / 2)),
		    		(target.getY() + (target.getHeight() / 2)) - (oval.getY() + (oval.getHeight() / 2))));
		}

	@Override
	public void run() {
		Projectile bullet1 = new Projectile(new GPoint(100, 50), 315);
		add(oval);
		add(bullet1.getOval());
	}
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public static void main(String args[]) {
		new Projectile(new GPoint(200, 200), 90).start();
	}
	}