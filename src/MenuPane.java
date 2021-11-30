import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	
	// you will use program to get access to all of the GraphicsProgram calls
	private MainApplication program; 
								
	private GImage background;
	private GLabel playGame;
	private GLabel shipCustom;
	private GLabel options;
	private GLabel exit;
	private boolean exitBool;
	private GLabel confirm;
	private GLabel yes;
	private GLabel no;
	private final int SHIFT = 50;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		Font space = new Font("Space", Font.PLAIN, 20);
		
		background = new GImage("Background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);
		
		playGame = new GLabel("Play Game", app.getWidth()/2 - 2 *SHIFT + 40, app.getHeight()/2-SHIFT*2);
		playGame.setFont(space);
		playGame.setColor(Color.GREEN);
		
		shipCustom = new GLabel("Customize",app.getWidth()/2- 2 *SHIFT + 40, app.getHeight()/2-SHIFT);
		shipCustom.setFont(space);
		shipCustom.setColor(Color.PINK);
		
		options = new GLabel("Options", app.getWidth()/2- 2* SHIFT + 50, app.getHeight()/2);
		options.setFont(space);
		options.setColor(Color.CYAN);
		
		exit = new GLabel("Exit", app.getWidth()/2-SHIFT/2 - 10, app.getHeight()/2 + SHIFT);
		exit.setFont(space);
		exit.setColor(Color.RED);
		
		confirm = new GLabel("Are You Sure?", app.getWidth()/2 - 2*SHIFT, app.getHeight()/2-SHIFT*2);
		confirm.setFont(space);
		confirm.setColor(Color.WHITE);
		confirm.sendToBack();
		
		yes = new GLabel("Yes", app.getWidth()/2 - 2*SHIFT, app.getHeight()/2 - SHIFT);
		yes.setFont(new Font("Space", Font.BOLD, 20));
		yes.setColor(Color.WHITE);
		yes.sendToBack();
		
		no = new GLabel("No", app.getWidth()/2 - 2*SHIFT + 100, app.getHeight()/2 - SHIFT);
		no.setFont(space);
		no.setColor(Color.WHITE);
		no.sendToBack();
	
	}

	@Override
	public void showContents() {
		program.add(confirm);
		program.add(yes);
		program.add(no);
		program.add(background);
		program.add(playGame);
		program.add(shipCustom);
		program.add(options);
		program.add(exit);
	}

	@Override
	public void hideContents() {
		program.remove(confirm);
		program.remove(yes);
		program.remove(no);
		program.remove(background);
		program.remove(playGame);
		program.remove(shipCustom);
		program.remove(options);
		program.remove(exit);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == playGame) {
			program.switchToPlayGameMenu();
		}
		
		else if(obj == shipCustom) {
			program.switchToShipCustom();
		}
		
		else if (obj == options) {
			//program.switchToOptions();
			program.switchToGameOver();
		}
		
		else if(obj == exit) {
			exitBool = true;
			background.sendToFront();
			confirm.sendToFront();
			yes.sendToFront();
			no.sendToFront();
		}
		else if(exitBool) {
			exitBool = false;
			if(obj == yes) {
				System.exit(0);
			}
			if(obj == no) {
				program.switchToMenu();
			}
		}
	}
}
