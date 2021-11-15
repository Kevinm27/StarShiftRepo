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
	private GButton returnButton;
	private GButton leftArrow;
	private GButton rightArrow;
	private GButton save;
	private final int BUTTON_SIZE = 50;

	
	public ShipCustomPane(MainApplication app) {
		super();
		program = app;
		background = new GImage("background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);

		title = new GLabel("Ship\nCustomization", app.getWidth() / 2, app.getHeight() / 2);
		title.setFont(new Font("Serif", Font.BOLD, 20));
	
		returnButton = new GButton("Return to Menu",app.getWidth()/2 - 200, app.getHeight()/2-BUTTON_SIZE, BUTTON_SIZE * 2, BUTTON_SIZE);
		
		leftArrow = new GButton("Arrow.jpg", app.getWidth()/2 + 200, app.getHeight()/2-BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
		
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(background);
		program.add(title);
		program.add(returnButton);
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(background);
		program.remove(title);
		program.add(returnButton);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

}
