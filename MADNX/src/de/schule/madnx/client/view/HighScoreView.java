/**
 * 
 */
package de.schule.madnx.client.view;

import java.util.Map;
import java.util.Set;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;

import de.schule.madnx.client.presenter.HighScorePresenter.Display;

/**
 * @author xgadscj
 *
 */
public class HighScoreView extends AbstractView implements Display {

	private Label lblTitle;
	private Grid table;
	private Button btnClose;

	@Override
	void init(FlowPanel rootPanel) {
		lblTitle = new Label("Highscore");
		table = new Grid(21, 3);
		btnClose = new Button("Zurück");
		table.setText(0, 0, "Platz");
		table.setText(0, 1, "Name");
		table.setText(0, 2, "Gewonnene Spiele");

		rootPanel.add(lblTitle);
		rootPanel.add(table);
		rootPanel.add(btnClose);

		initTable();
	}

	@Override
	void initStyles() {
		getRootPanel().addStyleName("network-Main");
		table.addStyleName("network-Table");
		lblTitle.addStyleName("menue-Title");
	}

	private void initTable() {
		for (int i = 1; i < 21; i++) {
			String content = "" + i;
			table.setText(i, 0, content);
		}
	}

	@Override
	public Grid getTable() {
		return table;
	}

	@Override
	public void setTable(Map<String, Integer> map) {
		
		Set<String> keySet = map.keySet();
		int i = 1;
		for (String s : keySet) {
			if (i < table.getRowCount()) {
				table.setText(i, 1, s);
				Integer value = map.get(s);
				String score = "" + value;
				table.setText(i, 2, score);
				i++;
			} else {
				return;
			}

		}
	}

	@Override
	public HasClickHandlers getBtnClose() {
		return btnClose;
	}

}
