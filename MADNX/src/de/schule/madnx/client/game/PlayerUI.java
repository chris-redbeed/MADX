/**
 * 
 */
package de.schule.madnx.client.game;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author xgadscj
 *
 */
public class PlayerUI extends Composite{
	
	private FlowPanel rootPanel;
	private FlowPanel body;
	private FlowPanel head;
	
	public PlayerUI(String style) {
		init();
		initStyles(style);
	}
	
	@Override
	public Widget asWidget() {
		return rootPanel;
	}

	private void initStyles(String style) {
		rootPanel.addStyleName("");
		body.addStyleName("");
		head.addStyleName("");
		body.addStyleName(style);
		head.addStyleName(style);
	}
	
	public void addClickHandler(ClickHandler handler) {
		rootPanel.addDomHandler(handler, ClickEvent.getType());
	}

	private void init() {
		rootPanel = new FlowPanel();
		body = new FlowPanel();
		head = new FlowPanel();
		
		rootPanel.add(head);
		rootPanel.add(body);
	}

}
