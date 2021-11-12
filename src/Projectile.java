import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import acm.graphics.GOval;
import acm.graphics.GRect;

class Projectile implements ActionListener{
	private static final int PROJECTILE_SPEED = 5;
	private static final int DELAY_MS = 25;
	private static final int PROJECTILE_DAMAGE = 100;
	
	  private int speed;
	  private int damage;
	  private boolean friendly;
	  
	  private GOval oval; //placeholder for projectile image
	  private float angle;
	  private Timer t;
	  Locations projectileLocation; 
	  
	  /*
	   * This is the default constructor for enemy projectiles. The firing method is going to work
	   * by grabbing the player's starting angle at the time the projectile fired, and it will then
	   * continue traveling in that direction until it hits the player or leaves the screen
	   */
	  Projectile(Locations projectileLocation, GRect target){
	    damage = PROJECTILE_DAMAGE;
	    friendly = false;
	    speed = PROJECTILE_SPEED;
	    this.projectileLocation = projectileLocation;
	    oval = new GOval(10, 10, projectileLocation.getX(), projectileLocation.getY());
	    
	    angle = getAngle(target);
	    
	    t = new Timer(DELAY_MS, this);
	    t.start();
	  }
	  
	  /*
	   * this is the default constructor for player projectiles. they're going to derive the
	   * angle of travel of the projectile based on a keyboard input.
	   */
	  Projectile(Locations projectileLocation, float angle){
		  damage = PROJECTILE_DAMAGE;
		    friendly = true;
		    speed = PROJECTILE_SPEED;
		    this.projectileLocation = projectileLocation;
		    oval = new GOval(10, 10, projectileLocation.getX(), projectileLocation.getY());
		    
		    this.angle = angle;
		    
		    t = new Timer(DELAY_MS, this);
		    t.start();
	  }

	  @Override
	  public void actionPerformed(ActionEvent e) {
		  oval.movePolar(speed, angle);
		  projectileLocation.setX((int) oval.getX());
		  projectileLocation.setY((int) oval.getY());
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


	  public void setProjectileLocation(int x, int y){
	    this.projectileLocation.setX(x);
	    this.projectileLocation.setY(y);
	  }
	  public Locations getProjectileLocation(){
	    return this.projectileLocation;
	  }
	  
	  /*this class grabs the angle of a target relative to the position of the projectile
	   * 
	   */
	  public float getAngle(GRect target) {
		    return (float) Math.toDegrees(Math.atan2(target.getX() + (target.getWidth() / 2) - projectileLocation.getX(),
		    		target.getY() + (target.getHeight() / 2) - projectileLocation.getY()));
		}
	  
	  //still need to include the timer
	  //still need to include
	  
	  public static void main(String[] args) {
	    System.out.println("Hello PRojecriles");
	  }
	}