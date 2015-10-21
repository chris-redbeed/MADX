/**
 * 
 */
package de.schule.madnx.client;

import com.google.gwt.user.client.ui.PopupPanel;

/**
 * @author xgadscj
 *
 */
public class UIManager {

	private PopupPanel blocker;
	/**
	 * 
	 */
	public UIManager() {
	}
	
	
	public void lock(){
		if(blocker == null){
			blocker = new PopupPanel(false, true);
		}
		if(!blocker.isShowing()){
			blocker.show();
		}
	}
	public void unlock(){
		if(blocker == null){
			return;
		}
		if(blocker.isShowing()){
			blocker.hide();
		}
	}
	
}
