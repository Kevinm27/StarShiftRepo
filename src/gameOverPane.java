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
	public static GLabel scoreLabel;
	private GLabel yes;
	private GLabel no;
	private final int SHIFT = 50;
	private Score score;
	
	public gameOverPane(MainApplication app) {
		super();
		program = app;
		Font space = new Font("Space", Font.PLAIN, 20);
		Font lose = new Font("Typewriter", Font.BOLD, 40);
		Color purple = new Color(128,0,128);
		background = new GImage("background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);
		
		title = new GLabel("Game Over", app.getWidth() / 2 - 100, app.getHeight() / 2 - 150);
		title.setFont(lose);
		title.setColor(Color.red);
		
		title1 = new GLabel("Play Again?", app.getWidth() / 2 - 55, app.getHeight() / 2 - 80);
		title1.setFont(space);
		title1.setColor(purple);
		
		scoreLabel = new GLabel("Final Score: " + Score.getScore(), app.getWidth() / 2 - 140, app.getHeight() / 2 - 110);
		scoreLabel.setFont(lose);
		scoreLabel.setColor(new Color(128, 0, 128));
		
		yes = new GLabel("Yes", app.getWidth() / 2 - 75, app.getHeight() / 2 );
		yes.setFont(space);
		yes.setColor(Color.green);
		
		no = new GLabel("No", app.getWidth() / 2  + 50, app.getHeight() / 2);
		no.setFont(space);
		no.setColor(Color.red);
		
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(background);
		program.add(title);
		program.add(title1);
		program.add(yes);
		program.add(no);
		program.add(scoreLabel);
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(background);
		program.remove(title);
		program.remove(title1);
		program.remove(yes);
		program.remove(no);
		program.remove(scoreLabel);
	}
	
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
		

}
