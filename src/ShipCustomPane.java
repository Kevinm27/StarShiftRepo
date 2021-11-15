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
	private GImage shipType;
	private GLabel change;
	private GImage leftArrow;
	private GImage rightArrow;
	private GButton save;
	private final int BUTTON_SIZE = 50;

	
	public ShipCustomPane(MainApplication app) {
		super();
		program = app;
		background = new GImage("background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);

		title = new GLabel("Ship\nCustomization", app.getWidth() / 2 - 100, app.getHeight() / 2 - 200);
		title.setFont(new Font("Serif", Font.BOLD, 25));
		title.setColor(Color.white);
	
		returnButton = new GLabel("Return to Menu",app.getWidth()/2 - 250, app.getHeight()/2-BUTTON_SIZE);
		returnButton.setFont(new Font("Serif", Font.BOLD, 20));
		returnButton.setColor(Color.white);
		
		
		change = new GLabel("Ship Color", app.getWidth()/2 + 125, app.getHeight() / 2 - BUTTON_SIZE + 25);
		change.setFont(new Font("Serif", Font.BOLD, 20));
		change.setColor(Color.magenta);
		
		leftArrow = new GImage("leftArrow.jpg", app.getWidth()/2 + 100, app.getHeight()/2 - BUTTON_SIZE + 50); //fix later
		leftArrow.setSize(55,40);
		rightArrow = new GImage("rightArrow.jpg", app.getWidth()/2 + 200, app.getHeight()/2-BUTTON_SIZE + 50);
		rightArrow.setSize(55,40);
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
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(background);
		program.remove(title);
		program.add(returnButton);
		program.add(leftArrow);
		program.add(rightArrow);
		program.add(change);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == returnButton) {
			program.switchToMenu();
		}
		else if(obj == leftArrow) {
			//change the gimage for the ship temporarily
			program.switchToMenu();
		}
		else if(obj == rightArrow) {
			//change the gimage for the ship temporarily
			program.switchToMenu();
		}
		else if(obj == save) {
			//save the ship / change it permanently
			program.switchToMenu();
		}
		
	}

}
