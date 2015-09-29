/**
 * 
 */
package de.schule.madnx.client.game;

import java.util.ArrayList;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author xgadscj
 *
 */
public class ClientMapGenerator {

	private AbsolutePanel panel;
	private int countPlayers;
	private int sizeMap;
	private ArrayList<int[][]> players;
	private int[][] map;
	private static final int WINDOW_SIZE = 13;

	public ClientMapGenerator(AbsolutePanel panel, ArrayList<int[][]> players, int[][] map) {
		this.panel = panel;
		this.players = players;
		this.map = map;
		sizeMap = map.length;
		countPlayers = players.get(0).length;
		generateMap();
		generatePlayer();
		
		Window.addResizeHandler(new WindowResizeHandler());
	}

	public void generatePlayer() {
		int offsetHeight = RootPanel.get().getOffsetHeight();
		int offsetWidth = RootPanel.get().getOffsetWidth();
		FlowPanel feld;
		for (int j = 0; j < players.size(); j++) {
			int[][] player = players.get(j);
			for (int i = 0; i < countPlayers; i++) {
				feld = new FlowPanel();
				feld.setStyleName(getPlayerStyle(j));
				int y = player[i][0];
				int x = player[i][1];
				panel.add(feld, offsetWidth / WINDOW_SIZE * x, offsetHeight / WINDOW_SIZE * y);
			}
		}
	}

	private String getPlayerStyle(int i) {	
		switch (i+1) {
		case 1: 
			return "player-red";
		case 2:
			return "player-blue";
		case 3:
			return "player-yellow";
		case 4:
			return "player-green";
			default: return "";
		}
	}

	public void generateMap() {
		int offsetHeight = RootPanel.get().getOffsetHeight();
		int offsetWidth = RootPanel.get().getOffsetWidth();
		FlowPanel feld;
		for (int i = 0; i < sizeMap; i++) {
			feld = new FlowPanel();
			feld.setStyleName("spielfeld");
			int y = map[i][0];
			int x = map[i][1];
			panel.add(feld, offsetWidth / WINDOW_SIZE * x, offsetHeight / WINDOW_SIZE * y);
		}
	}

	private class WindowResizeHandler implements ResizeHandler {

		@Override
		public void onResize(ResizeEvent event) {
			panel.clear();
			generateMap();
			generatePlayer();
		}
	}
}
