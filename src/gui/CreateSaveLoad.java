package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * @author will_crewe
 *
 */
public class CreateSaveLoad {
	
	private Button save;
	private Button load;

	public CreateSaveLoad() {
		this.save = null;
		this.load = null;
	}
	
	/**
	 * @return	A HBox with the save and load buttons.
	 */
	public HBox getSaveLoad() {
		HBox output = new HBox();
		output.getChildren().add(makeSave());
		output.getChildren().add(makeLoad());

		return output;
	}
	
	/**
	 * @return	A Button with the text "Save" on it.
	 * 
	 * A Placeholder method for editing the save button
	 * once functionality is included.
	 */
	private Button makeSave() {
		Button x = new Button();
		x.setText("Save");
		this.save = x;
		return x;
	}
	
	/**
	 * @return	A Button with the text "Load" on it.
	 * 
	 * A Placeholder method for editing the load button
	 * once functionality is included.
	 */
	private Button makeLoad() {
		Button x = new Button();
		x.setText("Load");
		this.load = x;
		return x;
	}
	
	public Button getSave() {
		return this.save;
	}
	
	public Button getLoad() {
		return this.load;
	}

}
