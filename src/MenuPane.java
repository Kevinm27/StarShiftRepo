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

	public MenuPane(MainApplication app) {
		super();
		program = app;
		Font space = new Font("Space", Font.PLAIN, 20);
		Color purple = new Color(128,0,128);
		
		background = new GImage("Background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);
		
		playGame = new GLabel("Play Game", app.getWidth()/2 - 2 *50 + 40, app.getHeight()/2-50*2);
		playGame.setFont(space);
		playGame.setColor(purple);
		
		shipCustom = new GLabel("Customize",app.getWidth()/2- 2 *50 + 40, app.getHeight()/2-50);
		shipCustom.setFont(space);
		shipCustom.setColor(purple);
		
		options = new GLabel("Options", app.getWidth()/2- 2* 50 + 50, app.getHeight()/2);
		options.setFont(space);
		options.setColor(purple);
		
		exit = new GLabel("Exit", app.getWidth()/2-50/2 - 10, app.getHeight()/2 + 50);
		exit.setFont(space);
		exit.setColor(purple);
		
		confirm = new GLabel("Are You Sure?", app.getWidth()/2 - 85, app.getHeight()/2-50*2);
		confirm.setFont(new Font("space", Font.PLAIN, 25));
		confirm.setColor(Color.white);
		confirm.sendToBack();
		
		yes = new GLabel("Yes", app.getWidth()/2 - 75, app.getHeight()/2 - 50);
		yes.setFont(new Font("Space", Font.BOLD, 20));
		yes.setColor(Color.GREEN);
		yes.sendToBack();
		
		no = new GLabel("No", app.getWidth()/2 + 25 , app.getHeight()/2 - 50);
		no.setFont(space);
		no.setColor(Color.RED);
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
			program.switchToOptions();
		}
		
		else if(obj == exit) {
			exitBool = true;
			background.sendToFront();
			confirm.sendToFront();
			yes.sendToFront();
			no.sendToFront();
		}
		if(exitBool) {
			
			if(obj == yes) {
				System.exit(0);
			}
			if(obj == no) {
				exitBool = false;
				program.switchToMenu();
			}
		}
	}
}
