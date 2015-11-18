/**
 * 
 */
package de.schule.madnx.client.game.dice;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.schule.madnx.client.game.dice.DiceUIResources.DiceContentUIStyle;

/**
 * @author xgadscj
 *
 */
public class DiceUI extends Composite{
	private FlowPanel rootPanel;
	private FlowPanel mid;
	private FlowPanel leftUp;
	private FlowPanel leftDown;
	private FlowPanel leftMid;
	private FlowPanel rightUp;
	private FlowPanel rightDown;
	private FlowPanel rightMid;
	
	private boolean isEnabled;
	
	private DiceContentUIStyle css = DiceUIResources.INSTANCE.css();

	public DiceUI() {
		init();
		initStyles();
		setContent(3);
	}

	private void initStyles() {
		css.ensureInjected();
		rootPanel.addStyleName(css.dice());
	}

	private void init() {
		
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

	public void setContent(int number) {
		rootPanel.addStyleName("throwing");
		moveDice();
		leftUp.removeStyleName(css.leftUp());
		rightDown.removeStyleName(css.rightDown());
		rightUp.removeStyleName(css.rightUp());
		leftDown.removeStyleName(css.leftDown());
		leftMid.removeStyleName(css.leftMid());
		rightMid.removeStyleName(css.rightMid());
		mid.removeStyleName(css.mid());
		
		switch (number) {
		case 1:
			mid.setStyleName(css.mid());
			break;
		case 2:
			leftUp.setStyleName(css.leftUp());
			rightDown.setStyleName(css.rightDown());
			break;
		case 3:
			mid.setStyleName(css.mid());
			leftUp.setStyleName(css.leftUp());
			rightDown.setStyleName(css.rightDown());
			break;
		case 4:
			leftUp.setStyleName(css.leftUp());
			rightDown.setStyleName(css.rightDown());
			rightUp.setStyleName(css.rightUp());
			leftDown.setStyleName(css.leftDown());
			break;
		case 5:
			mid.setStyleName(css.mid());
			leftUp.setStyleName(css.leftUp());
			rightDown.setStyleName(css.rightDown());
			rightUp.setStyleName(css.rightUp());
			leftDown.setStyleName(css.leftDown());
			break;
		case 6:
			leftUp.setStyleName(css.leftUp());
			rightDown.setStyleName(css.rightDown());
			rightUp.setStyleName(css.rightUp());
			leftDown.setStyleName(css.leftDown());
			leftMid.setStyleName(css.leftMid());
			rightMid.setStyleName(css.rightMid());
			break;
		}
		rootPanel.removeStyleName("throwing");
	}
	
	public void addClickHandler(ClickHandler handler) {
		rootPanel.addDomHandler(handler, ClickEvent.getType());
	}

	@Override
	public Widget asWidget() {
		return rootPanel;
	}
	
	public boolean getEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean b) {
		this.isEnabled = b;
	}

	private void moveDice() {
		int randomLeft = (int) (Math.random() * RootPanel.get().getOffsetWidth());
		int randomTop = (int) (Math.random() * RootPanel.get().getOffsetHeight());
		rootPanel.addStyleName(css.transition());
		rootPanel.getElement().getStyle().setLeft(randomLeft, Unit.PX);
		rootPanel.getElement().getStyle().setTop(randomTop, Unit.PX);
	}

}
