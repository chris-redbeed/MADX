/**
 * 
 */
package de.schule.madnx.client.presenter;

import de.schule.madnx.client.GameController;
import de.schule.madnx.client.view.AbstractView;

/**
 * @author xgadscj
 *
 */
public class SinglePlayerGamePresenter extends AbstractPresenter{
	
	public interface Display {
		
	}

	public SinglePlayerGamePresenter(AbstractView view, GameController gameController) {
		super(view, gameController);
	}

	@Override
	public void addHandler() {
		// TODO Auto-generated method stub
		
	}

}
