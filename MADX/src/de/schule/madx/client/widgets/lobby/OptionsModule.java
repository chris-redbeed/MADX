/**
 * 
 */
package de.schule.madx.client.widgets.lobby;

import com.google.gwt.event.dom.client.HasClickHandlers;
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

	public OptionsModule(String name, boolean host) {
		init(name, host);
		initStyles();
	}

	private void initStyles() {
	}

	private void init(String name, boolean host) {
		rootPanel = new FlowPanel();
		lblName = new Label(name);
		btnStatus = new Button("inaktiv");
		btnStatus.setEnabled(host);

		rootPanel.add(lblName);
		rootPanel.add(btnStatus);
	}

	@Override
	public Widget asWidget() {
		return rootPanel;
	}

	public void updateStatus(boolean status) {
		if (status) {
			btnStatus.setText("aktiv");
		} else {
			btnStatus.setText("aktiv");
		}
	}

	public HasClickHandlers getBtnStatus() {
		return btnStatus;
	}

}
