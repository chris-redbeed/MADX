/**
 * 
 */
package de.schule.madx.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author xgadscj
 *
 */
public class GetMessageEvent extends GwtEvent<GetMessageHandler> {
	public static Type<GetMessageHandler> TYPE = new Type<GetMessageHandler>();

	@Override
	public Type<GetMessageHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GetMessageHandler handler) {
		handler.getMessage(this);
	}
}
