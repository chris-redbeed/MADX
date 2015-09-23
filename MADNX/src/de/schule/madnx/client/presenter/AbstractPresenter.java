/**
 * 
 */
package de.schule.madnx.client.presenter;

import de.schule.madnx.client.GameController;
import de.schule.madnx.client.model.AbstractModel;
import de.schule.madnx.client.view.AbstractView;

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
		
		addHandler();
	}
	
	public abstract void addHandler();

	@Override
	public void go() {
		gameController.getContainer().clear();
		gameController.getContainer().add(view.asWidget());
	}

}
