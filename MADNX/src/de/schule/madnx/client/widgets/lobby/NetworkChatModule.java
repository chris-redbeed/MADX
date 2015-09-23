/**
 * 
 */
package de.schule.madnx.client.widgets.lobby;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author xgadscj
 *
 */
public class NetworkChatModule extends Composite{

	private FlowPanel rootPanel;
	private TextArea txtAContent;
	private TextBox txtMessage;
	private Button btnSend;
	
	public NetworkChatModule() {
		init();
		initStyles();
		
	}
	

	@Override
	public Widget asWidget() {
		return rootPanel;
	}

	private void init() {
		rootPanel = new FlowPanel();
		txtAContent = new TextArea();
		txtMessage = new TextBox();
		btnSend = new Button("Senden");
		
		rootPanel.add(txtAContent);
		rootPanel.add(txtMessage);
		rootPanel.add(btnSend);
	}

	private void initStyles() {
		txtAContent.addStyleName("lobby-Chat-TextArea");
		txtMessage.addStyleName("lobby-Chat-TextBox");
		btnSend.addStyleName("lobby-Chat-Button");
	}
	
	public void addStyle (String styleName) {
		rootPanel.addStyleName(styleName);
	}
	
	public HasClickHandlers getBtnSend() {
		return btnSend;
	}
	
	public TextBox getTxtMessage() {
		return txtMessage;
	}
	
	public TextArea getTxtAContent() {
		return txtAContent;
	}

}
