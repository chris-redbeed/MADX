package de.schule.madx.client.websocket;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Represents a native message event.
 */
public class MessageEvent extends JavaScriptObject {

        protected MessageEvent() {
        }
       
        public final native String getData() /*-{
                return this.data;
        }-*/;
}