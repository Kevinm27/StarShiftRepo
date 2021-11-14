import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class PlayGameMenu extends GraphicsPane {
	
	private MainApplication program;
	
	private GImage background;
	private GButton yes;
	private GButton no;
	private GButton level1;
	private GButton level2;
	private GButton backButton;
	private GLabel confirmText;
	private final int BUTTON_SIZE = 50;
	private boolean level1Clicked = false;


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
		
		confirmText = new GLabel("Start Game?", app.getWidth()/2 - 60, app.getHeight() / 2 - 100);
		confirmText.setFont(new Font("Space", Font.BOLD, 18));
		confirmText.setColor(Color.white);
		confirmText.setVisible(false);

		yes = new GButton("Yes", app.getWidth()/2-2*BUTTON_SIZE + 25, app.getHeight()/2, BUTTON_SIZE, BUTTON_SIZE);
		yes.setFillColor(Color.BLUE);
		yes.setVisible(false);
		
		no = new GButton("No", app.getWidth()/2 + 25, app.getHeight()/2, BUTTON_SIZE, BUTTON_SIZE);
		no.setFillColor(Color.RED);
	}

	@Override
	public void showContents() {
		program.add(background);
		program.add(level1);
		program.add(level2);
		program.add(backButton);
		program.add(yes);
		program.add(no);
		program.add(confirmText);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(level1);
		program.remove(level2);
		program.remove(backButton);
		program.remove(yes);
		program.remove(no);
		program.remove(confirmText);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		//popUp.sendToBack();
		if (obj == level1) {
			this.level1Clicked = true;
			confirmText.setVisible(true);
			
			level1.sendToBack();
			level2.sendToBack();
			backButton.sendToBack();
			System.out.println("mousepressed0");
			yes.sendToFront();
			yes.setVisible(true);
			no.setVisible(true);
			no.sendToFront();

		}
		else if(this.level1Clicked) {
			System.out.println("mousepressed");
			this.level1Clicked = false;
			if(obj == yes) {
				confirmText.setVisible(false);
				yes.sendToBack();
				yes.setVisible(false);
				no.sendToBack();
				no.setVisible(false);
				level1.sendToFront();
				level2.sendToFront();
				backButton.sendToFront();
				program.switchToMenu(); // for now to test it
				
			}
			if(obj == no) {
				confirmText.setVisible(false);
				yes.sendToBack();
				yes.setVisible(false);
				no.setVisible(false);
				no.sendToBack();
				level1.sendToFront();
				level2.sendToFront();
				backButton.sendToFront();
				program.switchToPlayGameMenu();
			}
			
		}
		/*
		if (obj == level2) {
			obj = program.getElementAt(e.getX(), e.getY());
			if(obj == yes) {
				//switch to game screen
				program.switchToMenu(); // for now to play test
			}
			if(obj == no) {
				program.switchToMenu();
			}
		}
		*/
		
		if (obj == backButton) {
			program.switchToMenu();
		}
	}

}
