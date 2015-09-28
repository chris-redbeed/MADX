/**
 * 
 */
package de.schule.madnx.server.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.websocket.Session;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.schule.madnx.server.handler.list.SessionLobby;
import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;
import de.schule.madnx.shared.gamelist.Game;
import de.schule.madnx.shared.gamelist.GameListCoder;

/**
 * @author xgadscj
 *
 */
public class LobbyHandler {
	private Logger logger = Logger.getLogger(LobbyHandler.class.getName());

	public String handleMessage(String message, Session session, Map<Integer, SessionLobby> lobbies) {
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(message);
		JsonObject jsonObject = jelement.getAsJsonObject();
		String method = JSONHelper.valueToString(jsonObject.get(Methods.METHOD).toString());
		logger.info("method: " + method);
		if (method.equals(Methods.CREATE_GAME)) {
			return createGame(session, lobbies);
		} else if (method.equals(Methods.CLOSE_GAME)) {
			return closeGame(session, lobbies);
		} else if (method.equals(Methods.LEAVE_GAME)) {
			return leaveGame(session, lobbies);
		} else if (method.equals(Methods.JOIN_GAME)) {
			return joinGame(jsonObject, session, lobbies);
		} else if (method.equals(Methods.LIST_GAMES)) {
			return listGame(lobbies);
		}
		return "error";
	}

	private String listGame(Map<Integer, SessionLobby> lobbies) {
		Collection<SessionLobby> collection = lobbies.values();
		ArrayList<SessionLobby> arrayListLobby = new ArrayList<>(collection);
		ArrayList<Game> arrayListGame = toGameList(arrayListLobby);
		String gameList = GameListCoder.encode(arrayListGame);

		JsonObject json = new JsonObject();
		json.addProperty(Methods.METHOD, Methods.LIST_GAMES);
		json.addProperty("result", gameList);

		return json.toString();
	}

	private String joinGame(JsonObject jsonObject, Session session, Map<Integer, SessionLobby> lobbies) {
		String game = JSONHelper.valueToString(jsonObject.get("game").toString());
		int lobbyID = Integer.parseInt(game);

		SessionLobby sessionLobby = lobbies.get(lobbyID);
		if (sessionLobby != null) {
		session.getUserProperties().remove(Methods.LOBBY);
		session.getUserProperties().put(Methods.LOBBY, lobbyID);
		
		sessionLobby.addToLobby(session);
		}

		// TODO ResultString erstellen!

		JsonObject json = new JsonObject();
		
//		sessionLobby.sendToAll("", session);
		return json.toString();
	}

	private String leaveGame(Session session, Map<Integer, SessionLobby> lobbies) {
		int lobbyID = (int) session.getUserProperties().get(Methods.LOBBY);
		session.getUserProperties().remove(Methods.LOBBY);
		session.getUserProperties().put(Methods.LOBBY, 0);

		SessionLobby lobby = lobbies.get(lobbyID);
		lobby.removeFromLobby(session);

		// TODO ReturnString erstellen!

		lobby.sendToAll("", session);

		return "";
	}

	private String closeGame(Session session, Map<Integer, SessionLobby> lobbies) {
		int lobbyID = (int) session.getUserProperties().get(Methods.LOBBY);
		SessionLobby sessionLobby = lobbies.get(lobbyID);
		lobbies.remove(lobbyID);

		// TODO ReturnString erstellen!

		sessionLobby.sendToAll("", session);

		return "";
	}

	private String createGame(Session session, Map<Integer, SessionLobby> lobbies) {
		int newLobbyID = getNewLobbyID(lobbies);

		session.getUserProperties().remove(Methods.LOBBY);
		session.getUserProperties().put(Methods.LOBBY, newLobbyID);
		String user = session.getUserProperties().get(Methods.USER).toString();

		SessionLobby lobby = new SessionLobby();
		lobby.addToLobby(session);
		lobby.setHost(user);
		lobbies.put(newLobbyID, lobby);

		JsonObject json = new JsonObject();
		json.addProperty(Methods.METHOD, Methods.CREATE_GAME);
		String returnResult = session.getUserProperties().get(Methods.USER).toString();
		json.addProperty("result", returnResult);

		return json.toString();
	}

	private int getNewLobbyID(Map<Integer, SessionLobby> lobbies) {
		Set<Integer> keySet = lobbies.keySet();
		Iterator<Integer> iterator = keySet.iterator();
		int max = 0;
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			if (next > max) {
				max = next;
			}
		}
		return max + 1;
	}

	private ArrayList<Game> toGameList(ArrayList<SessionLobby> lobbies) {
		ArrayList<Game> result = new ArrayList<>();

		for (SessionLobby l : lobbies) {
			Game game = new Game();
			game.setHost(l.getHost());
			game.setCurrentPlaces(l.getCurrentCount());
			game.setId(l.getID());
			result.add(game);
		}

		return result;
	}
}
