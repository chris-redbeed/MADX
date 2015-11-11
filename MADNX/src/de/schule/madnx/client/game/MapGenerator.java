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

import de.schule.madnx.client.game.dice.DiceUI;

/**
 * @author xgadscj
 *
 */
public class MapGenerator {

	private AbsolutePanel panel;
	private int countPlayers;
	private int sizeMap;
	private ArrayList<int[][]> playerFields;
	private int[][] map;
	private ArrayList<PlayerUI> playerUIs;
	private static final int WINDOW_SIZE = 13;
	private DiceUI diceUI;

	public MapGenerator(AbsolutePanel panel, ArrayList<int[][]> playerFields, int[][] map,
			ArrayList<int[][]> spawnFigures) {
		this.panel = panel;
		this.playerFields = playerFields;
		this.map = map;
		this.playerUIs = new ArrayList<>();
		this.diceUI = new DiceUI();
		saveSpawnFigures(spawnFigures);
		sizeMap = map.length;
		countPlayers = playerFields.get(0).length;
		generateMap();
		generatePlayer();
		generateFigures();
		generateDice();

		Window.addResizeHandler(new WindowResizeHandler());
	}

	private void saveSpawnFigures(ArrayList<int[][]> spawnFigures) {
		int count = 1;
		for (int i = 0; i < spawnFigures.size(); i++) {
			int[][] spawnFigureForPlayer = spawnFigures.get(i);
			for (int j = 0; j < spawnFigureForPlayer.length; j++) {
				Integer y = Integer.valueOf(spawnFigureForPlayer[j][0]);
				Integer x = Integer.valueOf(spawnFigureForPlayer[j][1]);
				playerUIs.add(new PlayerUI(getPlayerStyle(i), x, y, count));
				count++;
			}
		}
	}

	private void generateFigures() {
		int offsetHeight = RootPanel.get().getOffsetHeight();
		int offsetWidth = RootPanel.get().getOffsetWidth();
		for (PlayerUI p : playerUIs) {
			int y = p.getY();
			int x = p.getX();
			panel.add(p.asWidget(), offsetWidth / WINDOW_SIZE * x, offsetHeight / WINDOW_SIZE * y);
		}
	}

	private void generatePlayer() {
		int offsetHeight = RootPanel.get().getOffsetHeight();
		int offsetWidth = RootPanel.get().getOffsetWidth();
		FlowPanel feld;
		for (int j = 0; j < playerFields.size(); j++) {
			int[][] player = playerFields.get(j);
			for (int i = 0; i < countPlayers; i++) {
				feld = new FlowPanel();
				feld.setStyleName("field");
				feld.addStyleName(getPlayerStyle(j));
				int y = player[i][0];
				int x = player[i][1];
				panel.add(feld, offsetWidth / WINDOW_SIZE * x, offsetHeight / WINDOW_SIZE * y);
			}
		}
	}

	private String getPlayerStyle(int i) {
		switch (i + 1) {
		case 1:
			return "red";
		case 2:
			return "blue";
		case 3:
			return "green";
		case 4:
			return "yellow";
		default:
			return "";
		}
	}
	
	private void generatePlayerNames() {
		
	}

	private void generateMap() {
		int offsetHeight = RootPanel.get().getOffsetHeight();
		int offsetWidth = RootPanel.get().getOffsetWidth();
		FlowPanel feld;
		for (int i = 0; i < sizeMap; i++) {
			feld = new FlowPanel();
			feld.setStyleName("field");
			feld.addStyleName("white");
			int y = map[i][0];
			int x = map[i][1];
			panel.add(feld, offsetWidth / WINDOW_SIZE * x, offsetHeight / WINDOW_SIZE * y);
		}
	}
	
	private void generateDice() {
		panel.add(diceUI.asWidget());
	}

	public DiceUI getDiceUI() {
		return diceUI;
	}

	private class WindowResizeHandler implements ResizeHandler {

		@Override
		public void onResize(ResizeEvent event) {
			panel.clear();
			generateMap();
			generatePlayer();
			generateFigures();
			generateDice();
		}
	}
	
	public ArrayList<PlayerUI> getPlayerUIs() {
		return playerUIs;
	}
	
	public void moveFigureWithID(int id, int x, int y) {
		int offsetHeight = RootPanel.get().getOffsetHeight();
		int offsetWidth = RootPanel.get().getOffsetWidth();
		for (PlayerUI p : playerUIs) {
			if (p.getId() == id) {
				p.addStyleName("transition");
				panel.remove(p.asWidget());
				p.setX(x);
				p.setY(y);
				panel.add(p.asWidget(), offsetWidth / WINDOW_SIZE * x, offsetHeight / WINDOW_SIZE * y);
			}
		}
	}
}
