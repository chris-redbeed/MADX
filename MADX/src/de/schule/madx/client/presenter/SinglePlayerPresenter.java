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
public class SinglePlayerPresenter extends AbstractPresenter{
	
	public interface Display {
		
	}

	public SinglePlayerPresenter(AbstractModel model, AbstractView view, GameController gameController) {
		super(model, view, gameController);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addHandler() {
		// TODO Auto-generated method stub
		
	}

}
