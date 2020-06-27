package main.graphical_interface.gameWindows;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.graphical_interface.GUIController;
import main.graphical_interface.gameWindows.constants.SplashScreenConstants;
import main.graphical_interface.util.Command;

/**
 * Class handling the setup of the SplashScreen of the application
 * @author Daniel
 *
 */
public class SplashScreen {

	private final Image backgroundImage = new Image(SplashScreenConstants.BACKGROUND_IMAGE_PATH);
	
	private StackPane stackPane;
	
	public SplashScreen() {
		// Create and configure the 'New Game button'
		Button newGame = createButton("New Game");
		newGame.setOnAction(clicked -> {
			GUIController.updateWindow(Command.SWITCH_OPTIONS_SCREEN);
		});
		
		// Create and configure the 'Load Game' button
		Button loadGame = createButton("Load Game");
		loadGame.setOnAction(clicked -> {
			GUIController.update(Command.LOAD);
		});
		
		// Create and configure the 'Quit' button
		Button quit = createButton("Quit Game");
		quit.setOnAction(clicked -> {
			Runnable x = Platform::exit;
			x.run();
		});
		
		// Add the buttons to a VBox so they are grouped
		VBox menuOptions = new VBox(newGame, loadGame, quit);
		menuOptions.setSpacing(10.0);
		
		// Add all the elements to the anchor pane
		AnchorPane base = new AnchorPane();
		base.getChildren().add(menuOptions);
		AnchorPane.setLeftAnchor(menuOptions, 30.0);
		AnchorPane.setBottomAnchor(menuOptions, 50.0);

		// Setup the StackPane to hold all the elements
		stackPane = new StackPane();
		// Add the background image to the menu page.
		stackPane = setupBackground(stackPane);
		
		// Add the AnchorPane to the StackPane
		stackPane.getChildren().add(base);
	}
	
	/**
	 * Helper method that constructs a button that changes color and font size when hovered over
	 * @param buttonText
	 * @return
	 */
	private Button createButton(String buttonText) {
		Button button = new Button(buttonText);
		button.setPrefHeight(25.0);
		
		// Set the style of the button so it changes when the mouse enters and exits it
		button.setStyle(SplashScreenConstants.INACTIVE_BUTTON_STYLE);
		button.setOnMouseEntered(e -> button.setStyle(SplashScreenConstants.ACTIVE_BUTTON_STYLE));
		button.setOnMouseExited(e -> button.setStyle(SplashScreenConstants.INACTIVE_BUTTON_STYLE));
		return button;
		
	}
	
	/**
	 * @return The fully setup stackpane of the splash screen
	 */
	public StackPane getStackPane() {
		return stackPane;
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
		BackgroundImage bcImg = new BackgroundImage(this.backgroundImage, BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		Background background = new Background(bcImg);
		pane.setBackground(background);
		return pane;	
	}
}
