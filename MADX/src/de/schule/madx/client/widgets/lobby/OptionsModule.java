/**
 * 
 */
package de.schule.madx.client.widgets.lobby;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author xgadscj
 *
 */
public class OptionsModule extends Composite {
	
	private FlowPanel rootPanel;
	
	private Label lblName;
	
	private Button btnStatus;
	
	public OptionsModule(String name) {
		init(name);
		initStyles();
	}

	private void initStyles() {
	}

	private void init(String name) {
		rootPanel = new FlowPanel();
		lblName = new Label(name);
		btnStatus = new Button("inaktiv");
		
		rootPanel.add(lblName);
		rootPanel.add(btnStatus);
	}
	
	@Override
	public Widget asWidget() {
		return rootPanel;
	}

}
