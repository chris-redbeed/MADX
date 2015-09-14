/**
 * 
 */
package de.schule.madx.client;

import com.google.gwt.event.shared.HandlerManager;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;

import de.schule.madx.client.presenter.Presenter;
import de.schule.madx.client.websocket.WebSocket;

/**
 * @author xgadscj
 *
 */
public class GameControllerImpl implements GameController, Presenter{

	private WebSocket webSocket;
	private final HandlerManager eventBus;
	private AnimationHelper container;
	private AnimationViewChanger animationViewChanger;
	
	public GameControllerImpl(HandlerManager eventBus) {
		this.eventBus = eventBus;
		
		bind();
	}

	@Override
	public WebSocket getWebSocket() {
		return webSocket;
	}

	@Override
	public void setWebSocket(WebSocket webSocket) {
		this.webSocket = webSocket;
	}
	
	private void bind() {
		// TODO Die verschiedenen Event-Handler am Eventbus registieren
	}

	@Override
	public void go(AnimationHelper container) {
		this.container = container;
	}

	@Override
	public HandlerManager getEventBus() {
		return eventBus;
	}

	@Override
	public AnimationViewChanger getAnimationViewChanger() {
		if (animationViewChanger == null) {
			animationViewChanger = new AnimationViewChanger(container);
		}
		return animationViewChanger;
	}
}
