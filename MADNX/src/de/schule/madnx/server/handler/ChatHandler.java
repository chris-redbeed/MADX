/**
 * 
 */
package de.schule.madnx.server.handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.Session;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.schule.madnx.shared.JSONHelper;

/**
 * @author xgadscj
 *
 */
public class ChatHandler {

	private Logger logger = Logger.getLogger(ChatHandler.class.getName());

	public String handleMessage(String message, Session session, Set<Session> users) {
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(message);
		JsonObject jsonObject = jelement.getAsJsonObject();
		String method = JSONHelper.valueToString(jsonObject.get("method").toString());
		logger.info("method: " + method);
		if (method.equals("chat")) {
			String chatMessage = JSONHelper.valueToString(jsonObject.get("message").toString());
			String returnMessage = session.getUserProperties().get("user").toString() + ": " + chatMessage;

			JsonObject json = new JsonObject();
			json.addProperty("method", "chat");
			json.addProperty("message", returnMessage);

			// String lobby =
			// session.getUserProperties().get("lobby").toString();

			sendToUsers("", json.toString(), users, session);
			return json.toString();
		}
		return "error";
	}

	private void sendToUsers(String lobby, String message, Set<Session> users, Session session) {
		Iterator<Session> iterator = users.iterator();

		while (iterator.hasNext()) {
			Session next = iterator.next();
			// String userLobby =
			// next.getUserProperties().get("lobby").toString();

			// userLobby.equals(lobby)
			if (!session.equals(next) && next.isOpen()) {
				try {
					next.getBasicRemote().sendText(message);
				} catch (IOException e) {
					logger.log(Level.SEVERE, "Chat-Error", e);
					e.printStackTrace();
				}
				// }
			}

		}
	}
}
