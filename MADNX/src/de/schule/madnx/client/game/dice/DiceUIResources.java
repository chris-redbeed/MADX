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
public interface DiceUIResources extends ClientBundle {
	public static final DiceUIResources INSTANCE = GWT.create(DiceUIResources.class);

	@Source("de/schule/madnx/client/game/dice/diceUI.css")
	DiceUIStyle css();

	/*
	 * 
	 */

	public interface DiceUIStyle extends CssResource {

		@ClassName("face")
		String face();

		@ClassName("face-0")
		String face0();

		@ClassName("face-1")
		String face1();

		@ClassName("face-2")
		String face2();

		@ClassName("face-3")
		String face3();

		@ClassName("face-4")
		String face4();

		@ClassName("face-5")
		String face5();

		@ClassName("dice")
		String dice();

		@ClassName("content")
		String content();

		@ClassName("background")
		String background();

		@ClassName("stage")
		String stage();

		@ClassName("device")
		String device();
		

		@ClassName("transition")
		String transition();
	}
}
