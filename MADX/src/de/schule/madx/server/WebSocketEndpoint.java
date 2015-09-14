package de.schule.madx.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/serverendpoint")
public class WebSocketEndpoint {
	
	Logger logger = Logger.getLogger(WebSocketEndpoint.class.getName());
	
	@OnOpen
	public void handleOpen(Session session) {
		logger.log(Level.INFO, "client connected...");
	}
	
	@OnClose
	public void handleClose(Session session) {
		logger.log(Level.INFO, "client disconnected...");
	}
	
	@OnMessage
	public String handleMessage(String message, Session session) {
		logger.log(Level.INFO, "client send:" + message);
		return "echo" + message;
	}
	
	@OnError
	public void handleError(Throwable t) {
		logger.log(Level.SEVERE, "Endpoint-Error",t);
		t.printStackTrace();
	}

}
