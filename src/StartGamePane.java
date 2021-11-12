import java.awt.Color;
import java.awt.event.MouseEvent;
import acm.graphics.GImage;
import acm.graphics.GObject;
	
public class StartGamePane extends GraphicsPane {
	
		private MainApplication program;
		private GImage background;
		private GButton yes;
		private GButton no;
		private final int BUTTON_HEIGHT = 50;
		private final int BUTTON_WIDTH = 100;
		
		public StartGamePane(MainApplication app) {
			super();
			program = app;
			background = new GImage("Background.jpg");
			background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);
			
			yes = new GButton("Yes", app.getWidth()/2-BUTTON_WIDTH/2, app.getHeight()/2, BUTTON_WIDTH, BUTTON_HEIGHT);
			yes.setFillColor(Color.green);
			
			no = new GButton("Yes", app.getWidth(), app.getHeight()/2, BUTTON_WIDTH, BUTTON_HEIGHT);
			no.setFillColor(Color.red);

		}
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub

		}
		
		@Override
		public void showContents() {
			program.add(background);
			program.add(yes);
			program.add(no);
		}

		@Override
		public void hideContents() {
			program.remove(background);
			program.remove(yes);
			program.remove(no);
		}
		
		public void mousePressed(MouseEvent e) {
			GObject obj = program.getElementAt(e.getX(), e.getY());
			
			if(obj == yes) {
				//switch to starting the game
			}
			if(obj == no) {
				program.switchToMenu();
			}
			
		}

}


