/**
 * 
 */
package de.schule.madnx.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import de.schule.madnx.client.presenter.MenuePresenter.Display;

/**
 * @author xgadscj
 *
 */
public class MenueView extends AbstractView implements Display{
	
	private Label lblTitle;
	private Button btnSinglePlayer;
	private Button btnMultiPlayer;
	private Button btnHighScore;
	private Button btnOptions;
	private Button btnExit;

	private FlowPanel panelBody;
	private FlowPanel panel;
	
	private FlowPanel mainBox;

	@Override
	void init(FlowPanel rootPanel) {
		lblTitle = new Label("Menü");
		btnSinglePlayer = new Button("Einzelspieler");
		btnMultiPlayer = new Button("<i class='fa fa-users'></i> Mehrspieler");
		btnHighScore = new Button("<i class='fa fa-list-ol'></i> HighScore");
		btnOptions = new Button("<i class='fa fa-cog'></i> Optionen");
		btnExit = new Button("<i class='fa fa-times-circle'></i> Beenden");
		
		this.mainBox = new FlowPanel();
		this.panel = new FlowPanel();
		this.panelBody = new FlowPanel();
		
		this.panel.add(lblTitle);
//		rootPanel.add(btnSinglePlayer);
		this.panelBody.add(btnMultiPlayer);
		this.panelBody.add(btnHighScore);
		this.panelBody.add(btnOptions);
		this.panelBody.add(btnExit);

		this.panel.add(this.panelBody);
		this.mainBox.add(this.panel);
		rootPanel.add(this.mainBox);
		
	}

	@Override
	void initStyles() {
		
		this.mainBox.addStyleName("col-md-4 col-xs-10 col-md-offset-4 col-xs-offset-1");
		
		getRootPanel().addStyleName("menue-Main");
		this.panel.addStyleName("panel panel-default");
		this.panelBody.addStyleName("panel-body");
		lblTitle.addStyleName("menue-Title panel-heading");
		btnSinglePlayer.addStyleName("gwt-Button-Menue btn btn-success btn-block");
		btnMultiPlayer.addStyleName("gwt-Button-Menue btn btn-success btn-block");
		btnHighScore.addStyleName("gwt-Button-Menue btn btn-primary btn-block");
		btnOptions.addStyleName("gwt-Button-Menue btn btn-primary btn-block");
		btnExit.addStyleName("gwt-Button-Menue btn btn-danger btn-block");
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
