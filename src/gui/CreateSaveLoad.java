package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * @author will_crewe
 *
 */
public class CreateSaveLoad {

	public CreateSaveLoad() {
	}
	
	/**
	 * @return	A HBox with the save and load buttons.
	 */
	public HBox getSaveLoad() {
		HBox output = new HBox();
		output.getChildren().add(getSave());
		output.getChildren().add(getLoad());
		return output;
	}
	
	/**
	 * @return	A Button with the text "Save" on it.
	 * 
	 * A Placeholder method for editing the save button
	 * once functionality is included.
	 */
	private Button getSave() {
		Button save = new Button();
		save.setText("Save");
		save.setOnAction(e -> {
			System.out.println("Saving Game!");
		});
		return save;
	}
	
	/**
	 * @return	A Button with the text "Load" on it.
	 * 
	 * A Placeholder method for editing the load button
	 * once functionality is included.
	 */
	private Button getLoad() {
		Button load = new Button();
		load.setText("Load");
		load.setOnAction(e -> {
			System.out.println("Loading Game!");
		});
		return load;
	}

}
