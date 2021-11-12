import java.awt.Color;
import java.awt.event.MouseEvent;
import acm.graphics.GImage;
import acm.graphics.GObject;
//IF BRIAN FINDS A BETTER WAY OF CREATING POP UPS THEN USE THIS INSTEAD
public class PopUpPane extends GraphicsPane {
	private MainApplication program;
	private GImage background;
	private GButton yes;
	private GButton no;
	private GButton backButton;
	private final int BUTTON_HEIGHT = 50;
	private final int BUTTON_WIDTH = 100;
	
	public PopUpPane(MainApplication app, String choice) {
		super();
		program = app;
		background = new GImage("Background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);
		
		switch(choice) {
			case "Start-Game": //This will be the "Are you sure you want to start pane
				yes = new GButton("Yes", app.getWidth()/2-BUTTON_WIDTH/2, app.getHeight()/2, BUTTON_WIDTH, BUTTON_HEIGHT);
				yes.setFillColor(Color.green);
				
				no = new GButton("Yes", app.getWidth(), app.getHeight()/2, BUTTON_WIDTH, BUTTON_HEIGHT);
				no.setFillColor(Color.red);
				break;
			case "PopMenu":	  //This will be the in game pause menu
				break;
			case "PopOptions":  //This will be the in game options menu that let's us toggle music/sfx
				break;
			case "Quit-Game": //Self Explanatory
				break;
				
		}
		
		
		backButton = new GButton("Back", app.getWidth()/2-BUTTON_WIDTH/2, app.getHeight()/2 + 400, BUTTON_WIDTH, BUTTON_HEIGHT);
		backButton.setFillColor(Color.magenta);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public void showContents() {
		program.add(background);
		program.add(yes);
		program.add(no);
		program.add(backButton);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(yes);
		program.remove(no);
		program.remove(backButton);
	}
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
	
		
	}

}
