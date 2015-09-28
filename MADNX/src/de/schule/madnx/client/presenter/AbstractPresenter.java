/**
 * 
 */
package de.schule.madnx.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import de.schule.madnx.client.GameController;
import de.schule.madnx.client.PresenterMapper;
import de.schule.madnx.client.view.AbstractView;

/**
 * @author xgadscj
 *
 */
public abstract class AbstractPresenter implements Presenter{
	
	protected GameController gameController;
	protected AbstractView view;
	
	public AbstractPresenter(AbstractView view, GameController gameController) {
		this.view = view;
		this.gameController= gameController;
		
		addHandler();
	}
	
	public abstract void addHandler();

	@Override
	public void go() {
		gameController.getContainer().clear();
		gameController.getContainer().add(view.asWidget());
	}
	
	protected class CloseClickHandler implements ClickHandler {
		
		@Override
		public void onClick(ClickEvent event) {
			gameController.getPresenterChanger().goTo(PresenterMapper.MENUE);
		}
	}

}
