/**
 * 
 */
package de.schule.madnx.shared.coder;

import java.util.ArrayList;

import de.schule.madnx.shared.Player;

/**
 * @author xgadscj
 *
 */
public class PlayerListCoder {

	private static final String ATTR_INDICATOR = "_";
	private static final String PLAYER_INDICATOR = "/";

	public static ArrayList<Player> decode(String playerList) {
		ArrayList<Player> result = new ArrayList<>();
		if (playerList != null) {
			String[] players = playerList.split(PLAYER_INDICATOR);
			for (String s : players) {
				String[] attributes = s.split(ATTR_INDICATOR);
				Player player = new Player();
				player.setName(attributes[0]);
				player.setHost(Boolean.parseBoolean(attributes[1]));
				player.setStatus(Boolean.parseBoolean(attributes[2]));
				result.add(player);
			}
		}

		return result;
	}

	public static String encode(ArrayList<Player> playerList) {
		String result = "";
		if (playerList != null && playerList.size() > 0) {
			for (Player p : playerList) {
				result += p.getName() + ATTR_INDICATOR + String.valueOf(p.getHost()) + ATTR_INDICATOR
						+ String.valueOf(p.getStatus()) + PLAYER_INDICATOR;
			}
			result.substring(0, result.length() - 1);
		}
		return result;
	}
}
