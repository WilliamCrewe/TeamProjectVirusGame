package main.java.filehandling.gamecontent.realisations.components;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing the RequiredItemsType item in the XSD
 * @author Daniel
 *
 */
public class RequiredItemsType {

	private final List<RequiredItemType> requiredItemsTypesValues = new ArrayList<>();
	
	public RequiredItemsType(Node completedEventsNode) {
		NodeList childNodes = completedEventsNode.getChildNodes();
		
		// Each child node will be an EventOptionItem so construct a new ItemType and add it
		for (int i = 0; i < childNodes.getLength(); i++) {
			requiredItemsTypesValues.add(new RequiredItemType(childNodes.item(i)));
		}
	}
	
	/**
	 * Constructor to be used for junit testing
	 * @param requiredItemsTypesValues
	 */
	public RequiredItemsType(List<RequiredItemType> requiredItemsTypesValues) {
		this.requiredItemsTypesValues.addAll(requiredItemsTypesValues);
	}
	
	/**
	 * @return The list of all RequiredItemType beneath this RequiredItemsType tag
	 */
	public List<RequiredItemType> getRequiredItemsTypesValues() {
		return requiredItemsTypesValues;
	}
}
