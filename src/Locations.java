public class Locations {
  private double x;
  private double y;
  public Locations(double x, double y){
    this.x = x;
    this.y = y;
  }

  public double getX(){
    return this.x;
  }
  public double getY(){
    return this.y;
  }

  public void setX(double x){
    this.x = x;
  }
  public void setY(double y){
    this.y = y;
  }

  public static void main(String[] args){
	//Example
    Locations one = new Locations(5, 10);
    Locations two = new Locations(6, 12);
    System.out.println("One x: " + one.getX() + ", y: " + one.getY());
    System.out.println("One x: " + two.getX() + ", y: " + two.getY());
  }
}