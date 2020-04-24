package main.graphical_interface.gameWindows;

import javafx.scene.layout.StackPane;

public abstract class AbstractGameWindow {

	private StackPane mainWindow;
	
	public AbstractGameWindow() {
		// TODO Auto-generated constructor stub
	}
	
	public void createWindow() {}
	
	public StackPane getWindow() {
		return this.mainWindow;
	}
	

}
