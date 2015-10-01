/**
 * 
 */
package de.schule.madnx.server.game.field;

/**
 * @author xgadscj
 *
 */
public class SpawnField extends Field {
	private PlayerColor player;
	private int id;

	public SpawnField(PlayerColor player, Field next, int x, int y) {
		super(next, x, y);
		this.player = player;
	}

	public PlayerColor getPlayer() {
		return player;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
