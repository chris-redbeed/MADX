/**
 * 
 */
package de.schule.madnx.server.game;

import java.util.ArrayList;
import de.schule.madnx.server.game.map.FieldMap;
import de.schule.madnx.server.game.map.FourPlayerMap;

/**
 * @author xgadscj
 *
 */
public class GamePlay {
	private GameMap map;
	private Player currentPlayer;
	private ArrayList<Player> players;
	private String winner;
	private FieldMap fieldMap;
	private int currentDicedNumber;
	protected boolean _isPreGame = true;
	protected int _preGameWinningNumber = 0;
	protected int _preGameWinner = 0;
	
	public GamePlay(int countPlayers) {
		players = new ArrayList<>();
		map = new GameMap();
		switch (countPlayers) {

		default:
			fieldMap = new FourPlayerMap();
			break;
		}
		fieldMap.createFields(map);
	}

	public void start() {
		fieldMap.createFigures(map, players);
		currentPlayer = players.get(0);
		this._isPreGame = true;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Player nextPlayer() {
		int index = players.indexOf(currentPlayer) + 1;
		if (index < players.size()) {
			currentPlayer = players.get(index);
		} else {
			if(this._isPreGame){
				this._isPreGame = false;
				return currentPlayer = players.get(this._preGameWinner);
			}
			currentPlayer = players.get(0);
		}
		return currentPlayer;
	}

	public int dice() {
		int dice = Dice.dice();
		currentDicedNumber = dice;
		if(this._isPreGame){
			if(currentDicedNumber > this._preGameWinningNumber)
				this._preGameWinner = players.indexOf(this.currentPlayer);
				this._preGameWinningNumber = currentDicedNumber;
				this.setEnd();
		}
			
		return currentDicedNumber;
	}

	public void end() {
		
	}
	
	public Player setEnd() {
		return nextPlayer();
	}

	public int[][] set(int id) {
		ArrayList<Figure> figures = currentPlayer.getFigures();
		for (Figure f : figures) {
			if (f.getId() == id) {
				return map.setFigure(f, currentDicedNumber);
			}
		}
		return null;
	}

	public String getWinner() {
		return winner;
	}

	public int[][] getMap() {
		return fieldMap.getMapAsArray();
	}

	public ArrayList<int[][]> getFieldsForPlayers() {
		return fieldMap.getFieldsForPlayerList();
	}

	public ArrayList<int[][]> getSpawnsForPlayers() {
		return fieldMap.getSpawnForFigureList();
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
	public boolean getIsPreGame(){
		return this._isPreGame;
	}
}
