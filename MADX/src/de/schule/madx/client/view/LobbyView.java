package de.schule.madx.client.view;

import com.google.gwt.user.client.ui.FlowPanel;

import de.schule.madx.client.presenter.LobbyPresenter.Display;
import de.schule.madx.client.widgets.lobby.GameOptionsModule;
import de.schule.madx.client.widgets.lobby.LobbyModule;
import de.schule.madx.client.widgets.lobby.NetworkChatModule;

public class LobbyView extends AbstractView implements Display{
	
	private NetworkChatModule networkChat;
	private GameOptionsModule gameOptions;
	private LobbyModule lobby;
	
	@Override
	void init(FlowPanel rootPanel) {
		networkChat = new NetworkChatModule();
		gameOptions = new GameOptionsModule();
		lobby = new LobbyModule();
		
		rootPanel.add(lobby.asWidget());
		rootPanel.add(gameOptions.asWidget());
		rootPanel.add(networkChat.asWidget());
		
		rootPanel.addStyleName("lobby-RootPanel");
	}

	@Override
	void initStyles() {
		networkChat.addStyle("lobby-Chat");
		gameOptions.addStyle("lobby-OptionsPanel");
		lobby.addStyle("lobby-PlayerPanel");
		
	}
	
	public NetworkChatModule getNetworkChatModule() {
		return networkChat;
	}
	
	public GameOptionsModule getGameOptionsModule() {
		return gameOptions;
	}
	
	public LobbyModule getLobbyModule() {
		return lobby;
	}

}
