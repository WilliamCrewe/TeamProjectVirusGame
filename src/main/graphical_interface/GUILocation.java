package main.graphical_interface;

import main.graphical_interface.util.Command;

public class GUILocation {
	
	private Command command;
	private String name;
	private int page;
	//private Location locationObj;
	
	public GUILocation(Command command, String name, int page) {
		this.command = command;
		this.name = name;
		this.page = page;
	}

	protected Command getCommand() {
		return this.command;
	}

	protected String getName() {
		return this.name;
	}

	public int getPage() {
		return this.page;
	}
	
	

}
