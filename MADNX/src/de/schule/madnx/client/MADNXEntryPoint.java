package de.schule.madnx.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

import de.schule.madnx.client.game.dice.DiceUI;
import de.schule.madnx.client.view.LobbyView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MADNXEntryPoint implements EntryPoint {
	
	private RootPanel rootPanel;
	private GameController gameController;
	private EventBus eventBus;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		init();
	}

	private void init() {
		rootPanel = RootPanel.get();
		EventBus eventBus = new SimpleEventBus();
		
		gameController = new GameControllerImpl(eventBus);
		gameController.go(rootPanel);
//		rootPanel.add(new LobbyView().asWidget());
	}
}
