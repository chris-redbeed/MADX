package de.schule.madnx.client.game;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DiceUI extends Composite{
	private Button btnDice;
	
	public DiceUI() {
		btnDice = new Button("würfeln");
	}
	
	public void setDiceText(String txt) {
		btnDice.setText(txt);
	}
	
	@Override
	public Widget asWidget() {
		return btnDice;
	}
	
	public void setEnabled(boolean enabled) {
		btnDice.setEnabled(enabled);
	}
}
