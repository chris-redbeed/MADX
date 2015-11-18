/**
 * 
 */
package de.schule.madnx.client.presenter;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.AbsolutePanel;

import de.schule.madnx.client.GameController;
import de.schule.madnx.client.PresenterMapper;
import de.schule.madnx.client.event.GetMessageEvent;
import de.schule.madnx.client.event.GetMessageHandler;
import de.schule.madnx.client.game.MapGenerator;
import de.schule.madnx.client.game.PlayerUI;
import de.schule.madnx.client.view.AbstractView;
import de.schule.madnx.client.view.GameView;
import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;
import de.schule.madnx.shared.coder.GameMapCoder;
import de.schule.madnx.shared.coder.PlayerFieldCoder;

/**
 * @author xgadscj
 *
 */
public class GamePresenter extends AbstractPresenter {

	private MapGenerator generator;

	public interface Display {
		AbsolutePanel getBoard();
	}

	public GamePresenter(AbstractView view, GameController gameController) {
		super(view, gameController);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.schule.madnx.client.presenter.AbstractPresenter#addHandler()
	 */
	@Override
	public void addHandler() {
		gameController.getEventBus().addHandler(GetMessageEvent.TYPE, new GameGetMessageHandler());
	}

	private void generate(int[][] map, ArrayList<int[][]> playerFields, ArrayList<int[][]> spawnFigures) {
		AbsolutePanel board = ((GameView) view).getBoard();
		generator = new MapGenerator(board, playerFields, map, spawnFigures);
	}

	private class GameGetMessageHandler implements GetMessageHandler {

		@Override
		public void getMessage(GetMessageEvent event) {
			String data = event.getEvent().getData();

			JSONObject parse = (JSONObject) JSONParser.parse(data);
			String method = JSONHelper.valueToString(parse.get(Methods.METHOD).toString());

			switch (method) {
			case Methods.START_GAME:
				String map = JSONHelper.valueToString(parse.get("map").toString());
				String fields = JSONHelper.valueToString(parse.get("playerFields").toString());
				String spawns = JSONHelper.valueToString(parse.get("spawnFigure").toString());

				int[][] gameMap = GameMapCoder.decode(map);
				ArrayList<int[][]> playerFields = PlayerFieldCoder.decode(fields);
				ArrayList<int[][]> spawnFields = PlayerFieldCoder.decode(spawns);

				generate(gameMap, playerFields, spawnFields);

				for (PlayerUI p : generator.getPlayerUIs()) {
					p.addClickHandler(new PlayerUIClickHandler(p));
					p.setEnabled(false);
				}

				generator.getDiceUI().addClickHandler(new DiceUIClickHandler());

				gameController.getPresenterChanger().goTo(PresenterMapper.GAME);
				generator.getDiceUI().setEnabled(true);
				break;

			case Methods.END_GAME:
				break;

			case Methods.DICE:
				int dice = Integer.valueOf(JSONHelper.valueToString(parse.get("result").toString()));
				generator.getDiceUI().setContent(dice);

				boolean isPreGame = Boolean.parseBoolean(parse.get("pregame").toString());
				
				if (!isPreGame) {
				for (PlayerUI p : generator.getPlayerUIs()) {
					p.setEnabled(true);
				}
				}
				else {
					generator.getDiceUI().setEnabled(true);
				}

				break;

			case Methods.SET:
				String result = JSONHelper.valueToString(parse.get("result").toString());
				int id = Integer.valueOf(JSONHelper.valueToString(parse.get("id").toString()));

				int[][] figureCoord = GameMapCoder.decode(result);

				generator.moveFigureWithID(id, figureCoord[0][0], figureCoord[0][1]);
				for (PlayerUI p : generator.getPlayerUIs()) {
					p.setEnabled(false);
				}
				generator.getDiceUI().setEnabled(true);
				break;

			case Methods.END_SET:
				generator.getDiceUI().setEnabled(true);
				break;
			}
		}
	}

	private class PlayerUIClickHandler implements ClickHandler {

		private PlayerUI playerUI;

		public PlayerUIClickHandler(PlayerUI p) {
			playerUI = p;
		}

		@Override
		public void onClick(ClickEvent arg0) {
			if (playerUI.getEnabled()) {
				int id = playerUI.getId();

				JSONObject object = new JSONObject();
				object.put(Methods.METHOD, new JSONString(Methods.SET));
				object.put("id", new JSONString("" + id));

				gameController.getWebSocket().send(object.toString());
			}
		}
	}

	private class DiceUIClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent arg0) {
			if (generator.getDiceUI().getEnabled()) {
				generator.getDiceUI().setEnabled(false);
				JSONObject object = new JSONObject();
				object.put(Methods.METHOD, new JSONString(Methods.DICE));

				gameController.getWebSocket().send(object.toString());
			}
		}
	}
}
