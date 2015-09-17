/**
 * 
 */
package de.schule.madx.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;

import de.schule.madx.client.GameController;
import de.schule.madx.client.PresenterMapper;
import de.schule.madx.client.model.AbstractModel;
import de.schule.madx.client.view.AbstractView;
import de.schule.madx.client.view.MultiPlayerView;

/**
 * @author xgadscj
 *
 */
public class MultiPlayerPresenter extends AbstractPresenter{

	public interface Display {
		HasClickHandlers getBtnConnect();
		
		HasClickHandlers getBtnCreate();
	}
	
	public MultiPlayerPresenter(AbstractModel model, AbstractView view, GameController gameController) {
		super(model, view, gameController);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addHandler() {
		((MultiPlayerView) view).getBtnConnect().addClickHandler(new ConnectClickHandler());
((MultiPlayerView) view).getBtnCreate().addClickHandler(new CreateClickHandler());
		
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
			gameController.getPresenterChanger().goTo(PresenterMapper.LOBBY);
			
		}
	}
	
	

}
