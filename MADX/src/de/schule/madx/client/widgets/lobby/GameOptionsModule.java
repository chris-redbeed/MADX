/**
 * 
 */
package de.schule.madx.client.widgets.lobby;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author xgadscj
 *
 */
public class GameOptionsModule extends Composite {

	private FlowPanel rootPanel;
	private FlowPanel pnlContent;
	private Label lblTitle;
	private Button btnSave;

	public GameOptionsModule() {
		init();
		initStyles();
	}

	private void init() {
		rootPanel = new FlowPanel();
		pnlContent = new FlowPanel();
		lblTitle = new Label("Einstellungen");
		btnSave = new Button("Speichern");

		rootPanel.add(lblTitle);
		rootPanel.add(pnlContent);
		rootPanel.add(btnSave);

		initOptions();
	}

	private void initOptions() {
		pnlContent.add(new Label("Es sind keine Optionen verfügbar."));
		btnSave.setVisible(false);
	}

	private void initStyles() {

	}
	
	public void addStyle (String styleName) {
		rootPanel.addStyleName(styleName);
	}

	public void updateOptions(ArrayList<String> list) {
		pnlContent.clear();
		if (list.isEmpty()) {
			pnlContent.add(new Label("Es sind keine Optionen verfügbar."));
			btnSave.setVisible(false);
		} else {
			for (String s : list) {
				OptionsModule w = new OptionsModule(s,false);
				pnlContent.add(w.asWidget());
			}
			btnSave.setVisible(true);
		}
	}

	@Override
	public Widget asWidget() {
		return rootPanel;
	}

}
