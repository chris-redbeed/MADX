/**
 * 
 */
package de.schule.madx.client;

import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;

import de.schule.madx.client.view.AbstractView;

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
		
//		if (currentView instanceof LoginView && view instanceof LoginView) {
//			return Animations.FLIP;
//		} else if () {
//			
//		}
		
		return Animations.FLIP;
	}
}
