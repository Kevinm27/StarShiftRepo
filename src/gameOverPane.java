import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
public class gameOverPane extends GraphicsPane{

	private MainApplication program;
	private GImage background;
	private GLabel title;
	private GLabel title1;
	private GLabel yes;
	private GLabel no;
	private final int SHIFT = 50;

	
	public gameOverPane(MainApplication app) {
		super();
		program = app;
		Font space = new Font("Space", Font.PLAIN, 20);
		background = new GImage("background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);

		title = new GLabel("Game Over", app.getWidth() / 2 - 55, app.getHeight() / 2 - 100);
		title.setFont(space);
		title.setColor(Color.white);
		
		title1 = new GLabel("Play Again?", app.getWidth() / 2 - 55, app.getHeight() / 2 - 80);
		title1.setFont(space);
		title1.setColor(Color.white);
		
		yes = new GLabel("Yes", app.getWidth() / 2, app.getHeight() / 2);
		yes.setFont(space);
		yes.setColor(Color.white);
	
		
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(background);
		program.add(title);
		program.add(title1);
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(background);
		program.remove(title);
		program.remove(title1);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
	}
		

}
