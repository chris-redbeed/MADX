/**
 * 
 */
package de.schule.madnx.server.game;

import de.schule.madnx.server.game.field.Field.PlayerColor;

/**
 * @author xgadscj
 *
 */
public class Figure {
	private int id;
	private boolean set;
	private PlayerColor player;
	
	public Figure(int id, boolean set,PlayerColor player) {
		this.id= id;
		this.set = set;
		this.player = player;
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
	
	public boolean getSet() {
		return set;
	}
	
	public PlayerColor getPlayer() {
		return player;
	}
}
