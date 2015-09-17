package de.schule.madx.client.view;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;

import de.schule.madx.client.presenter.NetworkGamePresenter.Display;

public class NetworkGameView   extends AbstractView implements Display{
	
	
	private Label lblTitle;
	private Grid table;

	@Override
	void init(FlowPanel rootPanel) {
		lblTitle = new Label("Übersicht Netzwerkspiele");
		table = new Grid(21,3);
		table.setText(0, 0, "Spielname");
		table.setText(0, 1, "Status");
		table.setText(0, 2, "Offene Plätze");
		
		rootPanel.add(lblTitle);
		rootPanel.add(table);
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
	public void setTable(ArrayList<?> list) {
		table = new Grid(20,3);
		table.setText(0, 0, "Spielname");
		table.setText(0, 1, "Status");
		table.setText(0, 2, "Offene Plätze");
		
		for (int i = 0; i < list.size(); i++) {
			if (i < 20) {
				Object x = list.get(i);
				
				// Für eine Zeile
				table.setText(i+1, 0, x.toString());
				table.setText(i+1, 1, x.toString());
				table.setText(i+1, 2, x.toString());
			}
			else {
				return;
			}
		}
		
	}

}
