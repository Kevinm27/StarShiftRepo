
public enum PowerUpType {
	HP,
	SPD,
	MEGA;
	
	public String toString() {
		switch(this) {
		case HP: return "health";
		case SPD: return "speed";
		case MEGA: return "mega";
		}
		return "n/a";
	}
}
