/**
 * 
 */
package de.schule.madx.client;

import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;

/**
 * @author xgadscj
 *
 */
public class AnimationViewChanger {

	private AnimationHelper display;

	public AnimationViewChanger(AnimationHelper display) {
		this.display = display;
	}
	
	public void goTo() {
		
	}
	
	private Animation getAnimation() {
		return Animations.FLIP;
	}
}
