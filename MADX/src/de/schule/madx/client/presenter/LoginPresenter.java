/**
 * 
 */
package de.schule.madx.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

import de.schule.madx.client.GameController;
import de.schule.madx.client.model.AbstractModel;
import de.schule.madx.client.view.AbstractView;
import de.schule.madx.client.view.LoginView;

/**
 * @author xgadscj
 *
 */
public class LoginPresenter extends AbstractPresenter {
	
	public interface Display {
		
		HasText getTxtUser();
		
		HasText getPwTxtPassword();
		
		HasText getTxtServer();
		
		HasText getTxtPort();
		
		HasText getTxtUrl();
		
		HasClickHandlers getBtnLogin();
		
		HasClickHandlers getBtnRegister();
		
	}
	
	public LoginPresenter(AbstractModel model, AbstractView view, GameController gameController) {
		super(model, view, gameController);
		
		addHandler();
	}

	private void addHandler() {
		((LoginView) view).getBtnLogin().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		((LoginView) view).getBtnRegister().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
	}


}
