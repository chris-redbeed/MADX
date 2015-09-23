package de.schule.madnx.server;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import de.schule.madnx.server.handler.LoginHandler;

@ServerEndpoint("/serverendpoint")
public class WebSocketEndpoint {
	
	private Logger logger = Logger.getLogger(WebSocketEndpoint.class.getName());
	
	private static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());
	
	private LoginHandler loginHandler = new LoginHandler();
	
	@OnOpen
	public void handleOpen(Session userSession) {
		logger.log(Level.INFO, "client connected...");
		users.add(userSession);
	}
	
	@OnClose
	public void handleClose(Session userSession) {
		logger.log(Level.INFO, "client disconnected...");
		users.remove(userSession);
	}
	
	@OnMessage
	public String handleMessage(String message, Session userSession) {
		String handleLoginMessage = loginHandler.handleMessage(message);
		if (!handleLoginMessage.equals("error")) {
			return handleLoginMessage;
		}
		return "error";
	}
	
	@OnError
	public void handleError(Throwable t) {
		logger.log(Level.SEVERE, "Endpoint-Error",t);
		t.printStackTrace();
	}

}
