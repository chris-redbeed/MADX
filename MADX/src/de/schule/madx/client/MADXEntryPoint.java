package de.schule.madx.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MADXEntryPoint implements EntryPoint {
	
	private RootPanel rootPanel;
	private AnimationHelper display;
	private GameController gameController;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		init();
	}

	private void init() {
		rootPanel = RootPanel.get();
		display = new AnimationHelper();
		rootPanel.add(display);
		
		gameController = GWT.create(GameController.class);
		gameController.go(display);
	}
}
