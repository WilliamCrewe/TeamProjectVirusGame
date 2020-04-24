package main.graphical_interface.gameWindows.inGameWindows;

import javafx.scene.control.TextArea;

public class DisplayWindowController {
	
	private TextArea textView;
	
	public DisplayWindowController() {
		setup();
	}
	
	private void setup() {
		TextArea view = new TextArea();
		view.setEditable(false);
		view.setWrapText(true);
		view.setFocusTraversable(false);
		view.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas non enim eros. Nulla lacinia, ex sed mattis hendrerit, ipsum ipsum dapibus orci, non pharetra leo quam quis erat. Interdum et malesuada fames ac ante ipsum primis in faucibus. Sed vitae sem elementum, laoreet ex eu, lobortis leo. Sed lacus tortor, porta feugiat augue quis, pulvinar ultricies nulla. Phasellus massa ligula, auctor at mi pharetra, facilisis blandit eros. Nullam nec ligula sit amet velit scelerisque consequat. In ut ultricies tortor. Vestibulum id sapien condimentum, bibendum lacus id, consectetur erat. Nunc nibh augue, dapibus id finibus quis, mattis et mi. Nullam luctus turpis ex, sed elementum velit varius quis. Etiam in quam purus. Phasellus bibendum, metus fermentum vestibulum iaculis, lorem quam maximus ante, efficitur euismod odio purus volutpat nisl.\r\n" + 
				"\r\n" + 
				"Suspendisse a tellus interdum, pharetra lorem ullamcorper, porta justo. Ut id tempor eros. Donec nibh felis, venenatis at eros ut, facilisis varius massa. Donec dictum hendrerit est vel semper. Aliquam sollicitudin, leo tempor ullamcorper elementum, ante sapien elementum est, et iaculis lacus magna rhoncus diam. Praesent accumsan elit at sodales blandit. Nulla eu porttitor lectus. Nullam porttitor fringilla leo, nec ultricies turpis. Sed efficitur vehicula lacus, ac varius ipsum. In hac habitasse platea dictumst. Nunc sit amet urna volutpat, aliquam ex et, lobortis mi.\r\n" + 
				"\r\n" + 
				"Proin vitae elit justo. Ut tempor magna non metus blandit cursus. Sed dapibus rutrum porttitor. Sed nec venenatis justo. Morbi in est diam. Sed euismod semper purus. Suspendisse ornare lobortis nunc, a iaculis tortor efficitur id. Phasellus et ligula viverra metus viverra pretium. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pretium elementum dolor eget dignissim. Donec in augue id nisi luctus ultricies. Nulla lobortis et turpis eu aliquam.");
		this.textView = view;
	}
	
	public TextArea getTextView() {
		return this.textView;
	}
	
	public void updateDisplayTest(String input) {
		this.textView.setText(input);
	}

}
