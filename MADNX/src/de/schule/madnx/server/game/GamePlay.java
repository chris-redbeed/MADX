/**
 * 
 */
package de.schule.madnx.server.game;

import java.util.ArrayList;

/**
 * @author xgadscj
 *
 */
public class GamePlay {
	private GameMap map;
	private Player currentPlayer;
	private ArrayList<Player> players;
	private String winner;
	
	public GamePlay(String path) {
		players = new ArrayList<>();

		
	}
	
	public void start() {
		
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void nextPlayer() {
		
	}
	
	public void end() {
		
	}
	
	public boolean set(int id, int countFields) {
		return false;
	}
	
	public String getWinner() {
		return winner;
	}
}
