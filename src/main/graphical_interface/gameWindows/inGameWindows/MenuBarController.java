package main.graphical_interface.gameWindows.inGameWindows;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;

public class MenuBarController {
	
	private MenuBar menuBar;
	private MenuItem newGame;
	private MenuItem properties;
	
	public MenuBarController() {
		this.menuBar = new MenuBar();
	}
	
	/**
	 * @return A Control(MenuBar) containing the three
	 * 			menus for the primary game window.
	 * 
	 * This method is called by the SceneBuilder to
	 * create and retrieve the top menu.
	 */
	public Control getMainMenuBar() {
		this.menuBar.getMenus().add(createFileMenu());
		this.menuBar.getMenus().add(createEditMenu());
		this.menuBar.getMenus().add(createHelpMenu());
		
		return this.menuBar;
	}
	
	/**
	 * @return The "File" Menu with one Menu Item
	 * 
	 * This method creates the File Menu. Contains
	 * a two MenuItems, "New Game" that prints "Menu 1
	 * Action!" to the printline when clicked.
	 * "Quit", which closes the application when clicked.
	 */
	private Menu createFileMenu() {
		ArrayList<MenuItem> items = new ArrayList<>();
		MenuItem menuItem1 = createMenuItem("New Game");
		menuItem1.setOnAction(e -> {
			System.out.println("Menu 1 Action!");
		});
		
		MenuItem menuItem2 = createMenuItem("Quit");
		menuItem2.setOnAction(e -> {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Quit Game?");
			alert.setHeaderText("You're about to quit the Game.\n"
								+ "Are you sure?\n");
			alert.showAndWait();
			if (alert.getResult() != ButtonType.OK) {
				return;
			}
			Runnable x = Platform::exit;
			x.run();
		});
		
		
		items.add(menuItem1);
		items.add(menuItem2);
		
		this.newGame = menuItem1;
		
		return createMenu("File", items);
	}
	
	/**
	 * @return The "Edit" Menu with one Menu Item
	 * 
	 * This method creates the Edit Menu. Contains
	 * a single MenuItem, "Properties" that prints "Menu 2
	 * Action!" to the printline when clicked.
	 */
	private Menu createEditMenu() {
		ArrayList<MenuItem> items = new ArrayList<>();
		MenuItem menuItem1 = createMenuItem("Properties");
		menuItem1.setOnAction(e -> {
			System.out.println("Menu 2 Action!");
		});
		
		
		items.add(menuItem1);
		
		this.properties = menuItem1;
		
		return createMenu("Edit", items);
	}
	
	/**
	 * @return The "Help" Menu with one Menu Item
	 * 
	 * This method creates the Help Menu. Contains
	 * a single MenuItem, "GitHub Repo" that presents an alert
	 * box to the user to either confirm or cancel. Confirming
	 * opens the GitHub Repository for this project in the user's
	 * preferred browser.
	 */
	private Menu createHelpMenu() {
		ArrayList<MenuItem> items = new ArrayList<>();
		MenuItem menuItem1 = createMenuItem("GitHub Repository");
		menuItem1.setOnAction(e -> {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Open Webpage?");
			alert.setHeaderText("This will open a webpage\n"
								+ "to this projects GitHub\n"
								+ "repository. Continue? ");
			alert.showAndWait();
			if (alert.getResult() != ButtonType.OK) {
				return;
			}

			String websiteURL = "https://github.com/WilliamCrewe/TeamProjectVirusGame";
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				try {
					Desktop.getDesktop().browse(new URI(websiteURL));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			} else {
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("xdg-open" + websiteURL);
				} catch (IOException e3) {
					e3.printStackTrace();
				}
			}
		});
		
		
		items.add(menuItem1);
		
		return createMenu("Help", items);
	}
	
	
	/**
	 * @param title 	The text to be displayed on the Menu
	 * @param items		The Array of MenuItems to be included
	 * @return	A Menu object with the assigned title and assigned
	 * 			MenuItems.
	 * 
	 * A general script called by others to create their
	 * Menus.
	 */
	private Menu createMenu(String title, ArrayList<MenuItem> items) {
		Menu menu = new Menu();
		menu.setText(title);
		for (MenuItem x : items) {
			menu.getItems().add(x);
		}
		return menu;
	}
	
	
	/**
	 * @param title		The text to be displayed on the MenuItem
	 * @return	Returns the MenuItem with its assigned text.
	 * 
	 * A placeholder method that will be used to assign 
	 * blanket conditions to all MenuItems in the future.
	 */
	private MenuItem createMenuItem(String title) {
		MenuItem item = new MenuItem();
		item.setText(title);
		return item;
	}
	
	public MenuItem getNewGame() {
		return this.newGame;
	}
	
	public MenuItem getProperties() {
		return this.properties;
	}

}