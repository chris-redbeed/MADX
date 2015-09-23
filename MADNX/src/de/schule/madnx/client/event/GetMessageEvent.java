/**
 * 
 */
package de.schule.madnx.client.event;

import com.google.gwt.event.shared.GwtEvent;

import de.schule.madnx.client.websocket.MessageEvent;

/**
 * @author xgadscj
 *
 */
public class GetMessageEvent extends GwtEvent<GetMessageHandler> {
	public static Type<GetMessageHandler> TYPE = new Type<GetMessageHandler>();

	private MessageEvent event;

	public GetMessageEvent(MessageEvent event) {
		this.event = event;
	}

	@Override
	public Type<GetMessageHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GetMessageHandler handler) {
		handler.getMessage(this);
	}

	public MessageEvent getEvent() {
		return event;
	}
}
