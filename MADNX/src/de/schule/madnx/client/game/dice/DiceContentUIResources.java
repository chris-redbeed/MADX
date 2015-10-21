/**
 * 
 */
package de.schule.madnx.client.game.dice;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * @author xgadscj
 *
 */
public interface DiceContentUIResources extends ClientBundle {
	public static final DiceContentUIResources INSTANCE = GWT.create(DiceContentUIResources.class);

	@Source("de/schule/madnx/client/game/dice/diceContentUI.css")
	DiceContentUIStyle css();

	/*
	 * 
	 */

	public interface DiceContentUIStyle extends CssResource {

		@ClassName("dice-left-up")
		String leftUp();

		@ClassName("dice-left-mid")
		String leftMid();

		@ClassName("dice-left-down")
		String leftDown();

		@ClassName("dice-right-up")
		String rightUp();

		@ClassName("dice-right-mid")
		String rightMid();

		@ClassName("dice-right-down")
		String rightDown();

		@ClassName("dice-mid")
		String mid();

	}
}
