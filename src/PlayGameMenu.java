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
	private GLabel levelSelect;
	private GLabel level1;
	private GLabel level2;
	private GLabel level3;
	private GLabel level4;
	private GLabel level5;
	private GLabel backButton;
	private GLabel confirmText;
	private final int BUTTON_SIZE = 50;
	private boolean level1Clicked = false;
	private boolean level2Clicked = false;


	public PlayGameMenu(MainApplication app) {
		super();
		program = app;
		
		background = new GImage("Background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);
		
		levelSelect = new GLabel("Level Select", app.getWidth()/2 - 50, app.getHeight()/2 - 150);
		levelSelect.setFont(new Font("Space", Font.BOLD, 20));
		levelSelect.setColor(Color.WHITE);
		
		level1 = new GLabel("1", app.getWidth()/2-2*BUTTON_SIZE, app.getHeight()/2-2*BUTTON_SIZE);
		level1.setFont(new Font("Space", Font.BOLD, 18));
		level1.setColor(Color.WHITE);
		
		level2 = new GLabel("2", app.getWidth()/2-BUTTON_SIZE + 10, app.getHeight()/2-2*BUTTON_SIZE);
		level2.setFont(new Font("Space", Font.BOLD, 18));
		level2.setColor(Color.WHITE);
		
		level3 = new GLabel("3", app.getWidth()/2-BUTTON_SIZE + 20, app.getHeight()/2-2*BUTTON_SIZE);
		level3.setFont(new Font("Space", Font.BOLD, 18));
		level3.setColor(Color.WHITE);
		
		backButton = new GLabel("Back", app.getWidth()/2-BUTTON_SIZE/2, app.getHeight()/2);
		backButton.setFont(new Font("Space", Font.BOLD, 18));
		backButton.setColor(Color.WHITE);
		
		confirmText = new GLabel("Start Game?", app.getWidth()/2 - 60, app.getHeight() / 2 - 100);
		confirmText.setFont(new Font("Space", Font.BOLD, 18));
		confirmText.sendToBack();
		confirmText.setColor(Color.WHITE);

		yes = new GLabel("Yes", app.getWidth()/2-2*BUTTON_SIZE + 25, app.getHeight()/2);
		yes.setFont(new Font("Space", Font.BOLD, 18));
		yes.sendToBack();
		yes.setColor(Color.GREEN);
		
		no = new GLabel("No", app.getWidth()/2 + 25, app.getHeight()/2);
		no.setFont(new Font("Space", Font.BOLD, 18));
		no.sendToBack();
		no.setColor(Color.RED);
	}

	@Override
	public void showContents() {
		program.add(yes);
		program.add(no);
		program.add(confirmText);
		program.add(background);
		program.add(levelSelect);
		program.add(level1);
		program.add(level2);
		program.add(level3);
		program.add(level4);
		program.add(level5);
		program.add(backButton);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(levelSelect);
		program.remove(level1);
		program.remove(level2);
		program.add(level3);
		program.add(level4);
		program.add(level5);
		program.remove(backButton);
		program.remove(yes);
		program.remove(no);
		program.remove(confirmText);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == level1) {
			this.level1Clicked = true;
			sendPopUpToFront();
		}
		else if(this.level1Clicked) {
			this.level1Clicked = false;
			if(obj == yes) {
				program.switchToMenu(); // for now to test it	
			}
			else if(obj == no) {
				program.switchToPlayGameMenu();
			}
		}
		if (obj == level2) {
			this.level2Clicked = true;
			System.out.println("obj == level2");
			sendPopUpToFront();
		}
		else if(level2Clicked) {
			this.level2Clicked = false;
			if(obj == yes) { // We can make a method called setScreen so we have less redundancy
				program.switchToPlayGameMenu(); // for now to test it
			}
			else if(obj == no) {
				program.switchToPlayGameMenu();
			}
		}
		if (obj == backButton) {
			program.switchToMenu();
		}
	}
	
	public void sendPopUpToFront() {
		confirmText.sendToFront();
		levelSelect.sendToBack();
		level1.sendToBack();
		level2.sendToBack();
		backButton.sendToBack();
		yes.sendToFront();
		no.sendToFront();
	}

}
