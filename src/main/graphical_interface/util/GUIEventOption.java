package main.graphical_interface.util;

public class GUIEventOption {
	
	private Command command;
	private String description;
	private String postDescription;
	private int page;
	private String eventTrigger;
	private boolean eventAvailable;
	private String eventID;
	private String followingEventID;
	
	public GUIEventOption(Command c, String desc, String postDesc, int pageNo, String eventTrigger, boolean available) {
		this.command = c;
		this.description = desc;
		this.page = pageNo;
		this.eventTrigger = eventTrigger;
		this.eventAvailable = available;
	}
	
	public GUIEventOption() {
		this.command = null;
		this.description = null;
		this.postDescription = null;
		this.page = -1;
		this.eventAvailable = false;
		this.eventTrigger = null;
		this.eventID = null;
		this.setFollowingEventID(null);
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
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

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getFollowingEventID() {
		return followingEventID;
	}

	public void setFollowingEventID(String followingEventID) {
		this.followingEventID = followingEventID;
	}

	

}
