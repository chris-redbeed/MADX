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
import de.schule.madnx.client.PresenterMapper;
import de.schule.madnx.client.event.GetMessageEvent;
import de.schule.madnx.client.event.GetMessageHandler;
import de.schule.madnx.client.model.AbstractModel;
import de.schule.madnx.client.view.AbstractView;
import de.schule.madnx.client.view.NetworkGameView;
import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;
import de.schule.madnx.shared.gamelist.Game;
import de.schule.madnx.shared.gamelist.GameListCoder;

public class NetworkGamePresenter extends AbstractPresenter {

	public interface Display {
		Grid getTable();

		void setTable(ArrayList<?> list);

		HasClickHandlers getBtnClose();
	}

	public NetworkGamePresenter(AbstractModel model, AbstractView view, GameController gameController) {
		super(model, view, gameController);
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
			
			gameController.getPresenterChanger().goTo(PresenterMapper.LOBBY);
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
				Grid table = ((NetworkGameView) view).getTable();
				for (int i = 0; i < table.getRowCount()-1; i ++) {
					if (decode.size() > i) {
					Game game = decode.get(i);
					table.setText(i+1, 0, game.getHost());
					table.setText(i+1, 1, String.valueOf(game.getId()));
					table.setText(i+1, 2, String.valueOf(game.getCurrentPlaces()));
					}
					else {
						table.setText(i+1, 0,"");
						table.setText(i+1, 1,"");
						table.setText(i+1, 2,"");
					}
				}
			}
		}
	}

}
