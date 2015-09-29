/**
 * 
 */
package de.schule.madnx.client.view;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import de.schule.madnx.client.presenter.GamePresenter.Display;

/**
 * @author xgadscj
 *
 */
public class GameView extends AbstractView implements Display {
	public AbsolutePanel board;
	
	@Override
	public Widget asWidget() {
		return board;
	}

	public AbsolutePanel getBoard() {
		return board;
	}

	@Override
	void init(FlowPanel rootPanel) {
		board = new AbsolutePanel();
	}

	@Override
	void initStyles() {
		board.addStyleName("board");
	}

}
