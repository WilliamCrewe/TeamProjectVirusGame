package main.graphical_interface.util;

public class GUILocation {
	
	private Command command;
	private String name;
	private int page;
	private String locationID;
	
	public GUILocation(Command command, String name, int page) {
		this.command = command;
		this.name = name;
		this.page = page;
	}
	
	public GUILocation() {
		this.command = null;
		this.name = null;
		this.page = -1;
		this.locationID = null;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getLocationID() {
		return locationID;
	}

	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}
	
	
	
	

}
