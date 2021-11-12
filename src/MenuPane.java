import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	
	// you will use program to get access to all of the GraphicsProgram calls
	private MainApplication program; 
								
	private GImage background;
	private GButton rect;
	private GButton plyGameButt;
	private GButton optionsButt;
	private final int BUTTON_SIZE = 50;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		
		background = new GImage("Background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);
		
		rect = new GButton("Next", app.getWidth()/2-BUTTON_SIZE/2, app.getHeight()/2, BUTTON_SIZE, BUTTON_SIZE);
		rect.setFillColor(Color.RED);
		
		plyGameButt = new GButton("Play Game", app.getWidth()/2-BUTTON_SIZE/2, app.getHeight()/2-BUTTON_SIZE*2, BUTTON_SIZE, BUTTON_SIZE);
		plyGameButt.setFillColor(Color.BLUE);
		
		optionsButt = new GButton("Options", app.getWidth()/2-BUTTON_SIZE/2, app.getHeight()/2-BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
		optionsButt.setFillColor(Color.BLUE);
	}

	@Override
	public void showContents() {
		program.add(background);
		
		program.add(rect);
		program.add(plyGameButt);
		program.add(optionsButt);
	}

	@Override
	public void hideContents() {
		program.remove(background);

		program.remove(rect);
		program.remove(plyGameButt);
		program.remove(optionsButt);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == rect) {
			program.switchToSome();
		}
		
		if (obj == plyGameButt) {
			program.switchToPlayGameMenu();
		}
		
		if (obj == optionsButt) {
			program.switchToOptions();
		}
	}
}
