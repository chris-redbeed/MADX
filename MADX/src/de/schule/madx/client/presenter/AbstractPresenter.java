/**
 * 
 */
package de.schule.madx.client.presenter;

import de.schule.madx.client.GameController;
import de.schule.madx.client.model.AbstractModel;
import de.schule.madx.client.view.AbstractView;

/**
 * @author xgadscj
 *
 */
public abstract class AbstractPresenter implements Presenter{
	
	protected GameController gameController;
	protected AbstractModel model;
	protected AbstractView view;
	
	public AbstractPresenter(AbstractModel model, AbstractView view, GameController gameController) {
		this.model = model;
		this.view = view;
		this.gameController= gameController;
	}

	@Override
	public void go() {
		gameController.getContainer().goTo(view, gameController.getPresenterChanger().getAnimation(view));
	}

}