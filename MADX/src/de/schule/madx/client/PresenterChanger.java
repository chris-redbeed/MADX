/**
 * 
 */
package de.schule.madx.client;

import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;

import de.schule.madx.client.view.AbstractView;
import de.schule.madx.client.view.LoginView;
import de.schule.madx.client.view.MenueView;

/**
 * @author xgadscj
 *
 */
public class PresenterChanger {

	private AbstractView currentView;
	private GameController gameController;

	public PresenterChanger(GameController gameController) {
		this.
		gameController = gameController;
	}
	
	public void goTo(String view) {
		gameController.getPresenterMapper().startPresenterForView(view);
	}
	
	public Animation getAnimation(AbstractView view) {
		
		if (currentView instanceof MenueView && view instanceof LoginView) {
			return Animations.FLIP_REVERSE;
		}
		return Animations.FLIP;
	}
}
