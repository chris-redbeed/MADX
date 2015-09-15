/**
 * 
 */
package de.schule.madx.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

import de.schule.madx.client.presenter.MenuePresenter.Display;

/**
 * @author xgadscj
 *
 */
public class MenueView extends AbstractView implements Display{
	
	private Button btnSinglePlayer;
	private Button btnMultiPlayer;
	private Button btnHighScore;
	private Button btnOptions;
	private Button btnExit;

	@Override
	void init(FlowPanel rootPanel) {
		btnSinglePlayer = new Button("Einzelspieler");
		btnMultiPlayer = new Button("Mehrspieler");
		btnHighScore = new Button("HighScore");
		btnOptions = new Button("Optionen");
		btnExit = new Button("Beenden");
		
		rootPanel.add(btnSinglePlayer);
		rootPanel.add(btnMultiPlayer);
		rootPanel.add(btnHighScore);
		rootPanel.add(btnOptions);
		rootPanel.add(btnExit);
		
	}

	@Override
	void initStyles() {
		getRootPanel().addStyleName("Menue-Main");
		btnSinglePlayer.addStyleName("gwt-Button-Menue");
		btnMultiPlayer.addStyleName("gwt-Button-Menue");
		btnHighScore.addStyleName("gwt-Button-Menue");
		btnOptions.addStyleName("gwt-Button-Menue");
		btnExit.addStyleName("gwt-Button-Menue");
	}

	@Override
	public HasClickHandlers getBtnSinglePlayer() {
		return btnSinglePlayer;
	}

	@Override
	public HasClickHandlers getBtnMultiPlayer() {
		return btnMultiPlayer;
	}

	@Override
	public HasClickHandlers getBtnHighScore() {
		return btnHighScore;
	}

	@Override
	public HasClickHandlers getBtnOptions() {
		return btnOptions;
	}

	@Override
	public HasClickHandlers getBtnExit() {
		return btnExit;
	}

}
