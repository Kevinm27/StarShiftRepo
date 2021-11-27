import java.util.ArrayList;
import acm.graphics.GPoint;
import java.lang.Math;

public class EnemySpawner {
	private ArrayList<Enemy> sEnemies = new ArrayList<>();
	private int currTime;
	private GPoint playerLocation;
	private int fireDelay;
	private int health;
	private EntityType eType;
	private GPoint eLocation;
	private int numEnemies;
	
	public EnemySpawner(int time, GPoint pL) {
		currTime = time;
		playerLocation = pL;
	}
	
	public void setTime(int time) {
		currTime = time;
	}
	
	public void setPlayerLocation(GPoint pL) {
		playerLocation = pL;
	}
	
	private int getTime() {
		return currTime;
	}
	
	private GPoint getPlayerLocation() {
		return playerLocation;
	}
	
	public ArrayList<Enemy> spawnEnemies() {
		//clears ArrayList for next set of enemies
		sEnemies.clear();
		
		//the number of enemies is the square root of the time rounded up plus 5
		numEnemies = (int)Math.ceil(Math.sqrt(getTime()))+5;
		
		//goes through for loop to create enemies
		for(int i=0; i < numEnemies; i++) {
			sEnemies.add(new Enemy(createFireDelay(currTime), createHealth(currTime), createEntityType(), createEnemyLocation(playerLocation)));
		}
		
		return sEnemies;
	}
	
	private int createFireDelay(int t) {
		fireDelay = 50;
		
		return fireDelay;
	}
	
	private int createHealth(int t) {
		health = 200;
		
		return health;
	}
	
	private EntityType createEntityType() {
		
		return eType;
	}
	
	private GPoint createEnemyLocation(GPoint pL) {
		
		return eLocation;
	}
}
