/**
 * 
 */
package de.schule.madx.client.presenter;

import com.googlecode.mgwt.ui.client.animation.AnimationHelper;

import de.schule.madx.client.model.AbstractModel;
import de.schule.madx.client.view.AbstractView;

/**
 * @author xgadscj
 *
 */
public abstract class AbstractPresenter implements Presenter{
	
	AbstractModel model;
	AbstractView view;
	
	public AbstractPresenter(AbstractModel model, AbstractView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void go(AnimationHelper container) {
		// TODO Auto-generated method stub
		
	}

}
