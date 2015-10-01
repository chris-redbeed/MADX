/**
 * 
 */
package de.schule.madnx.server.game.map;

import java.util.ArrayList;

import de.schule.madnx.server.game.GameMap;
import de.schule.madnx.server.game.Player;

/**
 * @author xgadscj
 *
 */
public interface FieldMap {
	public void createFields(GameMap gameMap);

	public void createFigures(GameMap gameMap, ArrayList<Player> players);
	
	public int[][] getMapAsArray();
	
	public ArrayList<int[][]> getFieldsForPlayerList();
	
	public ArrayList<int[][]> getSpawnForFigureList();
}
