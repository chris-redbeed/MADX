/**
 * 
 */
package de.schule.madx.client;

import java.util.HashMap;
import java.util.Map;

import de.schule.madx.client.presenter.AbstractPresenter;
import de.schule.madx.client.presenter.LoginPresenter;
import de.schule.madx.client.view.LoginView;

/**
 * @author xgadscj
 *
 */
public class PresenterMapper {
	
	private Map<String, AbstractPresenter> presenterList;
	
	public static final String LOGIN_VIEW = "LoginView.class";
	
	public PresenterMapper(GameController gameController) {
		presenterList = new HashMap<>();
		LoginPresenter loginPresenter = new LoginPresenter(null, new LoginView(), gameController);
		presenterList.put(LOGIN_VIEW, loginPresenter);
	}
	
	public void startPresenterForView(String view) {
		AbstractPresenter newPresenter = presenterList.get(view);
		newPresenter.go();
	}
}
