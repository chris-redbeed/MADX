/**
 * 
 */
package de.schule.madnx.server.game.field;

/**
 * @author xgadscj
 *
 */
public class CornerField extends Field{
	
	private FinishField finish;
	private PlayerColor player;

	public CornerField(PlayerColor player, FinishField finish,Field next, int x, int y) {
		super(next, x, y);
		this.finish = finish;
		this.player = player;
	}
	
	public FinishField getFinish() {
		return finish;
	}
	
	public PlayerColor getPlayer() {
		return player;
	}

}
