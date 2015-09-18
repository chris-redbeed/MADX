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
public class PlayerModule extends Composite{
	
	private FlowPanel rootPanel;
	private Label lblPlayer;
	private FlowPanel pnlStatus;
	private Button btnStatus;
	
	public PlayerModule() {
		init();
		initStyles();
	}

	private void initStyles() {
		// TODO Auto-generated method stub
		
	}

	private void init() {
		rootPanel = new FlowPanel();
		lblPlayer = new Label();
		pnlStatus = new FlowPanel();
		btnStatus = new Button("bereit");
		
		rootPanel.add(lblPlayer);
		rootPanel.add(pnlStatus);
		rootPanel.add(btnStatus);
	}
	
	@Override
	public Widget asWidget() {
		return rootPanel;
	}
}
