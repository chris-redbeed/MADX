/**
 * 
 */
package de.schule.madx.client;

import com.google.gwt.event.shared.EventBus;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;

import de.schule.madx.client.websocket.WebSocket;

/**
 * @author xgadscj
 *
 */
public interface GameController {
	
	public WebSocket getWebSocket();
	
	public void setWebSocket(WebSocket webSocket);
	
	public EventBus getEventBus();
	
	public PresenterChanger getPresenterChanger();
	
	public void go(AnimationHelper container);
	
	public void initWebSocket(String url);
	
	public PresenterMapper getPresenterMapper();
	
	public AnimationHelper getContainer();

}
