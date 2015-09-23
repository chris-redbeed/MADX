/**
 * 
 */
package de.schule.madnx.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

import de.schule.madnx.client.presenter.LoginPresenter.Display;

/**
 * @author xgadscj
 *
 */
public class LoginView extends AbstractView implements Display{
	
	private Label lblTitle;
	private Label lblUser;
	private Label lblPassword;
	private Label lblServer;
	private Label lblPort;
	private Label lblUrl;
	
	private TextBox txtUser;
	private PasswordTextBox pwtxtPassword;
	private TextBox txtServer;
	private TextBox txtPort;
	private TextBox txtUrl;
	private Button btnRegister;
	private Button btnLogin;

	public LoginView() {
		super();
	}
	
	@Override
	void init(FlowPanel rootPanel) {
		lblTitle = new Label("MADX");
		lblUser = new Label("User:");
		lblPassword = new Label("Password:");
		lblServer = new Label("Server:");
		lblPort = new Label("Port:");
		lblUrl = new Label("Url:");
		
		txtPort = new TextBox();
		txtPort.setText("8080");
		txtServer = new TextBox();
		txtServer.setText("msgscjm1.gadeg.de");
		txtUrl = new TextBox();
		txtUrl.setText("MADX/serverendpoint");
		txtUser = new TextBox();
		pwtxtPassword = new PasswordTextBox();
		btnLogin = new Button("anmelden");
		btnRegister = new Button("registrieren");
		
		rootPanel.add(lblTitle);
		rootPanel.add(lblUser);
		rootPanel.add(txtUser);
		rootPanel.add(lblPassword);
		rootPanel.add(pwtxtPassword);
		rootPanel.add(lblServer);
		rootPanel.add(txtServer);
		rootPanel.add(lblPort);
		rootPanel.add(txtPort);
		rootPanel.add(lblUrl);
		rootPanel.add(txtUrl);

		rootPanel.add(btnRegister);
		rootPanel.add(btnLogin);
	}

	@Override
	void initStyles() {
		getRootPanel().addStyleName("login-Main");
		lblTitle.addStyleName("login-Title");
		lblUser.addStyleName("login-Label");
		lblPassword.addStyleName("login-Label");
		lblServer.addStyleName("login-Label");
		lblPort.addStyleName("login-Label");
		lblUrl.addStyleName("login-Label");
		txtPort.addStyleName("login-TextField");
		txtServer.addStyleName("login-TextField");
		txtUrl.addStyleName("login-TextField");
		txtUser.addStyleName("login-TextField");
		pwtxtPassword.addStyleName("login-TextField");
		btnLogin.addStyleName("login-Button");
		btnRegister.addStyleName("login-Button");
	}

	@Override
	public HasText getTxtUser() {
		return txtUser;
	}

	@Override
	public HasText getPwTxtPassword() {
		return pwtxtPassword;
	}

	@Override
	public HasText getTxtServer() {
		return txtServer;
	}

	@Override
	public HasText getTxtPort() {
		return txtPort;
	}

	@Override
	public HasText getTxtUrl() {
		return txtUrl;
	}

	@Override
	public HasClickHandlers getBtnLogin() {
		return btnLogin;
	}

	@Override
	public HasClickHandlers getBtnRegister() {
		return btnRegister;
	}
}
