package de.schule.madx.client.view;

import com.google.gwt.user.client.ui.FlowPanel;

import de.schule.madx.client.presenter.LobbyPresenter.Display;
import de.schule.madx.client.widgets.lobby.NetworkChatModule;
import de.schule.madx.client.widgets.lobby.GameOptionsModule;

public class LobbyView extends AbstractView implements Display{
	
	private NetworkChatModule networkChat;
	private GameOptionsModule gameOptions;
	
	@Override
	void init(FlowPanel rootPanel) {
		networkChat = new NetworkChatModule();
		gameOptions = new GameOptionsModule();
		
		rootPanel.add(gameOptions.asWidget());
		rootPanel.add(networkChat.asWidget());
	}

	@Override
	void initStyles() {
//		networkChat.addStyle("lobby-Chat");
		
	}

}
