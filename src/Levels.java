import java.util.ArrayList;
import acm.graphics.GPoint;

public class Levels {
	
	private playerShip player = new playerShip(5, 300, EntityType.PLAYER, new GPoint(0, 0));
	
	/*
	 * ArrayLists for Levels 1-5
	 * Function for Setting up ArrayList
	 * Names: enlevone = enemies level one, enlevtwo = enemies level two, etc.
	 * 
	 * */
	
	private ArrayList<Enemy> enlevone;
	private ArrayList<Enemy> enlevtwo;
	private ArrayList<Enemy> enlevthree;
	private ArrayList<Enemy> enlevfour;
	private ArrayList<Enemy> enlevfive;
	
	//ArrayList SetUp
	void enemySet(ArrayList<Enemy> enemies, Enemy enemy) {
		enemies.add(enemy);
	}
	
	/*
	 * Enemies
	 * Names for enemies are based on the level they are in, and which enemy they are in their respective level.
	 * First char of name is level number (i.e. 'on' for one, 'th' for three) , second char of name is which enemy they are. 
	 * */
	
	//Level 1 Enemy
	private Enemy oo = new Enemy(50, 200, EntityType.SHOOTER, new GPoint(100, 100));
	private Enemy ot = new Enemy(50, 200, EntityType.SHOOTER, new GPoint(200, 200));
	private Enemy oth = new Enemy(50, 200, EntityType.SHOOTER, new GPoint(300, 300));
	private Enemy of = new Enemy(50, 200, EntityType.SHOOTER, new GPoint(400, 400));
	
	void levelOneSet() {
		enemySet(enlevone, oo);
		enemySet(enlevone, ot);
		enemySet(enlevone, oth);
		enemySet(enlevone, of);
	}
	
	//Level 2 Enemy
	private Enemy to = new Enemy (0, 200, EntityType.SCOOTER, new GPoint(100, 100));
	private Enemy tt = new Enemy (0, 200, EntityType.SCOOTER, new GPoint(200, 200));
	private Enemy tth = new Enemy (0, 200, EntityType.SCOOTER, new GPoint(300, 300));
	private Enemy tf = new Enemy (0, 200, EntityType.SCOOTER, new GPoint(400, 400));
	
	void levelTwoSet() {
		enemySet(enlevtwo, to);
		enemySet(enlevtwo, tt);
		enemySet(enlevtwo, tth);
		enemySet(enlevtwo, tf);
	}
	
	//Level 3 Enemy
	private Enemy tho = new Enemy(50, 200, EntityType.SHOOTER, new GPoint(100, 100));
	private Enemy tht = new Enemy(50, 200, EntityType.SHOOTER, new GPoint(100, 200));
	private Enemy thth = new Enemy (0, 200, EntityType.SCOOTER, new GPoint(300, 300));
	private Enemy thf = new Enemy (0, 200, EntityType.SCOOTER, new GPoint(400, 400));
	
	void levelThreeSet() {
		enemySet(enlevthree, tho);
		enemySet(enlevthree, tht);
		enemySet(enlevthree, thth);
		enemySet(enlevthree, thf);
	}
	
	//Level 4 Enemy
	private Enemy fo = new Enemy(50, 200, EntityType.SHOOTER, new GPoint (100, 100));
	private Enemy ft = new Enemy(50, 200, EntityType.SHOOTER, new GPoint(200, 200));
	private Enemy fth = new Enemy(50, 200, EntityType.SHOOTER, new GPoint(300, 300));
	private Enemy ff = new Enemy(50, 200, EntityType.SHOOTER, new GPoint(400, 400));
	private Enemy ffi = new Enemy (0, 200, EntityType.SCOOTER, new GPoint(100, 200));
	private Enemy fs = new Enemy (0, 200, EntityType.SCOOTER, new GPoint(200, 300));
	private Enemy fse = new Enemy (0, 200, EntityType.SCOOTER, new GPoint(300, 400));
	private Enemy fe = new Enemy (0, 200, EntityType.SCOOTER, new GPoint(400, 500));
	
	void levelFourSet() {
		enemySet(enlevfour, fo);
		enemySet(enlevfour, ft);
		enemySet(enlevfour, fth);
		enemySet(enlevfour, ff);
		enemySet(enlevfour, ffi);
		enemySet(enlevfour, fs);
		enemySet(enlevfour, fse);
		enemySet(enlevfour, fe);
	}
	
	//Level 5 Enemy
	private Enemy fio = new Enemy(50, 400, EntityType.SCOOTER, new GPoint (100, 100));
	private Enemy fit = new Enemy(50, 400, EntityType.SCOOTER, new GPoint(200, 200));
	private Enemy fith = new Enemy(50, 400, EntityType.SCOOTER, new GPoint(300, 300));
	private Enemy fif = new Enemy(50, 400, EntityType.SCOOTER, new GPoint(400, 400));
	private Enemy fifi = new Enemy (0, 400, EntityType.SHOOTER, new GPoint(100, 200));
	private Enemy fis = new Enemy (0, 400, EntityType.SHOOTER, new GPoint(200, 300));
	private Enemy fise = new Enemy (0, 400, EntityType.SHOOTER, new GPoint(300, 400));
	private Enemy fie = new Enemy (0, 400, EntityType.SHOOTER, new GPoint(400, 500));
	
	void levelFiveSet() {
		enemySet(enlevfive, fio);
		enemySet(enlevfive, fit);
		enemySet(enlevfive, fith);
		enemySet(enlevfive, fif);
		enemySet(enlevfive, fifi);
		enemySet(enlevfive, fis);
		enemySet(enlevfive, fise);
		enemySet(enlevfive, fie);
	}
	
	//Levels
	Level one =  new Level(enlevone, player, false);
	Level two = new Level(enlevtwo, player, false);
	Level three = new Level(enlevthree, player, false);
	Level four = new Level(enlevfour, player, false);
	Level five = new Level(enlevfive, player , false);
	
	
}
