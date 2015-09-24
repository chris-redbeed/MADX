/**
 * 
 */
package de.schule.madnx.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

import de.schule.madnx.client.GameController;
import de.schule.madnx.client.PresenterMapper;
import de.schule.madnx.client.model.AbstractModel;
import de.schule.madnx.client.view.AbstractView;
import de.schule.madnx.client.view.MultiPlayerView;
import de.schule.madnx.shared.Methods;

/**
 * @author xgadscj
 *
 */
public class MultiPlayerPresenter extends AbstractPresenter {

	public interface Display {
		HasClickHandlers getBtnConnect();

		HasClickHandlers getBtnCreate();

		HasClickHandlers getBtnClose();
	}

	public MultiPlayerPresenter(AbstractModel model, AbstractView view, GameController gameController) {
		super(model, view, gameController);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addHandler() {
		((MultiPlayerView) view).getBtnConnect().addClickHandler(new ConnectClickHandler());
		((MultiPlayerView) view).getBtnCreate().addClickHandler(new CreateClickHandler());
		((MultiPlayerView) view).getBtnClose().addClickHandler(new CloseClickHandler());

	}

	public class ConnectClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			gameController.getPresenterChanger().goTo(PresenterMapper.NETWORKGAME);
		}
	}

	public class CreateClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			JSONObject object = new JSONObject();
			object.put(Methods.METHOD, new JSONString(Methods.CREATE_GAME));
			
			gameController.getWebSocket().send(object.toString());
			gameController.getPresenterChanger().goTo(PresenterMapper.LOBBY);

		}
	}

}
