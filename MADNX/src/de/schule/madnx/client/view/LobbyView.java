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

	private FlowPanel helpText;

	private FlowPanel bottomContainer;
	private FlowPanel chatContainer;
	private FlowPanel buttonCol;
	private FlowPanel textCol;
	
	@Override
	void init(FlowPanel rootPanel) {
		networkChat = new NetworkChatModule();
		gameOptions = new GameOptionsModule();
		lobby = new LobbyModule();

		this.bottomContainer = new FlowPanel();
		this.chatContainer = new FlowPanel();

		this.buttonCol = new FlowPanel();
		this.textCol = new FlowPanel();
		this.helpText = new FlowPanel();
		this.helpText.getElement().setInnerHTML("Du kannst <b>hier</b> mit deinen Spielkameraden Chaten oder direkt in das nächste Spiel starten!<br>Worauf wartest du? Los geht es!");
		
		btnStart = new Button("Spiel starten");
		btnClose = new Button("zurück");
		
		rootPanel.add(lobby.asWidget());
		rootPanel.add(gameOptions.asWidget());
		this.textCol.add(networkChat.asWidget());
		
		this.buttonCol.add(btnStart);
		this.buttonCol.add(btnClose);
		this.buttonCol.add(this.helpText);
		
		this.chatContainer.add(this.textCol);
		this.chatContainer.add(this.buttonCol);
		
		this.bottomContainer.add(this.chatContainer);
		rootPanel.add(this.bottomContainer);
		
		rootPanel.addStyleName("lobby-RootPanel");
	}

	@Override
	void initStyles() {
		
		this.bottomContainer.addStyleName("container-bottom");
		this.chatContainer.addStyleName("container");
		this.textCol.addStyleName("col-md-8");
		this.buttonCol.addStyleName("col-md-4");
		
		//networkChat.addStyle("");
		gameOptions.addStyle("lobby-OptionsPanel");
		lobby.addStyle("lobby-PlayerPanel");
		btnStart.addStyleName("btn btn-block btn-success");
		btnClose.addStyleName("btn btn-block btn-danger"); 
		
		this.helpText.addStyleName("well well-lg well-top");
		
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
