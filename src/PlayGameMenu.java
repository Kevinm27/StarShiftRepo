import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class PlayGameMenu extends GraphicsPane {
	
	private MainApplication program;
	
	private GImage background;
	private GButton level1;
	private GButton level2;
	private GButton backButton;
	private final int BUTTON_SIZE = 50;

	public PlayGameMenu(MainApplication app) {
		super();
		program = app;
		
		background = new GImage("Background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);
		
		level1 = new GButton("Level 1", app.getWidth()/2-2*BUTTON_SIZE, app.getHeight()/2-2*BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
		level1.setFillColor(Color.BLUE);
		
		level2 = new GButton("Level 2", app.getWidth()/2-BUTTON_SIZE, app.getHeight()/2-2*BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
		level2.setFillColor(Color.RED);
		
		backButton = new GButton("Back", app.getWidth()/2-BUTTON_SIZE/2, app.getHeight()/2, BUTTON_SIZE, BUTTON_SIZE);
		backButton.setFillColor(Color.magenta);
	}

	@Override
	public void showContents() {
		program.add(background);
		
		program.add(level1);
		program.add(level2);
		program.add(backButton);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		
		program.remove(level1);
		program.remove(level2);
		program.remove(backButton);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == level1) {
			
		}
		
		if (obj == level2) {
			
		}
		
		if (obj == backButton) {
			program.switchToMenu();
		}
	}

}
