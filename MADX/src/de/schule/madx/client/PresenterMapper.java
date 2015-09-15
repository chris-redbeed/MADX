/**
 * 
 */
package de.schule.madx.client;

import java.util.HashMap;
import java.util.Map;

import de.schule.madx.client.model.HighScoreModel;
import de.schule.madx.client.model.MultiPlayerModel;
import de.schule.madx.client.model.OptionsModel;
import de.schule.madx.client.model.SinglePlayerModel;
import de.schule.madx.client.presenter.HighScorePresenter;
import de.schule.madx.client.presenter.LoginPresenter;
import de.schule.madx.client.presenter.MenuePresenter;
import de.schule.madx.client.presenter.MultiPlayerPresenter;
import de.schule.madx.client.presenter.OptionsPresenter;
import de.schule.madx.client.presenter.Presenter;
import de.schule.madx.client.presenter.SinglePlayerPresenter;
import de.schule.madx.client.view.HighScoreView;
import de.schule.madx.client.view.LoginView;
import de.schule.madx.client.view.MenueView;
import de.schule.madx.client.view.MultiPlayerView;
import de.schule.madx.client.view.OptionsView;
import de.schule.madx.client.view.SinglePlayerView;

/**
 * @author xgadscj
 *
 */
public class PresenterMapper {
	
	private Map<String, Presenter> presenterList;
	
	public static final String LOGIN = "Login";
	public static final String MENUE = "Menue";
	public static final String SINGLEPLAYER = "SinglePlayer";
	public static final String MULTIPLAYER = "MultiPlayer";
	public static final String HIGHSCORE = "HighScore";
	public static final String OPTIONS = "Options";
	
	public PresenterMapper(GameController gameController) {
		
		presenterList = new HashMap<>();
		LoginPresenter loginPresenter = new LoginPresenter(null, new LoginView(), gameController);
		MenuePresenter menuePresenter = new MenuePresenter(null, new MenueView(), gameController);
		SinglePlayerPresenter singlePlayerPresenter = new SinglePlayerPresenter(new SinglePlayerModel(), new SinglePlayerView(), gameController);
		MultiPlayerPresenter multiPlayerPresenter = new MultiPlayerPresenter(new MultiPlayerModel(), new MultiPlayerView(), gameController);
		HighScorePresenter highScorePresenter = new HighScorePresenter(new HighScoreModel(), new HighScoreView(), gameController);
		OptionsPresenter optionsPresenter = new OptionsPresenter(new OptionsModel(), new OptionsView(), gameController);
		
		presenterList.put(LOGIN, loginPresenter);
		presenterList.put(MENUE, menuePresenter);
		presenterList.put(SINGLEPLAYER, singlePlayerPresenter);
		presenterList.put(MULTIPLAYER, multiPlayerPresenter);
		presenterList.put(HIGHSCORE, highScorePresenter);
		presenterList.put(OPTIONS, optionsPresenter);
	}
	
	public void startPresenterForView(String view) {
		if (presenterList.containsKey(view)) {
		Presenter newPresenter = presenterList.get(view);
		newPresenter.go();
		}
	}
}
