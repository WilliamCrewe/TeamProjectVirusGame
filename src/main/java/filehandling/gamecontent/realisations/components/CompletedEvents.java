package main.java.filehandling.gamecontent.realisations.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.XMLSerializable;

/**
 * Class representing the CompletedEvents item in the XSD
 * @author Daniel
 *
 */
public class CompletedEvents implements XMLSerializable {

	private final List<CompletedEvent> completedEventsValues = new ArrayList<>();
	private Set<String> completedEventIDs = new HashSet<>();
	
	private static final String SERIALIZED_FORMAT = "<CompletedEvents>%s</CompletedEvents>";
	
	/**
	 * Constructor to be used when there was no CompletedEvents element in the XML
	 */
	public CompletedEvents() {
	}
	
	public CompletedEvents(Node completedEventsNode) {
		NodeList childNodes = completedEventsNode.getChildNodes();
		
		// loop over all the child nodes (They will all be CompletedEventTypes)
		for (int i = 0; i < childNodes.getLength(); i++) {
			CompletedEvent completedEvent = new CompletedEvent(childNodes.item(i));
			completedEventsValues.add(completedEvent);
			completedEventIDs.add(completedEvent.getEventID());
		}
	}

	/**
	 * @return
	 */
	public List<CompletedEvent> getCompletedEvents() {
		return completedEventsValues;
	}
	
	public void addToCompletedEvent(CompletedEvent completedEvent) {
		completedEventsValues.add(completedEvent);
		completedEventIDs.add(completedEvent.getEventID());
	}
	
	public boolean containsCompletedEventID(String eventID) {
		return completedEventIDs.contains(eventID);
	}
	
	@Override
	public byte[] serialize() {
		return toXMLString().getBytes();
	}
	
	@Override
	public String toXMLString() {
		
		StringBuilder completedEventsStringBuilder = new StringBuilder();
		for (CompletedEvent completedEvent : completedEventsValues) {
			completedEventsStringBuilder.append(completedEvent.toXMLString());
		}
		
		return String.format(SERIALIZED_FORMAT, completedEventsStringBuilder.toString());
	}

}
