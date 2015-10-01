/**
 * 
 */
package de.schule.madnx.server.game;

import java.util.List;

import de.schule.madnx.server.game.field.Field;

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
		Field field = null;
		for (Field f: fields) {
			if (f.getFigure() != null && f.getFigure().getId() == id) {
				figure = f.getFigure();
				f.setFigure(null);
				field = f;
				break;
			}
		}
		for(int i = 0; i < countFields; i++) {
			field = field.getNext();
		}
		field.setFigure(figure);
		result[0][0] = field.getY();
		result[0][1] = field.getX();
		return result;
	}
	
}
