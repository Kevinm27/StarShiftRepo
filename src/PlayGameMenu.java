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
	private GLabel endlessMode;
	//private GLabel level1;
	//private GLabel level2;
	//private GLabel level3;
	//private GLabel level4;
	//private GLabel level5;
	private GLabel backButton;
	private GLabel confirmText;
	private final int SHIFT = 50;
	private boolean endlessModeClicked = false;
	//private boolean level1Clicked = false;
	//private boolean level2Clicked = false;
	//private boolean level3Clicked = false;
	//private boolean level4Clicked = false;
	//private boolean level5Clicked = false;
	
	public static int levelSelected;

	public PlayGameMenu(MainApplication app) {
		super();
		program = app;
		
		background = new GImage("Background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);
		
		///levelSelect = new GLabel("Level Select", app.getWidth()/2 - 45, app.getHeight()/2 - 150);
		///levelSelect.setFont(new Font("Space", Font.BOLD, 20));
		///levelSelect.setColor(Color.WHITE);
		
		///endlessMode = new GLabel("Endless", app.getWidth()/2-SHIFT/2, app.getHeight()/2 - 2*SHIFT);
		///endlessMode.setFont(new Font("Space", Font.BOLD, 18));
		///endlessMode.setColor(Color.white);
		/*
		level1 = new GLabel("1", app.getWidth()/2 - 2*SHIFT + 35, app.getHeight()/2-SHIFT);
		level1.setFont(new Font("Space", Font.BOLD, 18));
		level1.setColor(Color.WHITE);
		
		level2 = new GLabel("2", app.getWidth()/2 - 2*SHIFT + 70, app.getHeight()/2-SHIFT);
		level2.setFont(new Font("Space", Font.BOLD, 18));
		level2.setColor(Color.WHITE);
		
		level3 = new GLabel("3", app.getWidth()/2 - 2*SHIFT + 105, app.getHeight()/2-SHIFT);
		level3.setFont(new Font("Space", Font.BOLD, 18));
		level3.setColor(Color.WHITE);
		
		level4 = new GLabel("4", app.getWidth()/2 - 2*SHIFT + 140, app.getHeight()/2-SHIFT);
		level4.setFont(new Font("Space", Font.BOLD, 18));
		level4.setColor(Color.WHITE);
		
		level5 = new GLabel("5", app.getWidth()/2 - 2*SHIFT + 175, app.getHeight()/2-SHIFT);
		level5.setFont(new Font("Space", Font.BOLD, 18));
		level5.setColor(Color.WHITE);
		*/
		///backButton = new GLabel("Back", app.getWidth()/2-15, app.getHeight()/2);  //app.getWidth()/2-SHIFT/2
		///backButton.setFont(new Font("Space", Font.BOLD, 18));
		///backButton.setColor(Color.WHITE);
		
		confirmText = new GLabel("Start Game?", app.getWidth()/2 - 90, app.getHeight() / 2 - 100);
		confirmText.setFont(new Font("Space", Font.BOLD, 30));
		confirmText.setColor(Color.WHITE);

		yes = new GLabel("Yes", app.getWidth()/2-2*SHIFT + 25, app.getHeight()/2);
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
		//program.add(levelSelect);
		//program.add(endlessMode);
		/*
		program.add(level1);
		program.add(level2);
		program.add(level3);
		program.add(level4);
		program.add(level5);
		*/
		//program.add(backButton);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(levelSelect);
		program.remove(endlessMode);
		/*
		program.remove(level1);
		program.remove(level2);
		program.remove(level3);
		program.remove(level4);
		program.remove(level5);
		*/
		program.remove(backButton);
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
		
		/*
		if(obj == endlessMode) {
			endlessModeClicked = true;
			sendPopUpToFront();
		}
		else if(endlessModeClicked) {
			if (obj == yes) {
				endlessModeClicked = false;
				program.switchToLevel();
				levelSelected = 0;
			}
		}
		/*
		if (obj == level1) {
			level1Clicked = true;
			sendPopUpToFront();
		}
		else if(level1Clicked) {	
			if(obj == yes) {
				level1Clicked = false;
				//program.switchToLevel1(); 	
				//program.switchToPlay();	// will likely need to change input to levelSelected to determine which level variable gets called
				levelSelected = 1;
			}
			else if(obj == no) {
				level1Clicked = false;
				program.switchToPlayGameMenu();
			}
		}
		if (obj == level2) {
			this.level2Clicked = true;
			sendPopUpToFront();
		}
		else if(level2Clicked) {
			if(obj == yes) { // We can make a method called setScreen so we have less redundancy
				level2Clicked = false;
				program.switchToPlayGameMenu(); 
				//program.switchToPlay();
				levelSelected = 2;
			}
			else if(obj == no) {
				level2Clicked = false;
				program.switchToPlayGameMenu();
			}
		}
		if(obj == level3) {
			level3Clicked = true;
			sendPopUpToFront();
		}
		else if(level3Clicked) {
			if(obj == yes) {
				level3Clicked = false;
				program.switchToPlayGameMenu();
				//program.switchToPlay();
				levelSelected = 3;
			}
			else if(obj == no){
				level3Clicked = false;
				program.switchToPlayGameMenu();
			}
		}
		if(obj == level4) {
			level4Clicked = true;
			sendPopUpToFront();
		}
		else if(level4Clicked) {
			
			if(obj == yes) {
				level4Clicked = false;
				program.switchToPlayGameMenu();
				//program.switchToPlay();
				levelSelected = 4;
			}
			else if(obj == no){
				level4Clicked = false;
				program.switchToPlayGameMenu();
			}
		}
		if(obj == level5) {
			level5Clicked = true;
			sendPopUpToFront();
		}
		else if(level5Clicked) {
			if(obj == yes) {
				level5Clicked = false;
				program.switchToPlayGameMenu();
				//program.switchToPlay();
				levelSelected = 5;
			}
			else if(obj == no){
				level5Clicked = false;
				program.switchToPlayGameMenu();
			}
		}
		
		if (obj == backButton) {
			program.switchToMenu();
		}
	}
	
	/*
	public void sendPopUpToFront() {
		background.sendToFront();
		yes.sendToFront();
		no.sendToFront();
		confirmText.sendToFront();

	}
	/*
	public void sendPopUpToback() {
		confirmText.sendToBack();
		levelSelect.sendToFront();
		level1.sendToFront();
		level2.sendToFront();
		backButton.sendToFront();
		yes.sendToBack();
		no.sendToBack();
	}
	*/

}
