/**
 * 
 */
package de.schule.madx.client.widgets.lobby;

/**
 * @author xgadscj
 *
 */
public class Player {
	
	private String name;
	private boolean status;
	private boolean self;
	private int position;
	
	public Player(String name, boolean status, boolean self, int position) {
		this.name = name;
		this.status = status;
		this.self = self;
		this.position = position;
	}
	
	public String getName() {
		return name;
	}

	public boolean getStatus() {
		return status;
	}
	
	public boolean getSelf() {
		return self;
	}
	
	public int getPosition() {
		return position;
	}
}
