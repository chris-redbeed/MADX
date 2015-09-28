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
import de.schule.madnx.shared.Game;
import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;
import de.schule.madnx.shared.coder.GameListCoder;
import de.schule.madnx.shared.coder.OptionListCoder;
import de.schule.madnx.shared.coder.PlayerListCoder;

/**
 * @author xgadscj
 *
 */
public class LobbyHandler {
	private Logger logger = Logger.getLogger(LobbyHandler.class.getName());

	public String handleMessage(String message, Session session, Map<Integer, SessionLobby> lobbies) {
		
		// parst das json, um die Methode zu bekommen
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(message);
		JsonObject jsonObject = jelement.getAsJsonObject();
		String method = JSONHelper.valueToString(jsonObject.get(Methods.METHOD).toString());
		logger.info("method: " + method);
		
		// Unterscheidung zwischen den verschiedenen Methoden
		if (method.equals(Methods.CREATE_GAME)) {
			return createGame(session, lobbies);
		} else if (method.equals(Methods.CLOSE_GAME)) {
			return closeGame(session, lobbies);
		} else if (method.equals(Methods.JOIN_GAME)) {
			return joinGame(jsonObject, session, lobbies);
		} else if (method.equals(Methods.LIST_GAMES)) {
			return listGame(lobbies);
		}
		return "error";
	}

	// Listet alle Netzwerkspiele auf
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

	// Klingt den Spieler in eine Lobby ein
	private String joinGame(JsonObject jsonObject, Session session, Map<Integer, SessionLobby> lobbies) {
		String game = JSONHelper.valueToString(jsonObject.get("game").toString());
		int lobbyID = Integer.parseInt(game);
		JsonObject json = new JsonObject();
		SessionLobby sessionLobby = lobbies.get(lobbyID);
		if (sessionLobby != null) {
			session.getUserProperties().remove(Methods.LOBBY);
			session.getUserProperties().put(Methods.LOBBY, lobbyID);

			sessionLobby.addToLobby(session, false);

			for (Session s : sessionLobby.getLobby()) {
				if (s != session) {
					json = createJoinResult(sessionLobby, s);
					sessionLobby.sendTo(json.toString(), s);
				}
			}
			json = createJoinResult(sessionLobby, session);
		} else {
			json.addProperty(Methods.METHOD, Methods.JOIN_GAME);
			json.addProperty("result", "error");
		}
		json.remove("new");
		json.addProperty("new", "true");
		return json.toString();
	}

	// Dient der Unterscheidung, was für Optionen der Spieler in der Lobby hat
	// Der Spieler soll zum Beispiel nicht den Status eines anderen auf Fertig setzten!
	private JsonObject createJoinResult(SessionLobby sessionLobby, Session session) {
		String playerResult = PlayerListCoder.encode(sessionLobby.getPlayers());
		String optionsResult = OptionListCoder.encode(sessionLobby.getOptions());
		String user = session.getUserProperties().get(Methods.USER).toString();

		JsonObject json = new JsonObject();
		json.addProperty(Methods.METHOD, Methods.JOIN_GAME);
		json.addProperty("players", playerResult);
		json.addProperty("options", optionsResult);
		json.addProperty("result", "okay");
		json.addProperty("self", user);
		json.addProperty("new", "false");

		return json;
	}

	// Schließt das Spiel
	private String closeGame(Session session, Map<Integer, SessionLobby> lobbies) {
		int lobbyID = (int) session.getUserProperties().get(Methods.LOBBY);
		session.getUserProperties().remove(Methods.LOBBY);
		session.getUserProperties().put(Methods.LOBBY, 0);

		JsonObject json;
		SessionLobby lobby = lobbies.get(lobbyID);
		lobby.removeFromLobby(session);
		String user = session.getUserProperties().get(Methods.USER).toString();
		String host = lobby.getHost();
		
		// Unterscheidung zwischen Host und normalem Spieler
		if (!user.equals(host)) {
			lobby.removeFromLobby(session);
			for (Session s : lobby.getLobby()) {
				if (s != session) {
					json = createJoinResult(lobby, s);
					lobby.sendTo(json.toString(), s);
				}
			}
		} else {
			lobbies.remove(lobbyID);
			json = new JsonObject();
			json.addProperty(Methods.METHOD, Methods.CLOSE_GAME);
			lobby.sendToAll(json.toString(), session);
		}

		json = new JsonObject();
		json.addProperty(Methods.METHOD, Methods.CLOSE_GAME);

		return json.toString();
	}

	// Erstellt ein Spiel
	private String createGame(Session session, Map<Integer, SessionLobby> lobbies) {
		int newLobbyID = getNewLobbyID(lobbies);

		session.getUserProperties().remove(Methods.LOBBY);
		session.getUserProperties().put(Methods.LOBBY, newLobbyID);
		String user = session.getUserProperties().get(Methods.USER).toString();

		SessionLobby lobby = new SessionLobby();
		lobby.addToLobby(session, true);
		lobby.setHost(user);
		lobbies.put(newLobbyID, lobby);

		JsonObject json = new JsonObject();
		for (Session s : lobby.getLobby()) {
			if (s != session) {
				json = createJoinResult(lobby, s);
				lobby.sendTo(json.toString(), s);
			}
		}
		json = createJoinResult(lobby, session);
		json.remove("new");
		json.addProperty("new", "true");
		return json.toString();
	}

	// Gibt eine neue LobbyID zurück, die immer 1 größer ist, als die Lobby mit der höchsten ID
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

	// Erstellt eine Liste von Spielen aus allen Lobbies
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
