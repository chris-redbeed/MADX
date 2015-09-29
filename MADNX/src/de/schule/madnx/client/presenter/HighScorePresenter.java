/**
 * 
 */
package de.schule.madnx.client.presenter;

import java.util.Map;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.Grid;

import de.schule.madnx.client.GameController;
import de.schule.madnx.client.event.GetMessageEvent;
import de.schule.madnx.client.event.GetMessageHandler;
import de.schule.madnx.client.view.AbstractView;
import de.schule.madnx.client.view.HighScoreView;
import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;
import de.schule.madnx.shared.coder.HighScoreMapCoder;

/**
 * @author xgadscj
 *
 */
public class HighScorePresenter extends AbstractPresenter {

	public interface Display {
		Grid getTable();

		void setTable(Map<String, Integer> map);

		HasClickHandlers getBtnClose();
	}

	public HighScorePresenter(AbstractView view, GameController gameController) {
		super(view, gameController);
	}

	@Override
	public void addHandler() {
		((HighScoreView) view).getBtnClose().addClickHandler(new CloseClickHandler());
		gameController.getEventBus().addHandler(GetMessageEvent.TYPE, new HighScoreGetMessageHandler());
	}

	@Override
	public void go() {
		super.go();
		JSONObject object = new JSONObject();
		object.put(Methods.METHOD, new JSONString(Methods.LIST_HIGHSCORE));
		gameController.getWebSocket().send(object.toString());
	}

	private class HighScoreGetMessageHandler implements GetMessageHandler {

		@Override
		public void getMessage(GetMessageEvent event) {
			String data = event.getEvent().getData();

			JSONObject parse = (JSONObject) JSONParser.parse(data);
			String method = JSONHelper.valueToString(parse.get(Methods.METHOD).toString());
			if (method.equals(Methods.LIST_HIGHSCORE)) {
				String result = JSONHelper.valueToString(parse.get("result").toString());
				
				Map<String, Integer> highScoreMap = HighScoreMapCoder.decode(result);
				
				((HighScoreView) view).setTable(highScoreMap);
			}
		}
	}

}
