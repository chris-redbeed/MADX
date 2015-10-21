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
	
	private FlowPanel textDiv;
	private FlowPanel passwordDiv;

	private Button btnRegister;
	private Button btnLogin;

	public LoginView() {
		super();
	}
	
	@Override
	void init(FlowPanel rootPanel) {
		lblTitle = new Label("MADNX");
		lblUser = new Label("User:");
		lblPassword = new Label("Password:");

		txtUser = new TextBox();
		pwtxtPassword = new PasswordTextBox();
		btnLogin = new Button("<i class='fa fa-sign-in'></i> Anmelden");
		btnRegister = new Button("<i class='fa fa-users'></i> Registrieren");
		
		this.textDiv = new FlowPanel();
		this.passwordDiv = new FlowPanel();
		
		rootPanel.add(lblTitle);
		this.textDiv.add(lblUser);
		this.textDiv.add(txtUser);
		this.passwordDiv.add(lblPassword);
		this.passwordDiv.add(pwtxtPassword);
		
		rootPanel.add(this.textDiv);
		rootPanel.add(this.passwordDiv);

		rootPanel.add(btnRegister);
		rootPanel.add(btnLogin);
	}

	@Override
	void initStyles() {
		getRootPanel().addStyleName("login-Main col-md-4 col-xs-10 col-md-offset-4 col-xs-offset-1 well");
		lblTitle.addStyleName("login-Title h2 text-center block-center");
		lblUser.addStyleName("login-Label");
		lblPassword.addStyleName("login-Label");

		txtUser.addStyleName("login-TextField form-control");
		pwtxtPassword.addStyleName("login-TextField form-control");
		btnLogin.addStyleName("login-Button btn btn-success");
		btnRegister.addStyleName("login-Button btn btn-warning pull-right");

		this.textDiv.addStyleName("form-group");
		this.passwordDiv.addStyleName("form-group");
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
