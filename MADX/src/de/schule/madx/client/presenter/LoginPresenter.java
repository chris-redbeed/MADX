/**
 * 
 */
package de.schule.madx.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;

import de.schule.madx.client.GameController;
import de.schule.madx.client.PresenterMapper;
import de.schule.madx.client.event.GetMessageEvent;
import de.schule.madx.client.event.GetMessageHandler;
import de.schule.madx.client.model.AbstractModel;
import de.schule.madx.client.view.AbstractView;
import de.schule.madx.client.view.LoginView;
import de.schule.madx.client.websocket.WebSocket;
import de.schule.madx.shared.JSONHelper;

/**
 * @author xgadscj
 *
 */
public class LoginPresenter extends AbstractPresenter {
	
	private static final String WebSocketURL = "ws://msgscjm1.gadeg.de:8080/MADX/serverendpoint";

	public interface Display {

		HasText getTxtUser();

		HasText getPwTxtPassword();

		HasText getTxtServer();

		HasText getTxtPort();

		HasText getTxtUrl();

		HasClickHandlers getBtnLogin();

		HasClickHandlers getBtnRegister();

	}

	public LoginPresenter(AbstractModel model, AbstractView view, GameController gameController) {
		super(model, view, gameController);
	}

	@Override
	public void addHandler() {
		((LoginView) view).getBtnLogin().addClickHandler(new LoginClickHandler());

		((LoginView) view).getBtnRegister().addClickHandler(new RegisterClickHandler());
		
		gameController.getEventBus().addHandler(GetMessageEvent.TYPE, new GetMessageHandler() {

			@Override
			public void getMessage(GetMessageEvent event) {
				String data = event.getEvent().getData();

				JSONObject parse = (JSONObject) JSONParser.parse(data);
				String method = JSONHelper.valueToString(parse.get("method").toString());
				String result = JSONHelper.valueToString(parse.get("result").toString());
				if (method.equals("login")) {
					if (result.toString().equals("okay")) {
						gameController.getPresenterChanger().goTo(PresenterMapper.MENUE);
					} else {
						Window.alert("Die Nutzerdaten sind ungültig! Bitte probieren Sie es erneut!");
					}
				} else if (method.equals("register")) {
					if (result.toString().equals("okay")) {
						gameController.getPresenterChanger().goTo(PresenterMapper.MENUE);
					} else {
						Window.alert("Der Nutzername ist schon vorhanden! Bitte wählen sie einen anderen aus!");
					}
				}

			}
		});
	}

	private class LoginClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String user = ((LoginView) view).getTxtUser().getText();
			String password = ((LoginView) view).getPwTxtPassword().getText();
			
			JSONObject object = new JSONObject();
			object.put("method", new JSONString("login"));
			object.put("user", new JSONString(user));
			object.put("password", new JSONString(password));

			gameController.getWebSocket().send(object.toString());
		}
	}

	private class RegisterClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String user = ((LoginView) view).getTxtUser().getText();
			String password = ((LoginView) view).getPwTxtPassword().getText();
			
			JSONObject object = new JSONObject();
			object.put("method", new JSONString("register"));
			object.put("user", new JSONString(user));
			object.put("password", new JSONString(password));


			gameController.getWebSocket().send(object.toString());
		}
	}
	@Override
	public void go() {
		super.go();
		if (gameController.getWebSocket() == null || gameController.getWebSocket().getReadyState() == WebSocket.OPEN) {
			WebSocket socket = WebSocket.create(WebSocketURL);
			gameController.setWebSocket(socket);
			}
	}
}
