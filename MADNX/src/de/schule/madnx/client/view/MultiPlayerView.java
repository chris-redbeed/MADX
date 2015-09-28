package de.schule.madnx.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import de.schule.madnx.client.presenter.MultiPlayerMenuePresenter.Display;

public class MultiPlayerView   extends AbstractView implements Display{
	
	private Label lblTitle;
	private Button btnConnect;
	private Button btnCreate;
	private Button btnClose;

	@Override
	void init(FlowPanel rootPanel) {
		lblTitle = new Label("Multiplayer Menü");
		btnConnect = new Button("Beitreten");
		btnCreate = new Button("Anlegen");
		btnClose = new Button("zurück");
		
		rootPanel.add(lblTitle);
		rootPanel.add(btnConnect);
		rootPanel.add(btnCreate);
		rootPanel.add(btnClose);
	}

	@Override
	void initStyles() {
		getRootPanel().addStyleName("menue-Main");
		lblTitle.addStyleName("menue-Title");
		btnConnect.addStyleName("gwt-Button-Menue");
		btnCreate.addStyleName("gwt-Button-Menue");
		btnClose.addStyleName("gwt-Button-Menue");
	}

	@Override
	public HasClickHandlers getBtnConnect() {
		return btnConnect;
	}

	@Override
	public HasClickHandlers getBtnCreate() {
		return btnCreate;
	}

	@Override
	public HasClickHandlers getBtnClose() {
		return btnClose;
	}

}
