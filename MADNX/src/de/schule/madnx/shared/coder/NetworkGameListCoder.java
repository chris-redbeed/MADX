/**
 * 
 */
package de.schule.madnx.shared.coder;

import java.util.ArrayList;

import de.schule.madnx.shared.NetworkGame;

/**
 * @author xgadscj
 *
 */
public class NetworkGameListCoder {

	private static final String ATTR_INDICATOR = "_";
	private static final String GAME_INDICATOR = "/";

	public static ArrayList<NetworkGame> decode(String gameList) {
		ArrayList<NetworkGame> result = new ArrayList<>();
		if (gameList != null) {
			String[] games = gameList.split(GAME_INDICATOR);
			for (String s : games) {
				String[] attributes = s.split(ATTR_INDICATOR);
				NetworkGame game = new NetworkGame();
				game.setHost(attributes[0]);
				game.setId(Integer.valueOf(attributes[1]));
				game.setCurrentPlaces(Integer.valueOf(attributes[2]));
				result.add(game);
			}
		}

		return result;
	}

	public static String encode(ArrayList<NetworkGame> gameList) {
		String result = "";
		if (gameList != null && gameList.size() > 0) {
			for (NetworkGame g : gameList) {
				result += g.getHost() + ATTR_INDICATOR + g.getId() + ATTR_INDICATOR + g.getCurrentPlaces()
						+ GAME_INDICATOR;
			}
			result.substring(0, result.length() - 1);
		}
		return result;
	}
}
