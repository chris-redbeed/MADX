package de.schule.madnx.client.websocket;

/**
 * A close callback for an {@Link WebSocket} object.
 */
public interface CloseHandler {

  /**
   * This is called whenever the state of the WebSocket changes. See
   * {@link WebSocket#setOnCloseHandler}.
   *
   * @param webSocket the object which has been closed.
   */
  public void onClose(WebSocket webSocket);
}

