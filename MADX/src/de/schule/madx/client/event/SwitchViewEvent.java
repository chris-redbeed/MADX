/**
 * 
 */
package de.schule.madx.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author xgadscj
 *
 */
public class SwitchViewEvent extends GwtEvent<SwitchViewHandler> {
	public static Type<SwitchViewHandler> TYPE = new Type<SwitchViewHandler>();

	@Override
	public Type<SwitchViewHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SwitchViewHandler handler) {
		handler.switchView(this);
	}
}
