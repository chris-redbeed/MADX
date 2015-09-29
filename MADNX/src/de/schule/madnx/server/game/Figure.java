/**
 * 
 */
package de.schule.madnx.server.game;

/**
 * @author xgadscj
 *
 */
public class Figure {
	private int id;
	private boolean set;
	private boolean clickable;
	
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSet(boolean set) {
		this.set = set;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean getClickable() {
		return clickable;
	}
	
	public boolean getSet() {
		return set;
	}
}
