import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class PlayGameMenu extends GraphicsPane {
	
	private MainApplication program;	
	private GImage background;
	private GLabel yes;
	private GLabel no;
	private GLabel confirmText;
	
	public static int levelSelected;

	public PlayGameMenu(MainApplication app) {
		super();
		program = app;
		background = new GImage("Background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);
		
		confirmText = new GLabel("Start Game?", app.getWidth()/2 - 90, app.getHeight() / 2 - 100);
		confirmText.setFont(new Font("Space", Font.BOLD, 30));
		confirmText.setColor(Color.white);

		yes = new GLabel("Yes", app.getWidth()/2-2*50 + 25, app.getHeight()/2);
		yes.setFont(new Font("Space", Font.BOLD, 25));
		yes.setColor(Color.GREEN);
		
		no = new GLabel("No", app.getWidth()/2 + 25, app.getHeight()/2);
		no.setFont(new Font("Space", Font.BOLD, 25));
		no.setColor(Color.RED);
	}

	@Override
	public void showContents() {
		
		program.add(background);
		program.add(yes);
		program.add(no);
		program.add(confirmText);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(yes);
		program.remove(no);
		program.remove(confirmText);
	}
	//Whenever the mouse is pressed a GObject is created and is compared with all the GLabels 
	//If a level label is clicked it sets a boolean and shows a mini screen that pop ups a yes or no label
	//From here a simple if statement is used to switch to a different screen
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == yes) {
			program.switchToLevel();
		}
		else if(obj == no) {
			program.switchToMenu();
		}
	}
	

}
