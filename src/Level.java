import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
import acm.program.GraphicsProgram;
import acm.graphics.GOval;
import acm.graphics.GPoint;

public class Level extends GraphicsProgram implements KeyListener{
	private static final int DELAY_MS = 20;
	
	private ArrayList<Enemy> enemies = new ArrayList<> ();
	private playerShip player;
	private Projectile newBullet;
	private Timer uniTimer = new Timer(DELAY_MS, this);;
	
	//Using variable to store when timer is called
	private int shootDelay;

	
	//if we need playerShip as a component of the ArrayList
	//rewrite the code to iterate through the list looking for instance of playerShip
	private boolean isPaused = false;
	
	//Level Constructor
	public Level(ArrayList<Enemy> enemies, playerShip player) {
		this.player = player;
		this.enemies = enemies;
		
		initLevel();
	}
	
	/**level constructor (for Luke to play around with PlayerShip)
	 * 
	 * @return
	 */
	Level(playerShip player){
		this.player = player;
	}
	
	/**Called inside the Level constructor. Adds playerShip and all enemies to the screen, activates
	 * KeyListeners, then starts the game timer.
	 */
	private void initLevel() {
		addKeyListeners(new TAdapter());
		add(player.getRect());
		for(int i = 0; i < enemies.size(); i++) {
			add(enemies.get(i).getRect());
		}
		
		uniTimer.start();
	}
	
	public playerShip getPlayer() {
		return player;
	}

	public void setPlayer(playerShip player) {
		this.player = player;
	}
	
	/**Level Win Check
	 * 
	 * @return
	 */
	boolean isLevelWon() {
		if (enemies.size() < 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	boolean isLevelLost(playerShip player) {
		if(player.getHealth() < 1) {
			return true;
		}
		return false;
	}
	
	
	public void run() {
		Projectile t = new Projectile(new GPoint(200, 200), player.getRect());
		initLevel();
		
		uniTimer.start();
	}    
	
	//Console Functions
	
	public void pause() {
		
	}
	
	void play() {
		
	}
	
	public void moveAllProjOval() {
		 for(GOval projOval:Projectile.allProjOvals) {
			 projOval.movePolar(Projectile.PROJECTILE_SPEED, Projectile.angle);
		 }
	 }
	
	//Listeners
	
	/** As long as the game isn't paused, This is all of what the clock is going to execute once the
	 *  timer is triggered
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(Projectile.allProjOvals != null) {
			for(GOval projOval:Projectile.allProjOvals) {
				add(projOval);
			}
		}
		
		moveAllProjOval();
		
		//System.out.println("Timer ticked");
			player.operatePlayer();
			
			/*
			for(int i = 0; i < enemies.size(); i++) {
				if(enemies.get(i) != null) {
					enemies.get(i).operateEnemy(player);
				}
			}
			*/
	}
	
	/**This class was taken from an outside source. It overrides the KeyListeners of your choosing.
	 * In our case, we are overriding Level's KeyListener methods with the ones inside PlayerShip
	 * 
	 * @author lukeb
	 *
	 */
	private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
	
	@Override
	public void keyTyped(KeyEvent e) {
		//Pauses the Game
				//Will check if the player hit the Escape Key, pausing the game accordingly
				
				int id = e.getID();
				if (id == 27) {
					if(isPaused == false) {
						pause();
						isPaused = true;
					}
					else {
						play();
						isPaused = false;
					}
				}
	}
	
	public void init() {
		setSize(800, 600);
	}
	
	public static void main(String args[]) {
		new Level(new playerShip(new GPoint(200, 200))).start();
	}
}
