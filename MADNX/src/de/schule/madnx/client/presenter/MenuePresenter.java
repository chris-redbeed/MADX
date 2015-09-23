package de.schule.madnx.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;

import de.schule.madnx.client.GameController;
import de.schule.madnx.client.PresenterMapper;
import de.schule.madnx.client.model.AbstractModel;
import de.schule.madnx.client.view.AbstractView;
import de.schule.madnx.client.view.MenueView;

public class MenuePresenter extends AbstractPresenter {

	public interface Display {
		HasClickHandlers getBtnSinglePlayer();

		HasClickHandlers getBtnMultiPlayer();

		HasClickHandlers getBtnHighScore();

		HasClickHandlers getBtnOptions();

		HasClickHandlers getBtnExit();
	}

	public MenuePresenter(AbstractModel model, AbstractView view, GameController gameController) {
		super(model, view, gameController);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addHandler() {
		((MenueView) view).getBtnSinglePlayer()
				.addClickHandler(new ChangeViewClickHandler(PresenterMapper.LOBBY));

		((MenueView) view).getBtnMultiPlayer().addClickHandler(new ChangeViewClickHandler(PresenterMapper.MULTIPLAYER));

		((MenueView) view).getBtnHighScore().addClickHandler(new ChangeViewClickHandler(PresenterMapper.HIGHSCORE));

		((MenueView) view).getBtnOptions().addClickHandler(new ChangeViewClickHandler(PresenterMapper.OPTIONS));

		((MenueView) view).getBtnExit().addClickHandler(new ChangeViewClickHandler(PresenterMapper.LOGIN));
	}

	private class ChangeViewClickHandler implements ClickHandler {

		private String view;

		public ChangeViewClickHandler(String view) {
			this.view = view;
		}

		@Override
		public void onClick(ClickEvent event) {
			gameController.getPresenterChanger().goTo(view);
		}
	}

}
