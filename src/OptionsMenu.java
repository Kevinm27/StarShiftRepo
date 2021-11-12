import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class OptionsMenu extends GraphicsPane {
private MainApplication program;
	
	private GImage background;
	private GButton musicButt;
	private GButton sfxButt;
	private GButton backButton;
	private final int BUTTON_SIZE = 50;

	public OptionsMenu(MainApplication app) {
		super();
		program = app;
		
		background = new GImage("Background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);
		
		musicButt = new GButton("Music", app.getWidth()/2-BUTTON_SIZE/2, app.getHeight()/2-2*BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
		musicButt.setFillColor(Color.BLUE);
		
		sfxButt = new GButton("SFX", app.getWidth()/2-BUTTON_SIZE/2, app.getHeight()/2-BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
		sfxButt.setFillColor(Color.RED);
		
		backButton = new GButton("Back", app.getWidth()/2-BUTTON_SIZE/2, app.getHeight()/2, BUTTON_SIZE, BUTTON_SIZE);
		backButton.setFillColor(Color.magenta);
	}

	@Override
	public void showContents() {
		program.add(background);
		
		program.add(musicButt);
		program.add(sfxButt);
		program.add(backButton);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		
		program.remove(musicButt);
		program.remove(sfxButt);
		program.remove(backButton);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == musicButt) {
			
		}
		
		if (obj == sfxButt) {
			
		}
		
		if (obj == backButton) {
			program.switchToMenu();
		}
	}
}
