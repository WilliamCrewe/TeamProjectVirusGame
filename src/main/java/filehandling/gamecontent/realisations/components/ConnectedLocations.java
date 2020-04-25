package main.java.filehandling.gamecontent.realisations.components;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing the CompletedEvent item in the XSD
 * @author Daniel
 *
 */
public class ConnectedLocations {

	private final List<String> connectedLocationIDs = new ArrayList<>();
	
	public ConnectedLocations(Node connectedLocationsNode) {
		NodeList childNodes = connectedLocationsNode.getChildNodes();
		
		// loop over all the child nodes (They will all be CompletedEventTypes)
		for (int i = 0; i < childNodes.getLength(); i++) {
			connectedLocationIDs.add(childNodes.item(i).getTextContent());
		}
	}
	
	
	/**
	 * @return the connectedLocationIDs
	 */
	public List<String> getConnectedLocationIDs() {
		return connectedLocationIDs;
	}
}
