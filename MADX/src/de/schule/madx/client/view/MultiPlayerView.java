package de.schule.madx.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import de.schule.madx.client.presenter.MultiPlayerPresenter.Display;

public class MultiPlayerView   extends AbstractView implements Display{
	
	private Label lblTitle;
	private Button btnConnect;
	private Button btnCreate;

	@Override
	void init(FlowPanel rootPanel) {
		lblTitle = new Label("Multiplayer Menü");
		btnConnect = new Button("Beitreten");
		btnCreate = new Button("Anlegen");
		
		rootPanel.add(lblTitle);
		rootPanel.add(btnConnect);
		rootPanel.add(btnCreate);
	}

	@Override
	void initStyles() {
		getRootPanel().addStyleName("menue-Main");
		lblTitle.addStyleName("menue-Title");
		btnConnect.addStyleName("gwt-Button-Menue");
		btnCreate.addStyleName("gwt-Button-Menue");
	}

	@Override
	public HasClickHandlers getBtnConnect() {
		return btnConnect;
	}

	@Override
	public HasClickHandlers getBtnCreate() {
		return btnCreate;
	}

}
