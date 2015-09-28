package de.schule.madnx.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import de.schule.madnx.server.handler.ChatHandler;
import de.schule.madnx.server.handler.HighScoreHandler;
import de.schule.madnx.server.handler.LobbyHandler;
import de.schule.madnx.server.handler.LoginHandler;
import de.schule.madnx.server.handler.list.SessionLobby;

@ServerEndpoint("/serverendpoint")
public class WebSocketEndpoint {
	
	private Logger logger = Logger.getLogger(WebSocketEndpoint.class.getName());
	
	static Map<Integer,SessionLobby> lobbies = Collections.synchronizedMap(new HashMap<Integer,SessionLobby>());
	
	private LoginHandler loginHandler = new LoginHandler();
	private ChatHandler chatHandler = new ChatHandler();
	private LobbyHandler lobbyHandler = new LobbyHandler();
	private HighScoreHandler highScoreHandler = new HighScoreHandler();
	
	@OnOpen
	public void handleOpen(Session userSession) {
		logger.log(Level.INFO, "client connected...");
	}
	
	@OnClose
	public void handleClose(Session userSession) {
		logger.log(Level.INFO, "client disconnected...");
	}
	
	@OnMessage
	public String handleMessage(String message, Session userSession) {
		String handleLoginMessage = loginHandler.handleMessage(message, userSession);
		String handleChatMessage = chatHandler.handleMessage(message, userSession, lobbies);
		String lobbyChatMessage = lobbyHandler.handleMessage(message, userSession, lobbies);
		String highScoreString = highScoreHandler.handleMessage(message, userSession);
		if (!handleLoginMessage.equals("error")) {
			return handleLoginMessage;
		}
		if (!handleChatMessage.equals("error")) {
			return handleChatMessage;
		}
		if (!lobbyChatMessage.equals("error")) {
			return lobbyChatMessage;
		}
		if (!highScoreString.equals("error")) {
			return highScoreString;
		}
		
		return "error";
	}
	
	@OnError
	public void handleError(Throwable t) {
		logger.log(Level.SEVERE, "Endpoint-Error",t);
		t.printStackTrace();
	}

}
