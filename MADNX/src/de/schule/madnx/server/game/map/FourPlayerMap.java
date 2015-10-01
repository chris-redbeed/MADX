/**
 * 
 */
package de.schule.madnx.server.game.map;

import java.util.ArrayList;

import de.schule.madnx.server.game.Figure;
import de.schule.madnx.server.game.GameMap;
import de.schule.madnx.server.game.Player;
import de.schule.madnx.server.game.field.CornerField;
import de.schule.madnx.server.game.field.Field;
import de.schule.madnx.server.game.field.Field.PlayerColor;
import de.schule.madnx.server.game.field.FinishField;
import de.schule.madnx.server.game.field.SpawnField;
import de.schule.madnx.server.game.field.StartField;

/**
 * @author xgadscj
 *
 */
public class FourPlayerMap implements FieldMap {
	private int[][] map = { { 4, 1 }, { 4, 2 }, { 4, 3 }, { 4, 4 }, { 3, 4 }, { 2, 4 }, { 1, 4 }, { 1, 5 }, { 1, 6 },
			{ 1, 7 }, { 1, 8 }, { 2, 8 }, { 3, 8 }, { 4, 8 }, { 4, 9 }, { 4, 10 }, { 4, 11 }, { 5, 11 }, { 6, 11 },
			{ 7, 11 }, { 8, 11 }, { 8, 10 }, { 8, 9 }, { 8, 8 }, { 9, 8 }, { 10, 8 }, { 11, 8 }, { 11, 7 }, { 11, 6 },
			{ 11, 5 }, { 11, 4 }, { 10, 4 }, { 9, 4 }, { 8, 4 }, { 8, 3 }, { 8, 2 }, { 8, 1 }, { 7, 1 }, { 6, 1 },
			{ 5, 1 } };
	private int[][] redCorner = { { 6, 1 } };
	private int[][] blueCorner = { { 1, 6 } };
	private int[][] greenCorner = { { 6, 11 } };
	private int[][] yellowCorner = { { 11, 6 } };
	private int[][] redSpawn = { { 1, 1 }, { 1, 2 }, { 2, 1 }, { 2, 2 } };
	private int[][] blueSpawn = { { 1, 10 }, { 1, 11 }, { 2, 10 }, { 2, 11 } };
	private int[][] greenSpawn = { { 10, 10 }, { 10, 11 }, { 11, 10 }, { 11, 11 } };
	private int[][] yellowSpawn = { { 10, 1 }, { 11, 1 }, { 10, 2 }, { 11, 2 } };
	private int[][] redFinish = { { 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 } };
	private int[][] blueFinish = { { 2, 6 }, { 3, 6 }, { 4, 6 }, { 5, 6 } };
	private int[][] greenFinish = { { 6, 7 }, { 6, 8 }, { 6, 9 }, { 6, 10 } };
	private int[][] yellowFinish = { { 7, 6 }, { 8, 6 }, { 9, 6 }, { 10, 6 } };
	private int[][] redStart = { { 4, 1 } };
	private int[][] blueStart = { { 1, 8 } };
	private int[][] greenStart = { { 8, 11 } };
	private int[][] yellowStart = { { 11, 4 } };

	public void createFields(GameMap gameMap) {
		ArrayList<Field> result = new ArrayList<>();

		// Die Reihenfolge muss so bleiben!

		generateSpawnFields(result);

		generateStartFields(result);

		generateFinishFields(result);

		generateCornerFields(result);

		generateMap(result);

		gameMap.fields = result;
	}

	private void generateCornerFields(ArrayList<Field> result) {
		// generate Corner
		createCornerForPlayer(result, redCorner, PlayerColor.RED);
		createCornerForPlayer(result, blueCorner, PlayerColor.BLUE);
		createCornerForPlayer(result, greenCorner, PlayerColor.GREEN);
		createCornerForPlayer(result, yellowCorner, PlayerColor.YELLOW);
	}

	private void createCornerForPlayer(ArrayList<Field> result, int[][] cornerArray, PlayerColor playerColor) {
		for (int i = 0; i < cornerArray.length; i++) {
			FinishField finishField = searchForFirstFinish(result, playerColor);
			Field field = new CornerField(playerColor, finishField, null, cornerArray[i][0], cornerArray[i][1]);
			result.add(field);
		}
	}

	private FinishField searchForFirstFinish(ArrayList<Field> result, PlayerColor playerColor) {
		for (Field f : result) {
			if (f instanceof FinishField && ((FinishField) f).getIsFirst()
					&& ((FinishField) f).getPlayer().equals(playerColor)) {
				return (FinishField) f;
			}
		}
		return null;
	}

	private void generateFinishFields(ArrayList<Field> result) {
		// generate Finish
		createFinishForPlayer(result, redFinish, PlayerColor.RED);
		createFinishForPlayer(result, blueFinish, PlayerColor.BLUE);
		createFinishForPlayer(result, greenFinish, PlayerColor.GREEN);
		createFinishForPlayer(result, yellowFinish, PlayerColor.YELLOW);
	}

	private void createFinishForPlayer(ArrayList<Field> result, int[][] finishArray, PlayerColor playerColor) {
		boolean isFirst;
		for (int i = 0; i < finishArray.length; i++) {
			if (i == 0) {
				isFirst = true;
			} else {
				isFirst = false;
			}
			Field field = new FinishField(isFirst, playerColor, null, finishArray[i][0], finishArray[i][1]);
			for (Field f : result) {
				if (f instanceof FinishField && ((FinishField) f).getPlayer().equals(playerColor)
						&& f.getNext() == null) {
					f.setNext(field);
				}
			}
			result.add(field);
		}
	}

	private void generateMap(ArrayList<Field> result) {
		// generate Map
		int[] old = null;
		Field oldField = null;
		Field field = null;
		for (int[] i : this.map) {
			boolean notFound = true;
			for (Field f : result) {
				if (!(f instanceof SpawnField) && !(f instanceof FinishField)) {
					if (f.getX() == i[0] && f.getY() == i[1]) {
						notFound = false;
						if (old != null) {
							oldField = searchOld(result, old);
							oldField.setNext(f);
						}
						old = new int[2];
						old[0] = f.getX();
						old[1] = f.getY();
						break;
					}
				}
			}
			if (notFound) {
				field = new Field(null, i[0], i[1]);
				if (old != null) {
					oldField = searchOld(result, old);
					oldField.setNext(field);
				}
				old[0] = i[0];
				old[1] = i[1];
				result.add(field);
			}
		}
		for (Field f : result) {
			if (f instanceof SpawnField && ((SpawnField) f).getPlayer().equals(PlayerColor.RED)) {
				field.setNext(f);
			}
		}
	}

	private Field searchOld(ArrayList<Field> result, int[] old) {
		for (Field f : result) {
			if (f.getX() == old[0] && f.getY() == old[1]) {
				return f;
			}
		}
		return null;
	}

	private void generateStartFields(ArrayList<Field> result) {
		// generate Start

		createStartFieldForPlayer(result, redStart, PlayerColor.RED);
		createStartFieldForPlayer(result, blueStart, PlayerColor.BLUE);
		createStartFieldForPlayer(result, greenStart, PlayerColor.GREEN);
		createStartFieldForPlayer(result, yellowStart, PlayerColor.YELLOW);
	}

	private void generateSpawnFields(ArrayList<Field> result) {
		// generate SpawnFields
		generateSpawnForPlayer(result, redSpawn, PlayerColor.RED);
		generateSpawnForPlayer(result, blueSpawn, PlayerColor.BLUE);
		generateSpawnForPlayer(result, greenSpawn, PlayerColor.GREEN);
		generateSpawnForPlayer(result, yellowSpawn, PlayerColor.YELLOW);
	}

	private void generateSpawnForPlayer(ArrayList<Field> result, int[][] spawnArray, PlayerColor playerColor) {
		for (int[] i : spawnArray) {
			Field field = new SpawnField(playerColor, null, i[0], i[1]);
			result.add(field);
		}
	}

	@Override
	public int[][] getMapAsArray() {
		return map;
	}

	@Override
	public ArrayList<int[][]> getFieldsForPlayerList() {
		ArrayList<int[][]> result = new ArrayList<>();
		int[][] helpArray = new int[9][2];
		System.arraycopy(redSpawn, 0, helpArray, 0, redSpawn.length);
		System.arraycopy(redFinish, 0, helpArray, redSpawn.length, redFinish.length);
		System.arraycopy(redStart, 0, helpArray, redSpawn.length + redFinish.length, redStart.length);
		result.add(helpArray);
		helpArray = new int[9][2];
		System.arraycopy(blueSpawn, 0, helpArray, 0, blueSpawn.length);
		System.arraycopy(blueFinish, 0, helpArray, blueFinish.length, blueFinish.length);
		System.arraycopy(blueStart, 0, helpArray, blueSpawn.length + blueFinish.length, blueStart.length);
		result.add(helpArray);
		helpArray = new int[9][2];
		System.arraycopy(greenSpawn, 0, helpArray, 0, greenSpawn.length);
		System.arraycopy(greenFinish, 0, helpArray, greenSpawn.length, greenFinish.length);
		System.arraycopy(greenStart, 0, helpArray, greenSpawn.length + greenFinish.length, greenStart.length);
		result.add(helpArray);
		helpArray = new int[9][2];
		System.arraycopy(yellowSpawn, 0, helpArray, 0, yellowSpawn.length);
		System.arraycopy(yellowFinish, 0, helpArray, yellowSpawn.length, yellowFinish.length);
		System.arraycopy(yellowStart, 0, helpArray, yellowSpawn.length + yellowFinish.length, yellowStart.length);
		result.add(helpArray);
		return result;
	}

	public ArrayList<int[][]> getSpawnForFigureList() {
		ArrayList<int[][]> result = new ArrayList<>();

		result.add(redSpawn);
		result.add(blueSpawn);
		result.add(greenSpawn);
		result.add(yellowSpawn);

		return result;
	}

	@Override
	public void createFigures(GameMap gameMap, ArrayList<Player> players) {
		int id = 1;
		id = createFiguresForPlayer(gameMap, players, id, redSpawn, PlayerColor.RED);
		id = createFiguresForPlayer(gameMap, players, id, blueSpawn, PlayerColor.BLUE);
		id = createFiguresForPlayer(gameMap, players, id, greenSpawn, PlayerColor.GREEN);
		createFiguresForPlayer(gameMap, players, id, yellowSpawn, PlayerColor.YELLOW);
	}

	private int createFiguresForPlayer(GameMap gameMap, ArrayList<Player> players, int id, int[][] spawnArray,
			PlayerColor playerColor) {
		for (int[] j : spawnArray) {
			Player player = players.get(0);
			Figure figure = new Figure(id, false, playerColor);
			player.getFigures().add(figure);
			gameMap.setFigure(figure, j[0], j[1]);
			id++;
		}
		return id;
	}

	private void createStartFieldForPlayer(ArrayList<Field> result, int[][] startArray, PlayerColor player) {
		for (int[] i : startArray) {
			Field field = new StartField(player, null, i[0], i[1]);
			for (Field f : result) {
				if (f instanceof SpawnField && ((SpawnField) f).getPlayer().equals(player)) {
					f.setNext(field);
				}
			}
			result.add(field);
		}
	}
}
