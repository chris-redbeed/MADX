/**
 * 
 */
package de.schule.madnx.server.handler.list;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.Session;

/**
 * @author xgadscj
 *
 */
public class SessionLobby {
	private ArrayList<Session> lobby;
	private String host;
	private int size;
	private String status;
	
	private Logger logger = Logger.getLogger(SessionLobby.class.getName());
	
	public SessionLobby() {
		lobby = new ArrayList<>();
	}
	
	public void addToLobby(Session session) {
		lobby.add(session);
	}
	
	public void removeFromLobby(Session session) {
		lobby.remove(session);
	}
	
	public void sendToAll(String message, Session session) {
		
		for (Session s: lobby) {
			if (!session.equals(s) && s.isOpen() ) {
				try {
					s.getBasicRemote().sendText(message);
				} catch (IOException e) {
					logger.log(Level.SEVERE, "Chat-Error", e);
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getHost() {
		return host;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getCurrentCount() {
		return 0;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getID() {
		return (int)lobby.get(0).getUserProperties().get("lobby");
	}
}
