/**
 * 
 */
package de.schule.madnx.server.game;

import java.util.List;

import de.schule.madnx.server.game.field.Field;
import de.schule.madnx.server.game.field.SpawnField;

/**
 * @author xgadscj
 *
 */
public class GameMap {
	public List<Field> fields;
	private PathFinder finder;

	public boolean canBeSet(Figure figure) {
		return false;
	}

	public int[][] setFigure(Figure figure, int intCountFields) {
		return finder.searchForNewPosition(figure.getId(), intCountFields);
	}

	public boolean moveFigure(int id, int x, int y) {
		return false;
	}

	// initiales Setzen der Figur
	public boolean setFigure(Figure figure, int x, int y) {
		finder = new PathFinder(fields);
		for (Field f : fields) {
			if (f instanceof SpawnField && !f.getSet() && f.getX() == x && f.getY() == y) {
				f.setFigure(figure);
				return true;
			}
		}

		return false;
	}
}
