package de.schule.madnx.client.presenter;

import java.util.ArrayList;

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
import de.schule.madnx.client.PresenterMapper;
import de.schule.madnx.client.event.GetMessageEvent;
import de.schule.madnx.client.event.GetMessageHandler;
import de.schule.madnx.client.view.AbstractView;
import de.schule.madnx.client.view.LobbyView;
import de.schule.madnx.client.widgets.lobby.GameOptionsModule;
import de.schule.madnx.client.widgets.lobby.LobbyModule;
import de.schule.madnx.client.widgets.lobby.NetworkChatModule;
import de.schule.madnx.client.widgets.lobby.PlayerObject;
import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;
import de.schule.madnx.shared.Option;
import de.schule.madnx.shared.Player;
import de.schule.madnx.shared.coder.OptionListCoder;
import de.schule.madnx.shared.coder.PlayerListCoder;

public class LobbyPresenter extends AbstractPresenter {

	public interface Display {
		NetworkChatModule getNetworkChatModule();

		GameOptionsModule getGameOptionsModule();

		LobbyModule getLobbyModule();

		HasClickHandlers getBtnStart();

		HasClickHandlers getBtnClose();
	}

	public LobbyPresenter(AbstractView view, GameController gameController) {
		super(view, gameController);
	}

	@Override
	public void addHandler() {
		((LobbyView) view).getNetworkChatModule().getBtnSend().addClickHandler(new SendClickHandler());
		((LobbyView) view).getNetworkChatModule().getTxtMessage().addKeyDownHandler(new EnterKeyDownHandler());
		((LobbyView) view).getBtnClose().addClickHandler(new CloseGameClickHandler());
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
			} else if (method.equals(Methods.JOIN_GAME)) {
				String result = JSONHelper.valueToString(parse.get("result").toString());
				if (!result.equals("error")) {
					String newMember = JSONHelper.valueToString(parse.get("new").toString());
					if (newMember.equals("true")) {
						gameController.getPresenterChanger().goTo(PresenterMapper.LOBBY);
					}
					String self = JSONHelper.valueToString(parse.get("self").toString());
					String playerResult = JSONHelper.valueToString(parse.get("players").toString());
					String optionResult = JSONHelper.valueToString(parse.get("options").toString());

					ArrayList<Player> players = PlayerListCoder.decode(playerResult);
					ArrayList<Option> options = OptionListCoder.decode(optionResult);

					updatePlayers(players, self);
					updateOptions(options);
				}
			} else if (method.equals(Methods.CLOSE_GAME)) {
				gameController.getPresenterChanger().goTo(PresenterMapper.MENUE);
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

	private void updatePlayers(ArrayList<Player> players, String self) {
		ArrayList<PlayerObject> list = new ArrayList<>();
		for (Player p : players) {
			PlayerObject module = null;
			if (p.getName().equals(self)) {
				module = new PlayerObject(p.getName(), p.getStatus(), true, 0);
			} else {
				module = new PlayerObject(p.getName(), p.getStatus(), false, 0);
			}
			list.add(module);
		}
		((LobbyView) view).getLobbyModule().updatePlayer(list);
	}

	private void updateOptions(ArrayList<Option> options) {
		// TODO Update Options
		// ArrayList<PlayerModul> list = new ArrayList<>();
		// for (Option o : options) {
		//
		// }
		// ((LobbyView) view).getGameOptionsModule().updateOptions(list);;
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

	private class CloseGameClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			JSONObject object = new JSONObject();
			object.put(Methods.METHOD, new JSONString(Methods.CLOSE_GAME));
			gameController.getWebSocket().send(object.toString());
		}

	}

}
