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
		int[][] result = new int[1][2];
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
			if (field instanceof CornerField && ((CornerField) field).getPlayer().equals(figure.getPlayer())) {
				field = ((CornerField) field).getFinish();
			} else if (field instanceof FinishField && field.getNext() == null) {
				foundField.setFigure(figure);
				return result;
			} else if (field instanceof SpawnField && countFields == 6) {
				field = field.getNext();
				break;
			}else if (!(field instanceof SpawnField)){
				field = field.getNext();
			}
		}
		if (field.getFigure() == null) {
			field.setFigure(figure);
			result[0][0] = field.getY();
			result[0][1] = field.getX();
		} else {
			foundField.setFigure(figure);
		}
		return result;
	}

}
