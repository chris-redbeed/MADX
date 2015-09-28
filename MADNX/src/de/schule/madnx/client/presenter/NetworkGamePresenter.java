package de.schule.madnx.client.presenter;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

import de.schule.madnx.client.GameController;
import de.schule.madnx.client.event.GetMessageEvent;
import de.schule.madnx.client.event.GetMessageHandler;
import de.schule.madnx.client.view.AbstractView;
import de.schule.madnx.client.view.NetworkGameView;
import de.schule.madnx.shared.Game;
import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;
import de.schule.madnx.shared.coder.GameListCoder;

public class NetworkGamePresenter extends AbstractPresenter {

	public interface Display {
		Grid getTable();

		void setTable(ArrayList<Game> list);

		HasClickHandlers getBtnClose();
	}

	public NetworkGamePresenter(AbstractView view, GameController gameController) {
		super(view, gameController);
	}

	@Override
	public void addHandler() {
		Grid table = ((NetworkGameView) view).getTable();
		table.addClickHandler(new TableClickHandler(table));
		((NetworkGameView) view).getBtnClose().addClickHandler(new CloseClickHandler());
		gameController.getEventBus().addHandler(GetMessageEvent.TYPE, new NetworkGameGetMessageHandler());
	}
	
	@Override
	public void go() {
		super.go();
		JSONObject object = new JSONObject();
		object.put(Methods.METHOD, new JSONString(Methods.LIST_GAMES));
		gameController.getWebSocket().send(object.toString());
	}

	private class TableClickHandler implements ClickHandler {

		private Grid table;

		public TableClickHandler(Grid table) {
			this.table = table;
		}

		@Override
		public void onClick(ClickEvent event) {
			Cell cellForEvent = table.getCellForEvent(event);
			int rowIndex = cellForEvent.getRowIndex();
			
			// Server-Call um ins Spiel zu kommen
			JSONObject object = new JSONObject();
			object.put(Methods.METHOD, new JSONString(Methods.JOIN_GAME));
			String gameID = table.getText(rowIndex, 1);
			if (gameID != null && !gameID.equals("")) {
			object.put("game", new JSONString(gameID));
			gameController.getWebSocket().send(object.toString());
			}
		}
	}

	private class NetworkGameGetMessageHandler implements GetMessageHandler {

		@Override
		public void getMessage(GetMessageEvent event) {
			String data = event.getEvent().getData();

			JSONObject parse = (JSONObject) JSONParser.parse(data);
			String method = JSONHelper.valueToString(parse.get(Methods.METHOD).toString());
			if (method.equals(Methods.LIST_GAMES)) {
			String result = JSONHelper.valueToString(parse.get("result").toString());
				ArrayList<Game> decode = GameListCoder.decode(result);
				((NetworkGameView) view).setTable(decode);
			}
		}
	}

}
