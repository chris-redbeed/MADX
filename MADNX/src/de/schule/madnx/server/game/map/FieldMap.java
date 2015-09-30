/**
 * 
 */
package de.schule.madnx.server.game.map;

import java.util.ArrayList;

import de.schule.madnx.server.game.GameMap;

/**
 * @author xgadscj
 *
 */
public interface FieldMap {
	public void createFields(GameMap gameMap);
	
	public int[][] getMapAsArray();
	
	public ArrayList<int[][]> getFieldsForPlayerList();
	
	public ArrayList<int[][]> getSpawnForFigureList();
}
