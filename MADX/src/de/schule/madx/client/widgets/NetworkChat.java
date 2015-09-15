/**
 * 
 */
package de.schule.madx.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.schule.madx.client.event.GetMessageEvent;
import de.schule.madx.client.event.GetMessageHandler;
import de.schule.madx.client.websocket.WebSocket;

/**
 * @author xgadscj
 *
 */
public class NetworkChat extends Composite {

	private FlowPanel rootPanel;
	private TextArea txtAContent;
	private TextBox txtMessage;
	private Button btnSend;

	private WebSocket webSocket;
	private EventBus eventBus;

	public NetworkChat(WebSocket webSocket, EventBus eventBus) {
		this.webSocket = webSocket;
		this.eventBus = eventBus;

		init();
		initStyles();
		
		addHandlers();
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

	}
	
	private void sendMessage() {
		webSocket.send(txtMessage.getText());
		txtMessage.setText("");
	}
	
	private void addHandlers() {
		txtMessage.addKeyDownHandler(new EnterKeyDownHandler());
		
		btnSend.addClickHandler(new SendButtonClickHandler());
		
		eventBus.addHandler(GetMessageEvent.TYPE, new GetChatMessageHandler());
	}
	
	private class GetChatMessageHandler implements GetMessageHandler {
		
		@Override
		public void getMessage(GetMessageEvent event) {
			String data = event.getEvent().getData();
			
		}
	}
	
	private class SendButtonClickHandler implements ClickHandler {
		
		@Override
		public void onClick(ClickEvent event) {
			sendMessage();
		}
	}
	
	private class EnterKeyDownHandler implements KeyDownHandler {
		
		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				sendMessage();
			}
		}


	}

}
