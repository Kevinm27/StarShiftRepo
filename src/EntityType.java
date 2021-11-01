/**
 * 
 */


/**
 * @author lukeb
 * This enum is going to be used to differentiate types of Entities in the program
 * SHOOTER: an enemy that shoots projectiles at the player
 * SCOOTER: an enemy that moves towards the player and chases them down
 * PLAYER: the player ship
 */
public enum EntityType {
	SHOOTER,
	SCOOTER,
	PLAYER;

	public String toString() {
		switch(this) {
		case SHOOTER: return "shooter";
		case SCOOTER: return "scooter";
		case PLAYER: return "player";
		}
		return "n/a";
	}
}