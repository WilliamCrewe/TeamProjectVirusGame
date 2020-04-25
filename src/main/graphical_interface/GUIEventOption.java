package main.graphical_interface;

import main.graphical_interface.util.Command;

public class GUIEventOption {
	
	private Command command;
	//private EventOption event; Once the event object is created
	private String description;
	private int page;
	private String eventTrigger;
	private boolean eventAvailable;
	
	public GUIEventOption(Command c, String desc, int pageNo, String eventTrigger, boolean available) {
		this.command = c;
		this.description = desc;
		this.page = pageNo;
		this.eventTrigger = eventTrigger;
		this.eventAvailable = available;
	}

	protected Command getCommand() {
		return command;
	}

	protected String getDescription() {
		return description;
	}

	protected int getPage() {
		return page;
	}

	protected String getEventTrigger() {
		return eventTrigger;
	}
	
	
	
	

}
