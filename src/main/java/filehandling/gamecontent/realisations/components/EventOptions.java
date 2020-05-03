package main.java.filehandling.gamecontent.realisations.components;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing the EventOptions item in the XSD
 * @author Daniel
 *
 */
public class EventOptions {
	private final List<EventOption> eventOptionsValues = new ArrayList<>();
	
	public EventOptions(Node eventOptionsNode) {
		NodeList childNodes = eventOptionsNode.getChildNodes();
		
		// Each child node will be an EventOptionType so construct a new EventOption and add it
		for (int i = 0; i < childNodes.getLength(); i++) {
			eventOptionsValues.add(new EventOption(childNodes.item(i)));
		}
	}
	
	/**
	 * Constructor to be used for junit testing
	 * @param eventOptionsValues
	 */
	public EventOptions(List<EventOption> eventOptionsValues) {
		this.eventOptionsValues.addAll(eventOptionsValues);
	}
	
	/**
	 * @return
	 */
	public List<EventOption> getEventOptionsValues() {
		return this.eventOptionsValues;
	}
}
