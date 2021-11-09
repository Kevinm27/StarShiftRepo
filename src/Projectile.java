class Projectile {
	  private int damage;
	  private int distance;
	  private boolean isVertical;
	  private boolean isHorizontal;
	  private boolean friendly;
	  Locations projectileLocation; 
	  Projectile(int damage, int distance, boolean isVertical, boolean isHorizontal, boolean friendly){
	    this.damage = damage;
	    this.distance = distance;
	    this.isVertical = isVertical;
	    this.isHorizontal = isHorizontal;
	    this.friendly = friendly;
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

	  //still need to include the timer
	  //still need to include
	  
	  public static void main(String[] args) {
	    System.out.println("Hello PRojecriles");
	  }
	}