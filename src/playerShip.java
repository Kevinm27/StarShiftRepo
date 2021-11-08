import acm.graphics.GImage;

public class playerShip {
	ourEntity player;
	Locations playerLocation;
	
	void moveX(int x) {
		//will need a check for if the player is in bounds, can do in here or in level (discuss)
		int distMX = player.getEntityLocation().getX() + x;
		playerLocation = new Locations(distMX ,player.getEntityLocation().getY());
		player.setEntityLocation(playerLocation);
	}
	
	void moveY(int y) {
		//will need a check for if the player is in bounds, can do in here or in level (discuss)
		int distMY = player.getEntityLocation().getY() + y;
		playerLocation = new Locations(player.getEntityLocation().getX(), distMY);
		player.setEntityLocation(playerLocation);
	}
	
}
