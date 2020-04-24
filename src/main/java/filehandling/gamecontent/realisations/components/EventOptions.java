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
		
		for (int i = 0; i < childNodes.getLength(); i++) {
			eventOptionsValues.add(new EventOption(childNodes.item(i)));
		}
	}
	
	/**
	 * @return
	 */
	public List<EventOption> getEventOptionsValues() {
		return this.eventOptionsValues;
	}
}
