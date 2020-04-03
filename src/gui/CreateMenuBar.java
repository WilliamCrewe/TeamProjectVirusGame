package gui;

import java.util.ArrayList;

import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * @author wcrewe
 *
 */
public class CreateMenuBar {
	
	MenuBar menuBar;

	/**
	 * @author will_crewe
	 */
	public CreateMenuBar() {
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
	 * a single MenuItem, "New Game" that prints "Menu 1
	 * Action!" to the printline when clicked.
	 */
	private Menu createFileMenu() {
		ArrayList<MenuItem> items = new ArrayList<>();
		MenuItem menuItem1 = createMenuItem("New Game");
		menuItem1.setOnAction(e -> {
			System.out.println("Menu 1 Action!");
		});
		
		
		items.add(menuItem1);
		
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
		
		return createMenu("Edit", items);
	}
	
	/**
	 * @return The "Help" Menu with one Menu Item
	 * 
	 * This method creates the Help Menu. Contains
	 * a single MenuItem, "GitHub Repo" that prints "Menu 3
	 * Action!" to the printline when clicked.
	 */
	private Menu createHelpMenu() {
		ArrayList<MenuItem> items = new ArrayList<>();
		MenuItem menuItem1 = createMenuItem("GitHub Repo");
		menuItem1.setOnAction(e -> {
			System.out.println("Menu 3 Action!");
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

}