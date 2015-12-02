package de.schule.madnx.server.handler;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.Session;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.schule.madnx.server.SessionLobby;
import de.schule.madnx.server.game.GamePlay;
import de.schule.madnx.server.game.Player;
import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;
import de.schule.madnx.shared.User;
import de.schule.madnx.shared.coder.GameMapCoder;
import de.schule.madnx.shared.coder.PlayerFieldCoder;

public class GameHandler {

	private Logger logger = Logger.getLogger(GameHandler.class.getName());

	public String handleMessage(String message, Session session,
			Map<Integer, SessionLobby> lobbies) {
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(message);
		JsonObject jsonObject = jelement.getAsJsonObject();
		String method = JSONHelper.valueToString(jsonObject.get(Methods.METHOD)
				.toString());
		logger.info("method: " + method);
		SessionLobby lobby = getSessionLobby(lobbies, session);
		if (method.equals(Methods.START_GAME)) {
			return startGame(lobby);
		} else if (method.equals(Methods.DICE)) {
			return dice(lobby, session);
		} else if (method.equals(Methods.SET)) {
			return set(lobby, session, message);
		} else if (method.equals(Methods.CHECK_GAMESTATE)) {
			return checkGameState(lobby,session);
		}

		return "error";
	}

	private String set(SessionLobby lobby, Session session, String message) {
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(message);
		JsonObject jsonObject = jelement.getAsJsonObject();
		int id = jsonObject.get("id").getAsInt();
		GamePlay gamePlay = lobby.getGamePlay();
		int[][] result = gamePlay.set(id);

		JsonObject json = new JsonObject();
		json.addProperty(Methods.METHOD, Methods.SET);
		json.addProperty("result", GameMapCoder.encode(result));
		json.addProperty("id", "" + id);

		return json.toString();
	}

	private String dice(SessionLobby lobby, Session session) {
		GamePlay gamePlay = lobby.getGamePlay();
		String user = session.getUserProperties().get(Methods.USER).toString();
		if (gamePlay.getCurrentPlayer().getUser().equals(user)) {
			int dice = gamePlay.dice();
			JsonObject json = new JsonObject();
			json.addProperty(Methods.METHOD, Methods.DICE);
			json.addProperty("result", "" + dice);
			return json.toString();
		}
		return "error";
	}

	private String startGame(SessionLobby lobby) {
		lobby.setGamePlay(new GamePlay(4));
		for (User u : lobby.getPlayers()) {
			lobby.getGamePlay().addPlayer(new Player(u.getName()));
			lobby.getGamePlay().addPlayer(new Player(u.getName()));
			lobby.getGamePlay().addPlayer(new Player(u.getName()));
			lobby.getGamePlay().addPlayer(new Player(u.getName()));
		}
		lobby.getGamePlay().start();

		JsonObject json = new JsonObject();
		json.addProperty(Methods.METHOD, Methods.START_GAME);
		json.addProperty("map",
				GameMapCoder.encode(lobby.getGamePlay().getMap()));
		json.addProperty("playerFields", PlayerFieldCoder.encode(lobby
				.getGamePlay().getFieldsForPlayers()));
		json.addProperty("spawnFigure", PlayerFieldCoder.encode(lobby
				.getGamePlay().getSpawnsForPlayers()));
		return json.toString();
	}

	private String checkGameState(SessionLobby lobby, Session session) {
		GamePlay gamePlay = lobby.getGamePlay();
		JsonObject json = new JsonObject();
		json.addProperty(Methods.METHOD, Methods.CHECK_GAMESTATE);
		boolean isPreGame = gamePlay.getIsPreGame();
		if (isPreGame && gamePlay.get_diceCounter() > 0) {
			gamePlay.nextPlayer();
			json.addProperty("result", "dice");
			return json.toString();
		}
		switch (gamePlay.getGameState()) {
		case DICE:
			json.addProperty("result", "dice");
			break;
		case END:
			json.addProperty("result", "end");
			break;
		case NEXT_PLAYER:
			json.addProperty("result", "dice");
			gamePlay.nextPlayer();
			break;
		case SET:
			json.addProperty("result", "set");
			break;
		}
		json.addProperty("player", gamePlay.getCurrentPlayer().getUser());
		sendTo(json.toString(), gamePlay.getCurrentPlayer().getUser(), lobby);
		return json.toString();
	}

	private SessionLobby getSessionLobby(Map<Integer, SessionLobby> lobbies,
			Session session) {
		Integer lobbyID = Integer.valueOf(session.getUserProperties()
				.get("lobby").toString());
		SessionLobby sessionLobby = lobbies.get(lobbyID);
		return sessionLobby;
	}
	
	public void sendTo(String message, String user, SessionLobby lobby) {
		for (Session s : lobby.getLobby()) {
				try {
					if (s.getUserProperties().get(Methods.USER).toString().equals(user)) {
					s.getBasicRemote().sendText(message);
					}
				} catch (IOException e) {
					logger.log(Level.SEVERE, "CheckGameState-Error", e);
					e.printStackTrace();
				}
			}
		}

}
