/**
 * 
 */
package de.schule.madnx.shared.coder;

import java.util.ArrayList;

/**
 * @author xgadscj
 *
 */
public class PlayerFieldCoder {
	
	private static final String PLAYER_INDICATOR = ";";
	
	public static ArrayList<int[][]> decode(String playerFields) {
		ArrayList<int[][]> result = new ArrayList<>();
		String[] split = playerFields.split(PLAYER_INDICATOR);
		for (String s : split) {
			result.add(GameMapCoder.decode(s));
		}
		return result;
	}

	public static String encode(ArrayList<int[][]> playerFields) {
		String result = "";
		for (int[][] i : playerFields) {
			result += GameMapCoder.encode(i) + PLAYER_INDICATOR;
		}
		result.substring(0, result.length()-1);
		return result;
	}
	
}
