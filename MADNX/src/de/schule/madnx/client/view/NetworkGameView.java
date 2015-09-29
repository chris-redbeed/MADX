package de.schule.madnx.client.view;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;

import de.schule.madnx.client.presenter.NetworkGamePresenter.Display;
import de.schule.madnx.shared.NetworkGame;

public class NetworkGameView   extends AbstractView implements Display{
	
	
	private Label lblTitle;
	private Grid table;
	private Button	btnClose;

	@Override
	void init(FlowPanel rootPanel) {
		lblTitle = new Label("Übersicht Netzwerkspiele");
		table = new Grid(21,3);
		btnClose = new Button("Zurück");
		table.setText(0, 0, "Host");
		table.setText(0, 1, "Status");
		table.setText(0, 2, "Offene Plätze");
		
		rootPanel.add(lblTitle);
		rootPanel.add(table);
		rootPanel.add(btnClose);
	}

	@Override
	void initStyles() {
		getRootPanel().addStyleName("network-Main");
		table.addStyleName("network-Table");
		lblTitle.addStyleName("menue-Title");
		
	}

	@Override
	public Grid getTable() {
		return table;
	}

	@Override
	public void setTable(ArrayList<NetworkGame> list) {
		for (int i = 0; i < table.getRowCount()-1; i ++) {
			if (list.size() > i) {
			NetworkGame game = list.get(i);
			table.setText(i+1, 0, game.getHost());
			table.setText(i+1, 1, String.valueOf(game.getId()));
			table.setText(i+1, 2, String.valueOf(game.getCurrentPlaces()));
			}
			else {
				table.setText(i+1, 0,"");
				table.setText(i+1, 1,"");
				table.setText(i+1, 2,"");
			}
		}
		
	}

	@Override
	public HasClickHandlers getBtnClose() {
		return btnClose;
	}

}
