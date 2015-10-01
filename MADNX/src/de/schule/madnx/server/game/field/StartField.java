/**
 * 
 */
package de.schule.madnx.server.game.field;

/**
 * @author xgadscj
 *
 */
public class StartField extends Field {
	private PlayerColor player;

	public StartField(PlayerColor player,Field next, int x, int y) {
		super(next, x, y);
		this.player = player;
	}
	
	public PlayerColor getPlayer() {
		return player;
	}
}
