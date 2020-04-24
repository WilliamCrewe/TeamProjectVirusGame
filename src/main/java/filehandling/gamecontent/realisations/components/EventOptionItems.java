package main.java.filehandling.gamecontent.realisations.components;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Daniel
 *
 */
public class EventOptionItems {

	private final List<ItemType> eventOptionItemsValues = new ArrayList<>();
	
	public EventOptionItems(Node completedEventsNode) {
		NodeList childNodes = completedEventsNode.getChildNodes();
		
		// Each child node will be an EventOptionItem so construct a new ItemType and add it
		for (int i = 0; i < childNodes.getLength(); i++) {
			eventOptionItemsValues.add(new ItemType(childNodes.item(i)));
		}
	}
	
	/**
	 * @return The list of all ItemTypes beneath this EventOptionItems tag
	 */
	public List<ItemType> getEventOptionItemsValues() {
		return eventOptionItemsValues;
	}
}
