package main.graphical_interface.gameWindows;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import main.graphical_interface.GUIController;
import main.graphical_interface.util.Command;

public class MainMenu {
	
	private final Background bg = new Background(new BackgroundFill(new Color(0.4, 0.4, 0.4, 0.5),null,new Insets(-15.0, -10.0, -15.0, -10.0)));
	private VBox menu1; //baseMenu
	private VBox menu2; //newGameMenu

	public MainMenu() {
		setupMainMenu();
		setupNewGameMenu();
	}
	
	public void setupMainMenu() {
		Button newGame = createStandardButton("New Game");
		newGame.setOnAction(clicked -> {
			this.menu1.setVisible(false);
			this.menu2.setVisible(true);
		});
		Button loadGame = createStandardButton("Load Game");
		loadGame.setOnAction(clicked -> {
			GUIController.update(Command.LOAD);
		});
		Button options = createStandardButton("Options");
		disableButton(options);
		Button achievements = createStandardButton("Achievements");
		disableButton(achievements);
		Button quit = createStandardButton("Quit Game");
		quit.setOnAction(clicked -> {
			
		});
		
		this.menu1 = new VBox(newGame, loadGame, options, achievements, quit);
		this.menu1.setBackground(this.bg);
		this.menu1.setSpacing(10.0);
	}
	
	public void setupNewGameMenu() {
		Button easy = createDifficultyButton("Easy");
		easy.setOnAction(clicked -> {
			GUIController.updateWindow(Command.SWITCH_GAMEPLAY_UI);
		});
		Button medium = createDifficultyButton("Medium");
		disableButton(medium);
		Button hard = createDifficultyButton("Hard");
		disableButton(hard);
		
		VBox centralContainer = new VBox(easy, medium, hard);
		centralContainer.setBackground(this.bg);
		centralContainer.setSpacing(40.0);
		centralContainer.setAlignment(Pos.CENTER);
		
		Button cancelDifficulty = createStandardButton("Cancel");
		cancelDifficulty.setOnAction(click -> {
			menu1.setVisible(true);
			menu2.setVisible(false);
		});
		
		this.menu2 = new VBox(centralContainer, cancelDifficulty);
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
		b.setPrefWidth(100.0);
		b.setPrefHeight(25.0);
		return b;
	}
	
	private Button createDifficultyButton(String title) {
		Button b = new Button(title);
		b.setPrefWidth(200.0);
		b.setPrefHeight(50.0);
		Font f = new Font(30.0);
		b.setFont(f);
		return b;
	}

}
