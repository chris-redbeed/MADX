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
		
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void nextPlayer() {
		
	}
	
	public int dice() {
		currentDicedNumber = Dice.dice();
		return currentDicedNumber;
	}
	
	public void end() {
		
	}
	
	public boolean set(int id, int countFields) {
		return false;
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
}
