package de.schule.madnx.server.game.field;

public class FinishField extends Field {
	private PlayerColor player;
	private boolean isFirst;

	public FinishField(boolean isFirst,PlayerColor player,Field next, int x, int y) {
		super(next, x, y);
		this.player = player;
		this.isFirst = isFirst;
	}
	
	public PlayerColor getPlayer() {
		return player;
	}
	
	public boolean getIsFirst() {
		return isFirst;
	}
}
