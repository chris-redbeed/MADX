/**
 * 
 */
package de.schule.madnx.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;

import de.schule.madnx.client.websocket.WebSocket;

/**
 * @author xgadscj
 *
 */
public interface GameController {
	
	public WebSocket getWebSocket();
	
	public void setWebSocket(WebSocket webSocket);
	
	public EventBus getEventBus();
	
	public PresenterChanger getPresenterChanger();
	
	public void go(HasWidgets container);
	
	public PresenterMapper getPresenterMapper();
	
	public HasWidgets getContainer();

}
