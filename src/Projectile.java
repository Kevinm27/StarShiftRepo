class Projectile {
	  private int damage;
	  private int distance;
	  private boolean isVertical;
	  private boolean isHorizontal;
	  private boolean isFriendly;
	  Locations projectileLocation; 
	  //GImage projectile???
	  //Timer? StackOverflow says System.currentTimeMillis()
	  //LINK https://stackoverflow.com/questions/10820033/make-a-simple-timer-in-java/14323134
	  //long startTime = System.currentTimeMillis();
	  //long elapsedTime = System.currentTimeMillis - startTime
	  //long elapsedSeconds = elapsedTime / 1000;
	  //long secondsDisplay = elapsedSeconds % 60;

	  public Projectile(int damage, int distance, boolean isVertical, boolean isHorizontal, boolean isFriendly){
	    this.damage = damage;
	    this.distance = distance;
	    this.isVertical = isVertical;
	    this.isHorizontal = isHorizontal;
	    this.isFriendly = isFriendly;
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