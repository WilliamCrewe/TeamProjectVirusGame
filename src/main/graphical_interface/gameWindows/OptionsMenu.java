package main.graphical_interface.gameWindows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.graphical_interface.EventQueueHandler;
import main.graphical_interface.GUIController;
import main.graphical_interface.gameWindows.constants.OptionsMenuConstants;
import main.graphical_interface.util.Command;
import main.java.alert.AlertQueue;
import main.java.alert.types.DefaultAlert;
import main.java.logging.SystemLogger;

/**
 * Class handling the setup of the Options Menu (New save creation) screen of the application
 * @author Daniel
 *
 */
public class OptionsMenu {

	private final Image backgroundImage = new Image("/main/graphical_interface/util/BrickExtraGloomy.jpg");
	
	private StackPane stackPane;
	
	public OptionsMenu() {
		//
		//	Create the header label
		//
		Label labelNewGame = new Label("New Game");
		labelNewGame.setStyle(OptionsMenuConstants.SAVE_GAME_LABEL_STYLE);
		labelNewGame.setUnderline(true);
		
		
		//
		//	 Create the Save Name
		//
		Label labelSaveName = new Label("Save Name:");
		labelSaveName.setPrefWidth(300.00);
		labelSaveName.setAlignment(Pos.CENTER);
		labelSaveName.setStyle(OptionsMenuConstants.INACTIVE_BUTTON_STYLE);
		TextField textField = new TextField();
		textField.setPromptText("SaveName");
		textField.setPrefHeight(50.00);
		textField.setStyle(OptionsMenuConstants.TEXTAREA_STYLE);
		
		// Ensure the labels show when the users cursor overlaps
		HBox saveNameTextField = new HBox(labelSaveName, textField);
		saveNameTextField.setOnMouseEntered(e -> labelSaveName.setStyle(OptionsMenuConstants.ACTIVE_BUTTON_STYLE));
		saveNameTextField.setOnMouseExited(e -> labelSaveName.setStyle(OptionsMenuConstants.INACTIVE_BUTTON_STYLE));
		saveNameTextField.setSpacing(40.0);
		saveNameTextField.setAlignment(Pos.CENTER);
		
		
		//
		//	 Setup the difficulties
		//
		
		// Setup the Easy button
		RadioButton easyRadio = new RadioButton("Easy");
		easyRadio.setStyle(OptionsMenuConstants.DIFFICULTY_BUTTON_STYLE);
		easyRadio.setPrefWidth(200.0);
		easyRadio.setPrefHeight(25.0);
		easyRadio.setSelected(true);
		easyRadio.setPadding(new Insets(5.0, 0.0, 5.0,  0.0));
		
		
		HBox easyRadioContainer = new HBox(easyRadio);
		easyRadioContainer.setSpacing(40.0);
		easyRadioContainer.setAlignment(Pos.CENTER);
		
		// Setup the Medium button
		RadioButton mediumRadio = new RadioButton("Medium");
		mediumRadio.setStyle(OptionsMenuConstants.DIFFICULTY_BUTTON_STYLE);
		mediumRadio.setPrefWidth(200.0);
		mediumRadio.setPrefHeight(25.0);
		mediumRadio.setPadding(new Insets(5.0, 0.0, 5.0,  0.0));
		
		HBox mediumRadioContainer = new HBox(mediumRadio);
		mediumRadioContainer.setSpacing(40.0);
		mediumRadioContainer.setAlignment(Pos.CENTER);
		
		// Setup the Hard button
		RadioButton hardRadio = new RadioButton("Hard");
		hardRadio.setStyle(OptionsMenuConstants.DIFFICULTY_BUTTON_STYLE);
		hardRadio.setPrefWidth(200.0);
		hardRadio.setPrefHeight(25.0);
		hardRadio.setPadding(new Insets(5.0, 0.0, 5.0,  0.0));
		
		HBox hardRadioContainer = new HBox(hardRadio);
		hardRadioContainer.setSpacing(40.0);
		hardRadioContainer.setAlignment(Pos.CENTER);
	
		// Create the VBox to hold the buttons
		VBox difficultyButtonsContainer = new VBox(easyRadioContainer, mediumRadioContainer, hardRadioContainer);
		difficultyButtonsContainer.setPrefWidth(400.00);
		difficultyButtonsContainer.setStyle("-fx-background-color: transparent;");
		difficultyButtonsContainer.setAlignment(Pos.CENTER);
		
		Label labelDifficulty = new Label("Difficulty:");
		labelDifficulty.setPrefWidth(250.00);
		labelDifficulty.setStyle(OptionsMenuConstants.INACTIVE_BUTTON_STYLE);
		labelDifficulty.setAlignment(Pos.CENTER);
		
		HBox difficultyContainer = new HBox(labelDifficulty, difficultyButtonsContainer);
		difficultyContainer.setSpacing(40.0);
		
		difficultyButtonsContainer.setAlignment(Pos.CENTER);
		difficultyContainer.setOnMouseEntered(e -> labelDifficulty.setStyle(OptionsMenuConstants.ACTIVE_BUTTON_STYLE));
		difficultyContainer.setOnMouseExited(e -> labelDifficulty.setStyle(OptionsMenuConstants.INACTIVE_BUTTON_STYLE));
		
		
		//
		//	Setup the Seed
		//
		
		// Setup the seed label
		Label labelSeed = new Label("Seed (optional):");
		labelSeed.setPrefWidth(300.00);
		labelSeed.setAlignment(Pos.CENTER);
		labelSeed.setStyle(OptionsMenuConstants.INACTIVE_BUTTON_STYLE);
		
		TextField textFieldSeed = new TextField();
		textFieldSeed.setPromptText("Seed");
		textFieldSeed.setStyle(OptionsMenuConstants.TEXTAREA_STYLE);
		
		HBox seedTextField = new HBox(labelSeed, textFieldSeed);
		seedTextField.setSpacing(40.0);
		seedTextField.setAlignment(Pos.CENTER);
		seedTextField.setOnMouseEntered(e -> labelSeed.setStyle(OptionsMenuConstants.ACTIVE_BUTTON_STYLE));
		seedTextField.setOnMouseExited(e -> labelSeed.setStyle(OptionsMenuConstants.INACTIVE_BUTTON_STYLE));
		
		ToggleGroup radioGroup = new ToggleGroup();
		easyRadio.setToggleGroup(radioGroup);
		mediumRadio.setToggleGroup(radioGroup);
		hardRadio.setToggleGroup(radioGroup);

		VBox centralContainer = new VBox(labelNewGame, saveNameTextField, difficultyContainer, seedTextField);
		centralContainer.setSpacing(40.0);
		centralContainer.setAlignment(Pos.CENTER);
		
		
		//
		//	Setup the navigation buttons
		//
		
		//	Setup the 'Go' button (Will verify values and create save)
		Button goDifficulty = createStandardButton("Go!");
		goDifficulty.setStyle(OptionsMenuConstants.INACTIVE_GO_BUTTON_STYLE);
		goDifficulty.setOnMouseEntered(e -> goDifficulty.setStyle(OptionsMenuConstants.ACTIVE_GO_BUTTON_STYLE));
		goDifficulty.setOnMouseExited(e -> goDifficulty.setStyle(OptionsMenuConstants.INACTIVE_GO_BUTTON_STYLE));
		goDifficulty.setOnAction(click -> {
			// Verify the save name is correct
			if (!verifySaveName(textField.getText())) {
				SystemLogger.severe("The name %s is not a valid save name, it must not contain any spaces or special characters", textField.getText());
				AlertQueue.getInstance().add(new DefaultAlert("Invalid input", String.format("The name %s is not a valid save name, it must not contain any spaces or special characters", textField.getText())));
				
				return;
			}
			
			// Verify the seed is correct
			if (!verifySeed(textFieldSeed.getText())) {
				SystemLogger.severe("The seed %s is not a valid hex value", textFieldSeed.getText());
				AlertQueue.getInstance().add(new DefaultAlert("Invalid input", String.format("The seed %s is not a valid hex value", textFieldSeed.getText())));
				
				return;
			}
			
			// Work out the difficulty (Based on the selected radio button)
			String difficulty = "";
			if (easyRadio.isSelected()) {
				difficulty = "Easy";
			} else if (mediumRadio.isSelected()) {
				difficulty = "Medium";
			} else {
				difficulty = "Hard";
			}

			GUIController.updateWindow(Command.SWITCH_GAMEPLAY_UI);
			
			EventQueueHandler.addNewSaveEvent(textField.getText(), difficulty, textFieldSeed.getText());
		});
		
		//
		//	Setup the 'Cancel' button (Will take the user back to the main menu
		//
		Button cancelDifficulty = createStandardButton("Cancel");
		cancelDifficulty.setStyle(OptionsMenuConstants.INACTIVE_CANCEL_BUTTON_STYLE);
		cancelDifficulty.setOnMouseEntered(e -> cancelDifficulty.setStyle(OptionsMenuConstants.ACTIVE_CANCEL_BUTTON_STYLE));
		cancelDifficulty.setOnMouseExited(e -> cancelDifficulty.setStyle(OptionsMenuConstants.INACTIVE_CANCEL_BUTTON_STYLE));
		cancelDifficulty.setOnAction(click -> GUIController.updateWindow(Command.SWITCH_SPLASH_SCREEN));
		
		HBox buttonBox = new HBox(cancelDifficulty, goDifficulty);
		
		//
		//	Setup the overall container
		//
		
		VBox contentBox = new VBox(centralContainer, buttonBox);
		contentBox.setSpacing(50.0);
		contentBox.setMaxWidth(600.0);
		contentBox.setMaxHeight(100.0);
		contentBox.setAlignment(Pos.CENTER_RIGHT);
		
		//
		// Setup the StackPane to hold all the elements
		//
		stackPane = new StackPane();
		// Add the background image to the menu page.
		stackPane = setupBackground(stackPane);
		
		// Add the AnchorPane to the StackPane
		stackPane.getChildren().add(contentBox);
	}
	
	/**
	 * Method that creates a standardised button for the current screen
	 * @param title
	 * @return
	 */
	private Button createStandardButton(String title) {
		Button b = new Button(title);
		b.setPrefHeight(25.0);
		return b;
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
	
	/**
	 * Utility method that verifies the seed is valid (Is not null or empty and can be converted from Hex
	 * @param seed
	 * @return
	 */
	private boolean verifySeed(String seed) {
		if (seed == null || seed.isEmpty()) {
			return true;
		}
		
		SystemLogger.fine("Verifying the seed %s", seed);
		try {
			Long.parseLong(seed,16);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Verifies the save name matches the allowable values
	 * @param saveName
	 * @return
	 */
	private boolean verifySaveName(String saveName) {
		return saveName.matches("^[^*&\\.%\\s]+$");
	}
}
