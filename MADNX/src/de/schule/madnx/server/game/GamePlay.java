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
//	private int attempt;

	public GamePlay(int countPlayers) {
//		attempt = 0;
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
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Player nextPlayer() {
//		attempt = 0;
		int index = players.indexOf(currentPlayer) + 1;
		if (index < players.size()) {
			currentPlayer = players.get(index);
		} else {
			currentPlayer = players.get(0);
		}
		return currentPlayer;
	}

	public int dice() {
//		boolean figureSet = false;
//		for (Figure f : currentPlayer.getFigures()) {
//			if (f.getSet()) {
//				figureSet = true;
//			}
//		}
		int dice = Dice.dice();
//		if (!figureSet && dice != 6) {
//			status = Status.DICE;
//			attempt++;
//			if (attempt == 3) {
//				nextPlayer();
//			}
//			return 0;
//		}
		currentDicedNumber = dice;
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
}
