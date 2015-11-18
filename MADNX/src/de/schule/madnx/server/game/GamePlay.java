/**
 * 
 */
package de.schule.madnx.server.game;

import java.util.ArrayList;

import de.schule.madnx.server.game.field.Field;
import de.schule.madnx.server.game.field.FinishField;
import de.schule.madnx.server.game.field.StartField;
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
	protected int _diceCounter = 0;

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
		_diceCounter = 0;
		if (index < players.size()) {
			currentPlayer = players.get(index);
		} else {
			if (this._isPreGame) {
				this._isPreGame = false;
				return currentPlayer = players.get(this._preGameWinner);
			}
			currentPlayer = players.get(0);
		}
		return currentPlayer;
	}

	public int dice() {
		_diceCounter++;
		int dice = Dice.dice();
		currentDicedNumber = dice;
		if (this._isPreGame) {
			if (currentDicedNumber > this._preGameWinningNumber)
				this._preGameWinner = players.indexOf(this.currentPlayer);
			this._preGameWinningNumber = currentDicedNumber;
			this.setEnd();
			return dice;
		}

		if (_diceCounter == 3 && currentDicedNumber != 6) {
			return -2;
		}

		if (!getCurrentPlayer().hasFiguresOnField() && currentDicedNumber != 6) {
			return -1;
		}

		return currentDicedNumber;
	}

	public void end() {

	}

	public Player setEnd() {
		return nextPlayer();
	}

	public boolean canBeSet(int id) {
		int counterBefore = 0;
		int counterRules = 0;
		Figure maxCountFigure = null;
		ArrayList<Figure> figures = currentPlayer.getFigures();
		maxCountFigure = figures.get(0);
		for (Figure f : figures) {
			counterRules = 0;

			// Auszug aus Haus
			if (currentDicedNumber == 6 && currentPlayer.hasFigureInHouse(id)) {
				counterRules++;
			}
			// Vom Startfeld ziehen
			for (Field field : map.fields) {
				if (field instanceof StartField
						&& ((StartField) field).getPlayer() == f.getPlayer()) {
					if (f.getId() != id) {
						counterRules++;
					}
				}
			}
			// Schlagen
			if (f.getId() != id) {
				if (map.canBeatFigure(f, currentDicedNumber)) {
					counterRules++;
				}
			} else {
				if (map.canBeSetInHouse(f, currentDicedNumber)) {
					counterRules++;
				}
			}
			if (counterBefore < counterRules) {
				maxCountFigure = f;
				counterBefore = counterRules;
			}
		}
		if (maxCountFigure.getId() == id) {
			return true;
		}
		return false;
	}

	public boolean canAnyFigureSet() {
		ArrayList<Figure> figures = currentPlayer.getFigures();
		for (Figure f : figures) {
			if (canBeSet(f.getId())) {
				return true;
			}
		}
		return false;
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

	public boolean hasWinner() {
		ArrayList<Figure> figures = currentPlayer.getFigures();
		for (Figure f : figures) {
			if (!f.getSet()) {
				return false;
			}
			for (Field field : map.fields) {
				if (field.getFigure() == f) {
					if (!(field instanceof FinishField)) {
						return false;
					}
				}
			}
		}
		return true;
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

	public boolean getIsPreGame() {
		return this._isPreGame;
	}

	public int getCurrentDicedNumber() {
		return currentDicedNumber;
	}
}
