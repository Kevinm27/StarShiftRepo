//import javax.swing.Timer;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

class Projectile extends GraphicsProgram {
	private static final int PROJECTILE_SPEED = 5;
	private static final int DELAY_MS = 25;
	private static final int PROJECTILE_DAMAGE = 100;
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	
	  private int speed;
	  private int damage;
	  private boolean friendly;
	  
	  private GOval oval; //placeholder for projectile image
	  
	  
	  private float angle;
	  private Timer moveTimer = new Timer();
	  private TimerTask moveTask = new MoveTask();
	  
	  /*
	   * this is the default constructor for player projectiles. they're going to derive the
	   * angle of travel for the projectile based on keyboard inputs.
	   */
	  Projectile(GPoint projectileLocation, float angle){
		  damage = PROJECTILE_DAMAGE;
		    friendly = true;
		    speed = PROJECTILE_SPEED;
		    oval = new GOval(projectileLocation.getX(), projectileLocation.getY(),10 ,10 );
		    oval.setFilled(true);
		    oval.setColor(Color.BLUE);
		    this.angle = angle;
		    moveTimer.schedule(moveTask, 0, DELAY_MS);
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
	    oval = new GOval(10, 10, projectileLocation.getX(), projectileLocation.getY());
	    oval.setFilled(true);
	    oval.setColor(Color.RED);
	    angle = getAngle(target);
	    moveTimer.schedule(moveTask, 0, DELAY_MS);
	  }
	  
	 
	  
	  
	  class MoveTask extends TimerTask{
		  public void run() {
			  oval.movePolar(speed, angle);
		  }
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
	  public void setOval(GOval oval) {
		  this.oval = oval;
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