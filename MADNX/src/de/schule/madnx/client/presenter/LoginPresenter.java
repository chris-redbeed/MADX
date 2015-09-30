/**
 * 
 */
package de.schule.madnx.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;

import de.schule.madnx.client.GameController;
import de.schule.madnx.client.PresenterMapper;
import de.schule.madnx.client.event.GetMessageEvent;
import de.schule.madnx.client.event.GetMessageHandler;
import de.schule.madnx.client.view.AbstractView;
import de.schule.madnx.client.view.LoginView;
import de.schule.madnx.client.websocket.WebSocket;
import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;

/**
 * @author xgadscj
 *
 */
public class LoginPresenter extends AbstractPresenter {
	
	private static final String WebSocketURL = "ws://127.0.0.1:8080/MADNX/serverendpoint";

	public interface Display {

		HasText getTxtUser();

		HasText getPwTxtPassword();

		HasClickHandlers getBtnLogin();

		HasClickHandlers getBtnRegister();

	}

	public LoginPresenter(AbstractView view, GameController gameController) {
		super(view, gameController);
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
				String method = JSONHelper.valueToString(parse.get(Methods.METHOD).toString());
				String result = JSONHelper.valueToString(parse.get("result").toString());
				if (method.equals(Methods.LOGIN)) {
					if (result.toString().equals("okay")) {
						gameController.getPresenterChanger().goTo(PresenterMapper.MENUE);
					} else {
						Window.alert("Die Nutzerdaten sind ungültig! Bitte probieren Sie es erneut!");
					}
				} else if (method.equals(Methods.REGISTER)) {
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
			object.put("method", new JSONString(Methods.LOGIN));
			object.put("user", new JSONString(user));
			object.put("password", new JSONString(password));

			gameController.getWebSocket().send(object.toString());
			clearTxtBox();
		}
	}

	private class RegisterClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String user = ((LoginView) view).getTxtUser().getText();
			String password = ((LoginView) view).getPwTxtPassword().getText();
			
			JSONObject object = new JSONObject();
			object.put("method", new JSONString(Methods.REGISTER));
			object.put("user", new JSONString(user));
			object.put("password", new JSONString(password));


			gameController.getWebSocket().send(object.toString());
			clearTxtBox();
		}
	}
	
	private void clearTxtBox() {
		((LoginView) view).getPwTxtPassword().setText("");
		((LoginView) view).getTxtUser().setText("");
	}
	
	@Override
	public void go() {
		super.go();
		if (gameController.getWebSocket() == null || gameController.getWebSocket().getReadyState() != WebSocket.OPEN) {
			WebSocket socket = WebSocket.create(WebSocketURL);
			gameController.setWebSocket(socket);
			}
	}
}
