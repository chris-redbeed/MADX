/**
 * 
 */
package de.schule.madnx.client.game.dice;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import de.schule.madnx.client.game.dice.DiceContentUIResources.DiceContentUIStyle;

/**
 * @author xgadscj
 *
 */
public class DiceContentUI extends Composite{
	private FlowPanel rootPanel;
	private FlowPanel content;
	private FlowPanel background;
	private FlowPanel mid;
	private FlowPanel leftUp;
	private FlowPanel leftDown;
	private FlowPanel leftMid;
	private FlowPanel rightUp;
	private FlowPanel rightDown;
	private FlowPanel rightMid;
	
	private DiceContentUIStyle css = DiceContentUIResources.INSTANCE.css();

	public DiceContentUI(int number) {
		init();
		initStyles();
		setContent(number);
	}

	private void initStyles() {
		css.ensureInjected();
	}

	private void init() {
		
		rootPanel = new FlowPanel();
		content = new FlowPanel();
		background = new FlowPanel();
		mid = new FlowPanel();
		leftUp = new FlowPanel();
		leftDown = new FlowPanel();
		leftMid = new FlowPanel();
		rightUp = new FlowPanel();
		rightDown = new FlowPanel();
		rightMid = new FlowPanel();

		content.add(mid);
		content.add(leftUp);
		content.add(leftDown);
		content.add(leftMid);
		content.add(rightUp);
		content.add(rightDown);
		content.add(rightMid);
		
		rootPanel.add(content);
		rootPanel.add(background);
	}

	private void setContent(int number) {
		
		switch (number) {
		case 1:
			mid.addStyleName(css.mid());
			break;
		case 2:
			leftUp.addStyleName(css.leftUp());
			rightDown.addStyleName(css.rightDown());
			break;
		case 3:
			mid.addStyleName(css.mid());
			leftUp.addStyleName(css.leftUp());
			rightDown.addStyleName(css.rightDown());
			break;
		case 4:
			leftUp.addStyleName(css.leftUp());
			rightDown.addStyleName(css.rightDown());
			rightUp.addStyleName(css.rightUp());
			leftDown.addStyleName(css.leftDown());
			break;
		case 5:
			mid.addStyleName(css.mid());
			leftUp.addStyleName(css.leftUp());
			rightDown.addStyleName(css.rightDown());
			rightUp.addStyleName(css.rightUp());
			leftDown.addStyleName(css.leftDown());
			break;
		case 6:
			leftUp.addStyleName(css.leftUp());
			rightDown.addStyleName(css.rightDown());
			rightUp.addStyleName(css.rightUp());
			leftDown.addStyleName(css.leftDown());
			leftMid.addStyleName(css.leftMid());
			rightMid.addStyleName(css.rightMid());
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
	
	public FlowPanel getContent() {
		return content;
	}
	
	public FlowPanel getBackground() {
		return background;
	}
}
