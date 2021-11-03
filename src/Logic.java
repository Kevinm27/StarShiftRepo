
public class Logic {
	private boolean friendly;
	private boolean collision;
	
	public Logic(boolean friendly, boolean collision) {
		this.friendly = friendly;
		this.collision = collision;
	}
	
	public void setFriendly(boolean friendly) {
		this.friendly = friendly;
	}
	public void setCollisionStatus(boolean collision) {
		this.collision = collision;
	}
	
	public boolean getFriendly() {
		return this.friendly;
	}
	public boolean getCollisionStatus() {
		return this.collision;
	}
	
	
	public boolean collides() {
		//Finish this once all ships are in an arraylist with their own propper GImage or GRec\t
		return false;
	}
	
	

}
