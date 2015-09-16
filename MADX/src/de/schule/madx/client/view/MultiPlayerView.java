package de.schule.madx.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

import de.schule.madx.client.presenter.MultiPlayerPresenter.Display;

public class MultiPlayerView   extends AbstractView implements Display{
	
	private Button btnConnect;
	private Button btnCreate;

	@Override
	void init(FlowPanel rootPanel) {
		btnConnect = new Button("Beitreten");
		btnCreate = new Button("Anlegen");
		
		rootPanel.add(btnConnect);
		rootPanel.add(btnCreate);
	}

	@Override
	void initStyles() {
		// TODO Auto-generated method stub
		
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
