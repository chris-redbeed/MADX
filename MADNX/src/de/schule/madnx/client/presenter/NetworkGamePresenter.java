package de.schule.madnx.client.presenter;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

import de.schule.madnx.client.GameController;
import de.schule.madnx.client.model.AbstractModel;
import de.schule.madnx.client.view.AbstractView;
import de.schule.madnx.client.view.NetworkGameView;

public class NetworkGamePresenter extends AbstractPresenter{
	
	public interface Display {
		Grid getTable();
		
		void setTable(ArrayList<?> list);
	}

	public NetworkGamePresenter(AbstractModel model, AbstractView view, GameController gameController) {
		super(model, view, gameController);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addHandler() {
		Grid table = ((NetworkGameView)view).getTable();
		table.addClickHandler(new TableClickHandler(table));
	}
	
	public class TableClickHandler implements ClickHandler {
		
		private Grid table;

		public TableClickHandler(Grid table) {
			this.table = table;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			Cell cellForEvent = table.getCellForEvent(event);
			int rowIndex = cellForEvent.getRowIndex();
			// Server-Call um ins Spiel zu kommen
			
			
		}
	}

}
