/**
 * 
 */
package de.schule.madx.client;

/**
 * @author julianschulte
 *
 */
public class WebSocketClient {
	
	public WebSocketClient(String server, String port) {
		createWebSocket(server, port);
	}
	
	public final native String sendMessage(String message) /*-{
	  var webSocket = new WebSocket("ws://localhost:8080/MADX/serverendpoint");
	  webSocket.send(message);
	  var webMessage = webSocket.onmessage = function(message) { processMessage(message);};
	  function processMessage(message) {
	  	return message.data;
	  };
	}-*/;
	
	public final native void createWebSocket(String server, String port) /*-{
		var webSocket = new WebSocket("ws://" + server +":" + port +"/MADX/serverendpoint");
		webSocket.onopen = function(message) {
			Console.log("Server Connect...");
		};
		
		webSocket.onclose = function(message) {
			Console.log("Server Disconnect...");
		};
		
		webSocket.onerror = function(message) {
			Console.log("Error: " + message);
		};
		
		webSocket.OPEN;
	}-*/;
}
