/**
 * 
 */
package de.schule.madx.client;

/**
 * @author julianschulte
 *
 */
public class WebSocketClient {
	public static native void sendMessage(String message) /*-{
	  var webSocket = new WebSocket("ws://localhost:8080/MADX/serverendpoint");
	  webSocket.send(message);
	  webSocket.close
	}-*/;
}
