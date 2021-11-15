import acm.graphics.GImage;

public class playerShip extends ourEntity {
	ourEntity player;
	Locations playerLocation;
	Locations bulletLocation;
	Projectile bullet;
	
	playerShip() {
		player = new ourEntity(EntityType.PLAYER);
		player.setHealth(300);
	}

	
	
	//Check if the Player is in Bounds
	
	boolean isInBounds (int programHeight, int programWidth, ourEntity entity) {
		if (entity.getEntityLocation().getX() > programWidth || entity.getEntityLocation().getY() > programHeight) {
			return false;
		}
		else {
			return true;
		}
	}
	

	void moveX(int x) {
		//Updates the Position of the Player's Ship with respect to the X Axis
		//Will need a check for if the Player is in bounds, can do in here or in level (discuss)
		
		int distMX = player.getEntityLocation().getX() + x;
		playerLocation = new Locations(distMX ,player.getEntityLocation().getY());
		player.setEntityLocation(playerLocation);
		
	}
	
	void moveY(int y) {
		//Updates the Position of the Player's Ship with respect to the Y Axis
		//Will need a check for if the Player is in bounds, can do in here or in level (discuss)
		
		int distMY = player.getEntityLocation().getY() + y;
		playerLocation = new Locations(player.getEntityLocation().getX(), distMY);
		player.setEntityLocation(playerLocation);
		
	}
	
	void shootX(int x) {
		//Updates the Position of the Player's Bullets with respect to the X Axis
		
		int bullX = bullet.getProjectileLocation().getX() + x;
		bullet.setProjectileLocation(bullX, bullet.getProjectileLocation().getY());
		
	}
	
	void shootY(int y) {
		//Updates the Position of the Player's Bullets with respect to the Y Axis
		
		int bullY = bullet.getProjectileLocation().getY() + y;
		bullet.setProjectileLocation(bullet.getProjectileLocation().getX(), bullY);
		
	}
	
	int getHealth() {
		return player.getHealth();
	}
	
	void setHealth(int h) {
		player.setHealth(h);
	}
}
