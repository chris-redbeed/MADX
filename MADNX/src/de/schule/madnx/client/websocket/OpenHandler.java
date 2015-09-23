package de.schule.madnx.client.websocket;

/**
 * A open callback for an {@Link WebSocket} object.
 */
public interface OpenHandler {

  /**
   * This is called whenever the state of the WebSocket changes. See
   * {@link WebSocket#setOnOpen}.
   *
   * @param webSocket the object which has been opened.
   */
  public void onOpen(WebSocket webSocket);
}

