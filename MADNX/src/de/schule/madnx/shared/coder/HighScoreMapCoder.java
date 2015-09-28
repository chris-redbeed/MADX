/**
 * 
 */
package de.schule.madnx.shared.coder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xgadscj
 *
 */
public class HighScoreMapCoder {
	private static final String VALUE_INDICATOR = "_";
	private static final String KEY_INDICATOR = "/";
	
	public static Map<String, Integer> decode(String highScoreMap) {
		Map<String, Integer> result = new HashMap<>();
		if (highScoreMap != null) {
			String[] scores = highScoreMap.split(KEY_INDICATOR);
			for (String s : scores) {
				String[] attributes = s.split(VALUE_INDICATOR);
				result.put(attributes[0], Integer.valueOf(attributes[1]));
			}
		}

		return result;
	}

	public static String encode(Map<String, Integer> highScoreMap) {
		String result = "";
		if (highScoreMap != null && highScoreMap.size() > 0) {
			for (String s : highScoreMap.keySet()) {
				result += s + VALUE_INDICATOR + highScoreMap.get(s)
						+ KEY_INDICATOR;
			}
			result.substring(0, result.length() - 1);
		}
		return result;
	}
}
