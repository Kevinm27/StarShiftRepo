import java.util.ArrayList;
import java.util.Random;
import acm.graphics.GPoint;
import java.lang.Math;

public class EnemySpawner {
	private ArrayList<Enemy> sEnemies = new ArrayList<>();
	private int currTime;
	private GPoint playerLocation;
	private int fireDelay;
	private int health;
	private GPoint eLocation;
	private int numEnemies;
	private Random rng = new Random();
	private int LEVEL_BOUNDS_BOTTOM;
	private int LEVEL_BOUNDS_RIGHT;
	
	public EnemySpawner(int time, GPoint pL, int bottomBounds, int rightBounds) {
		currTime = time;
		playerLocation = pL;
		LEVEL_BOUNDS_BOTTOM = bottomBounds;
		LEVEL_BOUNDS_RIGHT = rightBounds;
	}
	
	public void setTime(int time) {
		currTime = time;
	}
	
	public void setPlayerLocation(GPoint pL) {
		playerLocation = pL;
	}
	
	public ArrayList<Enemy> spawnEnemies() {
		//clears ArrayList for next set of enemies
		sEnemies.clear();
		
		//the number of enemies is the square root of the time rounded up plus 5
		numEnemies = (int)(Math.floor(Math.sqrt(currTime/2))+3);
		
		//goes through for loop to create enemies
		for(int i=0; i < numEnemies; i++) {
			sEnemies.add(new Enemy(createFireDelay(currTime), createHealth(currTime), createEntityType(), createEnemyLocation(playerLocation)));
		}
		
		return sEnemies;
	}
	
	private int createFireDelay(int t) {
		fireDelay = 25;
		
		return fireDelay;
	}
	
	private int createHealth(int t) {
		health = 300;
		
		return health;
	}
	
	private EntityType createEntityType() {
	int eTNum = rng.nextInt(2);
		switch(eTNum) {
		case 0:
			return EntityType.SHOOTER;
		case 1:
			return EntityType.SCOOTER;
		default:
			return null;
		}
	}
	
	private GPoint createEnemyLocation(GPoint pL) {
		double x = pL.getX();
		double y = pL.getY();
		
		//generates random number to decide what quadrant the enemy will be in
		//will either be quadrant 1-4
		int quad = rng.nextInt(4) + 1;
		
		//using these if statements to check in which quadrants the enemies can spawn in
		if(x + 100 <= LEVEL_BOUNDS_RIGHT && y - 100 >= 0 && quad == 1) {
			//subtracting 100 so enemies domain to spawn is smaller
			int disFromScreenX = (int)(LEVEL_BOUNDS_RIGHT - x - 100);
			//subtracting 100 so enemies range to spawn is smaller
			int disFromScreenY = (int)(y - 100);
			
			eLocation = new GPoint(rng.nextInt(disFromScreenX) + x + 100, y - 100 - rng.nextInt(disFromScreenY));
		}
		else if(x - 100 >= 0 && y - 100 >= 0 && quad <= 2) {
			//subtracting 100 so enemies domain to spawn is smaller
			int disFromScreenX = (int)(x - 100);
			//subtracting 100 so enemies range to spawn is smaller
			int disFromScreenY = (int)(y - 100);
			
			eLocation = new GPoint(x - 100 - rng.nextInt(disFromScreenX), y - 100 - rng.nextInt(disFromScreenY));
		}
		else if(x - 100 >= 0 && y + 100 <= LEVEL_BOUNDS_BOTTOM && quad <= 3) {
			//subtracting 100 so enemies domain to spawn is smaller
			int disFromScreenX = (int)(x - 100);
			//subtracting 100 so enemies range to spawn is smaller
			int disFromScreenY = (int)(LEVEL_BOUNDS_BOTTOM - y - 100);
			
			eLocation = new GPoint(x - 100 - rng.nextInt(disFromScreenX), rng.nextInt(disFromScreenY) + y + 100);
		}
		else if(x + 100 <= LEVEL_BOUNDS_RIGHT && y + 100 <= LEVEL_BOUNDS_BOTTOM && quad <= 4) {
			//subtracting 100 so enemies domain to spawn is smaller
			int disFromScreenX = (int)(LEVEL_BOUNDS_RIGHT - x - 100);
			//subtracting 100 so enemies range to spawn is smaller
			int disFromScreenY = (int)(LEVEL_BOUNDS_BOTTOM - y - 100);
			
			eLocation = new GPoint(rng.nextInt(disFromScreenX) + x + 100, rng.nextInt(disFromScreenY) + y + 100);
		}
		else if(x + 100 <= LEVEL_BOUNDS_RIGHT && y - 100 >= 0 && quad <= 4) {
			//subtracting 100 so enemies domain to spawn is smaller
			int disFromScreenX = (int)(LEVEL_BOUNDS_RIGHT - x - 100);
			//subtracting 100 so enemies range to spawn is smaller
			int disFromScreenY = (int)(y - 100);
			
			eLocation = new GPoint(rng.nextInt(disFromScreenX) + x + 100, y - 100 - rng.nextInt(disFromScreenY));
		}
		else if(x - 100 >= 0 && y - 100 >= 0 && quad <= 4) {
			//subtracting 100 so enemies domain to spawn is smaller
			int disFromScreenX = (int)(x - 100);
			//subtracting 100 so enemies range to spawn is smaller
			int disFromScreenY = (int)(y - 100);
			
			eLocation = new GPoint(x - 100 - rng.nextInt(disFromScreenX), y - 100 - rng.nextInt(disFromScreenY));
		}
		else if(x - 100 >= 0 && y + 100 <= LEVEL_BOUNDS_BOTTOM && quad <= 4) {
			//subtracting 100 so enemies domain to spawn is smaller
			int disFromScreenX = (int)(LEVEL_BOUNDS_RIGHT - x - 100);
			//subtracting 100 so enemies range to spawn is smaller
			int disFromScreenY = (int)(LEVEL_BOUNDS_BOTTOM - y - 100);
			
			eLocation = new GPoint(rng.nextInt(disFromScreenX) + x + 100, rng.nextInt(disFromScreenY) + y + 100);
		}
		
		return eLocation;
	}
}
