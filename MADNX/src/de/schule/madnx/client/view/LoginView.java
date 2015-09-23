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
	
	private TextBox txtUser;
	private PasswordTextBox pwtxtPassword;

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

		txtUser = new TextBox();
		pwtxtPassword = new PasswordTextBox();
		btnLogin = new Button("anmelden");
		btnRegister = new Button("registrieren");
		
		rootPanel.add(lblTitle);
		rootPanel.add(lblUser);
		rootPanel.add(txtUser);
		rootPanel.add(lblPassword);
		rootPanel.add(pwtxtPassword);

		rootPanel.add(btnRegister);
		rootPanel.add(btnLogin);
	}

	@Override
	void initStyles() {
		getRootPanel().addStyleName("login-Main");
		lblTitle.addStyleName("login-Title");
		lblUser.addStyleName("login-Label");
		lblPassword.addStyleName("login-Label");

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
	public HasClickHandlers getBtnLogin() {
		return btnLogin;
	}

	@Override
	public HasClickHandlers getBtnRegister() {
		return btnRegister;
	}
}
