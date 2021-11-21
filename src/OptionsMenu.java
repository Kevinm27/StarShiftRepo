import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class OptionsMenu extends GraphicsPane {
private MainApplication program;
	
	private GImage background;
	
	private GLabel optionsTitle;
	private GLabel toggleMusicTitle;
	private GLabel toggleSFXTitle;
	private GLabel onButton0;
	private boolean on0 = true;
	private GLabel offButton0;
	private GLabel onButton1;
	private boolean on1 = true;
	private GLabel offButton1;
	private GLabel underline0;
	private GLabel underline1;

	private GLabel backButton;
	private final int SHIFT = 50;

	public OptionsMenu(MainApplication app) {
		super();
		program = app;
		
		Color purple = new Color(128,0,128);
		Font space = new Font("American Typewriter", Font.PLAIN, 20);
		
		background = new GImage("Background.jpg");
		background.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);
		
		optionsTitle = new GLabel("Options", app.getWidth() / 2 - 50, app.getHeight() / 2 - 100);
		optionsTitle.setFont(space);
		optionsTitle.setColor(purple);
		
		toggleMusicTitle = new GLabel("Toggle Music", app.getWidth() / 2 - 75, app.getHeight() / 2 - 75);
		toggleMusicTitle.setFont(space);
		toggleMusicTitle.setColor(purple);
		
		onButton0 = new GLabel("On", app.getWidth() / 2 - 60, app.getHeight() / 2 - 50);
		onButton0.setFont(space);
		onButton0.setColor(purple);
		
		underline0 = new GLabel("___", app.getWidth() / 2 - 63, app.getHeight() / 2 - 51);
		underline0.setFont(space);
		underline0.setColor(purple);
		
		
		offButton0 = new GLabel("Off", app.getWidth() / 2 + 5, app.getHeight() / 2 - 50);
		offButton0.setFont(space);
		offButton0.setColor(purple);
		
		toggleSFXTitle = new GLabel("Toggle SFX", app.getWidth() / 2 - 70, app.getHeight() / 2 - 25);
		toggleSFXTitle.setFont(space);
		toggleSFXTitle.setColor(purple);
		
		onButton1 = new GLabel("On", app.getWidth() / 2 - 60, app.getHeight() / 2);
		onButton1.setFont(space);
		onButton1.setColor(purple);
		
		underline1 = new GLabel("___", app.getWidth() / 2 - 63, app.getHeight() / 2 + 1);
		underline1.setFont(space);
		underline1.setColor(purple);
		
		offButton1 = new GLabel("Off", app.getWidth() / 2 + 5, app.getHeight() / 2);
		offButton1.setFont(space);
		offButton1.setColor(purple);
		
		backButton = new GLabel("Return", app.getWidth() / 2 - 45, app.getHeight() / 2 + 25);
		backButton.setFont(space);
		backButton.setColor(purple);
		

	}

	@Override
	public void showContents() {
		program.add(background);
		program.add(optionsTitle);
		program.add(toggleMusicTitle);
		program.add(onButton0);
		program.add(underline0);
		program.add(offButton0);
		program.add(toggleSFXTitle);
		program.add(backButton);
		program.add(onButton1);
		program.add(underline1);
		program.add(offButton1);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(optionsTitle);
		program.remove(toggleMusicTitle);
		program.remove(onButton0);
		program.remove(underline0);
		program.remove(offButton0);
		program.remove(toggleSFXTitle);
		program.remove(backButton);
		program.remove(onButton1);
		program.remove(underline1);
		program.remove(offButton1);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == onButton0 && !on0) {
			on0 = true;
			underline0.move(-1 * SHIFT - 18, 0);
			musicAndSFX.playMusic();//play music
			
		}
		else if(obj == offButton0 && on0) {
			on0 = false;
			underline0.move(SHIFT + 18, 0);
			musicAndSFX.pauseMusic();//mute/pause music
		}
		else if(obj == onButton1 && !on1) {
			on1 = true;
			underline1.move(-1 * SHIFT - 18, 0);
			musicAndSFX.unmuteSFX();//unmute sound effects
		}
		else if(obj == offButton1 && on1) {
			on1 = false;
			underline1.move(SHIFT + 18, 0);
			musicAndSFX.muteSFX();//mute/pause sfx
		}
		
		if (obj == backButton) {
			program.switchToMenu();
		}
	}
}
