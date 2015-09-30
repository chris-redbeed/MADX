/**
 * 
 */
package de.schule.madnx.client.presenter;

import java.util.ArrayList;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.ui.AbsolutePanel;

import de.schule.madnx.client.GameController;
import de.schule.madnx.client.PresenterMapper;
import de.schule.madnx.client.event.GetMessageEvent;
import de.schule.madnx.client.event.GetMessageHandler;
import de.schule.madnx.client.game.ClientMapGenerator;
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
	
	private ClientMapGenerator generator;
	
	public interface Display {
		AbsolutePanel getBoard();
	}

	public GamePresenter(AbstractView view, GameController gameController) {
		super(view, gameController);
	}

	/* (non-Javadoc)
	 * @see de.schule.madnx.client.presenter.AbstractPresenter#addHandler()
	 */
	@Override
	public void addHandler() {
		gameController.getEventBus().addHandler(GetMessageEvent.TYPE, new GameGetMessageHandler());
	}
	
	private void generate(int[][] map, ArrayList<int[][]> playerFields, ArrayList<int[][]> spawnFigures) {
		AbsolutePanel board = ((GameView)view).getBoard();
		generator = new ClientMapGenerator(board, playerFields, map, spawnFigures);
	}
	
	private class GameGetMessageHandler implements GetMessageHandler {
		
		@Override
		public void getMessage(GetMessageEvent event) {
			String data = event.getEvent().getData();

			JSONObject parse = (JSONObject) JSONParser.parse(data);
			String method = JSONHelper.valueToString(parse.get(Methods.METHOD).toString());

			if (method.equals(Methods.CHAT)) {
				
			} else if (method.equals(Methods.START_GAME)) {
				String map = JSONHelper.valueToString(parse.get("map").toString());
				String fields = JSONHelper.valueToString(parse.get("playerFields").toString());
				String spawns = JSONHelper.valueToString(parse.get("spawnFigure").toString());
				
				int[][] gameMap = GameMapCoder.decode(map);
				ArrayList<int[][]> playerFields = PlayerFieldCoder.decode(fields);
				ArrayList<int[][]> spawnFields = PlayerFieldCoder.decode(spawns);
				
				generate(gameMap, playerFields, spawnFields);
				
				gameController.getPresenterChanger().goTo(PresenterMapper.GAME);
			}  else if (method.equals(Methods.END_GAME)) {
				
			} else if (method.equals(Methods.VALID)) {
				
			} else if (method.equals(Methods.INVALID)) {
				
			}
		}
	}

}
