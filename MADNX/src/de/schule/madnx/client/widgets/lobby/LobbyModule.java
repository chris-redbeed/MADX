package de.schule.madnx.client.widgets.lobby;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LobbyModule extends Composite{
	
	private FlowPanel rootPanel;
	private Label lblTitle;
	
	private FlowPanel pnlContent;
	
	
	
	public LobbyModule() {
		
		init();
		initStyles();
		
		initPlayer(8);
	}

	private void initStyles() {
		// TODO Auto-generated method stub
		
	}
	
	public void initPlayer(int playerCount) {
		for (int i = 0; i < playerCount; i++) {
			pnlContent.add(new Label("Leerer Platz"));
		}
	}
	
	public void updatePlayer(ArrayList<Player> list) {
		pnlContent.clear();
		for (int i = 0; i < list.size(); i++) {
			Player player = list.get(i);
			if (i < player.getPosition()) {
				int differ = player.getPosition() - i;
				for (int y = 0; y < differ; y++) {
					pnlContent.add(new Label("Leerer Platz"));
				}
			}
			pnlContent.add(new PlayerModule(player.getName(),player.getSelf(), player.getStatus()));
		}
	}

	private void init() {
		rootPanel = new FlowPanel();
		lblTitle = new Label("Übersicht");
		pnlContent = new FlowPanel();
		
		rootPanel.add(lblTitle);
		rootPanel.add(pnlContent);
		
	}
	
	public void addStyle (String styleName) {
		rootPanel.addStyleName(styleName);
	}

	@Override
	public Widget asWidget() {
		return rootPanel;
	}
}
