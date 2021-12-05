import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class MainApplication extends GraphicsProgram implements KeyListener{
	
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final String MUSIC_FOLDER = "sounds";

	private GraphicsPane curScreen;
	private MenuPane menu;
	private PlayGameMenu playGameScn;
	private ShipCustomPane shipCustom;
	private OptionsMenu options;
	private gameOverPane gameOver;
	private Level endless;
	private Levels levels;
	private Level level1;
	
	
	/* Method: setupInteractions
	 * -------------------------
	 * must be called before switching to another
	 * pane to make sure that interactivity
	 * is setup and ready to go.
	 */
	protected void setupInteractions() {
		requestFocus();
		addKeyListeners();
		addMouseListeners();
	}
	
	/* switchToScreen(newGraphicsPane)
	 * -------------------------------
	 * will simply switch from making one pane that was currently
	 * active, to making another one that is the active class.
	 */
	protected void switchToScreen(GraphicsPane newScreen) {
		if(curScreen != null) {
			curScreen.hideContents();
		}
		newScreen.showContents();
		curScreen = newScreen;
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(curScreen != null) {
			curScreen.mousePressed(e);
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(curScreen != null) {
			curScreen.mouseClicked(e);
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(curScreen != null) {
			curScreen.keyPressed(e);
		}
	}
	

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		musicAndSFX.playMusic();
		levels = new Levels();
		//level1 = new Level(this, new playerShip(8, 1000, EntityType.PLAYER, new GPoint(200, 200)), levels.getLevel1Enemies());
		menu = new MenuPane(this);
		playGameScn = new PlayGameMenu(this);
		options = new OptionsMenu(this);
		shipCustom = new ShipCustomPane(this);
		gameOver = new gameOverPane(this);
		endless = new Level(this, new playerShip(8, 1000, EntityType.PLAYER, new GPoint(200, 200)));
		setupInteractions();
		switchToMenu();
	}

	public void switchToMenu() {
		switchToScreen(menu);
	}
	
	public void switchToPlayGameMenu() {
		switchToScreen(playGameScn);
	}
	
	public void switchToShipCustom() {
		switchToScreen(shipCustom);
	}
	
	public void switchToOptions() {
		switchToScreen(options);
	}
	public void switchToGameOver() {
		switchToScreen(gameOver);
	}
	
	public void switchToLevel() {
		switchToScreen(endless);
	}
	public void switchToLevel1() {
		switchToScreen(level1);
	}
	
	public static void main(String[] args) {
		new MainApplication().start();
	}
}
