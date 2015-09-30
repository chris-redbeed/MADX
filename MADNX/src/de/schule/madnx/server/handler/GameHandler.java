package de.schule.madnx.server.handler;

import java.util.Map;
import java.util.logging.Logger;

import javax.websocket.Session;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.schule.madnx.server.SessionLobby;
import de.schule.madnx.server.game.GamePlay;
import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;
import de.schule.madnx.shared.coder.GameMapCoder;
import de.schule.madnx.shared.coder.PlayerFieldCoder;

public class GameHandler {

	private Logger logger = Logger.getLogger(GameHandler.class.getName());
	
	public String handleMessage(String message, Session session, Map<Integer, SessionLobby> lobbies) {
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(message);
		JsonObject jsonObject = jelement.getAsJsonObject();
		String method = JSONHelper.valueToString(jsonObject.get(Methods.METHOD).toString());
		logger.info("method: " + method);
		SessionLobby lobby = getSessionLobby(lobbies, session);
		if (method.equals(Methods.START_GAME)) {
			return startGame(lobby);
		}else if (method.equals(Methods.DICE)) {
			dice();
		}else if (method.equals(Methods.SET)) {
			set();
		}
		
		return "error";
	}

	private void set() {
		// TODO Auto-generated method stub
		
	}

	private void dice() {
		// TODO Auto-generated method stub
		
	}

	private String startGame(SessionLobby lobby) {
		lobby.setGamePlay(new GamePlay(4));
		
		JsonObject json = new JsonObject();
		json.addProperty(Methods.METHOD, Methods.START_GAME);
		json.addProperty("map", GameMapCoder.encode(lobby.getGamePlay().getMap()));
		json.addProperty("playerFields", PlayerFieldCoder.encode(lobby.getGamePlay().getFieldsForPlayers()));
		json.addProperty("spawnFigure", PlayerFieldCoder.encode(lobby.getGamePlay().getSpawnsForPlayers()));
		return json.toString();
	}
	
	private SessionLobby getSessionLobby(Map<Integer, SessionLobby> lobbies, Session session) {
		Integer lobbyID = Integer.valueOf(session.getUserProperties().get("lobby").toString());
		SessionLobby sessionLobby = lobbies.get(lobbyID);
		return sessionLobby;
	}
	
}
