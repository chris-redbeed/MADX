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
	
	private FlowPanel main;
	
	private TextBox txtUser;
	private PasswordTextBox pwtxtPassword;
	private TextBox txtServer;
	private TextBox txtPort;
	private TextBox txtUrl;
	private Button btnRegister;
	private Button btnLogin;

	public LoginView() {
		init();
		initStyles();
		
		main.add(txtUser);
		main.add(pwtxtPassword);
		main.add(txtServer);
		main.add(txtPort);
		main.add(txtUrl);
		main.add(btnRegister);
		main.add(btnLogin);
	}
	
	private void init() {
		main = new FlowPanel();
		txtPort = new TextBox();
		txtServer = new TextBox();
		txtUrl = new TextBox();
		txtUser = new TextBox();
		pwtxtPassword = new PasswordTextBox();
		btnLogin = new Button("anmelden");
		btnRegister = new Button("registrieren");
	}
	
	private void initStyles() {
		
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
