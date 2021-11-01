public class Locations {
  private int x;
  private int y;
  public Locations(int x, int y){
    this.x = x;
    this.y = y;
  }

  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }

  public void setX(int x){
    this.x = x;
  }
  public void setY(int y){
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