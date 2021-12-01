import java.awt.Color;

import java.awt.Font;
import java.awt.event.MouseEvent;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class ShipCustomPane extends GraphicsPane {
	private MainApplication program;
	private GImage background;
	private GLabel title;
	private GLabel returnButton;
	private GLabel change;
	private GImage leftArrow;
	private GImage rightArrow;
	private GImage ship;
	private GLabel save;
	private GLabel shipSaved;
	private GLabel ok;
	private int num = 0;
	private final int SHIFT = 50;
	
	public static String shipColor;

	
	public ShipCustomPane(MainApplication app) {
		super();
		program = app;
		Font space = new Font("Space", Font.PLAIN, 20);
		background = new GImage("background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);

		title = new GLabel("Ship Customization", app.getWidth() / 2 - 98, app.getHeight() / 2 - 200);
		title.setFont(space);
		title.setColor(Color.white);
	
		returnButton = new GLabel("Return to Menu",app.getWidth()/2 - 250, app.getHeight()/2-SHIFT);
		returnButton.setFont(space);
		returnButton.setColor(Color.white);
		
		
		change = new GLabel("Ship Color", app.getWidth()/2 + 125, app.getHeight() / 2 - SHIFT + 25);
		change.setFont(space);
		change.setColor(Color.white);
		
		leftArrow = new GImage("rightArrow.jpg", app.getWidth()/2 + 200, app.getHeight()/2-SHIFT + 50);
		leftArrow.setSize(55,40);
		leftArrow.rotate(180);
		leftArrow.movePolar(leftArrow.getWidth(), 180);
		leftArrow.movePolar(leftArrow.getHeight()+1, 270);			// +1 is debatable on whether or not to include

		rightArrow = new GImage("rightArrow.jpg", app.getWidth()/2 + 200, app.getHeight()/2-SHIFT + 50);
		rightArrow.setSize(55,40);
	
		ship = new GImage("BigGreenShip.png", app.getWidth() / 2 - 50, app.getHeight() / 2 - 2*SHIFT - 25);
		ship.setSize(75,75);
		
		save = new GLabel("Save", app.getWidth() / 2 - 28, app.getHeight() / 2 + 3*SHIFT - 50);
		save.setFont(space);
		save.setColor(Color.white);
		
		shipSaved = new GLabel("Ship Saved", app.getWidth() / 2 - 50, app.getHeight() / 2 - SHIFT - 25);
		shipSaved.setFont(space);
		shipSaved.setColor(Color.white);
		
		
		ok = new GLabel("Ok", app.getWidth()/2 - 28, app.getHeight()/2 + 2 * SHIFT - 50);
		ok.setFont(space);
		ok.setColor(Color.white);
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(shipSaved);
		program.add(ok);
		program.add(background);
		program.add(title);
		program.add(returnButton);
		program.add(leftArrow);
		program.add(rightArrow);
		program.add(change);
		program.add(ship);
		program.add(save);
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(background);
		program.remove(title);
		program.remove(returnButton);
		program.remove(leftArrow);
		program.remove(rightArrow);
		program.remove(change);
		program.remove(ship);
		program.remove(save);
		program.remove(ok);
		program.remove(shipSaved);
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == returnButton) {
			program.switchToMenu();
		}
		else if(obj == leftArrow) {
			if(num == 0){
				num++;
				ship.setImage("BigOliveShip.png");
				ship.setSize(75,75);
							//change the gimage for the ship temporarily
				shipColor = "Olive";
			}
			else if(num == 1) {
				num++;
				ship.setImage("BigBrownShip.png");
				ship.setSize(75,75);
				shipColor = "Brown";
			}
			else {
				num = 0;
				ship.setImage("BigGreenShip.png");
				ship.setSize(75,75);
				shipColor = "Green";
			}
		}
		else if(obj == rightArrow) {
			//change the display for the ship temporarily
			if(num == 0) {
				num++;
				ship.setImage("BigGreenShip.png");
				ship.setSize(75,75);
				shipColor = "Green";
			}
			else if(num == 1) {
				num++;
				ship.setImage("BigOliveShip.png");		
				ship.setSize(75,75);
				shipColor = "Olive";
			}
			else {
				num = 0;
				ship.setImage("BigBrownShip.png");
				ship.setSize(75,75);
				shipColor = "Brown";
			}
		}
		else if(obj == save) {
			//save the ship / change it permanently
			background.sendToFront();
			ok.sendToFront();
			shipSaved.sendToFront();

		}
		else if(obj == ok) {
			program.switchToShipCustom();
		}
	}

}
