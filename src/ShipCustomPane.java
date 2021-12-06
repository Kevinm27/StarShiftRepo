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
	private int num = 0;
	private final int SHIFT = 50;
	
	public static String shipColor;

	
	public ShipCustomPane(MainApplication app) {
		super();
		program = app;
		Color purple = new Color(128,0,128);
		Font space = new Font("Space", Font.PLAIN, 20);
		background = new GImage("background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);

		title = new GLabel("Ship Customization", app.getWidth() / 2 - 95, app.getHeight() / 2 - 200);
		title.setFont(space);
		title.setColor(Color.white);
	
		returnButton = new GLabel("Return and Save", app.getWidth() / 2 - 85, app.getHeight()/2);
		returnButton.setFont(space);
		returnButton.setColor(purple);
		
		
		change = new GLabel("Use arrow keys to change color of the ship", app.getWidth()/2 - app.getWidth()/4, app.getHeight() / 2 + app.getHeight()/6);
		change.setFont(space);
		change.setColor(Color.white);
		
		leftArrow = new GImage("rightArrow.jpg", app.getWidth()/2 - app.getWidth()/10 + SHIFT/2, app.getHeight() / 2 - 2*SHIFT);
		leftArrow.setSize(55,40);
		leftArrow.rotate(180);
		leftArrow.movePolar(leftArrow.getWidth(), 180);
		leftArrow.movePolar(leftArrow.getHeight()+1, 270);			// +1 is debatable on whether or not to include

		rightArrow = new GImage("rightArrow.jpg", app.getWidth()/2 + app.getWidth()/10, app.getHeight() / 2 - 2*SHIFT);
		rightArrow.setSize(55,40);
	
		ship = new GImage("BigGreenShip.png", app.getWidth() / 2 - 50, app.getHeight() / 2 - 2*SHIFT - 25);
		ship.setSize(75,75);
		

	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub

		program.add(background);
		program.add(title);
		program.add(returnButton);
		program.add(leftArrow);
		program.add(rightArrow);
		program.add(change);
		program.add(ship);
		
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
				ship.setImage("BigBrownShip.png");
				ship.setSize(75,75);
				shipColor = "Brown";
				
			}
			else if(num == 1) {
				num++;
				ship.setImage("BigOliveShip.png");		
				ship.setSize(75,75);
				shipColor = "Olive";
			}
			else {
				num = 0;
				ship.setImage("BigGreenShip.png");
				ship.setSize(75,75);
				shipColor = "Green";
			}
		}
		
	}

}
