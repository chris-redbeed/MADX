/**
 * 
 */
package de.schule.madnx.shared.gamelist;

import java.util.ArrayList;

/**
 * @author xgadscj
 *
 */
public class GameListCoder {

	private static final String ATTR_INDICATOR = "_";
	private static final String GAME_INDICATOR = "/";

	public static ArrayList<Game> decode(String gameList) {
		ArrayList<Game> result = new ArrayList<>();
		if (gameList != null) {
			String[] games = gameList.split(GAME_INDICATOR);
			for (String s : games) {
				String[] attributes = s.split(ATTR_INDICATOR);
				Game game = new Game();
				game.setHost(attributes[0]);
				game.setId(Integer.valueOf(attributes[1]));
				game.setCurrentPlaces(Integer.valueOf(attributes[2]));
				result.add(game);
			}
		}

		return result;
	}

	public static String encode(ArrayList<Game> gameList) {
		String result = "";
		if (gameList != null) {
			for (Game g : gameList) {
				result += g.getHost() + ATTR_INDICATOR + g.getId() + ATTR_INDICATOR + g.getCurrentPlaces()
						+ GAME_INDICATOR;
			}
			result.substring(0, result.length() - 1);
		}
		return result;
	}
}
