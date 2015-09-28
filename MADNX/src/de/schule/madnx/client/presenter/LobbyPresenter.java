package de.schule.madnx.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;

import de.schule.madnx.client.GameController;
import de.schule.madnx.client.event.GetMessageEvent;
import de.schule.madnx.client.event.GetMessageHandler;
import de.schule.madnx.client.model.AbstractModel;
import de.schule.madnx.client.view.AbstractView;
import de.schule.madnx.client.view.LobbyView;
import de.schule.madnx.client.widgets.lobby.GameOptionsModule;
import de.schule.madnx.client.widgets.lobby.LobbyModule;
import de.schule.madnx.client.widgets.lobby.NetworkChatModule;
import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;

public class LobbyPresenter extends AbstractPresenter{
	
	public interface Display{
		NetworkChatModule getNetworkChatModule();
		
		GameOptionsModule getGameOptionsModule();
		
		LobbyModule getLobbyModule();
		
		HasClickHandlers getBtnStart();
		
		HasClickHandlers getBtnClose();
	}

	public LobbyPresenter(AbstractModel model, AbstractView view, GameController gameController) {
		super(model, view, gameController);
	}

	@Override
	public void addHandler() {
		((LobbyView) view).getNetworkChatModule().getBtnSend().addClickHandler(new SendClickHandler());
		((LobbyView) view).getNetworkChatModule().getTxtMessage().addKeyDownHandler(new EnterKeyDownHandler());
		((LobbyView) view).getBtnClose().addClickHandler(new CloseClickHandler());
		gameController.getEventBus().addHandler(GetMessageEvent.TYPE, new LobbyGetMessageHandler());
	}
	
	private class LobbyGetMessageHandler implements GetMessageHandler {
		
		@Override
		public void getMessage(GetMessageEvent event) {
			String data = event.getEvent().getData();
			
			JSONObject parse = (JSONObject) JSONParser.parse(data);
			String method = JSONHelper.valueToString(parse.get(Methods.METHOD).toString());
			
			if (method.equals(Methods.CHAT)) {
				String message = JSONHelper.valueToString(parse.get(Methods.MESSAGE).toString());
				
				String oldText = ((LobbyView) view).getNetworkChatModule().getTxtAContent().getText();
				if (oldText.equals("")) {
					((LobbyView) view).getNetworkChatModule().getTxtAContent().setText(message);
				} else {
				((LobbyView) view).getNetworkChatModule().getTxtAContent().setText(oldText + "\r\n" + message);
				}
			} else if (method.equals(Methods.LEAVE_GAME)) {
				
			} else if (method.equals(Methods.CLOSE_GAME)) {
				
			} else if (method.equals(Methods.JOIN_GAME)) {
				
			}
		}
	}
	
	private void sendMessage() {
		String message = ((LobbyView) view).getNetworkChatModule().getTxtMessage().getText();
		((LobbyView) view).getNetworkChatModule().getTxtMessage().setText("");
		JSONObject object = new JSONObject();
		object.put(Methods.METHOD, new JSONString(Methods.CHAT));
		object.put(Methods.MESSAGE, new JSONString(message));
		gameController.getWebSocket().send(object.toString());
	}
	
	private class SendClickHandler implements ClickHandler {
		
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
