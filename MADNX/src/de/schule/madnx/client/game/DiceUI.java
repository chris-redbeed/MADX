package de.schule.madnx.client.game;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class DiceUI extends Composite {
	private FlowPanel rootPanel;
	private FlowPanel mid;
	private FlowPanel leftUp;
	private FlowPanel leftDown;
	private FlowPanel leftMid;
	private FlowPanel rightUp;
	private FlowPanel rightDown;
	private FlowPanel rightMid;

	private boolean enabled;

	public DiceUI() {
		init();
		initStyles();
	}

	private void initStyles() {
		rootPanel.setStyleName("dice");
		rootPanel.addStyleName("white");
		mid.setStyleName("dice-mid");
		leftUp.setStyleName("dice-left-up");
		leftDown.setStyleName("dice-left-down");
		leftMid.setStyleName("dice-left-mid");
		rightUp.setStyleName("dice-right-up");
		rightDown.setStyleName("dice-right-down");
		rightMid.setStyleName("dice-right-mid");
	}

	private void init() {
		enabled = true;
		
		rootPanel = new FlowPanel();
		mid = new FlowPanel();
		leftUp = new FlowPanel();
		leftDown = new FlowPanel();
		leftMid = new FlowPanel();
		rightUp = new FlowPanel();
		rightDown = new FlowPanel();
		rightMid = new FlowPanel();

		rootPanel.add(mid);
		rootPanel.add(leftUp);
		rootPanel.add(leftDown);
		rootPanel.add(leftMid);
		rootPanel.add(rightUp);
		rootPanel.add(rightDown);
		rootPanel.add(rightMid);
	}

	public void setDice(int number) {
		mid.setStyleName("dice-mid");
		leftUp.setStyleName("dice-left-up");
		leftDown.setStyleName("dice-left-down");
		leftMid.setStyleName("dice-left-mid");
		rightUp.setStyleName("dice-right-up");
		rightDown.setStyleName("dice-right-down");
		rightMid.setStyleName("dice-right-mid");
		
		switch (number) {
		case 1:
			mid.addStyleName("black");
			break;
		case 2:
			leftUp.addStyleName("black");
			rightDown.addStyleName("black");
			break;
		case 3:
			mid.addStyleName("black");
			leftUp.addStyleName("black");
			rightDown.addStyleName("black");
			break;
		case 4:
			leftUp.addStyleName("black");
			rightDown.addStyleName("black");
			rightUp.addStyleName("black");
			leftDown.addStyleName("black");
			break;
		case 5:
			mid.addStyleName("black");
			leftUp.addStyleName("black");
			rightDown.addStyleName("black");
			rightUp.addStyleName("black");
			leftDown.addStyleName("black");
			break;
		case 6:
			leftUp.addStyleName("black");
			rightDown.addStyleName("black");
			rightUp.addStyleName("black");
			leftDown.addStyleName("black");
			leftMid.addStyleName("black");
			rightMid.addStyleName("black");
			break;
		}
	}
	
	public void addClickHandler(ClickHandler handler) {
		rootPanel.addDomHandler(handler, ClickEvent.getType());
	}

	@Override
	public Widget asWidget() {
		return rootPanel;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean getEnabled() {
		return enabled;
	}
}
