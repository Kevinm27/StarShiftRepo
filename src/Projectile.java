import acm.graphics.GOval;
import acm.graphics.GRect;

class Projectile {
	public static final int DELAY_MS = 25;
	
	  private int damage;
	  private int distance;
	  private boolean isVertical;
	  private boolean isHorizontal;
	  private boolean friendly;
	  private GOval oval; //placeholder for projectile image
	  private float angle;
	  Locations projectileLocation; 
	  Projectile(int damage, int distance, boolean isVertical, boolean isHorizontal, boolean friendly, Locations projectileLocation){
	    this.damage = damage;
	    this.distance = distance;
	    this.isVertical = isVertical;
	    this.isHorizontal = isHorizontal;
	    this.friendly = friendly;
	    this.projectileLocation = projectileLocation;
	    oval = new GOval(10, 10, projectileLocation.getX(), projectileLocation.getY());
	    
	  }

	  
	  
	  public int getDamage(){
	    return this.damage;
	  }
	  public void setDamage(int damage){
	    this.damage = damage;
	  }
	  public int getDistance(){
	    return this.distance;
	  }
	  public void setDistance(int distance){
	    this.distance = distance;
	  }
	  public void setFriendly(boolean friendly) {
		  this.friendly = friendly;
	  }
	  public boolean isFriendly() {
		  return this.friendly;
	  }

	  public boolean isVertical(){
	    return this.isVertical;
	  }
	  public void setIsVertical(boolean isVertical){
	    this.isVertical = isVertical;
	  }
	  public boolean isHorizontal(){
	    return this.isHorizontal;
	  }
	  public void setIsHorizontal(boolean isHorizontal){
	    this.isHorizontal = isHorizontal;
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