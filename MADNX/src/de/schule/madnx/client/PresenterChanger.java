/**
 * 
 */
package de.schule.madnx.client;

/**
 * @author xgadscj
 *
 */
public class PresenterChanger {

	private GameController gameController;

	public PresenterChanger(GameController gameController) {
		this.
		gameController = gameController;
	}
	
	public void goTo(String view) {
		gameController.getPresenterMapper().startPresenterForView(view);
	}
}
