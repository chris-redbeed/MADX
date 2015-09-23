/**
 * 
 */
package de.schule.madnx.client.websocket;

/**
 * @author julianschulte
 *
 */
import com.google.gwt.core.client.JavaScriptObject;

public class WebSocket extends JavaScriptObject {
       
        public static final int CONNECTING = 0;
        public static final int OPEN = 1;
        public static final int CLOSED = 2;
       
        /**
         * Creates an WebSocketClient object.
         *
         * @return the created object
         */
        public static native WebSocket create(String url) /*-{
                return new WebSocket(url);
        }-*/;
       
        public static native WebSocket create(String url, String protocol) /*-{
                return new WebSocket(url, protocol);
        }-*/;

        protected WebSocket() {
        }
       
        public final native int getReadyState() /*-{
                return this.readyState;
        }-*/;
       
        public final native int getBufferedAmount() /*-{
                return this.bufferedAmount;
        }-*/;

        public final native void send(String data) /*-{
                this.send(data);
        }-*/;
       
        public final native void close() /*-{
                this.close();
        }-*/;
       
        public final native void setOnOpen(OpenHandler handler) /*-{
                // The 'this' context is always supposed to point to the websocket object in the
                // onreadystatechange handler, but we reference it via closure to be extra sure.
                var _this = this;
                this.onopen = $entry(function() {
                        handler.@de.schule.madnx.client.websocket.OpenHandler::onOpen(Lde/schule/madnx/client/websocket/WebSocket;)(_this);
                });
        }-*/;
       
        public final native void setOnClose(CloseHandler handler) /*-{
                // The 'this' context is always supposed to point to the websocket object in the
                // onreadystatechange handler, but we reference it via closure to be extra sure.
                var _this = this;
                this.onclose = $entry(function() {
                        handler.@de.schule.madnx.client.websocket.CloseHandler::onClose(Lde/schule/madnx/client/websocket/WebSocket;)(_this);
                });
        }-*/;

        public final native void setOnError(ErrorHandler handler) /*-{
                // The 'this' context is always supposed to point to the websocket object in the
                // onreadystatechange handler, but we reference it via closure to be extra sure.
                var _this = this;
                this.onerror = $entry(function() {
                        handler.@de.schule.madnx.client.websocket.ErrorHandler::onError(Lde/schule/madnx/client/websocket/WebSocket;)(_this);
                });
        }-*/;
       
        public final native void setOnMessage(MessageHandler handler) /*-{
                // The 'this' context is always supposed to point to the websocket object in the
                // onreadystatechange handler, but we reference it via closure to be extra sure.
                var _this = this;
                this.onmessage = $entry(function(event) {
                        handler.@de.schule.madnx.client.websocket.MessageHandler::onMessage(Lde/schule/madnx/client/websocket/WebSocket;Lde/schule/madnx/client/websocket/MessageEvent;)(_this, event);
                });
        }-*/;
}

