/**
 * 
 */
package de.schule.madnx.server.game;

import java.util.List;

import de.schule.madnx.server.game.field.CornerField;
import de.schule.madnx.server.game.field.Field;
import de.schule.madnx.server.game.field.FinishField;
import de.schule.madnx.server.game.field.SpawnField;

/**
 * @author xgadscj
 *
 */
public class PathFinder {

	private List<Field> fields;

	public PathFinder(List<Field> fields) {
		this.fields = fields;
	}

	public int[][] searchForNewPosition(int id, int countFields) {
		int[][] result = new int[2][2];
		Figure figure = null;
		Field foundField = null;
		Field field = null;
		for (Field f : fields) {
			if (f.getFigure() != null && f.getFigure().getId() == id) {
				figure = f.getFigure();
				f.setFigure(null);
				foundField = f;
				field = f;
				result[0][0] = f.getY();
				result[0][1] = f.getX();
				break;
			}
		}
		for (int i = 0; i < countFields; i++) {
			if (field instanceof CornerField
					&& ((CornerField) field).getPlayer().equals(
							figure.getPlayer())) {
				field = ((CornerField) field).getFinish();
			} else if (field instanceof FinishField && field.getNext() == null) {
				foundField.setFigure(figure);
				figure.setSet(true);
				return result;
			} else if (field instanceof SpawnField && countFields == 6) {
				field = field.getNext();
				break;
			} else if (!(field instanceof SpawnField)) {
				field = field.getNext();
			}
		}
		if (field.getFigure() == null) {
			field.setFigure(figure);
			figure.setSet(true);
			result[0][0] = field.getY();
			result[0][1] = field.getX();
		} else {
			for (Field houseField : fields) {
				if (houseField instanceof SpawnField
						&& ((SpawnField) houseField).getId() == field.getFigure().getId()) {
					houseField.setFigure(field.getFigure());
					field.getFigure().setSet(false);
					field.setFigure(figure);
					figure.setSet(true);
					result[0][0] = field.getY();
					result[0][1] = field.getX();
					result[1][0] = houseField.getY();
					result[1][1] = houseField.getX();
				}
			}
		}
		return result;
	}

	public boolean canBeatFigure(int id, int diceNumber) {
		Field field = null;
		Figure currentFigure = null;
		for (Field f : fields) {
			if (f.getFigure() != null && f.getFigure().getId() == id) {
				currentFigure = f.getFigure();
				field = f;
				break;
			}
		}
		for (int i = 0; i < diceNumber; i++) {
			field = field.getNext();
		}
		Figure figure = field.getFigure();
		if (figure != null && figure.getPlayer() == currentFigure.getPlayer()) {
			return false;
		}
		return true;
	}

	public boolean canBeSetInHouse(int id, int diceNumber) {
		Field field = null;
		for (Field f : fields) {
			if (f.getFigure() != null && f.getFigure().getId() == id) {
				field = f;
				break;
			}
		}
		for (int i = 0; i < diceNumber; i++) {
			if (field instanceof CornerField) {
				field = ((CornerField) field).getFinish();
			}
			else {
				field = field.getNext();
			}
			if (field instanceof FinishField && field.getNext() == null) {
				if (field.getFigure() != null) {
					return false;
				}
			}
		}
		return true;
	}
}
