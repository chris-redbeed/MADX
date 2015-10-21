package de.schule.madnx.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import de.schule.madnx.client.presenter.MultiPlayerMenuePresenter.Display;

public class MultiPlayerMenueView   extends AbstractView implements Display{
	
	private Label lblTitle;
	private Button btnConnect;
	private Button btnCreate;
	private Button btnClose;
	
	private FlowPanel panel;
	private FlowPanel panelBody;
	
	private FlowPanel mainBox;

	@Override
	void init(FlowPanel rootPanel) {
		lblTitle = new Label("Multiplayer Menü");
		btnConnect = new Button("<i class='fa fa-users'></i> Beitreten");
		btnCreate = new Button("<i class='fa fa-sign-in'></i> Anlegen");
		btnClose = new Button("<i class='fa fa-chevron-circle-left'></i> zurück");
		
		this.mainBox = new FlowPanel();
		this.panel = new FlowPanel();
		this.panelBody = new FlowPanel();
		
		this.panel.add(lblTitle);
		this.panelBody.add(btnConnect);
		this.panelBody.add(btnCreate);
		this.panelBody.add(btnClose);
		
		this.panel.add(this.panelBody);

		this.mainBox.add(this.panel);
		rootPanel.add(this.mainBox);
	}

	@Override
	void initStyles() {
		
		this.mainBox.addStyleName("col-md-4 col-xs-10 col-md-offset-4 col-xs-offset-1");
		
		this.panel.addStyleName("panel panel-default");
		this.panelBody.addStyleName("panel-body");
		
		getRootPanel().addStyleName("menue-Main");
		lblTitle.addStyleName("menue-Title panel-heading");
		btnConnect.addStyleName("gwt-Button-Menue btn btn-primary btn-block");
		btnCreate.addStyleName("gwt-Button-Menue btn btn-success btn-block");
		btnClose.addStyleName("gwt-Button-Menue btn btn-link btn-block");
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
