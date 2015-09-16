package de.schule.madx.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MADXEntryPoint implements EntryPoint {
	
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
	}
}
