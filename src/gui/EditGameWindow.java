package gui;

import javax.swing.JFrame;

import gameObjects.instance.GameInstance;

public class EditGameWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9190386402174391670L;
	GameInstance gi;
	public EditGameWindow(GameInstance gi, LanguageHandler lh) {
		this.gi = gi;
		setContentPane(new EditGamePanel(gi, lh));
		setSize(500, 400);
	}

}
