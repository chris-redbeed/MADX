package de.schule.madnx.client.game.dice;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.schule.madnx.client.game.dice.DiceUIResources.DiceUIStyle;

public class DiceUI extends Composite {
	private FlowPanel rootPanel;
	private FlowPanel dice;
	private FlowPanel face0;
	private FlowPanel face1;
	private FlowPanel face2;
	private FlowPanel face3;
	private FlowPanel face4;
	private FlowPanel face5;
	
	private DiceUIStyle css = DiceUIResources.INSTANCE.css();
	private boolean isEnabled;
	
	public DiceUI() {
		init();
		initStyles();
	}
	
	private void initStyles() {
		css.ensureInjected();
		
		dice.addStyleName(css.dice());
		
		face0.addStyleName(css.face());
		face1.addStyleName(css.face());
		face2.addStyleName(css.face());
		face3.addStyleName(css.face());
		face4.addStyleName(css.face());
		face5.addStyleName(css.face());
		
		face0.addStyleName(css.face0());
		face1.addStyleName(css.face1());
		face2.addStyleName(css.face2());
		face3.addStyleName(css.face3());
		face4.addStyleName(css.face4());
		face5.addStyleName(css.face5());
		
		rootPanel.addStyleName(css.stage());
		rootPanel.addStyleName(css.device());
		
	}

	private void init() {
		rootPanel = new FlowPanel();
		dice = new FlowPanel();
		face0 = new FlowPanel();
		face1 = new FlowPanel();
		face2 = new FlowPanel();
		face3 = new FlowPanel();
		face4 = new FlowPanel();
		face5 = new FlowPanel();
		
		DiceContentUI content1 = new DiceContentUI(1);
		DiceContentUI content2= new DiceContentUI(2);
		DiceContentUI content3 = new DiceContentUI(3);
		DiceContentUI content4 = new DiceContentUI(4);
		DiceContentUI content5 = new DiceContentUI(5);
		DiceContentUI content6 = new DiceContentUI(6);
		
		content1.getContent().addStyleName(css.content());
		content1.getBackground().addStyleName(css.background());
		content2.getContent().addStyleName(css.content());
		content2.getBackground().addStyleName(css.background());
		content3.getContent().addStyleName(css.content());
		content3.getBackground().addStyleName(css.background());
		content4.getContent().addStyleName(css.content());
		content4.getBackground().addStyleName(css.background());
		content5.getContent().addStyleName(css.content());
		content5.getBackground().addStyleName(css.background());
		content6.getContent().addStyleName(css.content());
		content6.getBackground().addStyleName(css.background());
		
		face0.add(content1.asWidget());
		face1.add(content2.asWidget());
		face2.add(content3.asWidget());
		face3.add(content4.asWidget());
		face4.add(content5.asWidget());
		face5.add(content6.asWidget());
		
		dice.add(face0);
		dice.add(face1);
		dice.add(face2);
		dice.add(face3);
		dice.add(face4);
		dice.add(face5);
		
		rootPanel.add(dice);
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

	public void setDice(int Count) {
		int randomLeft = (int) (Math.random() * RootPanel.get().getOffsetWidth());
		int randomTop = (int) (Math.random() * RootPanel.get().getOffsetHeight());
		rootPanel.addStyleName(css.transition());
		rootPanel.getElement().getStyle().setLeft(randomLeft, Unit.PX);
		rootPanel.getElement().getStyle().setTop(randomTop, Unit.PX);
		
	}

	public void addClickHandler(ClickHandler diceUIClickHandler) {
		dice.addDomHandler(diceUIClickHandler, ClickEvent.getType());
	}
}
