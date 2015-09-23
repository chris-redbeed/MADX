/**
 * 
 */
package de.schule.madnx.client.widgets.lobby;

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
public class PlayerModule extends Composite{
	
	private FlowPanel rootPanel;
	private Label lblPlayer;
	private FlowPanel pnlStatus;
	private Button btnStatus;
	
	public PlayerModule(String name, boolean self, boolean status) {
		init(name,self,status);
		initStyles();
	}

	private void initStyles() {
		// TODO Auto-generated method stub
		
	}

	private void init(String name, boolean self, boolean status) {
		rootPanel = new FlowPanel();
		lblPlayer = new Label(name);
		pnlStatus = new FlowPanel();
		if (status) {
			btnStatus = new Button("bereit");
			pnlStatus.setStyleName("");
		}
		else {
			btnStatus = new Button("warte");
			pnlStatus.setStyleName("");
		}
		btnStatus.setVisible(self);
		
		rootPanel.add(lblPlayer);
		rootPanel.add(pnlStatus);
		rootPanel.add(btnStatus);
	}
	
	public void setStatus(boolean status) {
		if (status) {
			pnlStatus.setStyleName("");
		}
		else  {
			pnlStatus.setStyleName("");
		}
	}
	
	@Override
	public Widget asWidget() {
		return rootPanel;
	}
	
	public HasClickHandlers getBtnStatus() {
		return btnStatus;
	}
}
