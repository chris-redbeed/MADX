/**
 * 
 */
package de.schule.madx.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;

import de.schule.madx.client.event.GetMessageEvent;
import de.schule.madx.client.presenter.Presenter;
import de.schule.madx.client.websocket.CloseHandler;
import de.schule.madx.client.websocket.ErrorHandler;
import de.schule.madx.client.websocket.MessageEvent;
import de.schule.madx.client.websocket.MessageHandler;
import de.schule.madx.client.websocket.OpenHandler;
import de.schule.madx.client.websocket.WebSocket;

/**
 * @author xgadscj
 *
 */
public class GameControllerImpl implements GameController, Presenter {

	private WebSocket webSocket;
	private final EventBus eventBus;
	private HasWidgets container;
	private PresenterChanger animationViewChanger;
	private PresenterMapper presenterMapper;

	public GameControllerImpl(EventBus eventBus) {
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

		webSocket.setOnOpen(new OnOpenHandler());

		webSocket.setOnClose(new OnCloseHandler());

		webSocket.setOnError(new OnErrorHandler());

		webSocket.setOnMessage(new OnMessageHandler());
	}

	private void bind() {
		// TODO Die verschiedenen Event-Handler am Eventbus registieren

	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;
		go();
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PresenterChanger getPresenterChanger() {
		if (animationViewChanger == null) {
			animationViewChanger = new PresenterChanger(this);
		}
		return animationViewChanger;
	}

	@Override
	public void go() {
		getPresenterChanger().goTo(PresenterMapper.LOGIN);
	}

	@Override
	public PresenterMapper getPresenterMapper() {
		if (presenterMapper == null) {
			presenterMapper = new PresenterMapper(this);
		}
		return presenterMapper;
	}

	@Override
	public HasWidgets getContainer() {
		return container;
	}

	private class OnOpenHandler implements OpenHandler {

		@Override
		public void onOpen(WebSocket webSocket) {
			System.out.println("WebSocket: OPEN");
		}
	}

	private class OnCloseHandler implements CloseHandler {

		@Override
		public void onClose(WebSocket webSocket) {
			System.out.println("WebSocket: CLOSED");
		}
	}

	private class OnErrorHandler implements ErrorHandler {

		@Override
		public void onError(WebSocket webSocket) {
			System.out.println("WebSocket: ERROR");
		}
	}

	private class OnMessageHandler implements MessageHandler {

		@Override
		public void onMessage(WebSocket webSocket, MessageEvent event) {
			eventBus.fireEvent(new GetMessageEvent(event));
		}
	}
}
