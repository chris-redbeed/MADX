/**
 * 
 */
package de.schule.madnx.client.websocket;

/**
 * @author xgadscj
 *
 */

/**
 * A message callback for an {@Link WebSocket} object.
 */
public interface MessageHandler {

  /**
   * This is called whenever the state of the WebSocket changes. See
   * {@link WebSocket#setMessageHandler}.
   *
   * @param webSocket the object which has received a message.
   */
  public void onMessage(WebSocket webSocket, MessageEvent event);
}

