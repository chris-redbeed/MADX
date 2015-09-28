/**
 * 
 */
package de.schule.madnx.client;

import java.util.HashMap;
import java.util.Map;

import de.schule.madnx.client.presenter.HighScorePresenter;
import de.schule.madnx.client.presenter.LobbyPresenter;
import de.schule.madnx.client.presenter.LoginPresenter;
import de.schule.madnx.client.presenter.MenuePresenter;
import de.schule.madnx.client.presenter.MultiPlayerMenuePresenter;
import de.schule.madnx.client.presenter.NetworkGamePresenter;
import de.schule.madnx.client.presenter.OptionsPresenter;
import de.schule.madnx.client.presenter.Presenter;
import de.schule.madnx.client.presenter.SinglePlayerGamePresenter;
import de.schule.madnx.client.view.HighScoreView;
import de.schule.madnx.client.view.LobbyView;
import de.schule.madnx.client.view.LoginView;
import de.schule.madnx.client.view.MenueView;
import de.schule.madnx.client.view.MultiPlayerView;
import de.schule.madnx.client.view.NetworkGameView;
import de.schule.madnx.client.view.OptionsView;
import de.schule.madnx.client.view.SinglePlayerView;

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
		LoginPresenter loginPresenter = new LoginPresenter(new LoginView(), gameController);
		MenuePresenter menuePresenter = new MenuePresenter(new MenueView(), gameController);
		SinglePlayerGamePresenter singlePlayerPresenter = new SinglePlayerGamePresenter(new SinglePlayerView(), gameController);
		MultiPlayerMenuePresenter multiPlayerPresenter = new MultiPlayerMenuePresenter(new MultiPlayerView(), gameController);
		HighScorePresenter highScorePresenter = new HighScorePresenter(new HighScoreView(), gameController);
		OptionsPresenter optionsPresenter = new OptionsPresenter(new OptionsView(), gameController);
		LobbyPresenter lobbyPresenter = new LobbyPresenter(new LobbyView(), gameController);
		NetworkGamePresenter NetworkGamePresenter = new NetworkGamePresenter(new NetworkGameView(), gameController);
		
		
		
		
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
