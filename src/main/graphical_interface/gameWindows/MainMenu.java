package main.graphical_interface.gameWindows;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.graphical_interface.EventQueueHandler;
import main.graphical_interface.GUIController;
import main.graphical_interface.gameWindows.constants.SplashScreenConstants;
import main.graphical_interface.util.Command;
import main.java.alert.AlertQueue;
import main.java.alert.types.DefaultAlert;
import main.java.logging.SystemLogger;

public class MainMenu {
	
	private final Background bg = new Background(new BackgroundFill(new Color(0.4, 0.4, 0.4, 0.5),null,new Insets(-15.0, -10.0, -15.0, -10.0)));
	private final Background bgRadio = new Background(new BackgroundFill(new Color(0.8, 0.8, 0.8, 0.8),null,new Insets(-5.0, 0.0, -5.0, 0.0)));
	private VBox menu1; //baseMenu
	private VBox menu2; //newGameMenu
	private Label invalidInputLabel;

	public MainMenu() {
		setupMainMenu();
		setupNewGameMenu();
	}
	
	public void setupMainMenu() {
		
		// Create and configure the 'New Game button'
		Button newGame = createStandardButton("New Game");
		newGame.setStyle(SplashScreenConstants.INACTIVE_BUTTON_STYLE);
		newGame.setOnMouseEntered(e -> newGame.setStyle(SplashScreenConstants.ACTIVE_BUTTON_STYLE));
		newGame.setOnMouseExited(e -> newGame.setStyle(SplashScreenConstants.INACTIVE_BUTTON_STYLE));
		
		newGame.setOnAction(clicked -> {
			this.menu1.setVisible(false);
			this.menu2.setVisible(true);
		});
		
		// Create and configure the 'Load Game' button
		Button loadGame = createStandardButton("Load Game");
		loadGame.setStyle(SplashScreenConstants.INACTIVE_BUTTON_STYLE);
		loadGame.setOnMouseEntered(e -> loadGame.setStyle(SplashScreenConstants.ACTIVE_BUTTON_STYLE));
		loadGame.setOnMouseExited(e -> loadGame.setStyle(SplashScreenConstants.INACTIVE_BUTTON_STYLE));
		loadGame.setOnAction(clicked -> {
			GUIController.update(Command.LOAD);
		});
		
		// Create and configure the 'Quit' button
		Button quit = createStandardButton("Quit Game");
		quit.setStyle(SplashScreenConstants.INACTIVE_BUTTON_STYLE);
		quit.setOnMouseEntered(e -> quit.setStyle(SplashScreenConstants.ACTIVE_BUTTON_STYLE));
		quit.setOnMouseExited(e -> quit.setStyle(SplashScreenConstants.INACTIVE_BUTTON_STYLE));
		quit.setOnAction(clicked -> {
			Runnable x = Platform::exit;
			x.run();
		});
		
		this.menu1 = new VBox(newGame, loadGame, quit);
		this.menu1.setSpacing(10.0);
	}
	
	public void setupNewGameMenu() {
		
		Label labelSaveName = new Label("Save Name");
		
		TextField textField = new TextField();
		textField.setPromptText("SaveName");
		
		HBox saveNameTextField = new HBox(labelSaveName, textField);
		saveNameTextField.setBackground(this.bgRadio);
		saveNameTextField.setSpacing(40.0);
		saveNameTextField.setAlignment(Pos.CENTER);
		
		RadioButton easyRadio = new RadioButton("Easy");
		easyRadio.setPrefWidth(100.0);
		easyRadio.setPrefHeight(25.0);
		easyRadio.setSelected(true);
		
		HBox easyRadioContainer = new HBox(easyRadio);
		easyRadioContainer.setBackground(this.bgRadio);
		easyRadioContainer.setSpacing(40.0);
		easyRadioContainer.setAlignment(Pos.CENTER);
		
		
		RadioButton mediumRadio = new RadioButton("Medium");
		mediumRadio.setPrefWidth(100.0);
		mediumRadio.setPrefHeight(25.0);
		
		HBox mediumRadioContainer = new HBox(mediumRadio);
		mediumRadioContainer.setBackground(this.bgRadio);
		mediumRadioContainer.setSpacing(40.0);
		mediumRadioContainer.setAlignment(Pos.CENTER);
		
		
		Label labelSeed = new Label("Seed (optional)");
		
		TextField textFieldSeed = new TextField();
		textFieldSeed.setPromptText("Seed");
		HBox seedTextField = new HBox(labelSeed, textFieldSeed);
		seedTextField.setBackground(this.bgRadio);
		seedTextField.setSpacing(40.0);
		seedTextField.setAlignment(Pos.CENTER);
		
		RadioButton hardRadio = new RadioButton("Hard");
		hardRadio.setPrefWidth(100.0);
		hardRadio.setPrefHeight(25.0);

		HBox hardRadioContainer = new HBox(hardRadio);
		hardRadioContainer.setBackground(this.bgRadio);
		hardRadioContainer.setSpacing(40.0);
		hardRadioContainer.setAlignment(Pos.CENTER);
		
		ToggleGroup radioGroup = new ToggleGroup();
		easyRadio.setToggleGroup(radioGroup);
		mediumRadio.setToggleGroup(radioGroup);
		hardRadio.setToggleGroup(radioGroup);

		VBox centralContainer = new VBox(saveNameTextField, easyRadioContainer, mediumRadioContainer, hardRadioContainer, seedTextField);
		centralContainer.setBackground(this.bg);
		centralContainer.setSpacing(40.0);
		centralContainer.setAlignment(Pos.CENTER);
		
		Button goDifficulty = createStandardButton("Go");
		goDifficulty.setOnAction(click -> {
			if (!verifySaveName(textField.getText())) {
				SystemLogger.severe("The name %s is not a valid save name, it must not contain any spaces or special characters", textField.getText());
				AlertQueue.getInstance().add(new DefaultAlert("Invalid input", String.format("The name %s is not a valid save name, it must not contain any spaces or special characters", textField.getText())));
				
				return;
			}
			
			if (!verifySeed(textFieldSeed.getText())) {
				SystemLogger.severe("The seed %s is not a valid hex value", textFieldSeed.getText());
				AlertQueue.getInstance().add(new DefaultAlert("Invalid input", String.format("The seed %s is not a valid hex value", textFieldSeed.getText())));
				
				return;
			}
			
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
		
		Button cancelDifficulty = createStandardButton("Cancel");
		cancelDifficulty.setOnAction(click -> {
			menu1.setVisible(true);
			menu2.setVisible(false);
		});
		
		HBox buttonBox = new HBox(cancelDifficulty, goDifficulty);
		
		this.menu2 = new VBox(centralContainer, buttonBox);
		this.menu2.setBackground(bg);
		this.menu2.setSpacing(50.0);
		this.menu2.setMaxWidth(300.0);
		this.menu2.setMaxHeight(100.0);
		this.menu2.setVisible(false);
		this.menu2.setAlignment(Pos.CENTER_RIGHT);
	}
	
	public VBox getMenu1() {
		return this.menu1;
	}
	
	public VBox getMenu2() {
		return this.menu2;
	}
	
	private Button disableButton(Button b) {
		b.setDisable(true);
		return b;
		
	}
	
	private Button createStandardButton(String title) {
		Button b = new Button(title);
//		b.setPrefWidth(1000.0);
		b.setPrefHeight(25.0);
		return b;
	}

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
	
	private boolean verifySaveName(String saveName) {
		return saveName.matches("^[^*&\\.%\\s]+$");
	}
	
}
