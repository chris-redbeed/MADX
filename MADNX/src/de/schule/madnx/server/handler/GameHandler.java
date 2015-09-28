package de.schule.madnx.server.handler;

import java.util.Map;
import java.util.logging.Logger;

import javax.websocket.Session;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.schule.madnx.server.handler.list.SessionLobby;
import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;

public class GameHandler {

	private Logger logger = Logger.getLogger(GameHandler.class.getName());
	
	public String handleMessage(String message, Session session, Map<Integer, SessionLobby> lobbies) {
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(message);
		JsonObject jsonObject = jelement.getAsJsonObject();
		String method = JSONHelper.valueToString(jsonObject.get(Methods.METHOD).toString());
		logger.info("method: " + method);

		
		
		return "error";
	}
	
}
