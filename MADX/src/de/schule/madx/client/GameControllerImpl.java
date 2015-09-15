/**
 * 
 */
package de.schule.madx.client;

import com.google.gwt.event.shared.EventBus;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;

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
public class GameControllerImpl implements GameController, Presenter{

	private WebSocket webSocket;
	private final EventBus eventBus;
	private AnimationHelper container;
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
	}
	
	private void bind() {
		// TODO Die verschiedenen Event-Handler am Eventbus registieren
		
	}

	@Override
	public void go(AnimationHelper container) {
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
	public void initWebSocket(String url) {
		webSocket = WebSocket.create("ws://" + url);
		webSocket.setOnOpen(new OpenHandler() {
			
			@Override
			public void onOpen(WebSocket webSocket) {
				// TODO Auto-generated method stub
				
			}
		});
		
		webSocket.setOnClose(new CloseHandler() {
			
			@Override
			public void onClose(WebSocket webSocket) {
				// TODO Auto-generated method stub
				
			}
		});
		
		webSocket.setOnError(new ErrorHandler() {
			
			@Override
			public void onError(WebSocket webSocket) {
				// TODO Auto-generated method stub
				
			}
		});
		
		webSocket.setOnMessage(new MessageHandler() {
			
			@Override
			public void onMessage(WebSocket webSocket, MessageEvent event) {
				eventBus.fireEvent(new GetMessageEvent());
				
			}
		});
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
	public AnimationHelper getContainer() {
		return container;
	}
}
