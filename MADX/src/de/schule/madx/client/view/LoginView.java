/**
 * 
 */
package de.schule.madx.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

import de.schule.madx.client.presenter.LoginPresenter.Display;

/**
 * @author xgadscj
 *
 */
public class LoginView extends AbstractView implements Display{
	
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
		txtPort = new TextBox();
		txtServer = new TextBox();
		txtUrl = new TextBox();
		txtUser = new TextBox();
		pwtxtPassword = new PasswordTextBox();
		btnLogin = new Button("anmelden");
		btnRegister = new Button("registrieren");
		
		rootPanel.add(txtUser);
		rootPanel.add(pwtxtPassword);
		rootPanel.add(txtServer);
		rootPanel.add(txtPort);
		rootPanel.add(txtUrl);
		rootPanel.add(btnRegister);
		rootPanel.add(btnLogin);
	}

	@Override
	void initStyles() {
		// TODO Auto-generated method stub
		
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
