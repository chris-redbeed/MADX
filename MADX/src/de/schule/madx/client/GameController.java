/**
 * 
 */
package de.schule.madx.client;

import com.google.gwt.event.shared.HandlerManager;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;

import de.schule.madx.client.websocket.WebSocket;

/**
 * @author xgadscj
 *
 */
public interface GameController {
	
	public WebSocket getWebSocket();
	
	public void setWebSocket(WebSocket webSocket);
	
	public HandlerManager getEventBus();
	
	public AnimationViewChanger getAnimationViewChanger();
	
	public void go(AnimationHelper container);

}
