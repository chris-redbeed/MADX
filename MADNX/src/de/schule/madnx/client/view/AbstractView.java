/**
 * 
 */
package de.schule.madnx.client.view;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author xgadscj
 *
 */
public abstract class AbstractView implements View, IsWidget{
	
	private FlowPanel rootPanel;
	
	public AbstractView() {
		setRootPanel(new FlowPanel());
		init(getRootPanel());
		initStyles();
	}

	/**
	 * @param rootPanel the root panel to add the view's content
	 * @see #getRootPanel()
	 */
	abstract void init(FlowPanel rootPanel);
	
	abstract void initStyles();
	
	@Override
	public Widget asWidget() {
		return getRootPanel();
	}

	/**
	 * @return the root panel of this view
	 */
	public FlowPanel getRootPanel() {
		return rootPanel;
	}

	private void setRootPanel(FlowPanel rootPanel) {
		this.rootPanel = rootPanel;
	}

}
