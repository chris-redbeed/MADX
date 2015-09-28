/**
 * 
 */
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

/**
 * @author xgadscj
 *
 */
public class ChatHandler {

	private Logger logger = Logger.getLogger(ChatHandler.class.getName());

	public String handleMessage(String message, Session session, Map<Integer, SessionLobby> lobbies) {
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(message);
		JsonObject jsonObject = jelement.getAsJsonObject();
		String method = JSONHelper.valueToString(jsonObject.get(Methods.METHOD).toString());
		logger.info("method: " + method);
		if (method.equals(Methods.CHAT)) {
			String chatMessage = JSONHelper.valueToString(jsonObject.get(Methods.MESSAGE).toString());
			String returnMessage = session.getUserProperties().get(Methods.USER).toString() + ": " + chatMessage;

			JsonObject json = new JsonObject();
			json.addProperty(Methods.METHOD, Methods.CHAT);
			json.addProperty(Methods.MESSAGE, returnMessage);

			// Schickt die Nachricht nur an die Leute aus der Lobby
			int lobbyID = (int) session.getUserProperties().get(Methods.LOBBY);
			SessionLobby lobby = lobbies.get(lobbyID);

			if (lobbyID != 0) {
			lobby.sendToAll(json.toString(), session);
			}
			return json.toString();
		}
		return "error";
	}
}
