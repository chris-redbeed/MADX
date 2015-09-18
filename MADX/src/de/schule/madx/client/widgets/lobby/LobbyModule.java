package de.schule.madx.client.widgets.lobby;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LobbyModule extends Composite{
	
	private FlowPanel rootPanel;
	private Label lblTitle;
	
	public LobbyModule() {
		init();
		initStyles();
	}

	private void initStyles() {
		// TODO Auto-generated method stub
		
	}

	private void init() {
		rootPanel = new FlowPanel();
		lblTitle = new Label("Übersicht");
		
		rootPanel.add(lblTitle);
		
	}
	
	@Override
	public Widget asWidget() {
		return rootPanel;
	}
}
