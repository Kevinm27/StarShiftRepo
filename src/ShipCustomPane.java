import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class ShipCustomPane extends GraphicsPane{
	private MainApplication program;
	private GImage background;
	private GLabel title;
	private GLabel returnButton;
	private GLabel change;
	private GImage leftArrow;
	private GImage rightArrow;
	private GImage ship;
	private GButton save;
	private GLabel ok;
	private int num = 0;
	private final int SHIFT = 50;

	
	public ShipCustomPane(MainApplication app) {
		super();
		program = app;
		background = new GImage("background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);

		title = new GLabel("Ship Customization", app.getWidth() / 2 - 100, app.getHeight() / 2 - 200);
		title.setFont(new Font("Serif", Font.BOLD, 25));
		title.setColor(Color.white);
	
		returnButton = new GLabel("Return to Menu",app.getWidth()/2 - 250, app.getHeight()/2-SHIFT);
		returnButton.setFont(new Font("Serif", Font.BOLD, 20));
		returnButton.setColor(Color.white);
		
		
		change = new GLabel("Ship Color", app.getWidth()/2 + 125, app.getHeight() / 2 - SHIFT + 25);
		change.setFont(new Font("Serif", Font.BOLD, 20));
		change.setColor(Color.magenta);
		
		leftArrow = new GImage("leftArrow.jpg", app.getWidth()/2 + 100, app.getHeight()/2 - SHIFT + 50); //fix later
		leftArrow.setSize(55,40);
		rightArrow = new GImage("rightArrow.jpg", app.getWidth()/2 + 200, app.getHeight()/2-SHIFT + 50);
		rightArrow.setSize(55,40);
	
		ship = new GImage("GreenShip.png", app.getWidth() / 2 - 80, app.getHeight() / 2 - 2*SHIFT - SHIFT);
		ship.setSize(150,150);
		
		ok = new GLabel("Ship Saved", app.getWidth()/2 - 250, app.getHeight()/2 - SHIFT);
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
				ship.setImage("OliveShip.png");
				ship.setSize(150,150);
							//change the gimage for the ship temporarily
			}
			else if(num == 1) {
				num++;
				ship.setImage("BrownShip.png");
				ship.setSize(150,150);
			}
			else {
				num = 0;
				ship.setImage("GreenShip.png");
				ship.setSize(150,150);
			}
		}
		else if(obj == rightArrow) {
			//change the gimage for the ship temporarily
			if(num == 0) {
				num++;
				ship.setImage("GreenShip.png");
				ship.setSize(150,150);
			}
			else if(num == 1) {
				num++;
				ship.setImage("OliveShip.png");		
				ship.setSize(150,150);
			}
			else {
				num = 0;
				ship.setImage("BrownShip.png");
				ship.setSize(150,150);
			}
		}
		else if(obj == save) {
			//save the ship / change it permanently
			
		}
		
	}

}
