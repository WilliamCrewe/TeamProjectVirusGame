package main.graphical_interface.gameWindows;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class StartMenuWindow extends AbstractGameWindow {

	private StackPane mainWindow;
	
	private final Image background = new Image("/main/graphical_interface/util/BrickExtraGloomy.jpg");
	
	public StartMenuWindow() {
		createWindow();
	}
	
	@Override
	public void createWindow() {
		this.mainWindow = new StackPane();
		// Add the background image to the menu page.
		this.mainWindow = setupBackground(mainWindow);
		
		AnchorPane base = new AnchorPane();
		
		// Create the Main menu VBox.
		MainMenu menu = new MainMenu();
		VBox mainMenu = menu.getMenu1();
		VBox difficultyMenu = menu.getMenu2();
		
		base.getChildren().add(mainMenu);
		AnchorPane.setLeftAnchor(mainMenu, 30.0);
		AnchorPane.setBottomAnchor(mainMenu, 50.0);
		
		//Setup Music
		
		/*
		File f = new File("util/SpiresMusic.mp3");
		System.out.println(f.getAbsolutePath());
		Media m = new Media(f.toURI().toString());
		System.out.println(m);
		
		//Media backgroundSound = new Media(new File("util/SpiresMusic.ogg").toURI().toString());
		
		MediaPlayer mediaPlayer = new MediaPlayer(backgroundSound);
		mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
		mediaPlayer.setVolume(30.0);
		mediaPlayer.play();
		*/

		this.mainWindow.getChildren().add(base);
		this.mainWindow.getChildren().add(difficultyMenu);
	}
	
	
	public StackPane getWindow() {
		return mainWindow;
	}
	
	
	/**
	 * @param 	StackPane pane
	 * @return	StackPane pane
	 * 
	 * Assigns the chosen background image to the
	 * mainWindow: auto resizes to fit the background.
	 */
	private StackPane setupBackground(StackPane pane) {
		BackgroundSize size = new BackgroundSize(100.0, 100.0, true, true, false, true);
		BackgroundImage bcImg = new BackgroundImage(this.background, BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		Background background = new Background(bcImg);
		pane.setBackground(background);
		return pane;	
	}

}
