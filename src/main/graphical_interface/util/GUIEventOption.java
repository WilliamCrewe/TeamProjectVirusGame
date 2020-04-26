package main.graphical_interface.util;

public class GUIEventOption {

	private String description;
	private String postDescription;
	private String eventID;
	private String followingEventID;
	private int page;
	private String eventTrigger;
	private boolean eventAvailable;
	
	private Command command;
	public GUIEventOption() {
		this.command = null;
		this.description = null;
		this.postDescription = null;
		this.eventID = null;
		this.followingEventID = null;
		this.page = -1;
		this.eventTrigger = null;
		this.eventAvailable = false;
	}
	
	public GUIEventOption(Command c, String desc, String postDescription, int pageNo, String eventTrigger, boolean available) {
		this.command = c;
		this.description = desc;
		this.postDescription = postDescription;
		this.page = pageNo;
		this.eventTrigger = eventTrigger;
		this.eventAvailable = available;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPostDescription() {
		return postDescription;
	}

	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getEventTrigger() {
		return eventTrigger;
	}

	public void setEventTrigger(String eventTrigger) {
		this.eventTrigger = eventTrigger;
	}

	public boolean isEventAvailable() {
		return eventAvailable;
	}

	public void setEventAvailable(boolean eventAvailable) {
		this.eventAvailable = eventAvailable;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public String getFollowingEventID() {
		return this.followingEventID;
	}
	
	public void setFollowingEventID(String followingEventID) {
		this.followingEventID = followingEventID;
		
	}
	
	
	
	

}
