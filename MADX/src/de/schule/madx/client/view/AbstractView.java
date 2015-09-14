/**
 * 
 */
package de.schule.madx.client.view;

import java.util.Observable;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author xgadscj
 *
 */
public abstract class AbstractView implements View, IsWidget{
	
	protected FlowPanel main;
	
	public AbstractView() {
		init();
	}

	private void init() {
		main = new FlowPanel();
	}
	
	@Override
	public Widget asWidget() {
		return main;
	}

}
