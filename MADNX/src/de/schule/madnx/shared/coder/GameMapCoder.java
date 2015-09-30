/**
 * 
 */
package de.schule.madnx.shared.coder;

/**
 * @author xgadscj
 *
 */
public class GameMapCoder {

	private static final String VALUE_INDICATOR = "/";
	private static final String POS_INDICATOR = "_";
	
	public static int[][] decode(String gameMap) {
		String[] split = gameMap.split(VALUE_INDICATOR);
		int[][] result = new int[split.length][2];
		for (int i = 0; i < split.length; i++) {
			String s = split[i];
			String[] split2 = s.split(POS_INDICATOR);
				result[i][0] = Integer.valueOf(split2[0]);
				result[i][1] = Integer.valueOf(split2[1]);
		}

		return result;
	}

	public static String encode(int[][] gameMap) {
		String result = "";
		for (int i = 0; i < gameMap.length; i++) {
			result += gameMap[i][0] + POS_INDICATOR + gameMap[i][1] + VALUE_INDICATOR;
		}
		result.substring(0, result.length()-1);
		return result;
	}
	
}
