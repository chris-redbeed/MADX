package de.schule.madnx.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

import de.schule.madnx.client.presenter.LobbyPresenter.Display;
import de.schule.madnx.client.widgets.lobby.GameOptionsModule;
import de.schule.madnx.client.widgets.lobby.LobbyModule;
import de.schule.madnx.client.widgets.lobby.NetworkChatModule;

public class LobbyView extends AbstractView implements Display{
	
	private NetworkChatModule networkChat;
	private GameOptionsModule gameOptions;
	private LobbyModule lobby;
	private Button btnStart;
	private Button btnClose;
	
	@Override
	void init(FlowPanel rootPanel) {
		networkChat = new NetworkChatModule();
		gameOptions = new GameOptionsModule();
		lobby = new LobbyModule();
		btnStart = new Button("Spiel starten");
		btnClose = new Button("zurück");
		
		rootPanel.add(lobby.asWidget());
		rootPanel.add(gameOptions.asWidget());
		rootPanel.add(networkChat.asWidget());
		
		rootPanel.add(btnStart);
		rootPanel.add(btnClose);
		
		rootPanel.addStyleName("lobby-RootPanel");
	}

	@Override
	void initStyles() {
		networkChat.addStyle("lobby-Chat");
		gameOptions.addStyle("lobby-OptionsPanel");
		lobby.addStyle("lobby-PlayerPanel");
		btnStart.addStyleName("btn lobby-Button-bottom btn-block btn-success");
		btnClose.addStyleName("btn lobby-Button-top btn-block btn-danger");
		
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

	@Override
	public HasClickHandlers getBtnStart() {
		return btnStart;
	}

	@Override
	public HasClickHandlers getBtnClose() {
		return btnClose;
	}

}
