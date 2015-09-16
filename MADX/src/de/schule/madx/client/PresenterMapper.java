/**
 * 
 */
package de.schule.madx.client;

import java.util.HashMap;
import java.util.Map;

import de.schule.madx.client.model.HighScoreModel;
import de.schule.madx.client.model.LobbyModel;
import de.schule.madx.client.model.NetworkGameModel;
import de.schule.madx.client.model.OptionsModel;
import de.schule.madx.client.model.SinglePlayerModel;
import de.schule.madx.client.presenter.HighScorePresenter;
import de.schule.madx.client.presenter.LobbyPresenter;
import de.schule.madx.client.presenter.LoginPresenter;
import de.schule.madx.client.presenter.MenuePresenter;
import de.schule.madx.client.presenter.MultiPlayerPresenter;
import de.schule.madx.client.presenter.NetworkGamePresenter;
import de.schule.madx.client.presenter.OptionsPresenter;
import de.schule.madx.client.presenter.Presenter;
import de.schule.madx.client.presenter.SinglePlayerPresenter;
import de.schule.madx.client.view.HighScoreView;
import de.schule.madx.client.view.LobbyView;
import de.schule.madx.client.view.LoginView;
import de.schule.madx.client.view.MenueView;
import de.schule.madx.client.view.MultiPlayerView;
import de.schule.madx.client.view.NetworkGameView;
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
	public static final String MULTIPLAYERMENUE = "MultiPlayerMenue";
	public static final String LOBBY = "Lobby";
	public static final String NETWORKGAME = "NetworkGame";
	
	public PresenterMapper(GameController gameController) {
		
		presenterList = new HashMap<>();
		LoginPresenter loginPresenter = new LoginPresenter(null, new LoginView(), gameController);
		MenuePresenter menuePresenter = new MenuePresenter(null, new MenueView(), gameController);
		SinglePlayerPresenter singlePlayerPresenter = new SinglePlayerPresenter(new SinglePlayerModel(), new SinglePlayerView(), gameController);
		MultiPlayerPresenter multiPlayerPresenter = new MultiPlayerPresenter(null, new MultiPlayerView(), gameController);
		HighScorePresenter highScorePresenter = new HighScorePresenter(new HighScoreModel(), new HighScoreView(), gameController);
		OptionsPresenter optionsPresenter = new OptionsPresenter(new OptionsModel(), new OptionsView(), gameController);
		LobbyPresenter lobbyPresenter = new LobbyPresenter(new LobbyModel(), new LobbyView(), gameController);
		NetworkGamePresenter NetworkGamePresenter = new NetworkGamePresenter(new NetworkGameModel(), new NetworkGameView(), gameController);
		
		
		
		
		presenterList.put(LOGIN, loginPresenter);
		presenterList.put(MENUE, menuePresenter);
		presenterList.put(SINGLEPLAYER, singlePlayerPresenter);
		presenterList.put(MULTIPLAYER, multiPlayerPresenter);
		presenterList.put(HIGHSCORE, highScorePresenter);
		presenterList.put(OPTIONS, optionsPresenter);
		presenterList.put(LOBBY, lobbyPresenter);
		presenterList.put(NETWORKGAME, NetworkGamePresenter);	
	}
	
	public void startPresenterForView(String view) {
		if (presenterList.containsKey(view)) {
		Presenter newPresenter = presenterList.get(view);
		newPresenter.go();
		}
	}
}
