package de.schule.madx.client.websocket;

/**
 * A error callback for an {@Link WebSocket} object.
 */
public interface ErrorHandler {

  /**
   * This is called whenever the state of the WebSocket changes. See
   * {@link WebSocket#setOnErrorHandler}.
   *
   * @param webSocket the object which has been closed.
   */
  public void onError(WebSocket webSocket);
}
