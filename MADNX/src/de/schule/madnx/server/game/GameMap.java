/**
 * 
 */
package de.schule.madnx.server.game;

import java.util.List;

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
	
	public boolean setFigure(Figure figure, int x, int y) {
		return false;
	}
	
	public Figure getFigure(int x, int y) {
		return new Figure();
	}
	
	public boolean moveFigure(int id, int x, int y) {
		return false;
	}
}